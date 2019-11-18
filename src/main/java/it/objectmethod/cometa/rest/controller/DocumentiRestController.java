package it.objectmethod.cometa.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.cometa.dao.ArticoliDaoInterface;
import it.objectmethod.cometa.dao.DocumentiDaoInterface;
import it.objectmethod.cometa.dao.LottiDaoInterface;
import it.objectmethod.cometa.dao.ProfiloDocumentoInterface;
import it.objectmethod.cometa.dao.RigheDocumentoDaoInterface;
import it.objectmethod.cometa.model.Articolo;
import it.objectmethod.cometa.model.Documento;
import it.objectmethod.cometa.model.Lotto;
import it.objectmethod.cometa.model.ProfiloDocumento;
import it.objectmethod.cometa.model.RigaDocumento;

@RestController
@RequestMapping("/api/documenti")
public class DocumentiRestController {

	private static final String ERRORE_INSERIMENTO = "Errore Inserimento";

	@Autowired
	private DocumentiDaoInterface documentiDao;

	@Autowired
	private ArticoliDaoInterface articoliDao;

	@Autowired
	private LottiDaoInterface lottiDao;

	@Autowired
	private RigheDocumentoDaoInterface righeDao;

	@Autowired
	private ProfiloDocumentoInterface profiliDao;

	@PostMapping("/save")
	public String inserisci(@RequestBody Documento doc) throws ParseException {

		String outputMsg = "OK";
		int update = 1;
		int idProfilo = doc.getIdProfilo();
		ProfiloDocumento profile = profiliDao.getProfile(idProfilo);
		List<RigaDocumento> listRighe = doc.getRighe();
		boolean isValid = validateInsert(doc, profile);
		if (isValid) {
			boolean inserito = inserisciDocumento(doc, idProfilo);
			
			/*
			 * Come migliorare la logica dell'inserimento:
			 * 
			 * Abbiamo 2 tipi di controlli, uno in fase di inserimento documento e uno in fase di inserimento righe
			 * prima di guardare le righe inizierei a validare il documento (se necessario) e inserire il documento
			 * 
			 * Poi iniziamo a scorrere le righe, per ogni riga facciamo validazione della riga e inserimento della stessa
			 * Se incontro una riga che non supera la validazione abbiamo 2 possibilità:
			 * 
			 * 1) Se non abbiamo già aggiornato le quantità dei lotti di ogni riga, posso fare break dell'inserimento righe
			 *  e fare una delete sia delle righe già inserite che del documento stesso
			 * 2) Salto la riga in errore e mi segno un messaggio da ritornare come "warning", alla fine ritorno lista con tutti i messaggi warning.
			 * 3) Valido tutto pre-inserimento, ma per il validatore mi creo una classe apposita e non metto il metodo nel controller, strutturando la validazione con più metodi
			 * 	  procedo poi con l'inserimento/update solo dopo l'ok del validatore.
			 */
			
			// SPOSTARE TUTTA LA LOGICA IN INSERISCI DOCUMENTO
			for (RigaDocumento riga : listRighe) {
				String codiceLotto = riga.getCodiceLotto();
				int quantita = riga.getQuantita();

				// TODO utilizziamo ENUM per MovimentoMerce e costanti per i messaggi di errore
				// (tranne quando identificano più casi di un tipo di errore)
				if (profile.getMovimentoMerce().equals("+")) { // TODO getMovimentoMerce() potrebbe ritornare null --->
																// ("+").equals(profile.getMovimentoMerce()) cosi
																// risolviamo
					update = lottiDao.aggiungiQuantita(codiceLotto, quantita); // TODO errore, codice lotto non è
																				// sufficiente, usare idLotto o
																				// accoppiata codiceLotto + idArticolo
				}
				if (profile.getMovimentoMerce().equals("-")) {
					update = lottiDao.sottraiQuantita(codiceLotto, quantita); // TODO errore, codice lotto non è
																				// sufficiente, usare idLotto o
																				// accoppiata codiceLotto + idArticolo
				}
				if (update > 0) {
					if (!inserito) {
						outputMsg = ERRORE_INSERIMENTO;
						break;
					}
				} else {
					outputMsg = ERRORE_INSERIMENTO;
					break;
				}
			}
			
			//FINE DELLA LOGICA DA SPOSTARE
		} else {
			outputMsg = "ERRORE_INSERIMENTO";
		}

		return outputMsg;

	}

	public boolean inserisciDocumento(Documento doc, int idProfilo) {

		boolean value = false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(doc.getData());
		Articolo articolo = null;
		List<RigaDocumento> righe = doc.getRighe();
		int progressivo = documentiDao.getLastProgressivo(year, doc.getProfilo()) + 1;
		int inserisciDocumento = documentiDao.inserisciDocumento(idProfilo, doc.getData(), progressivo);
		int idDocumento = documentiDao.getIdDocumento(progressivo, idProfilo, doc.getData());
//		doc.setId(idDocumento); TODO non serve

		for (RigaDocumento riga : righe) {
			articolo = articoliDao.searchByCode(riga.getCodiceArticolo());
			int idArticolo = articolo.getId();
			int idLotto = lottiDao.getIdByCode(riga.getCodiceLotto(), articolo.getId());
			int inserisciRiga = righeDao.inserisciRiga(idDocumento, idArticolo, idLotto, riga.getQuantita());
			if (inserisciRiga > 0 && inserisciDocumento > 0) {
				value = true;
			} else {
				value = false;
				break;
			}
		}
		return value;
	}

	public boolean validateInsert(Documento doc, ProfiloDocumento profilo) {

		Lotto lotto = null;
		Articolo articolo = null;
		boolean isValid = false;
		String movimentoMerce = profilo.getMovimentoMerce();
		List<RigaDocumento> righe = doc.getRighe();

		for (RigaDocumento riga : righe) {
			lotto = lottiDao.codiceLottoInArticolo(riga.getCodiceLotto(), riga.getCodiceArticolo());
			articolo = articoliDao.searchByCode(riga.getCodiceArticolo());

			if (articolo != null) {
				if (lotto != null) { // TODO && !movimentoMerce.equals("+") il lotto può essere null in caso di
										// carico, in quel caso va creato il nuovo lotto
					if (movimentoMerce.equals("-")) { //"-".equals(movimentoMerce);
						if (lotto.getQuantita() >= riga.getQuantita()) {
							isValid = true;
						}
					} else {
						isValid = true;
					}
				}
			}
			if (isValid == false) {
				break;
			}
		}
		// TODO
		// Io di solito per codificare i tipi di errore mi creo un ENUM
		// chiamata tipo StatoInserimentoDocumentoEnum
		// (OK ("Inserimento con successo!"), ERR_LOTTO("Errore ricerca lotto, il lotto
		// indicato non esiste: "), ERR_ART("blabla")

		return isValid;
	}

	// Si può passare come paramentro il filtro e restituisce sempre gli elementi
	// filtrati
	@GetMapping("/list")
	public List<Documento> mostraDocumenti(@RequestParam(value = "profilo", required = false) Integer idProfilo,
			@RequestParam(value = "data1", required = false) String dateFromReq,
			@RequestParam(value = "data2", required = false) String dateToReq) throws ParseException {

		List<Documento> documenti = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calFrom = Calendar.getInstance();
		calFrom.set(Calendar.YEAR, 1900);
		Date dateFrom = calFrom.getTime();

		if (!StringUtils.isEmpty(dateFromReq)) {
			dateFrom = sdf.parse(dateFromReq);
		}

		Calendar calTo = Calendar.getInstance();
		calTo.set(Calendar.YEAR, 2200);
		Date dateTo = calTo.getTime();

		if (!StringUtils.isEmpty(dateToReq)) {
			dateTo = sdf.parse(dateToReq);
		}

		if (idProfilo == null) {
			idProfilo = 0;
		}
		documenti = documentiDao.getFilteredDocuments(idProfilo, dateFrom, dateTo);

		return documenti;
	}
}

package it.objectmethod.cometa.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import it.objectmethod.cometa.dto.DocumentoDTO;
import it.objectmethod.cometa.model.ProfiloDocumento;
import it.objectmethod.cometa.repositories.ProfiloDocumentoRepository;
import it.objectmethod.cometa.services.DocumentoService;
import it.objectmethod.cometa.validators.DocumentoValidator;

@RestController
@RequestMapping("/api/documenti")
public class DocumentoRestController {

	@Autowired
	private ProfiloDocumentoRepository profiliRepo;

	@Autowired
	private DocumentoService docService;

	@Autowired
	private DocumentoValidator validatoreDocumento;

	/*
	 * Come migliorare la logica dell'inserimento:
	 * 
	 * Abbiamo 2 tipi di controlli, uno in fase di inserimento documento e uno in
	 * fase di inserimento righe prima di guardare le righe inizierei a validare il
	 * documento (se necessario) e inserire il documento
	 * 
	 * Poi iniziamo a scorrere le righe, per ogni riga facciamo validazione della
	 * riga e inserimento della stessa Se incontro una riga che non supera la
	 * validazione abbiamo 2 possibilità:
	 * 
	 * 1) Se non abbiamo già aggiornato le quantità dei lotti di ogni riga, posso
	 * fare break dell'inserimento righe e fare una delete sia delle righe già
	 * inserite che del documento stesso 2) Salto la riga in errore e mi segno un
	 * messaggio da ritornare come "warning", alla fine ritorno lista con tutti i
	 * messaggi warning. 3) Valido tutto pre-inserimento, ma per il validatore mi
	 * creo una classe apposita e non metto il metodo nel controller, strutturando
	 * la validazione con più metodi procedo poi con l'inserimento/update solo dopo
	 * l'ok del validatore.
	 */
	@PostMapping("/save")
	public DocumentoDTO inserisci(@RequestBody DocumentoDTO doc){
		int idProfilo = doc.getIdProfilo();
		ProfiloDocumento profile = profiliRepo.getOne(idProfilo);
		String validationResult = validatoreDocumento.validate(doc, profile);
		if (validationResult.equals(DocumentoValidator.OK)) {
			doc = docService.inserisciDocumento(doc);
			doc.setEsitoInsert("Ok");
		} else {
			doc.setEsitoInsert(validationResult);
		}
		return doc;
	}

	// Si può passare come paramentro il filtro e restituisce sempre gli elementi
	// filtrati
	@GetMapping("/list")
	public List<DocumentoDTO> mostraDocumenti(@RequestParam(value = "profilo", required = false) Integer idProfilo,
			@RequestParam(value = "data1", required = false) String dateFromReq,
			@RequestParam(value = "data2", required = false) String dateToReq) throws ParseException {

		List<DocumentoDTO> documentiDTO = new ArrayList<DocumentoDTO>();
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

		documentiDTO = docService.findFilteredDocument(idProfilo, dateFrom, dateTo);

		return documentiDTO;
	}
}

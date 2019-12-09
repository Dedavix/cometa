package it.objectmethod.cometa.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.cometa.dto.DocumentoDTO;
import it.objectmethod.cometa.dto.RigaDocumentoDTO;
import it.objectmethod.cometa.model.Articolo;
import it.objectmethod.cometa.model.Lotto;
import it.objectmethod.cometa.model.ProfiloDocumento;
import it.objectmethod.cometa.repositories.ArticoloRepository;
import it.objectmethod.cometa.repositories.LottoRepository;

@Component
public class DocumentoValidator {
	
	public final static String OK = "Ok";
	
	@Autowired
	private LottoRepository lottiRepo;
	
	@Autowired
	private ArticoloRepository articoliRepo;


	// Invece di string usare un enum ValidationResult con i vari tipi di errore
	public String validate(DocumentoDTO doc, ProfiloDocumento profilo) {
	    return validateDocumento(doc, profilo);
	}

	private String validateDocumento(DocumentoDTO doc, ProfiloDocumento profilo) {
		Lotto lotto = null;
		int idArticolo = 0;
		Articolo articolo = null;
		boolean isValid = false;
		String movimentoMerce = profilo.getMovimentoMerce();
		List<RigaDocumentoDTO> righe = doc.getRigheDocumento();
		String statusMessage = OK;

		for (RigaDocumentoDTO riga : righe) {
			int indiceRiga = 1;
			lotto = lottiRepo.verificaLottoInArticolo(riga.getCodiceLotto(), riga.getCodiceArticolo());
			articolo = articoliRepo.findByCodice(riga.getCodiceArticolo());

			if (articolo != null) {
				idArticolo = articolo.getId();
				if (lotto != null && !"+".equals(movimentoMerce)) {
					if ("-".equals(movimentoMerce)) {
						if (lotto.getQuantita() >= riga.getQuantita()) {
							isValid = true;
						}else {
							statusMessage="ERRORE RIGA "+ indiceRiga +", QUANTITA LOTTO INFERIORE";
						}
					} else {
						isValid = true;
					}
				} else if (lotto == null && "+".equals(movimentoMerce)) {
					// inserisci lotto
					String codiceLotto = riga.getCodiceLotto();
					Lotto lottoDaInserire = new Lotto();
					lottoDaInserire.setArticolo(articolo);
					lottoDaInserire.setCodice(codiceLotto);
					lottoDaInserire.setQuantita(0);
                    Lotto lottoInserito = lottiRepo.save(lottoDaInserire);
					if (lottoInserito != null) {
						isValid = true;
						riga.setCodiceLotto(codiceLotto);
						riga.setIdLotto(lottoInserito.getId());
					}else {
						statusMessage="ERRORE RIGA "+ indiceRiga +", MANCATO INSERIMENTO LOTTO";
					}
				} else if(lotto != null && "+".equals(movimentoMerce)){
					isValid=true;}			
				else {
					statusMessage="ERRORE RIGA "+ indiceRiga +", LOTTO ASSENTE";
				}
			}else {
				statusMessage="ERRORE RIGA "+ indiceRiga +", ARTICOLO ASSENTE";
			}
			if (isValid == false) {
				break;
			}
			indiceRiga++;
		}
		return statusMessage;
	}

}

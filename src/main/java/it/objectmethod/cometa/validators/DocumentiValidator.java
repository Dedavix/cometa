package it.objectmethod.cometa.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.objectmethod.cometa.dao.ArticoliDaoInterface;
import it.objectmethod.cometa.dao.LottiDaoInterface;
import it.objectmethod.cometa.model.Articolo;
import it.objectmethod.cometa.model.Documento;
import it.objectmethod.cometa.model.Lotto;
import it.objectmethod.cometa.model.ProfiloDocumento;
import it.objectmethod.cometa.model.RigaDocumento;
import it.objectmethod.cometa.repositories.ArticoliRepository;
import it.objectmethod.cometa.repositories.LottiRepository;

@Component
public class DocumentiValidator {
	
	private final static String OK = "Ok";
	
	@Autowired
	private LottiRepository lottiRepo;
	
	@Autowired
	private ArticoliRepository articoliRepo;


	// Invece di string usare un enum ValidationResult con i vari tipi di errore
	public String validate(Documento doc, ProfiloDocumento profilo) {
	    return validateDocumento(doc, profilo);
	}

	private String validateDocumento(Documento doc, ProfiloDocumento profilo) {
		Lotto lotto = null;
		int idArticolo;
		Articolo articolo = null;
		boolean isValid = false;
		String movimentoMerce = profilo.getMovimentoMerce();
		List<RigaDocumento> righe = doc.getRighe();
		String statusMessage = OK;

		for (RigaDocumento riga : righe) {
			int indiceRiga = 1;
			lotto = lottiDao.codiceLottoInArticolo(riga.getCodiceLotto(), riga.getCodiceArticolo());
			articolo = articoliDao.searchByCode(riga.getCodiceArticolo());

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
					int lottoInserito = lottiDao.insert(idArticolo, codiceLotto, 0);
					if (lottoInserito > 0) {
						isValid = true;
					}else {
						statusMessage="ERRORE RIGA "+ indiceRiga +", MANCATO INSERIMENTO LOTTO";
					}
				} else {
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

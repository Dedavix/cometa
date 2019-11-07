package it.objectmethod.cometa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.cometa.dao.ArticoliDaoInterface;
import it.objectmethod.cometa.model.Articolo;


@RestController
@RequestMapping("/api/articoli")
public class ArticoliRestController {
	
	@Autowired
	private ArticoliDaoInterface articoliDao;
    
	@GetMapping("/list")//Test ok
	public List<Articolo> mostraArticoli(@RequestParam(value = "filtro", required = false) String filtroPassato) {
		List<Articolo> listaArticoli = null;
		if (filtroPassato == null) {
			listaArticoli = articoliDao.getItems("");
		} else {
			listaArticoli = articoliDao.getItems(filtroPassato);
		}
		return listaArticoli;
	}
    
	// Se id articolo = 0 allora fa insert altrimenti update, per update inserire idArticolo TEST OK
	@PostMapping("/save")
	public String effettuaModifica(@RequestBody Articolo articolo) {
		//String outputPage = "forward:/index";
		String outputMsg = "OPERAZIONE ESEGUITA CON SUCCESSO";
		int valid = 0;
		int idArticolo = articolo.getId();
		String codiceArticolo = articolo.getCodice();
		String descrizioneArticolo = articolo.getDescrizione();
		if (idArticolo!=0) {
			valid = articoliDao.update(idArticolo, codiceArticolo, descrizioneArticolo);
		} else {
			Articolo art = articoliDao.searchByCode(codiceArticolo);
			if (art == null) {
				valid = articoliDao.insert(codiceArticolo, descrizioneArticolo);
			}
		}
		if (valid <= 0) {
			outputMsg = "OPERAZIONE FALLITA";
			//outputPage = "forward:/modifica";
			}
		return outputMsg;
	}

}

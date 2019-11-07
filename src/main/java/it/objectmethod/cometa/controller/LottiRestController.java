package it.objectmethod.cometa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.cometa.dao.LottiDaoInterface;
import it.objectmethod.cometa.model.Lotto;

@RestController
@RequestMapping("/api/lotti")
public class LottiRestController {
	
	@Autowired
	private LottiDaoInterface lottiDao;
	
	@GetMapping("/articolo/{idArticolo}/list")
	public List<Lotto> mostraLotti(@PathVariable(value = "idArticolo", required = true) int articolo) {
		List<Lotto> listaLotti = lottiDao.getLotti(articolo);
		return listaLotti;
	}

}

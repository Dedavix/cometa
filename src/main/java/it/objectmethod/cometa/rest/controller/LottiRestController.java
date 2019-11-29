package it.objectmethod.cometa.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.cometa.model.Lotto;
import it.objectmethod.cometa.repositories.LottiRepository;

@RestController
@RequestMapping("/api/lotti")
public class LottiRestController {
	
	@Autowired
	private LottiRepository lottiRepo;
	
	@GetMapping("/articolo/{idArticolo}/list")
	public List<Lotto> mostraLotti(@PathVariable(value = "idArticolo", required = true) int articolo) {
		List<Lotto> listaLotti = lottiRepo.getLotti(articolo);
		return listaLotti;
	}

}

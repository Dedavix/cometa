package it.objectmethod.cometa.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.cometa.dao.ProfiloDocumentoInterface;
import it.objectmethod.cometa.model.ProfiloDocumento;

@RestController
@RequestMapping("/api/profiliDocumento")
public class ProfiliDocumentoRestController {

	@Autowired
	private ProfiloDocumentoInterface profiliDao;

	@GetMapping("/list")
	public List<ProfiloDocumento> mostraInserimento() {
		List<ProfiloDocumento> profiles = profiliDao.getProfiles();
		return profiles;
	}

}

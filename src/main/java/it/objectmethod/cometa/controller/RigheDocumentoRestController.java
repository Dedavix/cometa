package it.objectmethod.cometa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.cometa.dao.RigheDocumentoDaoInterface;
import it.objectmethod.cometa.model.RigaDocumento;

@RestController
@RequestMapping("/api/righeDocumento")
public class RigheDocumentoRestController {
	
	@Autowired
	private RigheDocumentoDaoInterface righeDao;
	
	@GetMapping("/{id}/list")
	public List<RigaDocumento> mostraRighe(@PathVariable(value = "id") int idDocumento) {
		List<RigaDocumento> righe = righeDao.getRighe(idDocumento);
		return righe;
	}
}

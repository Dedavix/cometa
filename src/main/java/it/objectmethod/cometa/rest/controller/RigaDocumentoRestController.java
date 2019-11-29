package it.objectmethod.cometa.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.cometa.dto.RigaDocumentoDTO;
import it.objectmethod.cometa.dto.mapper.RigaDocumentoMapper;
import it.objectmethod.cometa.model.RigaDocumento;
import it.objectmethod.cometa.repositories.RigaDocumentoRepository;

@RestController
@RequestMapping("/api/righeDocumento")
public class RigaDocumentoRestController {

	@Autowired
	private RigaDocumentoRepository righeRepo;

	@Autowired
	private RigaDocumentoMapper rdMapper;

	@GetMapping("/{id}/list")
	public List<RigaDocumentoDTO> mostraRighe(@PathVariable(value = "id") int idDocumento) {
		List<RigaDocumentoDTO> righeDto = new ArrayList<RigaDocumentoDTO>();
		List<RigaDocumento> righe = righeRepo.findAll();
		for (RigaDocumento riga : righe) {
			righeDto.add(rdMapper.toDto(riga));
		}

		return righeDto;
	}
}

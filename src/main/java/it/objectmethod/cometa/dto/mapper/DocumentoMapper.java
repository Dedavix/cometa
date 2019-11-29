package it.objectmethod.cometa.dto.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.cometa.dto.DocumentoDTO;
import it.objectmethod.cometa.dto.RigaDocumentoDTO;
import it.objectmethod.cometa.model.Documento;
import it.objectmethod.cometa.model.ProfiloDocumento;
import it.objectmethod.cometa.model.RigaDocumento;
import it.objectmethod.cometa.repositories.DocumentoRepository;
import it.objectmethod.cometa.repositories.ProfiloDocumentoRepository;

@Component
public class DocumentoMapper implements EntityMapper<DocumentoDTO, Documento> {

	@Autowired
	DocumentoRepository docRepo;

	@Autowired
	ProfiloDocumentoRepository pdRepo;

	@Autowired
	RigaDocumentoMapper rdMapper;

	@Override
	public Documento toEntity(DocumentoDTO dto) {
		Documento entity = new Documento();
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setProgressivo(dto.getProgressivo());
		ProfiloDocumento pd = pdRepo.findById(dto.getIdProfilo()).get();
		entity.setProfilo(pd);
		if (dto.getRigheDocumento() != null && !dto.getRigheDocumento().isEmpty()) {
			entity.setRighe(new ArrayList<RigaDocumento>());
			for (RigaDocumentoDTO rdDto : dto.getRigheDocumento()) {
				RigaDocumento rd = rdMapper.toEntity(rdDto);
				entity.getRighe().add(rd);
			}
		}
		return entity;
	}

	@Override
	public DocumentoDTO toDto(Documento entity) {
		DocumentoDTO dto = new DocumentoDTO();
		dto.setId(entity.getId());
		dto.setData(entity.getData());
		dto.setProgressivo(entity.getProgressivo());
		dto.setIdProfilo(entity.getProfilo().getId());
		dto.setCodiceProfilo(entity.getProfilo().getCodice());
		if (entity.getRighe() != null && !entity.getRighe().isEmpty()) {
			dto.setRigheDocumento(new ArrayList<RigaDocumentoDTO>());
			for (RigaDocumento rd : entity.getRighe()) {
				RigaDocumentoDTO rdDto = rdMapper.toDto(rd);
				dto.getRigheDocumento().add(rdDto);
			}
		}
		return dto;
	}

}

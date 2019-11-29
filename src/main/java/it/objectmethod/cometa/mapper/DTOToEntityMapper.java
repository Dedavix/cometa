package it.objectmethod.cometa.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.cometa.dto.DocumentoDTO;
import it.objectmethod.cometa.dto.RigaDocumentoDTO;
import it.objectmethod.cometa.model.Documento;
import it.objectmethod.cometa.model.RigaDocumento;
import it.objectmethod.cometa.repositories.ArticoliRepository;
import it.objectmethod.cometa.repositories.LottiRepository;
import it.objectmethod.cometa.repositories.ProfiliDocRepository;

@Component
public class DTOToEntityMapper {
	
	@Autowired
	ProfiliDocRepository profiliRepo;
	@Autowired
	ArticoliRepository articoliRepo;
	@Autowired
	LottiRepository lottiRepo;
	
	public Documento docDtoToEntityConverter(DocumentoDTO dtoDoc) {
		List<RigaDocumentoDTO> righeDto = dtoDoc.getRigheDocumento();
		List<RigaDocumento> righeDoc = new ArrayList<RigaDocumento>();
		Documento documento = new Documento();
		documento.setId(dtoDoc.getId());
		documento.setIdProfilo(dtoDoc.getIdProfilo());
		documento.setProfilo(profiliRepo.getOne(dtoDoc.getIdProfilo()));
		documento.setData(dtoDoc.getData());
		documento.setProgressivo(dtoDoc.getProgressivo());
		for(RigaDocumentoDTO riga:righeDto) {
			
		}
		documento.setRighe(righeDoc);
		return documento;
	}
	
	public RigaDocumento rigaDtoToEntityConverter(RigaDocumentoDTO rigaDto) {
		RigaDocumento rigaDoc= new RigaDocumento();
		rigaDoc.setArticolo(articoliRepo.findByCodice(rigaDto.getCodiceArticolo()));
		rigaDoc.setId(rigaDto.getId());
		rigaDoc.setIdArticolo(rigaDoc.getArticolo().getId());
		//rigaDoc.setLotto();
		return rigaDoc;
	}

}

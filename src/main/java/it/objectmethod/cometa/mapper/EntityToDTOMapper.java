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

@Component
public class EntityToDTOMapper {

	@Autowired
	private ArticoliRepository articoliRepo;

	@Autowired
	private LottiRepository lottiRepo;

	public DocumentoDTO documentToDTO(Documento doc) {
		
		List<RigaDocumento> righeDoc = doc.getRighe();
		List<RigaDocumentoDTO> righeDTO = new ArrayList<RigaDocumentoDTO>();
		DocumentoDTO dtoDoc = new DocumentoDTO();
		dtoDoc.setId(doc.getId());
		dtoDoc.setIdProfilo(doc.getIdProfilo());
		dtoDoc.setProgressivo(doc.getProgressivo());
		dtoDoc.setData(doc.getData());
		for(RigaDocumento riga : righeDoc) {
			righeDTO.add(rigaToDTO(riga));
		}
		dtoDoc.setRigheDocumento(righeDTO);
		return dtoDoc;
	}

	public RigaDocumentoDTO rigaToDTO(RigaDocumento riga) {

		RigaDocumentoDTO dtoRiga = new RigaDocumentoDTO();
		dtoRiga.setId(riga.getId());
		dtoRiga.setIdDocumento(riga.getIdDocumento());
		dtoRiga.setQuantita(riga.getQuantita());
		dtoRiga.setIdArticolo(riga.getIdArticolo());
		dtoRiga.setIdLottto(riga.getIdLotto());
		dtoRiga.setCodiceArticolo(articoliRepo.getOne(riga.getIdArticolo()).getCodice());
		dtoRiga.setCodiceLotto(lottiRepo.getOne(riga.getIdLotto()).getCodice());
		return dtoRiga;
	}
}

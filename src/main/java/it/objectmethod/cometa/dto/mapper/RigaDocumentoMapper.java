package it.objectmethod.cometa.dto.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.cometa.dto.RigaDocumentoDTO;
import it.objectmethod.cometa.model.Articolo;
import it.objectmethod.cometa.model.Lotto;
import it.objectmethod.cometa.model.RigaDocumento;
import it.objectmethod.cometa.repositories.ArticoloRepository;
import it.objectmethod.cometa.repositories.DocumentoRepository;
import it.objectmethod.cometa.repositories.LottoRepository;

@Component
public class RigaDocumentoMapper implements EntityMapper<RigaDocumentoDTO, RigaDocumento> {

	@Autowired
	ArticoloRepository artRepo;

	@Autowired
	DocumentoRepository docRepo;

	@Autowired
	LottoRepository lottoRepo;

	@Override
	public RigaDocumento toEntity(RigaDocumentoDTO dto) {
		RigaDocumento entity = new RigaDocumento();
		if(dto.getId()!=null) {
			entity.setId(dto.getId());
		}
		Articolo art = null;
		if (dto.getIdArticolo() != null) {
			Optional<Articolo> artOpt = artRepo.findById(dto.getIdArticolo());
			art = artOpt.get();
		} else {
			art = artRepo.findByCodice(dto.getCodiceArticolo());
		}
		entity.setArticolo(art);

		Lotto lt = null;
		if (dto.getIdLotto() != null) {
			Optional<Lotto> ltOpt = lottoRepo.findById(dto.getIdLotto());
			lt = ltOpt.get();
		} else {
			lt = lottoRepo.findByCodiceAndIdArticolo(dto.getCodiceLotto(), dto.getIdArticolo());
		}
		entity.setLotto(lt);

		if (dto.getIdDocumento() != null) {
			entity.setDocumento(docRepo.findById(dto.getIdDocumento()).get());
		}
		entity.setQuantita(dto.getQuantita());
		return entity;
	}

	@Override
	public RigaDocumentoDTO toDto(RigaDocumento entity) {
		RigaDocumentoDTO dto = new RigaDocumentoDTO();
		dto.setId(entity.getId());
		dto.setIdArticolo(entity.getArticolo().getId());
		dto.setCodiceArticolo(entity.getArticolo().getCodice());
		dto.setIdLotto(entity.getLotto().getId());
		dto.setCodiceLotto(entity.getLotto().getCodice());
		dto.setIdDocumento(entity.getDocumento().getId());
		dto.setQuantita(entity.getQuantita());
		dto.setDescrizioneArticolo(entity.getArticolo().getDescrizione());
		return dto;
	}

}

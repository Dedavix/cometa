package it.objectmethod.cometa.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.cometa.dto.DocumentoDTO;
import it.objectmethod.cometa.dto.mapper.DocumentoMapper;
import it.objectmethod.cometa.model.Documento;
import it.objectmethod.cometa.model.Lotto;
import it.objectmethod.cometa.model.RigaDocumento;
import it.objectmethod.cometa.repositories.ArticoloRepository;
import it.objectmethod.cometa.repositories.DocumentoRepository;
import it.objectmethod.cometa.repositories.LottoRepository;
import it.objectmethod.cometa.repositories.RigaDocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	DocumentoRepository docRepo;

	@Autowired
	RigaDocumentoRepository rdRepo;

	@Autowired
	ArticoloRepository artRepo;

	@Autowired
	LottoRepository ltRepo;

	@Autowired
	DocumentoMapper docMapper;


	public DocumentoDTO inserisciDocumento(DocumentoDTO docDto) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(docDto.getData());

		int progressivo = docRepo.getLastProgressivo(Integer.parseInt(year), docDto.getIdProfilo()) + 1;
		docDto.setProgressivo(progressivo);
		Documento doc = docMapper.toEntity(docDto);
		doc = docRepo.save(doc);
		for (RigaDocumento riga : doc.getRighe()) {

			// TODO utilizziamo ENUM per MovimentoMerce e costanti per i messaggi di errore
			// (tranne quando identificano più casi di un tipo di errore)
			Lotto lt = riga.getLotto();
			if (("+").equals(doc.getProfilo().getMovimentoMerce())) {
				lt.setQuantita(lt.getQuantita() + riga.getQuantita());
			}
			if (("-").equals(doc.getProfilo().getMovimentoMerce())) {
				lt.setQuantita(lt.getQuantita() - riga.getQuantita());
			}
		}
		docDto = docMapper.toDto(doc);
		return docDto;
	}

	public List<DocumentoDTO> findFilteredDocument(Integer idProfilo, Date dateFrom, Date dateTo) {
		List<DocumentoDTO> docDtoList = new ArrayList<DocumentoDTO>();
		List<Documento> docList = docRepo.findFilteredDocument(idProfilo, dateFrom, dateTo);
		for (Documento doc : docList) {
			docDtoList.add(docMapper.toDto(doc));
		}
		return docDtoList;
	}

}

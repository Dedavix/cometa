package it.objectmethod.cometa.dto;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DocumentoDTO {
	
private Integer id;
	
	private Integer idProfilo;
	
	private String codiceProfilo;
	
	private Integer Progressivo;
	
	private Date data;
	
	private List<RigaDocumentoDTO> righeDocumento;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProfilo() {
		return idProfilo;
	}

	public void setIdProfilo(Integer idProfilo) {
		this.idProfilo = idProfilo;
	}

	public String getCodiceProfilo() {
		return codiceProfilo;
	}

	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}

	public Integer getProgressivo() {
		return Progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		Progressivo = progressivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<RigaDocumentoDTO> getRigheDocumento() {
		return righeDocumento;
	}

	public void setRigheDocumento(List<RigaDocumentoDTO> righeDocumento) {
		this.righeDocumento = righeDocumento;
	}

	

}

package it.objectmethod.cometa.dto;

import org.springframework.stereotype.Component;

@Component
public class RigaDocumentoDTO {
	
	private Integer id;
	
	private Integer idDocumento;
	
	private Integer idArticolo;
	
	private Integer idLottto;
	
	private String codiceArticolo;
	
	private String codiceLotto;
	
	private Integer quantita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public Integer getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
	}

	public Integer getIdLottto() {
		return idLottto;
	}

	public void setIdLottto(Integer idLottto) {
		this.idLottto = idLottto;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public String getCodiceLotto() {
		return codiceLotto;
	}

	public void setCodiceLotto(String codiceLotto) {
		this.codiceLotto = codiceLotto;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

}

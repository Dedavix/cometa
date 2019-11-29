package it.objectmethod.cometa.dto;

public class RigaDocumentoDTO {
	
	private Integer id;
	
	private Integer idDocumento;
	
	private Integer idArticolo;
	
	private Integer idLotto;
	
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

	public Integer getIdLotto() {
		return idLotto;
	}

	public void setIdLotto(Integer idLotto) {
		this.idLotto = idLotto;
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

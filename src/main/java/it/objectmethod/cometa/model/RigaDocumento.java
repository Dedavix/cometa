package it.objectmethod.cometa.model;


public class RigaDocumento {
	
	private int id;
	private String codiceArticolo;
	private String descrizioneArticolo;
	private String codiceLotto;
	private int quantita;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodiceArticolo() {
		return codiceArticolo;
	}
	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}
	public String getDescrizioneArticolo() {
		return descrizioneArticolo;
	}
	public void setDescrizioneArticolo(String descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}
	public String getCodiceLotto() {
		return codiceLotto;
	}
	public void setCodiceLotto(String codiceLotto) {
		this.codiceLotto = codiceLotto;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

}

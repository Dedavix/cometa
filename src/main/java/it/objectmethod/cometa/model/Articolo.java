package it.objectmethod.cometa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "articoli")
public class Articolo {
    
	@GeneratedValue
	@Id
	private int id;
	
	@Column(name="codice")
	private String codice;
	
	@Column(name="descrizione")
	private String descrizione;
	
	@JoinColumn(name ="id_articolo")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Lotto> lotti;
	
//	@JoinColumn(name ="id_articolo")
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<RigaDocumento> righeDocumento;
	

//	public List<RigaDocumento> getRigheDocumento() {
//		return righeDocumento;
//	}
//
//	public void setRigheDocumento(List<RigaDocumento> righeDocumento) {
//		this.righeDocumento = righeDocumento;
//	}

	public List<Lotto> getLotti() {
		return lotti;
	}

	public void setLotti(List<Lotto> lotti) {
		this.lotti = lotti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

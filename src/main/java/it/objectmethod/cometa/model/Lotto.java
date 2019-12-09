package it.objectmethod.cometa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lotti")
public class Lotto {
	
	@GeneratedValue
	@Id
	private int id;
	
	@Column(name= "codice_lotto")
	private String codice;
	
	@Column(name="quantita")
	private int quantita;
    
	@JsonIgnore
	@JoinColumn(name = "id_articolo")
	@ManyToOne
	private Articolo articolo;
	
	@JsonIgnore
	@JoinColumn(name ="id_lotto")
	@OneToMany(cascade = CascadeType.ALL)
	private List<RigaDocumento> righeDocumento;
	
	

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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public List<RigaDocumento> getRigheDocumento() {
		return righeDocumento;
	}

	public void setRigheDocumento(List<RigaDocumento> righeDocumento) {
		this.righeDocumento = righeDocumento;
	}

	
	
}

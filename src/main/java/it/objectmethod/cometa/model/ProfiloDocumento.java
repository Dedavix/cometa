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
@Table(name="profilo_documento")
public class ProfiloDocumento {
	
	@GeneratedValue
	@Id
	private int id;
	
	@Column(name="codice")
	private String codice;
	
	@Column(name="movimento_merce")
	private String movimentoMerce;
	
	@JoinColumn(name ="id_profilo_documento")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Documento> documenti;
	
	
	public List<Documento> getDocumenti() {
		return documenti;
	}
	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
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
	public String getMovimentoMerce() {
		return movimentoMerce;
	}
	public void setMovimentoMerce(String movimentoMerce) {
		this.movimentoMerce = movimentoMerce;
	}

}

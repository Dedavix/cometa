package it.objectmethod.cometa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "profilo_documento")
public class ProfiloDocumento {

	@GeneratedValue
	@Id
	private Integer id;

	@Column(name = "codice")
	private String codice;

	@Column(name = "movimento_merce")
	private String movimentoMerce;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

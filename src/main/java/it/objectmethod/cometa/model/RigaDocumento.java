package it.objectmethod.cometa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "righe_documento")
public class RigaDocumento {

	@GeneratedValue
	@Id
	private int id;

	@Column(name = "quantita")
	private int quantita;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_documento")
	private Documento documento;

	@ManyToOne
	@JoinColumn(name = "id_articolo")
	private Articolo articolo;

	@ManyToOne
	@JoinColumn(name = "id_lotto")
	private Lotto lotto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Lotto getLotto() {
		return lotto;
	}

	public void setLotto(Lotto lotto) {
		this.lotto = lotto;
	}

}

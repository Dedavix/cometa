package it.objectmethod.cometa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "documenti")
public class Documento {

	@GeneratedValue
	@Id
	private Integer id;

	@Column(name = "data")
	private Date data;

	@Column(name = "progressivo")
	private Integer progressivo;

	@JoinColumn(name = "id_documento")
	@OneToMany(fetch = FetchType.EAGER)
	private List<RigaDocumento> righe;

	@ManyToOne
	@JoinColumn(name = "id_profilo_documento")
	private ProfiloDocumento profilo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	public List<RigaDocumento> getRighe() {
		return righe;
	}

	public void setRighe(List<RigaDocumento> righe) {
		this.righe = righe;
	}

	public ProfiloDocumento getProfilo() {
		return profilo;
	}

	public void setProfilo(ProfiloDocumento profilo) {
		this.profilo = profilo;
	}

}

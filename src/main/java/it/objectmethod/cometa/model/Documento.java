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
@Table(name="documenti")
public class Documento {
    
	@GeneratedValue
	@Id
	private int id;
	
	@Column(name="data")
	private Date data;
	
	@Column(name="progressivo")
	private int progressivo;
	
	@JoinColumn(name ="id_documento")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<RigaDocumento> righe;
	
	@ManyToOne
	private ProfiloDocumento profilo;
	

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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getProgressivo() {
		return progressivo;
	}
	public void setProgressivo(int progressivo) {
		this.progressivo = progressivo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}

package it.objectmethod.cometa.model;

import java.util.Date;
import java.util.List;


public class Documento {
    
	private int idProfilo;
	private String profilo;
	private Date data;
	private int progressivo;
	private int id;
	private List<RigaDocumento> righe;
	
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
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
	public List<RigaDocumento> getRighe() {
		return righe;
	}
	public void setRighe(List<RigaDocumento> righe) {
		this.righe = righe;
	}
	public int getIdProfilo() {
		return idProfilo;
	}
	public void setIdProfilo(int idProfilo) {
		this.idProfilo = idProfilo;
	}

}

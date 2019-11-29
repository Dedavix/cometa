package it.objectmethod.cometa.dao;

import java.util.Date;
import java.util.List;

import it.objectmethod.cometa.model.Documento;


public interface DocumentiDaoInterface {
	
	public int inserisciDocumento(int idProfilo, Date data, int progressivo);
	
	public int getLastProgressivo(String anno,String profilo);
	
	public int getIdDocumento(int progressivo, int idProfilo, Date data);
	
	public List<Documento> getFilteredDocuments(int idProfilo, Date data1, Date data2);
	
	

}

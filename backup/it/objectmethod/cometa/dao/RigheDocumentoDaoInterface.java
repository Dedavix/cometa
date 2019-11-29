package it.objectmethod.cometa.dao;

import java.util.List;

import it.objectmethod.cometa.model.RigaDocumento;

public interface RigheDocumentoDaoInterface {
	
	public List<RigaDocumento> getRighe(int idDocumento);
	
	public int inserisciRiga(int idDocumento, int idArticolo, int idLotto, int quantita);

}

package it.objectmethod.cometa.dao;

import java.util.List;

import it.objectmethod.cometa.model.ProfiloDocumento;

public interface ProfiloDocumentoInterface {
	
	public List <ProfiloDocumento> getProfiles();
	
	public String getCodeById(int id);

	public ProfiloDocumento getProfile(int id);

}

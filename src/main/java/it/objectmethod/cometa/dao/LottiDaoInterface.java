package it.objectmethod.cometa.dao;

import java.util.List;

import it.objectmethod.cometa.model.Lotto;

public interface LottiDaoInterface {
	
	public List<Lotto> getLotti(int articolo);
	public int getIdByCode(String codice, int idArticolo);
	public Lotto codiceLottoInArticolo(String codiceLotto, String codiceArticolo);
	public Lotto verificaQuantita(String codiceLotto, int quantita);
	public int sottraiQuantita(int idArticolo, String codiceLotto,int quantita);
	public int aggiungiQuantita(int idArticolo, String codiceLotto, int quantita);
	public int insert(int idArticolo,String codiceLotto, int quantità);
}

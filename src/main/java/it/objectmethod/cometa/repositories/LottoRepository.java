package it.objectmethod.cometa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.cometa.model.Lotto;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Integer>{
	
	@Query("select l from Lotto l where l.articolo.id=?1")
	public List<Lotto> getLotti(int idArticolo);
	
	@Query("select l from Lotto l where l.codice = ?1 and l.quantita >= ?2")
	public List<Lotto> verificaQuantita(String codiceLotto, int quantita);
	
	@Query("Select l from Lotto l where l.codice= ?1 and l.articolo.codice= ?2")
	public Lotto verificaLottoInArticolo(String codiceLotto,String codiceArticolo);
	
	public Lotto findQuantitaById(int idLotto);
    
	@Query("select l from Lotto l where l.codice= ?1 and l.articolo.id = ?2")
	public Lotto getLottoByIdArticoloAndCodice(String codiceLotto, Integer idArticolo);

}

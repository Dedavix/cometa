package it.objectmethod.cometa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.cometa.model.ArticoloView;

@Repository
public interface ArticoloViewRepository extends JpaRepository<ArticoloView, Integer> {

	@Query("select a from ArticoloView a where a.codice like %?1% or a.descrizione like %?1%")
	public List<ArticoloView> getArticoliFiltrati(String filtro);

	public Optional<ArticoloView> findById(Integer id);

	public ArticoloView findByCodice(String codice);

}

package it.objectmethod.cometa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.cometa.model.Articolo;
import it.objectmethod.cometa.model.ArticoloView;

@Repository
public interface ArticoliRepository extends JpaRepository<Articolo, Integer> {
	
    public Optional<Articolo> findById(Integer id);
	
	public Articolo findByCodice(String codice);

}

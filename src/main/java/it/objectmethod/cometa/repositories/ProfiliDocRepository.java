package it.objectmethod.cometa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.cometa.model.ProfiloDocumento;

@Repository
public interface ProfiliDocRepository extends JpaRepository<ProfiloDocumento, Integer> {

}
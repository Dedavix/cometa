package it.objectmethod.cometa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.cometa.model.RigaDocumento;

@Repository
public interface RigaDocumentoRepository extends JpaRepository<RigaDocumento, Integer>{
	
	@Query("select r from RigaDocumento r where r.documento.id = ?1")
	public List<RigaDocumento> findByIdDocumentoselect(int idDocumento);

}

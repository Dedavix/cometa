package it.objectmethod.cometa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.cometa.model.RigaDocumento;

@Repository
public interface RigheDocRepository extends JpaRepository<RigaDocumento, Integer>{
	
	public List<RigaDocumento> findByIdDocumento(int idDocumento);

}

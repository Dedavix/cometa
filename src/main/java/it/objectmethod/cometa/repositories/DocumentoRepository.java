package it.objectmethod.cometa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.cometa.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
	
	@Query("select max(d.progressivo) from Documento d where year(d.data)=?1 and d.profilo.id = ?2")
	public Integer getLastProgressivo(String anno, int idProfilo);
	
	@Query(value ="select d.id from Documento d where d.progressivo = ?1 and d.profilo.id = ?2 and d.data = ?3")
	public Integer getDocumentId(int progressivo, int idProfilo, Date data);
	
	@Query("select d from Documento d join fetch d.profilo pd where (:idProfilo = 0 or pd.id = :idProfilo) AND d.data BETWEEN :dataFrom and :dataTo ")
	public List<Documento> findFilteredDocument(@Param("idProfilo")int idProfilo, @Param("dataFrom") Date data1, @Param("dataTo") Date data2);
}

package it.objectmethod.cometa.dao.implementation;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.objectmethod.cometa.dao.ProfiloDocumentoInterface;
import it.objectmethod.cometa.model.Documento;
import it.objectmethod.cometa.model.ProfiloDocumento;

@Service
public class ProfiloDocumentoDao extends NamedParameterJdbcDaoSupport implements ProfiloDocumentoInterface {
	
	public ProfiloDocumentoDao(DataSource datasource) {
		super();
		setDataSource(datasource);
		}

	@Override
	public List<ProfiloDocumento> getProfiles() {
		List<ProfiloDocumento> profili = null;
		String sql = "Select id, codice from cometa_easy.profilo_documento order by id";
		BeanPropertyRowMapper<ProfiloDocumento> rm = new BeanPropertyRowMapper<ProfiloDocumento>(
				ProfiloDocumento.class);
		profili = getNamedParameterJdbcTemplate().query(sql, rm);
		return profili;
	}

	@Override
	public String getCodeById(int id) {
		String sql = "Select codice from cometa_easy.profilo_documento where id = ?";
		String codice = getJdbcTemplate().queryForObject(sql, new Object[] {id}, String.class);
		return codice;
				
	}
	
	@Override
	public ProfiloDocumento getProfile(int id) {
		String sql = "Select id, codice, movimento_merce as movimentoMerce from cometa_easy.profilo_documento where id = :id";
		//ProfiloDocumento profilo  = getJdbcTemplate().queryForObject(sql, new Object[] {id}, ProfiloDocumento.class);
		MapSqlParameterSource param = new MapSqlParameterSource();
		BeanPropertyRowMapper<ProfiloDocumento> rm = new BeanPropertyRowMapper<ProfiloDocumento>(ProfiloDocumento.class);
		param.addValue("id", id);
		ProfiloDocumento profilo = getNamedParameterJdbcTemplate().queryForObject(sql, param, rm);
		return profilo;
	}

}

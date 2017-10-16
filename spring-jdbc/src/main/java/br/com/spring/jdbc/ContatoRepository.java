package br.com.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ContatoRepository {

	private static final Logger LOGGER = Logger.getLogger(ContatoRepository.class);
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ContatoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Cacheable(value = "findByMsisdn", unless = "#result == null")
	public Contato getContatoPorMsisdn(String msisdn)
			throws IllegalArgumentException {
		if (msisdn == null || "".equals(msisdn.trim())) {
			throw new IllegalArgumentException("Parametro MSISDN obrigatorio!");
		}

		Contato contato = null;
		try {
			String sql = "SELECT * FROM CONTATO WHERE TELEFONE_CONTATO = ?";
			contato = jdbcTemplate.queryForObject(sql, new Object[] { msisdn },
					new ContatoMapper());
		} catch (DataAccessException ex) {
			LOGGER.error(ex.getMessage());
		} catch (Exception ex) {
			LOGGER.error(ex);
		}

		return contato;
	}

	public List<Contato> getContatos() {
		List<Contato> contatos = new ArrayList<Contato>();
		try {
			String sql = "SELECT * FROM CONTATO ORDER BY IDENTIFICADOR ASC";
			contatos = jdbcTemplate.query(sql, new ContatoMapper());
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		return contatos;
	}

	private class ContatoMapper implements RowMapper<Contato> {

		public Contato mapRow(ResultSet resultSet, int position)
				throws SQLException {
			Contato contato = new Contato.ContatoBuilder()
					.addId(resultSet.getInt("IDENTIFICADOR"))
					.addEmail(resultSet.getString("EMAIL_CONTATO"))
					.addNome(resultSet.getString("NOME_CONTATO"))
					.addSobrenome(resultSet.getString("ULTIMO_NOME_CONTATO"))
					.addTelefone(resultSet.getString("TELEFONE_CONTATO"))
					.createContato();
			return contato;
		}

	}
}

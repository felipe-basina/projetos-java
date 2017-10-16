package br.com.spring.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class TesteRepository {

	private static final Logger LOGGER = Logger.getLogger(TesteRepository.class);
	
	private static final String INSERT_TESTE_TBL = "INSERT INTO TESTE (IDENTIFICADOR, DT_CADASTRO, NOME) VALUES (null, now(), ?)";
	
	private static final String DELETE_TESTE_TBL = "DELETE FROM TESTE";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public TesteRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private void salvarUnitario(final List<Teste> testeLista) throws Exception {
		for (Teste teste : testeLista) {
			
			this.jdbcTemplate.update(INSERT_TESTE_TBL, teste.getNome());
			
		}
	}
	
	private void salvarBatchUpdate(final List<Teste> testeLista) throws Exception {
		this.jdbcTemplate.batchUpdate(INSERT_TESTE_TBL, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int indice) throws SQLException {
				
				ps.setString(1, testeLista.get(indice).getNome());
				
			}
			
			@Override
			public int getBatchSize() {
				return testeLista.size();
			}

		});
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void salvar(final List<Teste> testeLista, boolean batchUpdate) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		
		try {
			
			// Verifica se existe transação (precisa estar configurado corretamente - ver application-context.xml)
			LOGGER.debug("Transacao ativa? " 
					+ TransactionSynchronizationManager.isActualTransactionActive());
			
			LOGGER.debug("# Preparando para inserir [ " + 
					testeLista.size() + " ] registros. Batch update [ " 
					+ batchUpdate + " ]");
		
			LOGGER.debug("# Inicio: " + sdf.format(new Date()));
			long inicio = System.currentTimeMillis();
			
			if (batchUpdate) {
				
				this.salvarBatchUpdate(testeLista);
				
			} else {
				
				this.salvarUnitario(testeLista);
				
			}
					
			long duracao = System.currentTimeMillis() - inicio;
					
			LOGGER.debug("# Fim: " + sdf.format(new Date()) 
					+ ". Duracao aproximada: [ " + duracao / 1000 + "(s) ]");
			
		} catch (Exception ex) {
			LOGGER.error("ERRO: " + ex.getMessage(), ex);
		}
	}
	
	public int removerRegistros() {
		int totalRegistros = -1;
		
		try {

			totalRegistros = this.jdbcTemplate.update(DELETE_TESTE_TBL);
			
		} catch (Exception ex) {
			LOGGER.error("ERRO: " + ex.getMessage(), ex);
		}
		
		return totalRegistros;
	}
}

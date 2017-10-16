package br.com.spring.jdbc;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;

public class SchemaRepository extends JdbcTemplate {
	
	public void verificarDataSource() {
		if (!ObjectUtils.isEmpty(this.getDataSource())) {
			
			try {
			
				System.out.println("Usando schema [ " + 
						this.getDataSource().getConnection().getCatalog() + " ]");
				
				this.exibirTabelas();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			
			System.err.println("# ERRO ao identificar data source = NULO");
			
		}
	}
	
	private void exibirTabelas() {
		try {
		
			final DatabaseMetaData meta = this.getDataSource()
					.getConnection().getMetaData();
			
			final ResultSet resultSet = meta.getTables(null, null, "%", null);
			
			System.out.print("***********************");
			System.out.println("\n Exibindo tabelas");
			System.out.println("***********************");
			
			while (resultSet.next()) {
				System.out.println(".... " + resultSet.getString(3));
	        }
			
			System.out.println("#-------------------------#\n");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
package br.com.spring.jdbc;


public class DataSourceDinamicoMain {

	public static void main(String[] args) {
		
		SchemaRepository schema = SpringContext.getInstance().getBean("schemaRepository");
		schema.verificarDataSource();
		
		SchemaContextHolder.setSchemaTypes("JPA2");
		
		schema = SpringContext.getInstance().getBean("schemaRepository");
		schema.verificarDataSource();

		SchemaContextHolder.setSchemaTypes("ROBSON");
		
		schema = SpringContext.getInstance().getBean("schemaRepository");
		schema.verificarDataSource();
		
	}

}
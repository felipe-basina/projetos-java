package br.com.spring.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class SchemaRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("ContextHolder [ " + SchemaContextHolder.getSchemaTypes() + " ]");
		return SchemaContextHolder.getSchemaTypes();
	}

}

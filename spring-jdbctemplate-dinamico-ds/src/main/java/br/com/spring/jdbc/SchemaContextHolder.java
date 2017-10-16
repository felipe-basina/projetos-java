package br.com.spring.jdbc;

import org.springframework.util.Assert;

public class SchemaContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setSchemaTypes(String schemaType) {
		Assert.notNull(schemaType, "schemaType cannot be null");
		contextHolder.set(schemaType);
	}

	public static String getSchemaTypes() {
		return contextHolder.get();
	}

	public static void clearSchemaTypes() {
		contextHolder.remove();
	}

}

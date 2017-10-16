package br.com.spring.jdbc;

import org.springframework.util.Assert;

public class CopyOfSchemaContextHolder {

	private static final ThreadLocal<SchemaType> contextHolder = new ThreadLocal<SchemaType>();

	public static void setSchemaTypes(SchemaType schemaType) {
		Assert.notNull(schemaType, "schemaType cannot be null");
		contextHolder.set(schemaType);
	}

	public static SchemaType getSchemaTypes() {
		return (SchemaType) contextHolder.get();
	}

	public static void clearSchemaTypes() {
		contextHolder.remove();
	}

}

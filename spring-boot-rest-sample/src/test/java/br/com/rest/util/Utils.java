package br.com.rest.util;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}

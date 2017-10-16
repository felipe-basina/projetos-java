package br.com.ws.client.config;

public class StringUtils {

	public static String[] splitStringSemiColon(String string) {
		return split(string, ";");
	}
	
	private static String[] split(String string, String split) {
		return string.split(split);
	}
	
	public static boolean isStringNullOrEmpty(String string) {
		return (string == null || "".equals(string.trim())) ? true : false; 
	}
}

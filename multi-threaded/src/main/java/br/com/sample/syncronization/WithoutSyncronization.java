package br.com.sample.syncronization;

import java.net.HttpURLConnection;
import java.net.URL;

public class WithoutSyncronization {

	private static String[] hostList = { "http://crunchify.com", "http://yahoo.com", "http://www.ebay.com",
			"http://google.com", "http://www.example.co", "https://paypal.com", "http://bing.com/",
			"http://techcrunch.com/", "http://mashable.com/", "http://thenextweb.com/", "http://wordpress.com/",
			"http://wordpress.org/", "http://example.com/", "http://sjsu.edu/", "http://ebay.co.uk/",
			"http://google.co.uk/", "http://www.wikipedia.org/", "http://en.wikipedia.org/wiki/Main_Page" };

	public static void main(String[] args) {
		
		long init = System.currentTimeMillis();
		
		new WithoutSyncronization().execute();
		
		long end = System.currentTimeMillis();
		
		System.out.println("\nTempo de processamento (ms): " + (end - init));
	}

	private void execute() {
		for (String host : hostList) {
			this.executeHttpRequest(host);
		}
	}

	private void executeHttpRequest(String host) {
		String result = "";
		int code = 200;
		try {
			URL siteURL = new URL(host);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			code = connection.getResponseCode();
			if (code == 200) {
				result = "Green\t";
			} else {
				result = "Code = " + code;
			}
		} catch (Exception e) {
			result = "->Red<-\t";
		}
		System.out.println(host + "\t\tStatus:" + result);
	}
}

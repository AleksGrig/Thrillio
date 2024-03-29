package com.aurelius.thrillio.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpConnect {

	public static String download(String sourceUrl) throws MalformedURLException, URISyntaxException {
		System.out.println("Downloading: " + sourceUrl);
		URL url = new URI(sourceUrl).toURL();

		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int responseCode = connection.getResponseCode();
			if (responseCode >= 200 && responseCode < 300) {
				return IOUtil.read(connection.getInputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

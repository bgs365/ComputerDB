package com.excilys.cdb.ui;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class RestClient {
	public static final int PORT = 8085;
	public static final String DEFAULT_URL_CONTEXT = "http://localhost:8085/webservice/computer/";

	public static void main(final String[] arguments) throws IOException {
		
	}
	
	public static void get() throws ClientProtocolException, IOException {
		String name = "1";
		HttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(DEFAULT_URL_CONTEXT+name));
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		System.out.println(responseString);
	}
	
	public static void postCreate() throws ClientProtocolException, IOException {
		String name = "1";
		HttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(DEFAULT_URL_CONTEXT+name));
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		System.out.println(responseString);
	}

}

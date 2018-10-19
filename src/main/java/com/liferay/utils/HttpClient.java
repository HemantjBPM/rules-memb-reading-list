package com.liferay.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.models.RuleDataModel;

public class HttpClient {

	public static void get(String getURL) {
		try {

			URL url = new URL(getURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(LiferayConstant.HTTPGET);
			conn.setRequestProperty("Accept", LiferayConstant.CONTENTTYPE);
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				ObjectMapper mapper = new ObjectMapper();
				RuleDataModel responseDataModel = mapper.readValue(output, RuleDataModel.class);
				System.out.println("Ouput from rule: " + responseDataModel);
				System.out.println("Output rules matched: "+ responseDataModel.getName());
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void post(String postURL, String data) {
		try {
			URL url = new URL(postURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(LiferayConstant.HTTPPOST);
			conn.setRequestProperty("Content-Type", LiferayConstant.CONTENTTYPE);
			//String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	
	public static void main(String[] args) {
		HttpClient.get("http://localhost:8080/ROOT/getData");
	}
}

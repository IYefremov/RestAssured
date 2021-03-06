package com.cyberiansoft.test.dataprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

import java.io.*;

public class JSonDataParser {
	
	public static <T>T getTestDataFromJson(JSONObject jsondata, Class<T> classOfT) {
		Gson gson = new Gson();
		return gson.fromJson(jsondata.toString(), classOfT);
	}

	public static <T>T getTestDataFromJson(String jsondata, Class<T> classOfT) {
		Gson gson = new Gson();
		return gson.fromJson(jsondata, classOfT);
	}
	
	public static <T>T getTestDataFromJson(File filejson, Class<T> classOfT) throws IOException {
		
		ObjectMapper obj = new ObjectMapper();
		Reader reader = null;
		try {
	    	reader = new FileReader(filejson);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.readValue(reader, classOfT);
	}

}

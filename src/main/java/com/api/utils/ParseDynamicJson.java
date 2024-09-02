package com.api.utils;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseDynamicJson {

	public static void parseObject(JSONObject json, String key) {
		// System.out.println(json.has(key));
		System.out.println(json.get(key));
	}

	//Recursive function to traverse the json
	public static void getKey(JSONObject json, String key) {

		boolean exists = json.has(key);
		Iterator<?> keys;
		String nextKeys;

		if (!exists) {
			keys = json.keys();
			while (keys.hasNext()) {
				nextKeys = (String) keys.next();
				try {

					if (json.get(nextKeys) instanceof JSONObject) {

						if (exists == false) {
							getKey(json.getJSONObject(nextKeys), key);
						}

					} else if (json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for (int i = 0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJSOn = new JSONObject(jsonarrayString);

							if (exists == false) {
								getKey(innerJSOn, key);
							}

						}

					}

				} catch (Exception e) {
					
				}

			}

		} else {
			parseObject(json, key);
		}

	}

	
	public static void main(String[] args) {

		String inputJson = "{\n"
				+ "    \"page\": 1,\n"
				+ "    \"per_page\": 6,\n"
				+ "    \"total\": 12,\n"
				+ "    \"total_pages\": 2,\n"
				+ "    \"data\": [\n"
				+ "        {\n"
				+ "            \"id\": 1,\n"
				+ "            \"email\": \"george.bluth@reqres.in\",\n"
				+ "            \"first_name\": \"George\",\n"
				+ "            \"last_name\": \"Bluth\",\n"
				+ "            \"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"id\": 2,\n"
				+ "            \"email\": \"janet.weaver@reqres.in\",\n"
				+ "            \"first_name\": \"Janet\",\n"
				+ "            \"last_name\": \"Weaver\",\n"
				+ "            \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"id\": 3,\n"
				+ "            \"email\": \"emma.wong@reqres.in\",\n"
				+ "            \"first_name\": \"Emma\",\n"
				+ "            \"last_name\": \"Wong\",\n"
				+ "            \"avatar\": \"https://reqres.in/img/faces/3-image.jpg\"\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"id\": 4,\n"
				+ "            \"email\": \"eve.holt@reqres.in\",\n"
				+ "            \"first_name\": \"Eve\",\n"
				+ "            \"last_name\": \"Holt\",\n"
				+ "            \"avatar\": \"https://reqres.in/img/faces/4-image.jpg\"\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"id\": 5,\n"
				+ "            \"email\": \"charles.morris@reqres.in\",\n"
				+ "            \"first_name\": \"Charles\",\n"
				+ "            \"last_name\": \"Morris\",\n"
				+ "            \"avatar\": \"https://reqres.in/img/faces/5-image.jpg\"\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"id\": 6,\n"
				+ "            \"email\": \"tracey.ramos@reqres.in\",\n"
				+ "            \"first_name\": \"Tracey\",\n"
				+ "            \"last_name\": \"Ramos\",\n"
				+ "            \"avatar\": \"https://reqres.in/img/faces/6-image.jpg\"\n"
				+ "        }\n"
				+ "    ],\n"
				+ "    \"support\": {\n"
				+ "        \"url\": \"https://reqres.in/#support-heading\",\n"
				+ "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n"
				+ "    }\n"
				+ "}";
		JSONObject inputJSONOBject = new JSONObject(inputJson);

		getKey(inputJSONOBject, "first_name");
		getKey(inputJSONOBject, "last_name");
	}

}

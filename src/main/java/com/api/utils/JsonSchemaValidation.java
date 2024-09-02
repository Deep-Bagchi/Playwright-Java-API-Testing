package com.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonSchemaValidation {
	
public static void	JsonSchemaValidator(JSONObject jsonSchema, JSONObject jsonData) {
	
	//Validate Schema
	Schema schemaValidator = SchemaLoader.load(jsonSchema);
	schemaValidator.validate(jsonData);
	System.out.println("Response JSON SCHEMA Validation was successfull!!!!");
	
	
}


public static JSONObject readJsonSchemaFile() throws FileNotFoundException {

	//JSON Schema
		File schemaFile = new File("usersSchema.json");
		JSONTokener schemaData = new JSONTokener(new FileInputStream(schemaFile));
		JSONObject jsonSchema = new JSONObject(schemaData);
		return jsonSchema;
}

public static JSONObject readJsonDataFile() throws FileNotFoundException {

	//JSON Data
		File dataFile = new File("users.json");
		JSONTokener tokenData = new JSONTokener(new FileInputStream(dataFile));
		JSONObject jsonData = new JSONObject(tokenData);
		return jsonData;
		
}

}

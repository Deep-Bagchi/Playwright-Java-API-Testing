package com.qa.api.tests.GET;

import java.io.IOException;
import java.util.Map;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.api.utils.FetchProperties;
import com.api.utils.JsonSchemaValidation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;



public class GETAPICall {
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;
    
    

    @BeforeTest
    public void setup(){
         playwright = Playwright.create();
         request =  playwright.request();
         requestContext = request.newContext();
    }

    @Test
    public void getUserApiTest() throws IOException, ParseException {

    	String baseURI= FetchProperties.fetchURI();
    	String resource = "/api/users";
    	String endpoint = baseURI.concat(resource);
    	System.out.println(endpoint);
    	APIResponse apiResponse = requestContext.get(endpoint,RequestOptions.create());
        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(apiResponse.ok(), true);
        
        String statusResText = apiResponse.statusText();
        System.out.println(statusResText);

        System.out.println("----print api response with plain text----");
        System.out.println(apiResponse.text());

        System.out.println("----print api json response----");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyRespose = jsonResponse.toPrettyString();
        System.out.println(jsonPrettyRespose);
        
        //Read the expected json from a static json string
        //JSONObject expectedJSONOBject = new JSONObject(JSONSchema.inputJson);
        
        //Read the expected json from a json file.
        //String strJson = JSONToString.getJSONFromFile(".\\usersSchema.json");
        
        //Read the expected json from a json URL.
        //String strJson = JSONToString.getJSONFromURL("URL");
        
        System.out.println("====================================Response JSON SCHEMA Validation has started!!!================================================");        
        JSONObject actualJSONOBject = new JSONObject(jsonResponse.toString());    
        JSONObject expectedJSONSchemaOBject = JsonSchemaValidation.readJsonSchemaFile();
        try {
        JsonSchemaValidation.JsonSchemaValidator(expectedJSONSchemaOBject, actualJSONOBject);
        }
        catch(Exception e) {
        	System.out.println("Response JSON SCHEMA Validation was unsuccessfull!!!!");
        	System.out.println("Message           : "  + e.getMessage());
        	System.out.println("Localized Message : "  + e.getLocalizedMessage());
        	System.out.println("Stack Trace       : "  + e.getStackTrace());
        	System.out.println("Cause             : "  + e.getCause());
        	System.out.println("=====================================Response JSON SCHEMA Validation has been completed!!!==========================================");
        	
        	//To fail the test:
        	Assert.assertTrue(false);
        }
        
        System.out.println("=====================================Response JSON SCHEMA Validation has been completed!!!==========================================");        
        
}


    @Test
    public void getUserAndValidateHeaderApiTest() throws IOException {
    	String baseURI= FetchProperties.fetchURI();
    	String resource = "/api/users";
    	String endpoint = baseURI.concat(resource);
    	APIResponse apiResponse = requestContext.get(endpoint);
    	int statusCode = apiResponse.status();
    	System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(apiResponse.ok(), true);
        String statusResText = apiResponse.statusText();
        System.out.println(statusResText);

        System.out.println("----print api response with plain text----");
        System.out.println(apiResponse.text());

        System.out.println("----print api json response----");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
       	String jsonPrettyRespose = jsonResponse.toPrettyString();
       	System.out.println(jsonPrettyRespose);

        System.out.println("----print api url----");
        System.out.println(apiResponse.url());

        System.out.println("====================================Response JSON SCHEMA Validation has started!!!================================================");        
        JSONObject actualJSONOBject = new JSONObject(jsonResponse.toString());    
        JSONObject expectedJSONSchemaOBject = JsonSchemaValidation.readJsonSchemaFile();
        try {
        JsonSchemaValidation.JsonSchemaValidator(expectedJSONSchemaOBject, actualJSONOBject);
        }
        catch(Exception e) {
        	System.out.println("Response JSON SCHEMA Validation was unsuccessfull!!!!");
        	System.out.println("Message           : "  + e.getMessage());
        	System.out.println("Localized Message : "  + e.getLocalizedMessage());
        	System.out.println("Stack Trace       : "  + e.getStackTrace());
        	System.out.println("Cause             : "  + e.getCause());
        	System.out.println("=====================================Response JSON SCHEMA Validation has been completed!!!==========================================");
        	
        	//To fail the test:
        	Assert.assertTrue(false);
        }
        
        System.out.println("=====================================Response JSON SCHEMA Validation has been completed!!!==========================================");        
        
        System.out.println("----print response headers----");
        Map<String, String> headersMap = apiResponse.headers();
        System.out.println(headersMap);
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");
        Assert.assertEquals(headersMap.get("vary"), "Accept-Encoding");
        
    }

    @AfterTest
    public void tearDown(){
        playwright.close();
    }

}

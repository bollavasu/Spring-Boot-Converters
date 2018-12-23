package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONObject;
import org.json.XML;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class PersonController {

	@Autowired
	private Person person;
	
	@RequestMapping("/")
	public String healthCheck() {
		xmlToJson();
		xmlFileToJsonFile();
		jsonToXml();
		jsonFileToXmlFile();
		return "Converted Successfully...";
	}
	
	public void xmlToJson(){
		int PRETTY_PRINT_INDENT_FACTOR = 4;
		
		String xmlString = 	"<Customer>" + 
				"  <name>Mary</name>" + 
				"  <age>37</age>" + 
				"  <address>" + 
				"    <street>NANTERRE CT</street>" + 
				"    <postcode>77471</postcode>" + 
				"  </address>" + 
				"</Customer>";
		
		JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);		
		System.out.println(jsonPrettyPrintString);
	}
	
	public void xmlFileToJsonFile(){
		int PRETTY_PRINT_INDENT_FACTOR = 4;
		String xmlFile = "E:\\InputFiles\\Customer.xml";
		String jsonFile = "E:\\InputFiles\\Customer.json";
		
		try{ 
			
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlFile)));
		JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
		String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			 		
		FileWriter fileWriter = new FileWriter(jsonFile);
		fileWriter.write(jsonPrettyPrintString);
		fileWriter.flush();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void jsonToXml(){
		String jsonStr = "{\"Customer\": {" +
			    "\"address\": {" +
			        "\"street\": \"NANTERRE CT\"," +
			        "\"postcode\": 77471" +
			    "}," + 
			    "\"name\": \"Mary\"," +
			    "\"age\": 37" +
			"}}";

		JSONObject json = new JSONObject(jsonStr);
		String xml = XML.toString(json);		
		System.out.println(xml);
	}
	
	public void jsonFileToXmlFile(){
		String jsonFile = "E:\\InputFiles\\Customer.json";
		String xmlFile = "E:\\InputFiles\\Customer.xml";
		
		try {
			
		String jsonStr = new String(Files.readAllBytes(Paths.get(jsonFile)));
		JSONObject json = new JSONObject(jsonStr);
		FileWriter fileWriter = new FileWriter(xmlFile);
		fileWriter.write(XML.toString(json));
		fileWriter.flush();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

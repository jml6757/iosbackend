package com.ios.REST;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.ios.Persistance.*;
import com.ios.amazonAccess.AmazonAccessor;
import com.ios.amazonAccess.TestRequestHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
@author Mohsin
*/
@Path("iosInformationService")
public class RESTHandler {
	
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    
	dataModel persistance = new dataModel();
	AmazonAccessor access = new AmazonAccessor();
	TestRequestHandler blah = new TestRequestHandler(); 
	
@GET
@Path("/GETAttributes/{i}")
@Produces(MediaType.TEXT_PLAIN)
public String getAttributes(@PathParam("i") String i){
	String[] ids = i.split("\\|");
	return "I found that you had "+ ids.length + " seperated ids";
}
@GET
@Path("/GETFavorite/{i}")
@Produces(MediaType.TEXT_PLAIN)
public String getFavorite(@PathParam("i") String i){
	String[] ids = i.split("\\|");
	System.out.println("Size: " + ids.length);
	for(int j=0; j<ids.length; j++){
		System.out.println(ids[j]);
	}
	return "I found that you had "+ ids.length + " seperated ids";
}

@GET
@Path("/GETSuggestion/{i}")
@Produces(MediaType.TEXT_XML)
public String getSuggestion(@PathParam("i") String i){
	//parse header for keywords TEMPORARY SHOULD GO TO AI WHEN DONE
	String[] ids = i.split("\\|");
 try {
	DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	Document doc = docBuilder.newDocument();
	Element rootElement = doc.createElement("Products");
	doc.appendChild(rootElement);
	Attr attr;
	
	ItemIphoneDisplay iphoneDisplay = TestRequestHandler.testKeywordSearch(ids);
	
	for (ItemIphoneDisplay.SimpleItem simpleItem : iphoneDisplay.getItemList()) {
		Element product = doc.createElement("Product");
		
		
		//product.setAttribute("Price" );
		product.setAttribute("ProductLink", simpleItem.getProductLink());
		product.setAttribute("ImageLink", simpleItem.getImageLink());
		product.setAttribute("Rating", simpleItem.getRating());
		
		/*
		System.out.println(simpleItem.getTitle());
		System.out.println(simpleItem.getProductLink());
		System.out.println(simpleItem.getImageLink());
		*/
		product.setTextContent("Blah");
		rootElement.appendChild(product);
	}
	System.out.println(doc.getNodeName());
	return doc.toString();
} catch (ParserConfigurationException pce) {
    pce.printStackTrace();
  }
return i;
}
}

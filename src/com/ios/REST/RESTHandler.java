package com.ios.REST;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.ECS.client.jax.Tracks.Disc.Track;
import com.ios.Persistance.ItemIphoneDisplay;
import com.ios.Persistance.dataModel;
import com.ios.amazonAccess.AmazonAccessor;
import com.ios.amazonAccess.TestRequestHandler;
import com.sun.jersey.spi.resource.Singleton;



/**
@author Mohsin
*/
@Path("iosInformationService")
@Singleton
public class RESTHandler {
	dataModel persistance = dataModel.getInstance();
	AmazonAccessor access = new AmazonAccessor();
	TestRequestHandler blah = new TestRequestHandler(); 

	
	//Added stuff
	//---------------------------------------------------------------------/POSTPerson-----(done) add a new person with attributes.
@POST
@Path("/POSTPerson")
@Consumes(MediaType.TEXT_PLAIN)
public Response createPerson(String info){
	
	String[] message = info.split("\\|");
	String[] keyValue;
	
	HashMap<String,Integer> attr = new HashMap<String,Integer>();
	
	for(int j=2; j<message.length; j++){
		keyValue = message[j].split("=");
		System.out.println(keyValue.length);
		attr.put(keyValue[0], Integer.parseInt(keyValue[1]) );
	}
	
	//create a person
	System.out.println(attr.toString());
	persistance.addPerson(message[0], message[1], attr);
	/*
	System.out.println(attr.toString());
	System.out.println(persistance.getPeople().get(message[0]).size());
	System.out.println(persistance.getPeople().get(message[0]).get(message[1]).getAttributes().toString());
	*/
	//getAttributes("EE7D4834-CB72-4A9C-88F5-DD9C4E95BFAF|Mohsin");
	return Response.status(201).entity("Saved").build();
	
}


//---------------------------------------------------------------------/POSTFavorite----- (done)- adding to the favorites (should also make remove from favorites)
@POST
@Path("/POSTFavorite")
@Consumes(MediaType.TEXT_PLAIN)
public Response addFavorite(String info){
	String[] message = info.split("\\|");
	//create a person
	persistance.addFavorite(message[0], message[1], message[2]);
	//add the new favorite information to the person.
	// do this if the person exists.
	return Response.status(201).entity("Saved").build();
	//if the person does not exist return a bad request or something.
	
	
}
	

//---------------------------------------------------------------------/POSTYesOrNoInfo-----
@POST
@Path("/POSTYesOrNoInfo")
@Consumes(MediaType.APPLICATION_JSON)
public Response updatePreferenceInfo(Track track){
	
	return Response.status(201).entity("Saved").build();
	
}

//---------------------------------------------------------------------/GETAttributes/{i}----- (done)
@GET
@Path("/GETAttributes/{i}")
@Produces(MediaType.TEXT_PLAIN)
public String getAttributes(@PathParam("i") String i){
	System.out.println("I got a message" + i);
	String[] ids = i.split("\\|");
	for(String str : persistance.getPeople().keySet()){
		System.out.println(str);
		for(String str2 : persistance.getPeople().get(str).keySet()){
			System.out.println(str2);
		}
		
		
	}
	System.out.println(persistance.getPerson(ids[0],ids[1]).getID());
	HashMap<String,Integer> attri =persistance.getPerson(ids[0],ids[1]).getAttributes();
	StringBuilder response = new StringBuilder();
	for(String key : attri.keySet()){
		response.append(key);
		response.append("=");
		response.append(attri.get(key));
		response.append("|");
	}
	response.deleteCharAt(response.length()-1);
	System.out.println(response.toString());
	
	return response.toString();
}

//---------------------------------------------------------------------/GETFavorite/{i}----- (done)
@GET
@Path("/GETFavorite/{i}")

@Produces(MediaType.TEXT_PLAIN)
public String getFavorite(@PathParam("i") String i){
	String[] ids = i.split("\\|");
	System.out.println("Size: " + ids.length);
	StringBuilder response = new StringBuilder();
	for(ItemIphoneDisplay.SimpleItem item :persistance.getPeople().get(ids[0]).get(ids[1]).getFavorites().getItemList()){
		response.append("ASIN="+item.getASIN());
		response.append("title="+item.getTitle());
		response.append("productLink="+item.getProductLink());
		response.append("imageLink="+item.getImageLink());
		response.append("price="+item.getPrice());
		response.append("rating="+item.getRating());
		response.append("|");
	}
	response.deleteCharAt(response.length()-1);
	System.out.println(response.toString());
	return response.toString();
}


//---------------------------------------------------------------------/GETSuggestion/{i}-----
@GET
@Path("/GETSuggestion/{i}")
@Produces(MediaType.TEXT_XML)
public String getSuggestion(@PathParam("i") String i){
	//parse header for keywords TEMPORARY SHOULD GO TO AI WHEN DONE
	String[] ids = i.split("\\|");
	
	ItemIphoneDisplay iphoneDisplay = TestRequestHandler.testKeywordSearch();
	
	for (ItemIphoneDisplay.SimpleItem simpleItem : iphoneDisplay.getItemList()) {

		//product.setAttribute("Price" );
		/*
		System.out.println(simpleItem.getTitle());
		System.out.println(simpleItem.getProductLink());
		System.out.println(simpleItem.getImageLink());
		*/
	}
	return ids[0];
}
	
}

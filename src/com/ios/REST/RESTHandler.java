package com.ios.REST;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.ios.Persistance.*;
/**
@author Mohsin
*/
@Path("iosInformationService")
public class RESTHandler {
	

	
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
@Produces(MediaType.TEXT_PLAIN)
public String getSuggestion(@PathParam("i") String i){
	String[] ids = i.split("\\|");
	
	return "I found that you had "+ ids.length + " seperated ids";
}

}

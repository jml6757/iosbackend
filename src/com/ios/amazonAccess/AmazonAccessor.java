package com.ios.amazonAccess;

import java.util.ArrayList;
import java.util.List;

import com.ECS.client.jax.*;


public class AmazonAccessor {
	
	public static Items searchItemsByKeyword(String searchKeywordTerms, ObjectFactory factory, AWSECommerceServicePortType port) {
		
		ItemSearchRequest request = factory.createItemSearchRequest();
		request.setKeywords(searchKeywordTerms);
		request.setSearchIndex("All");
			
		ItemSearchResponse response = AmazonUtils.sendSearch(request, factory, port);
		return response.getItems().get(0);
	}
	
	public static String getLargestImageURL(String ASIN, ObjectFactory factory, AWSECommerceServicePortType port) {
		
		ItemLookupRequest request = factory.createItemLookupRequest();
		request.setIdType("ASIN");
		request.getItemId().add(ASIN);
		request.getResponseGroup().add("Images");
		
		ItemLookupResponse response = AmazonUtils.sendLookup(request, factory, port);
		
		return response.getItems().get(0).getItem().get(0).getLargeImage().getURL();	
		
	}
	
	public static String getPrice(String ASIN, ObjectFactory factory, AWSECommerceServicePortType port) {
		
		return "";
	}
	
	public static String getRating(String ASIN, ObjectFactory factory, AWSECommerceServicePortType port) {
		
		return "";
	}
	
	public static Items getItemDetails(String ASIN, ObjectFactory factory, AWSECommerceServicePortType port) {
		
		return null;
	}
	
}

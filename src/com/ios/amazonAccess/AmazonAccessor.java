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
	
	public static Items detailedKeywordSearch(String keywords, ObjectFactory factory, AWSECommerceServicePortType port) {
		
		ItemSearchRequest request = factory.createItemSearchRequest();
		request.setKeywords(keywords);
		request.setSearchIndex("All");
		request.getResponseGroup().add("Offers");
		request.getResponseGroup().add("Images");
		request.getResponseGroup().add("ItemAttributes");
		request.setMerchantId("All");
			
		ItemSearchResponse response = AmazonUtils.sendSearch(request, factory, port);
		if (response != null) {
			return response.getItems().get(0);			
		} else return null;
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
		ItemLookupRequest request = factory.createItemLookupRequest();
		request.setIdType("ASIN");
		request.getItemId().add(ASIN);
		request.setMerchantId("All");
		request.getResponseGroup().add("Offers");
		
		ItemLookupResponse response = AmazonUtils.sendLookup(request, factory, port);
		Item item = response.getItems().get(0).getItem().get(0);
		
		return AmazonUtils.getLowestListedPrice(item);
	}
	
	
	public static Items getItemDetails(String ASIN, ObjectFactory factory, AWSECommerceServicePortType port) {
		ItemLookupRequest request = factory.createItemLookupRequest();
		request.setIdType("ASIN");
		request.getItemId().add(ASIN);
		request.getResponseGroup().add("Offers");
		request.getResponseGroup().add("Images");
		request.getResponseGroup().add("ItemAttributes");
		request.setMerchantId("All");
		
		ItemLookupResponse response = AmazonUtils.sendLookup(request, factory, port);
		if (response == null) {
			return null;
		}
		return response.getItems().get(0);
	}
	
	public static TopItemSet getBrowseNodeMostGifted(String browseNodeId, ObjectFactory factory, AWSECommerceServicePortType port) {
		BrowseNodeLookupRequest request = factory.createBrowseNodeLookupRequest();
		request.getBrowseNodeId().add(browseNodeId);
		request.getResponseGroup().add("MostGifted");
		
		BrowseNodeLookupResponse response = AmazonUtils.sendBrowseNodeLookup(request, factory, port);
		if (response == null) {
			return null;
		}
		BrowseNodes browseNodes = response.getBrowseNodes().get(0);
		BrowseNode browseNode = browseNodes.getBrowseNode().get(0);
		if (browseNode.getTopItemSet().size() != 0) {
			return browseNode.getTopItemSet().get(0);			
		}
		else return null;
	}
	
}

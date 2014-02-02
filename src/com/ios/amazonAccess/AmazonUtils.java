package com.ios.amazonAccess;
import java.util.List;

import org.apache.commons.logging.Log;

import com.ECS.client.jax.*;

public class AmazonUtils {
	
	public static ItemSearchResponse sendSearch(ItemSearchRequest request, ObjectFactory fac, AWSECommerceServicePortType port) {
		ItemSearch itemSearch = fac.createItemSearch();
		itemSearch.getRequest().add(request);
		
		ItemSearchResponse response = null;
		try {
			response = port.itemSearch(itemSearch);
		} catch (Exception e) {
			System.err.println("Http client was unavailable for a call - proceeding with null");
			response = null;
		}
		return response;
	}
	
	public static ItemSearchResponse sendSearch(List<ItemSearchRequest> requests, ObjectFactory fac, AWSECommerceServicePortType port) {
		ItemSearch itemSearch = fac.createItemSearch();
		for (ItemSearchRequest request : requests) {
			itemSearch.getRequest().add(request);
		}
		return port.itemSearch(itemSearch);
	}
	
	public static ItemLookupResponse sendLookup(ItemLookupRequest request, ObjectFactory fac, AWSECommerceServicePortType port) {
		ItemLookup itemLookup = fac.createItemLookup();
		itemLookup.getRequest().add(request);
		ItemLookupResponse response = null;
		try {
			response = port.itemLookup(itemLookup);
		} catch (Exception e) {
			System.err.println("Http client was unavailable for a call - proceeding with null");
			response = null;
		}
		return response;
	}
	
	public static ItemLookupResponse sendLookup(List<ItemLookupRequest> requests, ObjectFactory fac, AWSECommerceServicePortType port) {
		ItemLookup itemLookup = fac.createItemLookup();
		for (ItemLookupRequest request : requests) {
			itemLookup.getRequest().add(request);
		}
		return port.itemLookup(itemLookup);
	}
	
	public static BrowseNodeLookupResponse sendBrowseNodeLookup(BrowseNodeLookupRequest request, ObjectFactory fac, AWSECommerceServicePortType port) {
		BrowseNodeLookup browseNodeLookup = fac.createBrowseNodeLookup();
		browseNodeLookup.getRequest().add(request);
		BrowseNodeLookupResponse response = null;
		try {
			response = port.browseNodeLookup(browseNodeLookup);
		} catch (Exception e) {
			System.err.println("Http client was unavailable for a call - proceeding with null");
			response = null;
		}
		
		return response;
	}
	
	public static String getLowestListedPrice(Item item) {
		String result = "";
		if (item.getOfferSummary() != null) {
			if (item.getOfferSummary().getLowestUsedPrice() != null) {
				result = item.getOfferSummary().getLowestUsedPrice().getFormattedPrice();
			} else if (item.getOfferSummary().getLowestNewPrice() != null) {
				result = item.getOfferSummary().getLowestNewPrice().getFormattedPrice();
			}
		}
		
		return result;
	}
}

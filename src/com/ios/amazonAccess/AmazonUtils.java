package com.ios.amazonAccess;
import java.util.List;

import com.ECS.client.jax.*;

public class AmazonUtils {
	
	public static ItemSearchResponse sendSearch(ItemSearchRequest request, ObjectFactory fac, AWSECommerceServicePortType port) {
		ItemSearch itemSearch = fac.createItemSearch();
		itemSearch.getRequest().add(request);
		return port.itemSearch(itemSearch);
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
		return port.itemLookup(itemLookup);
	}
	
	public static ItemLookupResponse sendLookup(List<ItemLookupRequest> requests, ObjectFactory fac, AWSECommerceServicePortType port) {
		ItemLookup itemLookup = fac.createItemLookup();
		for (ItemLookupRequest request : requests) {
			itemLookup.getRequest().add(request);
		}
		return port.itemLookup(itemLookup);
	}
}

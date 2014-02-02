package com.ios.amazonAccess;
import com.ECS.client.jax.*;

public class ObjectFactory {
	
	private final String AWS_ACCESS_KEY_ID;
	private final String AWS_SECRET_KEY;
	private final String ASSOCIATE_TAG;
	
	public ObjectFactory(String AWS_ACCESS_KEY_ID, String AWS_SECRET_KEY, String ASSOCIATE_TAG) {
		this.AWS_ACCESS_KEY_ID = AWS_ACCESS_KEY_ID;
		this.AWS_SECRET_KEY = AWS_SECRET_KEY;
		this.ASSOCIATE_TAG = ASSOCIATE_TAG;
	}
	
	public AWSECommerceService createAWSECommerceService() {
		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver(AWS_SECRET_KEY));
		return service;
	}
	
	public AWSECommerceServicePortType createAWSECommerceServicePortType() {
		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver(AWS_SECRET_KEY));
		return service.getAWSECommerceServicePort();
	}
	
	public ItemSearchRequest createItemSearchRequest() {
		return new ItemSearchRequest();
	}
	
	public ItemSearch createItemSearch() {
		ItemSearch itemSearch = new ItemSearch();
		itemSearch.setAssociateTag(ASSOCIATE_TAG);
		itemSearch.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		return itemSearch;
	}
	
	public ItemLookupRequest createItemLookupRequest() {
		return new ItemLookupRequest();
	}
	
	public ItemLookup createItemLookup() {
		ItemLookup itemLookup = new ItemLookup();
		itemLookup.setAssociateTag(ASSOCIATE_TAG);
		itemLookup.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		return itemLookup;
	}
	
	public BrowseNodeLookupRequest createBrowseNodeLookupRequest() {
		return new BrowseNodeLookupRequest();
	}
	
	public BrowseNodeLookup createBrowseNodeLookup() {
		BrowseNodeLookup browseNodeLookup = new BrowseNodeLookup();
		browseNodeLookup.setAssociateTag(ASSOCIATE_TAG);
		browseNodeLookup.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		return browseNodeLookup;
	}
}

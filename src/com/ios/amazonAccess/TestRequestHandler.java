package com.ios.amazonAccess;
import java.util.ArrayList;
import java.util.List;

import com.ECS.client.jax.*;
import com.ECS.client.jax.TopItemSet.TopItem;
import com.ios.Persistance.ItemIphoneDisplay;

public class TestRequestHandler {
	
	private static final String AWS_ACCESS_KEY_ID = "AKIAIUHBQUPB24XSBH2Q";
	
	private static final String AWS_SECRET_KEY = "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";
	
	private static final String ASSOCIATE_TAG = "intel004-20";
	
	public static ItemIphoneDisplay testKeywordSearch() {
		ItemIphoneDisplay result = new ItemIphoneDisplay();
		List<String> keywordTerms = new ArrayList<String>();
		keywordTerms.add("soccer ball");
		keywordTerms.add("dress shirt");
		keywordTerms.add("Harry Potter");
		
		ObjectFactory factory = new ObjectFactory(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY, ASSOCIATE_TAG);
		AWSECommerceServicePortType port = factory.createAWSECommerceServicePortType();
		
		for (String term : keywordTerms) {
			Items searchResults = AmazonAccessor.searchItemsByKeyword(term, factory, port);
			ItemIphoneDisplay.SimpleItem simpleItem = result.createSimpleItemInstance();
			
			if (searchResults.getItem().size() != 0) {
				Item currItem = searchResults.getItem().get(0);
				
				simpleItem.setTitle(currItem.getItemAttributes().getTitle());
				simpleItem.setProductLink(currItem.getDetailPageURL());
				
				String imageUrl = AmazonAccessor.getLargestImageURL(currItem.getASIN(), factory, port);
				simpleItem.setImageLink(imageUrl);
				
				result.getItemList().add(simpleItem);				
			}
			
		}
		
		return result;
	}
	
	public static ItemIphoneDisplay keywordSearchRequestHandler(List<String> keywords) {
		ItemIphoneDisplay result = new ItemIphoneDisplay();
		
		
		
		ObjectFactory factory = new ObjectFactory(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY, ASSOCIATE_TAG);
		AWSECommerceServicePortType port = factory.createAWSECommerceServicePortType();
		
		for (String term : keywords) {
			String origTerm = term;
			term.replaceAll(",", " ");
			
			Items searchResults = AmazonAccessor.detailedKeywordSearch(term, factory, port);
			ItemIphoneDisplay.SimpleItem simpleItem = result.createSimpleItemInstance();
			
			
			if (searchResults != null && searchResults.getItem().size() != 0) {
				Item currItem = searchResults.getItem().get(0);
				
				simpleItem.setASIN(currItem.getASIN());
				simpleItem.setTitle(currItem.getItemAttributes().getTitle());
				simpleItem.setProductLink(currItem.getDetailPageURL());
				simpleItem.setImageLink(currItem.getLargeImage().getURL());
				simpleItem.setPrice(AmazonUtils.getLowestListedPrice(currItem));
				simpleItem.setSearchTerms(origTerm);
				
				result.getItemList().add(simpleItem);				
			}
			
		}
		
		return result;
	}
	
	public static ItemIphoneDisplay browseNodeMostGiftedItems(List<String> browseNodeIds) {
		ItemIphoneDisplay result = new ItemIphoneDisplay();
		
		ObjectFactory factory = new ObjectFactory(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY, ASSOCIATE_TAG);
		AWSECommerceServicePortType port = factory.createAWSECommerceServicePortType();
		
		for (String browseNodeId : browseNodeIds) {
			TopItemSet topItems = AmazonAccessor.getBrowseNodeMostGifted(browseNodeId, factory, port);
			ItemIphoneDisplay.SimpleItem simpleItem = result.createSimpleItemInstance();
			
			if (topItems != null && topItems.getTopItem().size() != 0) {
				TopItem topItem = topItems.getTopItem().get(0);
				
				simpleItem.setASIN(topItem.getASIN());
				simpleItem.setTitle(topItem.getTitle());
				simpleItem.setProductLink(topItem.getDetailPageURL());
				
				//Get more details about the item from the ASIN
				Items asinLookupItems = AmazonAccessor.getItemDetails(topItem.getASIN(), factory, port);
				if (asinLookupItems != null) {
					Item topItemDetail = asinLookupItems.getItem().get(0);
					
					simpleItem.setImageLink(topItemDetail.getLargeImage().getURL());
					simpleItem.setPrice(AmazonUtils.getLowestListedPrice(topItemDetail));
					
					result.getItemList().add(simpleItem);					
				}
				
			}
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		
		System.out.println("**********************************");
		List<String> keywordList = new ArrayList<String>();
		keywordList.add("HarryPotter");
		keywordList.add("Lego");
		keywordList.add("dildo");
		keywordList.add("Plush toy");
		
		ItemIphoneDisplay iphoneDisplay = keywordSearchRequestHandler(keywordList);
		
		for (ItemIphoneDisplay.SimpleItem simpleItem : iphoneDisplay.getItemList()) {
			System.out.println(simpleItem.getASIN() + " - " + simpleItem.getTitle());
			System.out.println(simpleItem.getPrice());
			System.out.println(simpleItem.getProductLink());
			System.out.println(simpleItem.getImageLink());
		}
		
		System.out.println("**********************************");
		List<String> browseNodeList = new ArrayList<String>();
		browseNodeList.add("172282");
		browseNodeList.add("3375251");
		
		ItemIphoneDisplay browseNodeIphoneDisplay = browseNodeMostGiftedItems(browseNodeList);
		
		for (ItemIphoneDisplay.SimpleItem simpleItem : browseNodeIphoneDisplay.getItemList()) {
			System.out.println(simpleItem.getASIN() + " - " + simpleItem.getTitle());
			System.out.println(simpleItem.getPrice());
			System.out.println(simpleItem.getProductLink());
			System.out.println(simpleItem.getImageLink());
		}
		
	}
	
}

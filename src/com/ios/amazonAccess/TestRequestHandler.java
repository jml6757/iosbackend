package com.ios.amazonAccess;
import java.util.ArrayList;
import java.util.List;

import com.ECS.client.jax.*;
import com.ios.Persistance.ItemIphoneDisplay;

public class TestRequestHandler {
	
	private static final String AWS_ACCESS_KEY_ID = "AKIAIUHBQUPB24XSBH2Q";
	
	private static final String AWS_SECRET_KEY = "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";
	
	private static final String ASSOCIATE_TAG = "intel004-20";
	
	public static ItemIphoneDisplay testKeywordSearch(String[] keywords) {
		ItemIphoneDisplay result = new ItemIphoneDisplay();
		List<String> keywordTerms = new ArrayList<String>();
		for(String keyword : keywords){
			keywordTerms.add(keyword);
		}
		/*
		keywordTerms.add("soccer ball");
		keywordTerms.add("dress shirt");
		keywordTerms.add("Harry Potter");
		*/
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
	/*
	public static void main(String args[]) {
		System.out.println("**********************************");
		String[] keywords = {"book","lamp"};
		ItemIphoneDisplay iphoneDisplay = testKeywordSearch(keywords);
		
		for (ItemIphoneDisplay.SimpleItem simpleItem : iphoneDisplay.getItemList()) {
			System.out.println(simpleItem.getTitle());
			System.out.println(simpleItem.getProductLink());
			System.out.println(simpleItem.getImageLink());
		}
	}
	*/
	
}

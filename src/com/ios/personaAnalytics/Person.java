package com.ios.personaAnalytics;

import com.ios.Persistance.ItemIphoneDisplay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4160402205183963411L;
	private String ID;
	private ItemIphoneDisplay favorites;
	private Queue<ItemIphoneDisplay> pendingSuggestions;
	private HashMap<String, ItemIphoneDisplay.SimpleItem> suggestionList;
	private HashMap<String,Integer> attributes;
	private KeywordMap keywordMap;
	private Random randGen;
		
	
	public Person(String ID, HashMap<String, Integer> attributes){
		this.ID= ID;
		pendingSuggestions = new LinkedList<ItemIphoneDisplay>();
		suggestionList = new HashMap<String,ItemIphoneDisplay.SimpleItem>();
		favorites = new ItemIphoneDisplay();
		randGen = new Random();
		PersonHelper.fillRemainingAttributes(attributes);
		this.attributes = attributes;
		keywordMap = new KeywordMap();
		PersonHelper.updateKeywordMapFromAttributes(keywordMap, attributes);
	}
	
	public void addFavorite(String ASIN){
		if(!favorites.getItemList().contains(getSuggestedItem(ASIN)))
			favorites.getItemList().add((getSuggestedItem(ASIN)));
	}

	public void removeFavorite(String ASIN){
		for(int i=0; i<favorites.getItemList().size(); i++){
			System.out.println(favorites.getItemList().get(i).getASIN());
			System.out.println(ASIN);
			if(favorites.getItemList().get(i).getASIN().trim().equals(ASIN.trim())){
				System.out.println("Found something same " + ASIN);
				favorites.getItemList().remove(i);
				break;
			}
		}
		
	}
	public  ItemIphoneDisplay.SimpleItem getSuggestedItem(String ASIN){
		return suggestionList.get(ASIN);
	}
	public void setFavorites(ItemIphoneDisplay favorites) {
		this.favorites = favorites;
	}
	public String getID(){
		return ID;
	}
	
	public HashMap<String, ItemIphoneDisplay.SimpleItem> getSuggestionList() {
		return suggestionList;
	}

	public ItemIphoneDisplay getPendingSuggestions() {
		//should add to suggestionList
		ItemIphoneDisplay tmp = pendingSuggestions.remove();
		for(ItemIphoneDisplay.SimpleItem item : tmp.getItemList()){
			this.suggestionList.put(item.getASIN(), item);	
		}
		return tmp;
	}

	public void addPendingSuggestions(ItemIphoneDisplay pendingSuggestion){
		this.pendingSuggestions.add(pendingSuggestion);
	}
	public void setPendingSuggestions(Queue<ItemIphoneDisplay> pendingSuggestions) {
		this.pendingSuggestions = pendingSuggestions;
	}

	
	
	public void setSuggestionList(
			HashMap<String, ItemIphoneDisplay.SimpleItem> suggestionList) {
		this.suggestionList = suggestionList;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	
	public HashMap<String,Integer> getAttributes(){
		return attributes;
	}
	/*
	 * Extremely intesive call - only call from a background thread when other computation is not going on. Calling this can
	 * knock out the amazon API server pretty easily, which can kind of fuck with other stuff.
	 */
	/*
	public void populatePendingSuggestionsWithMostGiftedItems() {
		List<String> browseNodeIds = PersonHelper.getRelaventBrowseNodeIDs(attributes);
		pendingSuggestions.add(com.ios.amazonAccess.TestRequestHandler.browseNodeMostGiftedItems(browseNodeIds));
	}
	*/
	
	public void updateKeywords(String ASIN, boolean result) {
		int modifier = 1;
		if (!result) {
			modifier-=2;
		}
		System.out.println("modifier is : "+ modifier);
		ItemIphoneDisplay.SimpleItem simpleItem = suggestionList.get(ASIN);
		if (simpleItem != null) {
			String[] keywords = simpleItem.getSearchTerms().split(",");
			for (String keyword : keywords) {
				keywordMap.modifyWithSort(keyword, modifier);
			}
		}
	}
	public ItemIphoneDisplay getFavorites(){
		return favorites;
	}

	public void setAttributes(HashMap<String,Integer> attr){
		attributes = attr;
		System.out.println(attributes.toString());
	}
	
	
	public void doItemSearch()  {
		List<String> searches = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			String searchTerm = "";
			searchTerm += keywordMap.get(randGen.nextInt(10)) + " ";
			searchTerm += keywordMap.get(randGen.nextInt(10));			
			searches.add(searchTerm);
			System.out.println(searchTerm);
		}
		pendingSuggestions.add(com.ios.amazonAccess.TestRequestHandler.keywordSearchRequestHandler(searches));
	}

	public ItemIphoneDisplay getNewSuggestion(){
		ItemIphoneDisplay itemlist =  pendingSuggestions.poll();
		for(ItemIphoneDisplay.SimpleItem item : itemlist.getItemList()){
			suggestionList.put(item.getASIN(), item);
		}
		return itemlist;
		
	}
	
	public static void main(String[] args) {
		Person testPerson = new Person("myID", createSampleAttributes());
		testPerson.keywordMap.modifyWithSort("kanye west", 6);
		testPerson.doItemSearch();
		System.out.println();
		System.out.println(testPerson.keywordMap);
		System.out.println();
		ItemIphoneDisplay suggestions = testPerson.getNewSuggestion();
		//suggestions = testPerson.getNewSuggestion();
		for (ItemIphoneDisplay.SimpleItem simpleItem : suggestions.getItemList()) {
			System.out.println(simpleItem.getASIN() + " - " + simpleItem.getTitle());
			System.out.println(simpleItem.getPrice());
			System.out.println(simpleItem.getProductLink());
			System.out.println(simpleItem.getImageLink());
			System.out.println();
			System.out.println("******************************************************");
			System.out.println();
		}
	}
	
	private static HashMap<String, Integer> createSampleAttributes() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("electronics", 0);
		map.put("reading", 0);
		map.put("travelling", 0);
		map.put("sports", 0);
		map.put("videogames", 0);
		map.put("movies", 0);
		map.put("music", 0);
		map.put("fashion", 1);
		map.put("cooking", 1);
		map.put("art", 1);
		map.put("toys", 0);
		
		
		return map;
	}
}

package com.ios.personaAnalytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonHelper {
	
	public static void fillRemainingAttributes(HashMap<String, Integer> attributes) {
		if (attributes.get("electronics") == null) {
			attributes.put("electronics", 0);
		}
		if (attributes.get("reading") == null) {
			attributes.put("reading", 0);
		}
		if (attributes.get("travelling") == null) {
			attributes.put("travelling", 0);
		}
		if (attributes.get("sports") == null) {
			attributes.put("sports", 0);
		}
		if (attributes.get("videogames") == null) {
			attributes.put("videogames", 0);
		}
		if (attributes.get("movies") == null) {
			attributes.put("movies", 0);
		}
		if (attributes.get("music") == null) {
			attributes.put("music", 0);
		}
		if (attributes.get("music") == null) {
			attributes.put("music", 0);
		}
		if (attributes.get("fashion") == null) {
			attributes.put("fashion", 0);
		}
		if (attributes.get("cooking") == null) {
			attributes.put("cooking", 0);
		}
		if (attributes.get("art") == null) {
			attributes.put("art", 0);
		}
		if (attributes.get("toys") == null) {
			attributes.put("toys", 0);
		}
	}

	public static void updateKeywordMapFromAttributes(KeywordMap keywordMap, HashMap<String, Integer> attributes) {
		if (attributes.get("electronics") != 0) {
			int val = attributes.get("electronics");
			keywordMap.modifyWithoutSort("tablet", val);
			keywordMap.modifyWithoutSort("computer", val);
			keywordMap.modifyWithoutSort("phone", val);
			keywordMap.modifyWithoutSort("camera", val);
			keywordMap.modifyWithoutSort("home audio", val);
			keywordMap.modifyWithoutSort("television", val);
		}
		if (attributes.get("reading") != 0) {
			int val = attributes.get("reading");
			keywordMap.modifyWithoutSort("harry potter", val);
			keywordMap.modifyWithoutSort("lord of the rings", val);
			keywordMap.modifyWithoutSort("magazine", val);
			keywordMap.modifyWithoutSort("game of thrones", val);
			keywordMap.modifyWithoutSort("fiction", val);
			keywordMap.modifyWithoutSort("novel", val);
		}
		if (attributes.get("travelling") != 0) {
			int val = attributes.get("travelling");
			keywordMap.modifyWithoutSort("travel", val);
			keywordMap.modifyWithoutSort("bag", val);
			keywordMap.modifyWithoutSort("backpack", val);
			keywordMap.modifyWithoutSort("luggage", val);
			keywordMap.modifyWithoutSort("europe", val);
			keywordMap.modifyWithoutSort("adventure", val);
		}
		if (attributes.get("sports") != 0) {
			int val = attributes.get("sports");
			keywordMap.modifyWithoutSort("hiking", val);
			keywordMap.modifyWithoutSort("basketball", val);
			keywordMap.modifyWithoutSort("sports", val);
			keywordMap.modifyWithoutSort("football", val);
			keywordMap.modifyWithoutSort("outdoor", val);
			keywordMap.modifyWithoutSort("fitness", val);
		}
		if (attributes.get("videogames") != 0) {
			int val = attributes.get("videogames");
			keywordMap.modifyWithoutSort("xbox", val);
			keywordMap.modifyWithoutSort("sony", val);
			keywordMap.modifyWithoutSort("playstation", val);
			keywordMap.modifyWithoutSort("nintendo", val);
			keywordMap.modifyWithoutSort("controller", val);
			keywordMap.modifyWithoutSort("microsoft", val);
		}
		if (attributes.get("movies") != 0) {
			int val = attributes.get("movies");
			keywordMap.modifyWithoutSort("movie tickets", val);
			keywordMap.modifyWithoutSort("theater", val);
			keywordMap.modifyWithoutSort("autograph", val);
			keywordMap.modifyWithoutSort("dvd bluray", val);
			keywordMap.modifyWithoutSort("collection", val);
			keywordMap.modifyWithoutSort("poster", val);
		}
		if (attributes.get("music") != 0) {
			int val = attributes.get("music");
			keywordMap.modifyWithoutSort("rock music", val);
			keywordMap.modifyWithoutSort("classical music", val);
			keywordMap.modifyWithoutSort("kanye west", val);
			keywordMap.modifyWithoutSort("jazz", val);
			keywordMap.modifyWithoutSort("hip hop", val);
			keywordMap.modifyWithoutSort("soundtrack", val);
		}
		if (attributes.get("fashion") != 0) {
			int val = attributes.get("fashion");
			keywordMap.modifyWithoutSort("shirts", val);
			keywordMap.modifyWithoutSort("shoes", val);
			keywordMap.modifyWithoutSort("dresses", val);
			keywordMap.modifyWithoutSort("makeup", val);
			keywordMap.modifyWithoutSort("accessories", val);
			keywordMap.modifyWithoutSort("tie", val);
		}
		if (attributes.get("cooking") != 0) {
			int val = attributes.get("cooking");
			keywordMap.modifyWithoutSort("cooking utensils", val);
			keywordMap.modifyWithoutSort("spices", val);
			keywordMap.modifyWithoutSort("oven", val);
			keywordMap.modifyWithoutSort("cookbooks", val);
			keywordMap.modifyWithoutSort("candy", val);
			keywordMap.modifyWithoutSort("recipe", val);
		}
		if (attributes.get("art") != 0) {
			int val = attributes.get("art");
			keywordMap.modifyWithoutSort("paint", val);
			keywordMap.modifyWithoutSort("colors", val);
			keywordMap.modifyWithoutSort("painting", val);
			keywordMap.modifyWithoutSort("art", val);
			keywordMap.modifyWithoutSort("sketch", val);
			keywordMap.modifyWithoutSort("statue", val);
		}
		if (attributes.get("toys") != 0) {
			int val = attributes.get("toys");
			keywordMap.modifyWithoutSort("legos", val);
			keywordMap.modifyWithoutSort("toy", val);
			keywordMap.modifyWithoutSort("action figures", val);
			keywordMap.modifyWithoutSort("doll", val);
			keywordMap.modifyWithoutSort("puzzle", val);
			keywordMap.modifyWithoutSort("game", val);
		}

		keywordMap.sort();
	}
	
	public static List<String> getRelaventBrowseNodeIDs(HashMap<String, Integer> attributes) {
		List<String> IDs = new ArrayList<String>();
		if (attributes.get("electronics") >= 5) {
			IDs.add(BrowseNodeIDs.ELECTRONICS.getBroseNodeID());
		}
		if (attributes.get("reading") >= 5) {
			IDs.add(BrowseNodeIDs.BOOKS.getBroseNodeID());
		}
		if (attributes.get("travelling") >= 5) {
			IDs.add(BrowseNodeIDs.TRAVEL_ACCESSORIES.getBroseNodeID());
		}
		if (attributes.get("sports") >= 5) {
			IDs.add(BrowseNodeIDs.SPORTING_GOODS.getBroseNodeID());
		}
		if (attributes.get("videogames") >= 5) {
			IDs.add(BrowseNodeIDs.VIDEO_GAMES.getBroseNodeID());
		}
		if (attributes.get("movies") >= 5) {
			IDs.add(BrowseNodeIDs.MOVIES.getBroseNodeID());
		}
		if (attributes.get("music") >= 5) {
			IDs.add(BrowseNodeIDs.MUSIC.getBroseNodeID());
		}
		if (attributes.get("fashion") >= 5) {
			IDs.add(BrowseNodeIDs.APPAREL.getBroseNodeID());
		}
		if (attributes.get("cooking") >= 5) {
			IDs.add(BrowseNodeIDs.KITCHEN.getBroseNodeID());
		}
		if (attributes.get("art") >= 5) {
			IDs.add(BrowseNodeIDs.ARTS_AND_CRAFTS.getBroseNodeID());
		}
		if (attributes.get("toys") >= 5) {
			IDs.add(BrowseNodeIDs.TOYS.getBroseNodeID());
		}

		
		return IDs;		
	}
	
}

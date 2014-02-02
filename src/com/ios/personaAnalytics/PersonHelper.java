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
			keywordMap.modifyWithoutSort("kindle", 5);
			keywordMap.modifyWithoutSort("computer", 5);
			keywordMap.modifyWithoutSort("phone", 5);
		}
		if (attributes.get("reading") != 0) {
			keywordMap.modifyWithoutSort("Harry Potter", 5);
			keywordMap.modifyWithoutSort("lord of the rings", 5);
			keywordMap.modifyWithoutSort("magazine", 5);
		}
		if (attributes.get("travelling") != 0) {
			keywordMap.modifyWithoutSort("travel", 5);
			keywordMap.modifyWithoutSort("bag", 5);
			keywordMap.modifyWithoutSort("backpack", 5);
		}
		if (attributes.get("sports") != 0) {
			keywordMap.modifyWithoutSort("hiking", 5);
			keywordMap.modifyWithoutSort("basketball", 5);
			keywordMap.modifyWithoutSort("sports", 5);
		}
		if (attributes.get("video games") != 0) {
			keywordMap.modifyWithoutSort("xbox", 5);
			keywordMap.modifyWithoutSort("playstation", 5);
		}
		if (attributes.get("movies") != 0) {
			keywordMap.modifyWithoutSort("movie tickets", 5);
			keywordMap.modifyWithoutSort("theater", 5);
			keywordMap.modifyWithoutSort("autograph", 5);
		}
		if (attributes.get("music") != 0) {
			keywordMap.modifyWithoutSort("kanye west", 5);
			keywordMap.modifyWithoutSort("the beatles", 5);
			keywordMap.modifyWithoutSort("Justin Beiber", 5);
		}
		if (attributes.get("fashion") != 0) {
			keywordMap.modifyWithoutSort("shirts", 5);
			keywordMap.modifyWithoutSort("shoes", 5);
			keywordMap.modifyWithoutSort("dresses", 5);
		}
		if (attributes.get("cooking") != 0) {
			keywordMap.modifyWithoutSort("cooking utensils", 5);
			keywordMap.modifyWithoutSort("spices", 5);
			keywordMap.modifyWithoutSort("oven", 5);
		}
		if (attributes.get("art") != 0) {
			keywordMap.modifyWithoutSort("paint", 5);
			keywordMap.modifyWithoutSort("colors", 5);
			keywordMap.modifyWithoutSort("poster", 5);
		}
		if (attributes.get("toys") != 0) {
			keywordMap.modifyWithoutSort("legos", 5);
			keywordMap.modifyWithoutSort("toy", 5);
			keywordMap.modifyWithoutSort("action figures", 5);
		}

		keywordMap.sort();
	}
	
	public static List<String> getRelaventBrowseNodeIDs(HashMap<String, Integer> attributes) {
		List<String> IDs = new ArrayList<String>();
		if (attributes.get("electronics") != 0) {
			IDs.add(BrowseNodeIDs.ELECTRONICS.getBroseNodeID());
		}
		if (attributes.get("reading") != 0) {
			IDs.add(BrowseNodeIDs.BOOKS.getBroseNodeID());
		}
		if (attributes.get("travelling") != 0) {
			IDs.add(BrowseNodeIDs.TRAVEL_ACCESSORIES.getBroseNodeID());
		}
		if (attributes.get("sports") != 0) {
			IDs.add(BrowseNodeIDs.SPORTING_GOODS.getBroseNodeID());
		}
		if (attributes.get("video games") != 0) {
			IDs.add(BrowseNodeIDs.VIDEO_GAMES.getBroseNodeID());
		}
		if (attributes.get("movies") != 0) {
			IDs.add(BrowseNodeIDs.MOVIES.getBroseNodeID());
		}
		if (attributes.get("music") != 0) {
			IDs.add(BrowseNodeIDs.MUSIC.getBroseNodeID());
		}
		if (attributes.get("fashion") != 0) {
			IDs.add(BrowseNodeIDs.APPAREL.getBroseNodeID());
		}
		if (attributes.get("cooking") != 0) {
			IDs.add(BrowseNodeIDs.KITCHEN.getBroseNodeID());
		}
		if (attributes.get("art") != 0) {
			IDs.add(BrowseNodeIDs.ARTS_AND_CRAFTS.getBroseNodeID());
		}
		if (attributes.get("toys") != 0) {
			IDs.add(BrowseNodeIDs.TOYS.getBroseNodeID());
		}

		
		return IDs;		
	}
	
}

package com.ios.personaAnalytics;

public enum BrowseNodeIDs {
	APPAREL("1036592"),
	ARTS_AND_CRAFTS("2617941011"),
	BEAUTY("11055981"),
	BOOKS("3103"),
	ELECTRONICS("172282"),
	KITCHEN("284507"),
	MUSIC("37"),
	SPORTING_GOODS("3375251"),
	VIDEO_GAMES("468642"),
	TRAVEL_ACCESSORIES("15743971"),
	MOVIES("2625373011"),
	TOYS("165793011");
	
	
	private String ID;
	 
	private BrowseNodeIDs(String s) {
		ID = s;
	}
 
	public String getBroseNodeID() {
		return ID;
	}
	
}

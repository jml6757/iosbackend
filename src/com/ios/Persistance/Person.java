package com.ios.Persistance;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	
	String name;
	String ID;
	ArrayList<ItemIphoneDisplay> favorites;
	ArrayList<ItemIphoneDisplay> suggestionList;
	HashMap<String,Integer> attributes;
		
	public Person(String ID){
		name = ID;
		favorites = new ArrayList<ItemIphoneDisplay>();
		attributes = new HashMap<String,Integer>();
	}

}

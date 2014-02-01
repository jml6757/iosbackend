package com.ios.Persistance;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	
	String name;
	String ID;
	ArrayList<amazonProduct> favorites;
	ArrayList<amazonProduct> suggestionList;
	HashMap<String,Integer> attributes;
		
	public Person(String ID){
		name = ID;
		favorites = new ArrayList<amazonProduct>();
		attributes = new HashMap<String,Integer>();
	}

}

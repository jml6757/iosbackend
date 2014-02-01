package com.ios.Persistance;

import java.io.Serializable;
import java.util.HashMap;

public class dataModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,HashMap<String,Person>> people;
	
	public dataModel(){
		people = new HashMap<String,HashMap<String,Person>>();
	}
	
	public void addPerson(String AppID, String ID, HashMap<String,Integer> attr){
		
		Person guy = new Person(ID);
		if(people.get(AppID) == null){
			people.put(AppID, new HashMap<String,Person> ());
			people.get(AppID).put(ID, guy);
		}
		else{
			if(people.get(AppID).get(ID) == null){
				people.get(AppID).put(ID, guy);
			}
		}
		setAttributes(attr);
		
		
		
	}
	public void setAttributes(HashMap<String,Integer> attr){
		
	}
	
	public void getPerson(){
		
	}
	
}

package com.ios.Persistance;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;

import com.ios.personaAnalytics.Person;

public class dataModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	HashMap<String,HashMap<String,Person>> people;
	
	private static dataModel instance = null;
	
	protected dataModel(){
		//deserialize stuff and put ot to the map
		
		try {
	        FileInputStream file = new FileInputStream("persistance.sp");
	        ObjectInputStream in = new ObjectInputStream(file);
	        dataModel persistance = (dataModel) in.readObject();
	        System.out.println("I got it homie?");
	        in.close();
	        people = persistance.getPeople();
	        file.close();
	    } catch (Exception ex) {
	        System.err.println("error" + ex);
	        people = new HashMap<String,HashMap<String,Person>> ();
	    }
		

	}
	public static dataModel getInstance(){
		
		if(instance == null){
			instance = new dataModel();
		}
		return instance;
	}
	
	public HashMap<String,HashMap<String,Person>> getPeople(){
		return people;
	}
	
	public void addPerson(String AppID, String ID, HashMap<String,Integer> attr){
		
		Person guy = new Person(ID,attr);
		if(people.get(AppID) == null){
			people.put(AppID, new HashMap<String,Person> ());
			people.get(AppID).put(ID, guy);
		}
		else{
			if(people.get(AppID).get(ID) == null){
				people.get(AppID).put(ID, guy);
			}
		}
		
		setAttributes(attr, people.get(AppID).get(ID));
	}

	public void setAttributes(HashMap<String,Integer> attr,Person guy ){
		guy.setAttributes(attr);
	
	}
	
	public Person getPerson(String AppID, String id){
		for(String str : people.keySet()){
			System.out.println(str);
			for(String str2 : people.get(str).keySet()){
				System.out.println(str2);
			}
		}
		System.out.println("implies we got nothing");
		System.out.println(people.get(AppID).toString());
		System.out.println(people.get(AppID).get(id).toString());
		return people.get(AppID).get(id);
	}
	
	public void addFavorite(String appID, String id, String ASIN ){
		
		people.get(appID).get(id).addFavorite(ASIN);
		
	}
	
	
}

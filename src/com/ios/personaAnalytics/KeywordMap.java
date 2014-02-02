package com.ios.personaAnalytics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeywordMap implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -618445864684191777L;

	private static String keywords = "tablet,computer,phone,camera,home audio,television,harry potter,"
			+ "lord of the rings,magazine,game of thrones,fiction,novel,travel,bag,backpack,luggage,europe,"
			+ "adventure,hiking,basketball,sports,football,outdoor,fitness,xbox,sony,playstation,nintendo,"
			+ "controller,microsoft,movie tickets,theater,autograph,dvd bluray,collection,poster,rock music,"
			+ "classical music,kanye west,jazz,hip hop,soundtrack,shirts,shoes,dresses,makeup,accessories,"
			+ "tie,cooking utensils,spices,oven,cookbooks,candy,recipe,paint,colors,painting,art,sketch,statue,"
			+ "legos,toy,action figures,doll,puzzle,game";
	
	private List<KeyValue> keyMap;
	
	/*
	 * Takes in a comma-separated string of keywords
	 */
	public KeywordMap(String keywords) {
		
		keyMap = new ArrayList<KeyValue>();
		String[] keywordArray = keywords.split(",");
		for (String keyword : keywordArray) {
			keyMap.add(new KeyValue(0, keyword));
		}
	}
	
	public KeywordMap() {
		this(keywords);
	}
	
	public void sort() {
		Collections.sort(keyMap);
	}
	
	public String get(int i) {
		return keyMap.get(i).getValue();
	}
	
	public String toString() {
		String result = "";
		for (KeyValue kv : keyMap) {
			result += kv.getKey() + " - " + kv.getValue() + ", \n";
		}
		return result;
	}
	
	public void modifyWithoutSort(String keyword, int amount) {
		for (KeyValue kv : keyMap) {
			if (kv.getValue().equals(keyword)) {
				kv.modifyKey(amount);
			}
		}
	}
	
	public void modifyWithSort(String keyword, int amount) {
		int index = -1;
		boolean stillLooking = true; 
		while (stillLooking) {
			index++;
			KeyValue kv = keyMap.get(index);
			if (kv.getValue().equals(keyword)) {
				stillLooking = false;
			} else if (index == keyMap.size() - 1) {
				return;
			}
		}
		
		keyMap.get(index).modifyKey(amount);
		int newKey = keyMap.get(index).getKey();
		if (amount > 0) {
			for (int i = index - 1; i >= 0; i--) {
				if (keyMap.get(i).getKey() >= newKey) {
					return;
				}
				//Switch element i with element i+1 (the changed one)
				keyMap.get(i + 1).setKey(keyMap.get(i).getKey());
				keyMap.get(i + 1).setValue(keyMap.get(i).getValue());
				keyMap.get(i).setKey(newKey);
				keyMap.get(i).setValue(keyword);
			}
		} else {
			for (int i = index + 1; i < keyMap.size(); i++) {
				if (keyMap.get(i).getKey() <= newKey) {
					return;
				}
				//Switch element i with element i-1 (the changed one)
				keyMap.get(i - 1).setKey(keyMap.get(i).getKey());
				keyMap.get(i - 1).setValue(keyMap.get(i).getValue());
				keyMap.get(i).setKey(newKey);
				keyMap.get(i).setValue(keyword);
			}
		}
	}
	
	private class KeyValue implements Comparable<KeyValue>,Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2216179007691372898L;
		private int key;
		private String value;
		
		public KeyValue(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public void modifyKey(int amount) {
			this.key += amount;
		}
		
		public int getKey() {
			return key;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String val) {
			this.value = val;
		}
		
		public void setKey(int val) {
			this.key = val;
		}

		@Override
		public int compareTo(KeyValue other) {
			return (-1)*((Integer)this.getKey()).compareTo(other.getKey());
		}
	}	
}

package com.ios.Persistance;
import java.util.ArrayList;
import java.util.List;


public class ItemIphoneDisplay {
	
	public class SimpleItem {
		String title;
		String productLink;
		String imageLink;
		String price;
		String rating;
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String val) {
			this.title = val;
		}
		
		public String getProductLink() {
			return productLink;
		}
		
		public void setProductLink(String val) {
			this.productLink = val;
		}
		
		public String getImageLink() {
			return imageLink;
		}
		
		public void setImageLink(String val) {
			this.imageLink = val;
		}
		
		public String getPrice() {
			return price;
		}
		
		public void setPrice(String val) {
			this.price = val;
		}
		
		public String getRating() {
			return rating;
		}
		
		public void setRating(String val) {
			this.rating = val;
		}
		
	}
	
	private List<SimpleItem> itemList;
	
	public ItemIphoneDisplay() {
		itemList = new ArrayList<SimpleItem>();
	}
	
	public List<SimpleItem> getItemList() {
		return itemList;
	}
	
	public SimpleItem createSimpleItemInstance() {
		return new SimpleItem();
	}
	
}

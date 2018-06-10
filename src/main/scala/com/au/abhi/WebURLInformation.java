package com.au.abhi;

public class WebURLInformation {
	private String searchString;
	private String URL;
	private String country;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	
	public String getSearchString() {
		return searchString;
	}
	public String getURL() {
		return URL;
	}
}

package net.randomjoe.materialquotecloud;

import java.util.ArrayList;

public class QuoteBean {

	public QuoteBean(Long id, String name, String urlImageString, ArrayList<String> quotesArray) {
		this.id = id;
		this.name = name;
		this.urlImageString = urlImageString;
		this.quotesArray = quotesArray;
	}

	public QuoteBean() {

	}

	private Long id;

	private String name;
	private String urlImageString;

	private ArrayList<String> quotesArray;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlImageString() {
		return urlImageString;
	}

	public void setUrlImageString(String urlImageString) {
		this.urlImageString = urlImageString;
	}

	public ArrayList<String> getQuotesArray() {
		return quotesArray;
	}

	public void setQuotesArray(ArrayList<String> quotesArray) {
		this.quotesArray = quotesArray;
	}
}

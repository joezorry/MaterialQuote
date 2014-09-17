package net.randomjoe.materialquote;

import java.io.Serializable;
import java.util.ArrayList;

public class QuotesPerson implements Serializable {

	long mId;
	String mName;
	String mImageUrl;
	ArrayList<String> mQuotes;

	public QuotesPerson(long id, String name, String imageUrl, ArrayList<String> quotes) {
		mId = id;
		mName = name;
		mImageUrl = imageUrl;
		mQuotes = quotes;
	}

	public long getId() {
		return mId;
	}

	public void setId(long id) {
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getImageUrl() {
		return mImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		mImageUrl = imageUrl;
	}

	public ArrayList<String> getQuotes() {
		return mQuotes;
	}

	public void setQuotes(ArrayList<String> quotes) {
		mQuotes = quotes;
	}
}

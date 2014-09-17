package net.randomjoe.materialquote;

public class PersonViewModel {
	private final String mUrl;
	private final String mName;

	public PersonViewModel(String url, String name) {
		mUrl = url;
		mName = name;
	}

	public String getName() {
		return mName;
	}

	public String getUrl() {
		return mUrl;
	}
}

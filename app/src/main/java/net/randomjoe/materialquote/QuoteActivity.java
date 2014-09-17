package net.randomjoe.materialquote;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class QuoteActivity extends Activity {

	@InjectView(R.id.quote_activity_person_image)
	ImageView mQuoteActivityPersonImage;
	@InjectView(R.id.quote_activity_person_name)
	TextView mQuoteActivityPersonName;
	@InjectView(R.id.quotes_list)
	RecyclerView mQuotesListView;

	private ArrayList<String> mQuotesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quote_activity);
		ButterKnife.inject(this);

		String imageUrl = getIntent().getStringExtra("imageurl");
		String name = getIntent().getStringExtra("name");
		Picasso.with(this).load(imageUrl).into(mQuoteActivityPersonImage);
		mQuoteActivityPersonName.setText(name);

		mQuotesList = (ArrayList<String>) getIntent().getSerializableExtra("quotes");

		QuotesAdapter quotesAdapter = new QuotesAdapter(mQuotesList);
		mQuotesListView.setHasFixedSize(false);
		mQuotesListView.setLayoutManager(new LinearLayoutManager(this));
		mQuotesListView.setItemAnimator(new DefaultItemAnimator());
		mQuotesListView.setAdapter(quotesAdapter);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finishAfterTransition();
	}
}

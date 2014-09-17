package net.randomjoe.materialquote;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class HomeScreenActivity extends Activity {

	private static String LOGTAG = HomeScreenActivity.class.getSimpleName();

	@InjectView(R.id.list)
	RecyclerView mList;

	private PersonAdapter mPersonAdapter;
	private List<QuotesPerson> mPersonList = new ArrayList<QuotesPerson>();
	private PersonAdapter.ViewHolder mViewHolder;

	Handler mHandler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message message) {
			ArrayList<QuotesPerson> quotesPerson = (ArrayList<QuotesPerson>) message.obj;
			mPersonList.clear();
			mPersonList.addAll(quotesPerson);
			mPersonAdapter.notifyDataSetChanged();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		setContentView(R.layout.activity_home_screen);
		ButterKnife.inject(this);

		mPersonAdapter = new PersonAdapter(mPersonList, new ClickListListener() {
			@Override
			public void listClicked(PersonAdapter.ViewHolder holder, int position) {
				startQuoteActivity(holder);
			}
		});

		mList.setHasFixedSize(true);
		mList.setAdapter(mPersonAdapter);
		mList.setLayoutManager(new LinearLayoutManager(this));
		mList.setItemAnimator(new DefaultItemAnimator());

		EndPointsQuotesImpl endPointsQuotes = new EndPointsQuotesImpl();
		endPointsQuotes.pullFromRemote(mHandler);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mViewHolder != null) {
			mViewHolder.mImageView.setViewName(null);
			mViewHolder.mTextView.setViewName(null);
		}
	}

	private void startQuoteActivity(PersonAdapter.ViewHolder holder) {
		mViewHolder = holder;
		holder.mImageView.setViewName("image");
		holder.mTextView.setViewName("name");
		Intent i = new Intent(HomeScreenActivity.this, QuoteActivity.class);
		i.putExtra("imageurl", mPersonList.get(holder.getPosition()).getImageUrl());
		i.putExtra("name", mPersonList.get(holder.getPosition()).getName());
		i.putExtra("quotes", mPersonList.get(holder.getPosition()).getQuotes());
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
				Pair.create((View) holder.mImageView, "image"),
				Pair.create((View) holder.mTextView, "name"));
		startActivity(i, options.toBundle());
	}
}

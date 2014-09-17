package net.randomjoe.materialquote;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {

	private List<String> mItems;

	public QuotesAdapter(List<String> items) {
		mItems = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_info_quote, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		String item = mItems.get(position);
		viewHolder.mTextViewQuote.setText(item);
		viewHolder.itemView.setTag(item);
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView mTextViewQuote;
		public TextView mPersonName;
		public ImageView mPersonImage;

		public ViewHolder(View itemView) {
			super(itemView);
			mTextViewQuote = (TextView) itemView.findViewById(R.id.quote_activity_quote);
			mPersonImage = (ImageView) itemView.findViewById(R.id.quote_activity_person_image);
			mPersonName = (TextView) itemView.findViewById(R.id.quote_activity_person_name);
		}
	}
}

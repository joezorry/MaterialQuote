package net.randomjoe.materialquote;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

	private List<QuotesPerson> mItems;
	private ClickListListener mClickListListener;

	public PersonAdapter(List<QuotesPerson> items, ClickListListener clickListListener) {
		mItems = items;
		mClickListListener = clickListListener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_info, viewGroup, false);
		return new ViewHolder(v, mClickListListener);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		QuotesPerson item = mItems.get(position);
		viewHolder.mTextView.setText(item.getName());
		viewHolder.mImageView.setImageBitmap(null);
		Picasso.with(viewHolder.mImageView.getContext()).cancelRequest(viewHolder.mImageView);
		Picasso.with(viewHolder.mImageView.getContext()).load(item.getImageUrl()).into(viewHolder.mImageView);
		viewHolder.itemView.setTag(item);
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		public ImageView mImageView;
		public TextView mTextView;
		public ClickListListener mClickListListener;

		public ViewHolder(View itemView, ClickListListener listListener) {
			super(itemView);
			mImageView = (ImageView) itemView.findViewById(R.id.card_view_image);
			mTextView = (TextView) itemView.findViewById(R.id.card_view_name);
			mClickListListener = listListener;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
		    mClickListListener.listClicked(this, getPosition());
		}
	}
}

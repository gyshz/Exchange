package com.shz.model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shz.card.CardInfo;
import com.shz.nfcexchange.R;

public class CardHolderAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private ArrayList<CardInfo> mCardList = new ArrayList<CardInfo>();

	public CardHolderAdapter(Context context) {
		this.mInflater = LayoutInflater.from(context);
	}

	public void setCardList(ArrayList<CardInfo> cardList) {
		mCardList.clear();
		mCardList.addAll(cardList);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCardList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class ViewHolder {
		ImageView cardIcon;
		TextView cardName;
		TextView cardCompany;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_card, null);
			holder = new ViewHolder();
			holder.cardIcon = (ImageView) convertView.findViewById(R.id.card_icon);
			holder.cardName = (TextView) convertView.findViewById(R.id.text_name);
			holder.cardCompany = (TextView) convertView.findViewById(R.id.text_company);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CardInfo cardInfo = mCardList.get(position);
		holder.cardName.setText(cardInfo.getName());
		holder.cardCompany.setText(cardInfo.getCompany());

		return convertView;
	}

}


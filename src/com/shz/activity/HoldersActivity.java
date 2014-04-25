package com.shz.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.shz.card.CardInfo;
import com.shz.database.DBInfo;
import com.shz.database.DBManager;
import com.shz.model.CardHolderAdapter;
import com.shz.model.CustomizedPopupView;
import com.shz.nfcexchange.R;

public class HoldersActivity extends Activity implements OnItemClickListener {
	// private static final String TAG = "HoldersActivity";

	private ListView mListView;
	private CustomizedPopupView mPopupView;
	private CardHolderAdapter mAdapter;
	private DBManager dbManager;
	private ArrayList<CardInfo> mCardList = new ArrayList<CardInfo>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_holder);

		dbManager = new DBManager(this);
		initView();
		setupCardList();
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.list_cards);
		mAdapter = new CardHolderAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	private void initPopupView() {
		mPopupView = new CustomizedPopupView(this);
	}

	private void setupCardList() {
		mCardList = dbManager.queryAllCardInfo(DBInfo.TB_OWNER);
		mAdapter.setCardList(mCardList);
		mAdapter.notifyDataSetChanged();
	}

	private void showDetailsPopuView(CardInfo mInfo) {
		if (mPopupView == null) {
			initPopupView();
		}
		mPopupView.setPopupContent(mInfo);
		mPopupView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId()) {
				case R.id.btn_accept:
					mPopupView.getPopupContent();
					dismissPopupView();
					break;
				case R.id.btn_cancel:
					dismissPopupView();
					break;
				}
			}
		});
		mPopupView.showPopupView(this.getWindow().getDecorView());
	}

	private void dismissPopupView() {
		if (mPopupView != null) {
			mPopupView.dismissPopupView();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		showDetailsPopuView(mCardList.get(arg2));
	}
}

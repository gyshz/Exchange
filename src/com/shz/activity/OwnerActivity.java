package com.shz.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.shz.card.CardInfo;
import com.shz.card.CardStyle;
import com.shz.database.DBInfo;
import com.shz.database.DBManager;
import com.shz.nfcexchange.R;

public class OwnerActivity extends Activity {
	private static final String TAG = "FirstActivity";

	private EditText mNameEdit, mPositionEdit, mCompanyEdit, mPhoneEdit, mEmailEdit;
	private DBManager dbManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owner);

		dbManager = new DBManager(this);
		CardInfo info = new CardInfo();
		info.setName("高运");
		info.setPosition("软件工程师");
		info.setCompany("合肥英莱电子科技有限公司");
		info.setTel("18155176464");
		info.setEmail("shz_gy@sina.cn");
		info.setStyle(CardStyle.ONE);
		dbManager.addCardInfo(info, DBInfo.TB_OWNER);

		initView();
		setEditTextFocusable(false);
		setupCardInfo();
	}

	private void initView() {
		mNameEdit = (EditText) findViewById(R.id.text_name);
		mPositionEdit = (EditText) findViewById(R.id.text_pos);
		mCompanyEdit = (EditText) findViewById(R.id.text_company);
		mPhoneEdit = (EditText) findViewById(R.id.text_tel);
		mEmailEdit = (EditText) findViewById(R.id.text_email);
	}
	
	private void setEditTextFocusable(boolean focusable) {
		mNameEdit.setFocusable(focusable);
		mPositionEdit.setFocusable(focusable);
		mCompanyEdit.setFocusable(focusable);
		mPhoneEdit.setFocusable(focusable);
		mEmailEdit.setFocusable(focusable);
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "On Resume");
	}

	private void setupCardInfo() {
		ArrayList<CardInfo> cardList = dbManager.queryAllCardInfo(DBInfo.TB_OWNER);
		CardInfo info = cardList.get(cardList.size() - 1);
		mNameEdit.setText(info.getName());
		mPositionEdit.setText(info.getPosition());
		mCompanyEdit.setText(info.getCompany());
		mPhoneEdit.setText(info.getTel());
		mEmailEdit.setText(info.getEmail());
	}

}

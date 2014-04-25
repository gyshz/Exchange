package com.shz.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.shz.card.CardInfo;
import com.shz.nfcexchange.R;

public class CustomizedPopupView {
	// private static final String TAG = "CusomizedPPopupMenu";

	private CardInfo mCardInfo;
	private Context context;
	private PopupWindow mPopupView;
	private EditText mNameEdit, mPositionEdit, mCompanyEdit, mPhoneEdit, mEmailEdit;
	private Button mAcceptBtn, mCancelBtn;

	public CustomizedPopupView(Context context) {
		this.context = context;
		View view = LayoutInflater.from(context).inflate(R.layout.activity_details, null);
		setupView(view);
	}

	public void setPopupContent(CardInfo mInfo) {
		this.mCardInfo = mInfo;
	}
	
	public CardInfo getPopupContent() {
		return this.mCardInfo;
	}

	public void showPopupView(View parent) {
		updateViewContent(mCardInfo);
		mPopupView.showAtLocation(parent, Gravity.CENTER, 0, 0);
		mPopupView.setFocusable(true);
		mPopupView.setOutsideTouchable(true);
		mPopupView.update();
	}

	public void dismissPopupView() {
		if (mPopupView != null && mPopupView.isShowing()) {
			mPopupView.dismiss();
		}
	}

	public void setOnClickListener(OnClickListener mListener) {
		if(mListener != null) {
			mAcceptBtn.setOnClickListener(mListener);
			mCancelBtn.setOnClickListener(mListener);
		}
	}

	private void setupView(View view) {
		mPopupView = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mPopupView.setAnimationStyle(R.style.AnimationPopupView);
		mPopupView.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

		mNameEdit = (EditText) view.findViewById(R.id.text_name);
		mPositionEdit = (EditText) view.findViewById(R.id.text_pos);
		mCompanyEdit = (EditText) view.findViewById(R.id.text_company);
		mPhoneEdit = (EditText) view.findViewById(R.id.text_tel);
		mEmailEdit = (EditText) view.findViewById(R.id.text_email);

		mAcceptBtn = (Button) view.findViewById(R.id.btn_accept);
		mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);
	}

	private void updateViewContent(CardInfo info) {
		mNameEdit.setText(info.getName());
		mPositionEdit.setText(info.getPosition());
		mCompanyEdit.setText(info.getCompany());
		mPhoneEdit.setText(info.getTel());
		mEmailEdit.setText(info.getEmail());
	}

}

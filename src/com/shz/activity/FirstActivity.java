package com.shz.activity;

import com.shz.nfcexchange.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FirstActivity extends Activity {
	private static final String TAG = "FirstActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(R.string.tab_one);

	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		Log.d(TAG, "On Resume");
	}
}

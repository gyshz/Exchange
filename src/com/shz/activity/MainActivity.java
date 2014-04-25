package com.shz.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shz.indicator.UnderlinePageIndicator;
import com.shz.nfcexchange.R;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity implements OnCheckedChangeListener {

	private static final String TAG = "MainActivity";

	private RadioGroup radioGroup;
	private ViewPager mViewPager;
	private List<View> listViews;
	private LocalActivityManager mActivityManager = null;
	private MyPagerAdapter mpAdapter = null;
	private UnderlinePageIndicator mIndicator = null;

	private static final int[] RADIO_BTN_IDS = new int[] { R.id.radio_one, R.id.radio_two, R.id.radio_three };
	private static final String TAB_1 = "one";
	private static final String TAB_2 = "two";
	private static final String TAB_3 = "three";
	private static final String[] TABS = { TAB_1, TAB_2, TAB_3 };
	private Intent mFirstIntent;
	private Intent mSecondIntent;
	private Intent mThirdIntent;
	private Intent[] mIntents = new Intent[TABS.length];

	private int mCurrentPageIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mActivityManager = new LocalActivityManager(this, true);
		mActivityManager.dispatchCreate(savedInstanceState);

		initViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Init Views */
	private void initViews() {
		mFirstIntent = new Intent(this, OwnerActivity.class);
		mSecondIntent = new Intent(this, HoldersActivity.class);
		mThirdIntent = new Intent(this, ThirdActivity.class);
		mIntents = new Intent[] { mFirstIntent, mSecondIntent, mThirdIntent };

		initTabs();
	}

	/** Init Tabs */
	private void initTabs() {
		listViews = new ArrayList<View>();
		listViews.add(getView(TAB_1, mFirstIntent));
		listViews.add(getView(TAB_2, mSecondIntent));
		listViews.add(getView(TAB_3, mThirdIntent));

		mpAdapter = new MyPagerAdapter(listViews);
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager.setOffscreenPageLimit(0);
		mViewPager.setAdapter(mpAdapter);
		mViewPager.setCurrentItem(mCurrentPageIndex);

		mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		mViewPager.setOnPageChangeListener(mIndicator);
		mIndicator.setViewPager(mViewPager);
		mIndicator.setFades(false);
		mIndicator.setOnPageChangeListener(new MyOnPageChangeListener());

		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(this);
	}

	@Override
	protected void onStart() {
		Log.i(TAG, "on start.");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "on resume.");
		mActivityManager.startActivity(TABS[mCurrentPageIndex], mIntents[mCurrentPageIndex]);
		mActivityManager.dispatchResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "on pause.");
		mActivityManager.dispatchPause(false);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		if (mActivityManager != null) {
			mActivityManager.dispatchDestroy(true);
		}
		super.onDestroy();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		mActivityManager.dispatchPause(false);
		switch (checkedId) {
		case R.id.radio_one:
			mCurrentPageIndex = 0;
			break;
		case R.id.radio_two:
			mCurrentPageIndex = 1;
			break;
		case R.id.radio_three:
			mCurrentPageIndex = 2;
			break;
		default:
			break;
		}
		mViewPager.setCurrentItem(mCurrentPageIndex);
		Log.d(TAG, "On tab changed: id=" + mCurrentPageIndex);

		mActivityManager.startActivity(TABS[mCurrentPageIndex], mIntents[mCurrentPageIndex]);
		mActivityManager.dispatchResume();
	}

	public LocalActivityManager getManager() {
		if (mActivityManager == null) {
			mActivityManager = new LocalActivityManager(this, true);
		}
		return mActivityManager;
	}

	/**
	 * ViewPager Adapter
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * ViewPager Listener £¬Keeping Title RadioGroup synchronized with ViewPager
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			RadioButton rb = (RadioButton) radioGroup.findViewById(RADIO_BTN_IDS[arg0]);
			rb.setChecked(true);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	private View getView(String id, Intent intent) {
		return mActivityManager.startActivity(id, intent).getDecorView();
	}

	@Override
	public void onBackPressed() {
		mActivityManager.getCurrentActivity().onBackPressed();
	}

}

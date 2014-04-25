package com.shz.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shz.card.CardInfo;
import com.shz.card.CardStyle;

public class DBManager {
	private DBHelper helper;
	private SQLiteDatabase db;

	public DBManager(Context context) {
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}

	/**
	 * add business card information
	 * 
	 * @param info
	 *            business card information
	 * @param tableName
	 *            which table needs to insert
	 * @return if failed return -1, else return the row ID of the newly inserted row.
	 */
	public long addCardInfo(CardInfo info, String tableName) {
		if (info == null || db == null) {
			return -1;
		}

		long ret = 0;
		db.beginTransaction();
		try {
			ContentValues mValues = new ContentValues();
			mValues.put(DBInfo.ATTRI_NAME, info.getName());
			mValues.put(DBInfo.ATTRI_POST, info.getPosition());
			mValues.put(DBInfo.ATTRI_CMP, info.getCompany());
			mValues.put(DBInfo.ATTRI_TEL, info.getTel());
			mValues.put(DBInfo.ATTRI_EMAIL, info.getEmail());
			mValues.put(DBInfo.ATTRI_LOGO, info.getLogo());
			mValues.put(DBInfo.ATTRI_STYLE, info.getStyle().getLayoutId());
			ret = db.insert(tableName, null, mValues);
			// db.execSQL(
			// "insert into " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
			// new Object[] { null, info.getName(), info.getPosition(),
			// info.getCompany(), info.getTel(),
			// info.getEmail(), info.getLogo(), info.getStyle() });
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		return ret;
	}

	/**
	 * query business card record list from database
	 * 
	 * @param tableName
	 *            which table needs to query
	 * @return return business card list or null
	 * */
	public ArrayList<CardInfo> queryAllCardInfo(String tableName) {
		if (tableName == null || db == null) {
			return null;
		}

		db.beginTransaction();
		ArrayList<CardInfo> cardList = new ArrayList<CardInfo>();
		try {
			Cursor cursor = db.rawQuery("select * from " + tableName + " order by " + DBInfo.ATTRI_NAME + " ASC",
					new String[] {});
			while (cursor.moveToNext()) {
				CardInfo card = new CardInfo();
				card.setName(cursor.getString(cursor.getColumnIndex(DBInfo.ATTRI_NAME)));
				card.setPosition(cursor.getString(cursor.getColumnIndex(DBInfo.ATTRI_POST)));
				card.setCompany(cursor.getString(cursor.getColumnIndex(DBInfo.ATTRI_CMP)));
				card.setEmail(cursor.getString(cursor.getColumnIndex(DBInfo.ATTRI_EMAIL)));
				card.setTel(cursor.getString(cursor.getColumnIndex(DBInfo.ATTRI_TEL)));
				card.setLogo(cursor.getString(cursor.getColumnIndex(DBInfo.ATTRI_LOGO)));
				card.setStyle(CardStyle.getCardStyleByLayoutId(cursor.getInt(cursor.getColumnIndex(DBInfo.ATTRI_STYLE))));
				cardList.add(card);
			}
			cursor.close();
		} finally {
			db.endTransaction();
		}
		return cardList;
	}

	/**
	 * close database
	 */
	public void closeDB() {
		db.close();
	}
}

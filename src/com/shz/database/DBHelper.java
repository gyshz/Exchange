package com.shz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// private static final String TAG = "DbHelper";

	public DBHelper(Context context) {
		super(context, DBInfo.DB_NAME, null, DBInfo.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + DBInfo.TB_OWNER
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DBInfo.ATTRI_NAME +" VARCHAR,"
				+ DBInfo.ATTRI_POST + " VARCHAR,"
				+ DBInfo.ATTRI_CMP + " VARCHAR,"
				+ DBInfo.ATTRI_TEL + " VARCHAR,"
				+ DBInfo.ATTRI_EMAIL + " VARCHAR,"
				+ DBInfo.ATTRI_LOGO + " VARCHAR,"
				+ DBInfo.ATTRI_STYLE + " INTEGER)"
				);
		db.execSQL("CREATE TABLE IF NOT EXISTS " + DBInfo.TB_HOLDER
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DBInfo.ATTRI_NAME +" VARCHAR,"
				+ DBInfo.ATTRI_POST + " VARCHAR,"
				+ DBInfo.ATTRI_CMP + " VARCHAR,"
				+ DBInfo.ATTRI_TEL + " VARCHAR,"
				+ DBInfo.ATTRI_EMAIL + " VARCHAR,"
				+ DBInfo.ATTRI_LOGO + " VARCHAR,"
				+ DBInfo.ATTRI_STYLE + " INTEGER)"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
	}
}

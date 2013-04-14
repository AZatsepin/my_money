package com.example.mymoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_TABLE = "CREATE TABLE "
			+ MyContract.TableEntry.TABLE_NAME + " ("
			+ MyContract.TableEntry.COLUMN_NAME_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ MyContract.TableEntry.COLUMN_NAME_MODE + TEXT_TYPE + COMMA_SEP
			+ MyContract.TableEntry.COLUMN_NAME_CATEGORY + TEXT_TYPE
			+ COMMA_SEP + MyContract.TableEntry.COLUMN_NAME_ITEM + TEXT_TYPE
			+ COMMA_SEP + MyContract.TableEntry.COLUMN_NAME_DESCRIPTION
			+ TEXT_TYPE + COMMA_SEP + MyContract.TableEntry.COLUMN_NAME_VALUE
			+ " INTEGER" + COMMA_SEP + MyContract.TableEntry.COLUMN_NAME_DATE
			+ " DATE" + " )";

	private static final String SQL_CLEAR_TABLE = "DROP TABLE IF EXISTS "
			+ MyContract.TableEntry.TABLE_NAME;

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "mymoney.db";

	// private static final String DATABASE_PATH =
	// "/data/data/com.example.moneybalance/databases/";

	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		SQLiteDatabase dbExists = null;
		String myDBPath = DATABASE_NAME;

		try {
			dbExists = SQLiteDatabase.openDatabase(myDBPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
		}

		if (dbExists == null) {
			db.execSQL(SQL_CREATE_TABLE);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CLEAR_TABLE);
		onCreate(db);
	}

}

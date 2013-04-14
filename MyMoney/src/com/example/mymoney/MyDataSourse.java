package com.example.mymoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDataSourse {
	private SQLiteDatabase database;
	private MyDBHelper dbhelper;

	public SQLiteDatabase getDatabase() {
		return database;
	}

	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}

	public MyDBHelper getDbhelper() {
		return dbhelper;
	}

	public void setDbhelper(MyDBHelper dbhelper) {
		this.dbhelper = dbhelper;
	}

	public MyDataSourse(Context context) {
		dbhelper = new MyDBHelper(context);
		dbhelper.getReadableDatabase();
	}

	public void openWritable() throws SQLException {
		database = dbhelper.getWritableDatabase();
	}

	public void openReadable() throws SQLException {
		database = dbhelper.getReadableDatabase();
	}

	public void close() throws SQLException {
		dbhelper.close();
	}

	// COMMIT Database
	public void commitEntry(MyEntry entry) {
		ContentValues values = new ContentValues();
		values.put(MyContract.TableEntry.COLUMN_NAME_MODE,
				entry.getMode());
		values.put(MyContract.TableEntry.COLUMN_NAME_CATEGORY,
				entry.getCategory());
		values.put(MyContract.TableEntry.COLUMN_NAME_ITEM,
				entry.getItem());
		values.put(MyContract.TableEntry.COLUMN_NAME_DESCRIPTION,
				entry.getDescription());
		values.put(MyContract.TableEntry.COLUMN_NAME_VALUE,
				entry.getValue());
		values.put(MyContract.TableEntry.COLUMN_NAME_DATE,
				entry.getDate());

		database.insert(MyContract.TableEntry.TABLE_NAME, null,
				values);

	}

	public void clearDB() {
		dbhelper.onUpgrade(database, 0, 0);
	}

	// Number of Rows in Database
	public long numberOfRows() {
		this.openReadable();
		return DatabaseUtils.queryNumEntries(database, "datatable");
	}
}

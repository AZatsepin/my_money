package com.example.mymoney;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EntriesListActivity extends Activity {

	private MyDataSourse datasourse;
	private SQLiteDatabase database;
	// Context context = getApplicationContext();
	private String[] allColumns = { MyContract.TableEntry.COLUMN_NAME_ID,
			MyContract.TableEntry.COLUMN_NAME_MODE,
			MyContract.TableEntry.COLUMN_NAME_CATEGORY,
			MyContract.TableEntry.COLUMN_NAME_ITEM,
			MyContract.TableEntry.COLUMN_NAME_DESCRIPTION,
			MyContract.TableEntry.COLUMN_NAME_VALUE,
			MyContract.TableEntry.COLUMN_NAME_DATE };

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entries_list);
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		try {
			datasourse = new MyDataSourse(this);
		} catch (Exception exc) {
			System.exit(0);
			// toast = Toast.makeText(context, TEXT_CREATE_DB_ERROR, duration);
			// toast.show();
		}

		datasourse.openReadable();
		database = datasourse.getDatabase();

		ListView lv = (ListView) findViewById(R.id.listView1);
		int rows = (int) datasourse.numberOfRows();
		String[] listOfEntries = new String[rows];
		String str;
		String sep = "  ";

		Cursor cursor = database.query(MyContract.TableEntry.TABLE_NAME,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			str = cursor.getLong(0) + sep + cursor.getString(1) + sep
					+ cursor.getString(2) + sep + cursor.getString(3) + sep
					+ cursor.getString(4) + sep + cursor.getString(5) + sep
					+ cursor.getString(6);

			listOfEntries[cursor.getPosition()] = str;

			cursor.moveToNext();

		}
		cursor.close();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfEntries);
		lv.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entries_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.listView1: // This ID
			// represents the Home or Up button. In the case of this //
			// activity,
			// the Up button is shown. Use NavUtils to allow users // to
			// navigate up
			// one level in the application structure. For // more details, see
			// the
			// Navigation pattern on Android Design: // //
			// http://developer.android.com
			// /design/patterns/navigation.html#up-vs-back //
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}

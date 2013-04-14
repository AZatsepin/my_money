package com.example.mymoney;

import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String INCOME_TEXT = "INCOME";
	private static final String OUTCOME_TEXT = "OUTCOME";
	// private static final String TEXT_OK = "Data saved";
	// private static final String TEXT_COMMIT_ERROR =
	// "Error: Database commit failed";
	// private static final String TEXT_CREATE_DB_ERROR =
	// "Error: DB was not created";
	// private static final String TEXT_CLEAR_DB_ERROR =
	// "Error: DB was not cleared";
	// private static final String TEXT_CLEAR_DB_OK = "DB was cleared";

	// private Toast toast;
	private MyDataSourse datasourse;

	// Context context = getApplicationContext();
	String text = "";
	int duration = Toast.LENGTH_SHORT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			datasourse = new MyDataSourse(this);
		} catch (Exception exc) {
			System.exit(0);
			// toast = Toast.makeText(context, TEXT_CREATE_DB_ERROR, duration);
			// toast.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Show toast when RadioButton clicked
	public void radioButtonToast(View view) {
		RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		int rbNo = rg.getCheckedRadioButtonId();

		switch (rbNo) {
		case R.id.radio1:
			text = INCOME_TEXT;
			break;

		case R.id.radio2:
			text = OUTCOME_TEXT;

		default:
			break;
		}

		// toast = Toast.makeText(context, text, duration);
		// toast.show();
	}

	// Get entry from user input for writing to database
	public MyEntry getEntry() {

		MyEntry entry = new MyEntry();
		String text = "";

		// Writing Mode (Income/Outcome)
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		int rbNo = radioGroup.getCheckedRadioButtonId();

		switch (rbNo) {
		case R.id.radio1:
			text = INCOME_TEXT;
			break;

		case R.id.radio2:
			text = OUTCOME_TEXT;

		default:
			break;
		}
		entry.setMode(text);

		// Writing Category
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		text = spinner.getSelectedItem().toString();

		entry.setCategory(text);

		// Writing Item, Description, Value
		// Item
		EditText editText = (EditText) findViewById(R.id.editText1);
		text = editText.getText().toString();

		entry.setItem(text);

		// Value
		editText = (EditText) findViewById(R.id.editText2);
		text = editText.getText().toString();

		entry.setValue(text);

		// Description
		editText = (EditText) findViewById(R.id.editText3);
		text = editText.getText().toString();

		entry.setDescription(text);

		// Writing Date
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);

		String year = String.valueOf(datePicker.getYear());
		String month = String.valueOf(datePicker.getMonth());
		String day = String.valueOf(datePicker.getDayOfMonth());

		String date = year.concat(month).concat(day);

		entry.setDate(date);

		return entry;
	}

	// Clearing database
	public void execClear(View view) {

		try {
			datasourse.openWritable();
			datasourse.clearDB();
			datasourse.close();
			// toast = Toast.makeText(context, TEXT_CLEAR_DB_OK, duration);
		} catch (Exception exc) {
			// toast = Toast.makeText(context, TEXT_CLEAR_DB_ERROR, duration);
		}

		// toast.show();
	}

	// Commit database
	public void execCommit(View view) {

		try {
			datasourse.openWritable();
			datasourse.commitEntry(getEntry());
			// toast = Toast.makeText(context, TEXT_OK, duration);
			datasourse.close();
		} catch (Exception exc) {
			// toast = Toast.makeText(context, TEXT_COMMIT_ERROR, duration);
		}

		// toast.show();
	}

	public void seeAllData(View view) {
		Intent intent = new Intent(this, EntriesListActivity.class);
		startActivity(intent);
	}
}

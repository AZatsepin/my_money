package com.example.mymoney;

import android.provider.BaseColumns;

public class MyContract {

	public static abstract class TableEntry implements BaseColumns {

		public static final String TABLE_NAME = "datatable";
		public static final String COLUMN_NAME_ID = "_id";
		public static final String COLUMN_NAME_MODE = "mode";
		public static final String COLUMN_NAME_CATEGORY = "category";
		public static final String COLUMN_NAME_ITEM = "item";
		public static final String COLUMN_NAME_DESCRIPTION = "description";
		public static final String COLUMN_NAME_VALUE = "value";
		public static final String COLUMN_NAME_DATE = "date";

	}

	private MyContract() {
	};

}

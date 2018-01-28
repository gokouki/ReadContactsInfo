package com.example.s151304064;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {


	private static final String TAG = "PersonSQLiteOpenHelper";
	public PersonSQLiteOpenHelper(Context context) {
		super(context,"person.db",null,3);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		db.execSQL("create table person (id integer primary key autoincrement,name varchar(20),number varchar(20)) ");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
		Log.i(TAG, "数据库的版本变化了");
	}

}

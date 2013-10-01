package com.csn.datasource;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.webkit.WebChromeClient.CustomViewCallback;

import com.csn.entity.DBEntity;
import com.csn.entity.EventDetailEntity;
import com.csn.entity.ShopDetailEntity;

@SuppressLint("ParserError")
public class DatabaseHelper extends SQLiteOpenHelper {
	static final String DBname = "puripuri";
	private Context mContext;
	public static final int SHOP = 1;
	public static final int EVENT = 2;
	private String[] allColumns = {"ID","NAME","TYPE","URL","GENRE","CATEGORY","TIME"};
	
	public DatabaseHelper(Context context) {
		super(context, DBname, null, 1);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	/**
	 * db {id,name,type,url,genre,category,time}
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE tblHistory (ID VARCHAR(100) ,NAME text,TYPE integer, URL text, GENRE text,CATEGORY text,TIME VARCHAR(100))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS tblHistory");
		onCreate(db);
	}
	
	public void dropTable() {
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblHistory");
	}
	
	
	
	public void insertShop(ShopDetailEntity shopDetailEntity) {
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("ID", shopDetailEntity.getId());
		contentValues.put("NAME", shopDetailEntity.getName());
		contentValues.put("TYPE",SHOP );
		contentValues.put("URL", shopDetailEntity.getImageUrl());
		contentValues.put("GENRE", shopDetailEntity.getGenreEntity().getName());
		contentValues.put("CATEGORY", shopDetailEntity.getCategoryEntity().getName());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		contentValues.put("TIME", dateFormat.format(date));
		sqLiteDatabase.insert("tblHistory", null, contentValues);
		sqLiteDatabase.close();
	}
	
	public void insertEvent(EventDetailEntity eventDetailEntity) {
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("ID", eventDetailEntity.getId());
		contentValues.put("NAME", eventDetailEntity.getName());
		contentValues.put("TYPE",EVENT );
		contentValues.put("URL", eventDetailEntity.getImage());
		contentValues.put("GENRE", "");
		contentValues.put("CATEGORY", "");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		contentValues.put("TIME", dateFormat.format(date));
		sqLiteDatabase.insert("tblHistory", null, contentValues);
		sqLiteDatabase.close();
	}
	
	public ArrayList<DBEntity> listHistory() {
		ArrayList<DBEntity> listDbEntities = new ArrayList<DBEntity>();
		Cursor cursor = this.getWritableDatabase().query("tblHistory", allColumns, null, null, "NAME", null, "TIME","20");
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      DBEntity dbEntity = cursorToDbEntity(cursor);
	      listDbEntities.add(dbEntity);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return listDbEntities;
	}
	
	private DBEntity cursorToDbEntity(Cursor cursor) {
	     DBEntity dbEntity = new DBEntity();
	     dbEntity.setId(cursor.getString(0));
	     dbEntity.setName(cursor.getString(1));
	     dbEntity.setType(cursor.getInt(2));
	     dbEntity.setUrl(cursor.getString(3));
	     dbEntity.setGenre(cursor.getString(4));
	     dbEntity.setCategory(cursor.getString(5));
	     dbEntity.setTime(cursor.getString(6));
	     return dbEntity;
	  }

}

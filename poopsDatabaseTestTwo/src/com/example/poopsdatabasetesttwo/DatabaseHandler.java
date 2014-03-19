package com.example.poopsdatabasetesttwo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "poopsDatabase";
	private static final String TABLE_POOPS = "poops";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_DURATION = "duration";
	private static final String KEY_AMOUNT_EARNED = "amount_earned";
	private static final String KEY_RATING = "rating";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_POOPS_TABLE = "CREATE TABLE " + TABLE_POOPS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_DURATION + " INTEGER, "
				+ KEY_AMOUNT_EARNED + " INTEGER, "
				+ KEY_RATING + " INTEGER);";
		db.execSQL(CREATE_POOPS_TABLE);						
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_POOPS);
		onCreate(db);
	}
	
	public void addPoop(Poop poop){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_DURATION, poop.getDuration());
		values.put(KEY_AMOUNT_EARNED, poop.getAmountEarned());
		values.put(KEY_RATING, poop.getRating());
		
		if (db == null){
			Log.i(null, "problem");
		}
		db.insert(TABLE_POOPS, null, values);
		db.close();
	}
	
	public Poop getPoop(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_POOPS, new String[] { KEY_ID,
				KEY_DURATION, KEY_AMOUNT_EARNED, KEY_RATING}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		
		Poop poop = new Poop(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
		return poop;
		
	}
	public List<Poop> getAllPoops(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		List<Poop> poops = new ArrayList<Poop>();
		Cursor cursor = db.query(TABLE_POOPS, new String[] { KEY_ID,
				KEY_DURATION, KEY_AMOUNT_EARNED, KEY_RATING}, null, null, null, null, null); 
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Poop poop = new Poop(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
			poops.add(poop);
			cursor.moveToNext();
		}
		cursor.close();
		return poops;
	}
	
	public void deleteAllEntriesDatabase(){
		SQLiteDatabase db = this.getReadableDatabase();		
		db.delete(TABLE_POOPS, null, null);
	}
	
}

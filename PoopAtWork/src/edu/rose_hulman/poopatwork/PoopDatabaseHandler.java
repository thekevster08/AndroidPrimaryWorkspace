package edu.rose_hulman.poopatwork;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PoopDatabaseHandler extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "poopsDatabase";
	private static final String TABLE_POOPS = "poops";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_DATE = "date";
	private static final String KEY_DURATION = "duration";
	private static final String KEY_AMOUNT_EARNED = "amount_earned";
	
	private String[] allColumns = {KEY_DATE, KEY_DURATION, KEY_AMOUNT_EARNED};
	
	//Constructor
	public PoopDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_POOPS_TABLE = "CREATE TABLE " + TABLE_POOPS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_DATE + " TEXT, "
				+ KEY_DURATION + " INTEGER, "
				+ KEY_AMOUNT_EARNED + " REAL);";
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
		values.put(KEY_DATE, poop.getDate());
		values.put(KEY_DURATION, poop.getDuration());
		values.put(KEY_AMOUNT_EARNED, poop.getAmountEarned());
		
		db.insert(TABLE_POOPS, null, values);
		db.close();
		
	}
	
//	public Poop getPoop(int id){
//		//TODO:
//		
//	}
//	
	public List<Poop> getAllPoops(){
		SQLiteDatabase db = this.getReadableDatabase();
		List<Poop> poops = new ArrayList<Poop>();
		Cursor cursor = db.query(TABLE_POOPS, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Poop poop = cursorToPoop(cursor);
		    poops.add(poop);
		    cursor.moveToNext();
		}
		cursor.close();
		return poops;
	}
	
	private Poop cursorToPoop(Cursor cursor) {
		Poop poop = new Poop();
		poop.setDate(cursor.getString(1));
		poop.setDuration(cursor.getInt(2));
		poop.setAmountEarned(cursor.getFloat(3));
		return poop;
    }
//
//	public void deletePoop(int id){
//		//TODO:
//	}
//	

	public List<String> getAllPoopDates() {
		SQLiteDatabase db = this.getReadableDatabase();
		List<String> poops = new ArrayList<String>();
		Cursor cursor = db.query(TABLE_POOPS, new String[] {KEY_DATE}, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
		    poops.add(cursor.getString(0));
		    cursor.moveToNext();
		}
		cursor.close();
		return poops;
	}

	public Poop getPoopFromDate(String date) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_POOPS, allColumns, KEY_DATE + "=?", new String[] { date }, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			if (cursor.getString(0).equals(date)){
				Poop poop = new Poop(cursor.getString(0), cursor.getInt(1), cursor.getFloat(2));
				cursor.close();
				return poop;
			}
			else{
				cursor.moveToNext();
			}
		}
		return null;
	}
	//open method?
}

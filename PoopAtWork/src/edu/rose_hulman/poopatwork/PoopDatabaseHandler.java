package edu.rose_hulman.poopatwork;

import java.util.List;

import android.content.Context;
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
				+ KEY_AMOUNT_EARNED + " INTEGER);";
		db.execSQL(CREATE_POOPS_TABLE);			
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_POOPS);
		onCreate(db);
	}
	
	public void addPoop(Poop poop){
		//TODO:
	}
	
	public Poop getPoop(int id){
		//TODO:
	}
	
	public List<Poop> getAllPoops(){
		//TODO:
	}

	public void deletePoop(int id){
		//TODO:
	}
	
	public List<String> getAllDates(){
		//TODO:
	}
}

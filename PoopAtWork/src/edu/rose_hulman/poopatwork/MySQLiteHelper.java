package edu.rose_hulman.poopatwork;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{

	public static final String TABLE_NAME = "poops";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_DATE = "date";
	  public static final String COLUMN_TIME_START = "time start";
	  public static final String COLUMN_TIME_END = "time end";
	  public static final String COLUMN_DURATION = "duration";
	  public static final String COLUMN_AMOUNT_EARNED = "amount earned";
	  public static final String COLUMN_RATING = "rating";
	  
	  public static final String TEXT_TYPE = " TEXT";
	  public static final String COMMA_SEP = ",";

	  private static final String DATABASE_NAME = "poops.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = 
		"create table " + TABLE_NAME + " (" + 
	    COLUMN_ID + " INTEGER PRIMARY KEY," + 
	    COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
	    COLUMN_TIME_START + TEXT_TYPE + COMMA_SEP +
	    COLUMN_TIME_END + TEXT_TYPE + COMMA_SEP +
	    COLUMN_DURATION + TEXT_TYPE + COMMA_SEP +
	    COLUMN_AMOUNT_EARNED + TEXT_TYPE + COMMA_SEP +
	    COLUMN_RATING + TEXT_TYPE + COMMA_SEP +
	    ")";
	  
	  private static final String SQL_DELETE_ENTRIES =
			    "DROP TABLE IF EXISTS " + TABLE_NAME;

	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // This database is only a cache for online data, so its upgrade policy is
	        // to simply to discard the data and start over
	        db.execSQL(SQL_DELETE_ENTRIES);
	        onCreate(db);
	    }

}
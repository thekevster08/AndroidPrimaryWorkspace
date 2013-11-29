package edu.rose_hulman.poopatwork;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PoopHistoryDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
	      MySQLiteHelper.COLUMN_DATE, MySQLiteHelper.COLUMN_TIME_START, 
	      MySQLiteHelper.COLUMN_TIME_END, MySQLiteHelper.COLUMN_DURATION,
	      MySQLiteHelper.COLUMN_AMOUNT_EARNED, MySQLiteHelper.COLUMN_RATING};

	  public PoopHistoryDataSource(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Poop createPoop(String date, String timeStart, String timeEnd, int duration, 
			  int amountEarned, int rating) {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_DATE, date);
	    values.put(MySQLiteHelper.COLUMN_TIME_START, timeStart);
	    values.put(MySQLiteHelper.COLUMN_TIME_END, timeEnd);
	    values.put(MySQLiteHelper.COLUMN_DURATION, duration);
	    values.put(MySQLiteHelper.COLUMN_AMOUNT_EARNED, amountEarned);
	    values.put(MySQLiteHelper.COLUMN_RATING, rating);	    
	    
	    long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null,
	        values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
	        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Poop newPoop = cursorToPoop(cursor);
	    cursor.close();
	    return newPoop;
	  }

	  public void deletePoop(Poop poop) {
	    long id = poop.getId();
	    System.out.println("Poop deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Poop> getAllComments() {
	    List<Poop> poops = new ArrayList<Poop>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Poop poop = cursorToPoop(cursor);
	      poops.add(poop);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return poops;
	  }

	  private Poop cursorToPoop(Cursor cursor) {
	    Poop poop = new Poop();
	    poop.setId(cursor.getLong(0));
	    return poop;
	  }
	} 
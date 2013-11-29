package edu.rose_hulman.poopatwork;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;
    
	Button startButton, stopButton;
	Chronometer mChronometer;
	TextView amountEarned;
	
	SharedPreferences myPrefs;
	
	SharedPreferences.Editor mEditor;
	double salary;
	double earned;
	double secondsElapsed;
	NumberFormat baseFormat;
	
	//database fields
	//private SQLiteDatabase database;
	//private MySQLiteHelper dbHelper;	
	private PoopsDbAdapter mDbHelper;
	private Cursor mPoopsCursor;
	private Calendar mCalendar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		
		startButton.setOnClickListener(mStartListener);
		stopButton.setOnClickListener(mStopListener);
		mChronometer.setOnChronometerTickListener(mChronometerTickListener);
		
		amountEarned = (TextView) findViewById(R.id.amountEarned);
		
		myPrefs = getSharedPreferences("myPrefs", 0);
		//salary = myPrefs.getInt("salary", 0);
		//TODO:return real salary
		salary = 60000;
		earned = 0;
		secondsElapsed = 0;
		
		baseFormat = NumberFormat.getCurrencyInstance(Locale.US);
		
		//sql stuff
		mDbHelper = new PoopsDbAdapter(this);
		mDbHelper.open();
		fillData();
		
		//getting values
		mCalendar = Calendar.getInstance();
		registerForContextMenu(getListView());
		this.createPoop();
	}

	@SuppressWarnings("deprecation")
	private void fillData() {
        // Get all of the rows from the database and create the item list
        mPoopsCursor = mDbHelper.fetchAllPoops();
        startManagingCursor(mPoopsCursor);

        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{PoopsDbAdapter.KEY_DATE};

        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1};

        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter poops = 
            new SimpleCursorAdapter(this, R.layout.poops_row, mPoopsCursor, from, to);
        setListAdapter(poops);
    }
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
// //       menu.add(0, INSERT_ID, 0, R.string.menu_insert);
//		return true;
//	}
////	
////    @Override
////    public boolean onMenuItemSelected(int featureId, MenuItem item) {
////        switch(item.getItemId()) {
////            case INSERT_ID:
////                createNote();
////                return true;
////        }
////
////        return super.onMenuItemSelected(featureId, item);
////    }
//	
////    @Override
////    public void onCreateContextMenu(ContextMenu menu, View v,
////            ContextMenuInfo menuInfo) {
////        super.onCreateContextMenu(menu, v, menuInfo);
////        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
////    }
////
////    @Override
////    public boolean onContextItemSelected(MenuItem item) {
////        switch(item.getItemId()) {
////            case DELETE_ID:
////                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
////                mDbHelper.deleteNote(info.id);
////                fillData();
////                return true;
////        }
////        return super.onContextItemSelected(item);
////    }
//
	View.OnClickListener mStopListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.stop();
			createPoop();
		}
	};
	//TODO write this
	   private void createPoop() {
	//        Intent i = new Intent(this, NoteEdit.class);
	//        startActivityForResult(i, ACTIVITY_CREATE);
	//	   Date date = mCalendar.getTime();
	//	   String dateString = date.toString();
		   String date = "11/12/12";
		   String timeStart = "123";
		   String timeEnd = "456";
		   String duration = "00:15";
		   String amountEarned = "$45";
		   String rating = "5";
		   mDbHelper.createPoop(date, timeStart, timeEnd, duration, amountEarned, rating);
		   
	    }
	
	View.OnClickListener mStartListener = new OnClickListener(){
		public void onClick (View v){
			salary = myPrefs.getInt("salary", 0);
			mChronometer.setBase(SystemClock.elapsedRealtime());
			mChronometer.start();
		}
	};
	
	OnChronometerTickListener mChronometerTickListener = new OnChronometerTickListener(){
		@Override
		public void onChronometerTick(Chronometer mChronometer) {
			secondsElapsed ++;
			earned = (secondsElapsed * salary/(2080*60*60));
			amountEarned.setText(baseFormat.format(earned));
		}
	};
//	
////    @Override
////    protected void onListItemClick(ListView l, View v, int position, long id) {
////    	  super.onListItemClick(l, v, position, id);
////    	  Intent i = new Intent(this, NoteEdit.class);
////    	  i.putExtra(NotesDbAdapter.KEY_ROWID, id);
////    	  startActivityForResult(i, ACTIVITY_EDIT);
////    }
//	 public void openSettings(){
//	    	Intent intent = new Intent(this, SettingsActivity.class);
//	    	startActivity(intent);
//	    }
//	 @Override
//	    public boolean onOptionsItemSelected(MenuItem item){
//	    	switch(item.getItemId()){
//	    	case R.id.action_settings:
//	    		openSettings();
//	    		return true;
//	    	default:
//	    		return super.onOptionsItemSelected(item);
//	    	}
//	    }
//	 
//	    @Override
//	    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//	        super.onActivityResult(requestCode, resultCode, intent);
//	        fillData();
//	    }
}

package edu.rose_hulman.poopatwork;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button mStartButton, mStopButton;
	Chronometer mChronometer;
	TextView mAmountEarnedText;
	
	SharedPreferences myPrefs;
	SharedPreferences.Editor mPrefsEditor;
	
	int salary;
	double secondsElapsed;	
	double amountEarned;
	
	String date;
	
	NumberFormat baseFormat;
	
	PoopDatabaseHandler db;
	
	Context context = this;
	
	ArrayAdapter<String> listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new PoopDatabaseHandler(this);
		
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		mStartButton = (Button) findViewById(R.id.startButton);
		mStopButton = (Button) findViewById(R.id.stopButton);
		
		mStartButton.setOnClickListener(mStartListener);
		mStopButton.setOnClickListener(mStopListener);
		mChronometer.setOnChronometerTickListener(mChronometerTickListener);

		mAmountEarnedText = (TextView) findViewById(R.id.amountEarned);
		
		myPrefs = getSharedPreferences("myPrefs", 0);
		mPrefsEditor = myPrefs.edit();
		//salary = myPrefs.getInt("salary", 0);
		//TODO:return real salary
		salary = 60000;
		amountEarned = 0;
		secondsElapsed = 0;
		
		baseFormat = NumberFormat.getCurrencyInstance(Locale.US);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.action_history:
    		openHistory();
    		return true;
    	case R.id.action_settings:
    		openSettings();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
	private void openSettings() {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View promptView = layoutInflater.inflate(R.layout.settings, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setView(promptView);
		
		final EditText mSalaryEditText = (EditText) promptView.findViewById(R.id.userInput);
		
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id){
					//EditText mSalaryEditText = (EditText) findViewById(R.id.userInput);
					//String temp2 = mSalaryEditText.getText().toString();
				//	int temp = Integer.parseInt(mSalaryEditText.getText().toString());
					
					mPrefsEditor.putInt("salary", Integer.parseInt(mSalaryEditText.getText().toString()));
					mPrefsEditor.commit();
//					
				}
			})
			.setNegativeButton("Cancel", 
					new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							dialog.cancel();
						}
			});
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();
		mSalaryEditText.setText(Integer.toString(salary));
	}

	private void openHistory() {
		List<String> poopDates = db.getAllPoopDates();
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		View historyView = (View) inflater.inflate(R.layout.history, null);
		alertDialog.setView(historyView);
		alertDialog.setTitle("history");
		ListView listView = (ListView) historyView.findViewById(R.id.list);
		listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,poopDates);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
					LayoutInflater inflater = getLayoutInflater();
					View detailsView = (View) inflater.inflate(R.layout.details, null);
					TextView dateTextView = (TextView) detailsView.findViewById(R.id.dateTextView);
					TextView durationTextView = (TextView) detailsView.findViewById(R.id.durationTextView);
					TextView amountEarnedTextView = (TextView) detailsView.findViewById(R.id.amountEarnedTextView);
					//Set Details
					Poop poop = db.getPoopFromDate(listAdapter.getItem(position));
					dateTextView.setText("Date: " + poop.getDate());
					durationTextView.setText("Duration: " + poop.getDuration());
					amountEarnedTextView.setText("Amount Earned: " + poop.getAmountEarned());
					
					alertDialog.setView(detailsView);
					alertDialog.setTitle("Details");
					alertDialog.show();
					
			  }
			}); 
		alertDialog.show();		
	}
	

	//Listeners
	View.OnClickListener mStopListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.stop();
			date = getDateTime();			
			Poop poop = new Poop(date, (int) secondsElapsed, (int) amountEarned);
			db.addPoop(poop);
		}
	};

	
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
			amountEarned = (secondsElapsed * salary/(2080*60*60));
			mAmountEarnedText.setText(baseFormat.format(amountEarned));
		}
	};
	//Helper Methods
	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}
	
	


}

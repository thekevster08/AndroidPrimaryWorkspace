package edu.rose_hulman.poopatwork;

import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button startButton, stopButton;
	Chronometer mChronometer;
	TextView amountEarned;
	
	SharedPreferences myPrefs;
	
	SharedPreferences.Editor mEditor;
	double salary;
	double earned;
	double secondsElapsed;
	NumberFormat baseFormat;
	
	
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
		salary = myPrefs.getInt("salary", 0);
		earned = 0;
		secondsElapsed = 0;
		
		baseFormat = NumberFormat.getCurrencyInstance(Locale.US);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	View.OnClickListener mStopListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.stop();
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
			earned = (secondsElapsed * salary/(2080*60*60));
		//	amountEarned.setText(Integer.toString(salary));
			amountEarned.setText(baseFormat.format(earned));
		}
	};
	
	 public void openSettings(){
	    	Intent intent = new Intent(this, SettingsActivity.class);
	    	startActivity(intent);
	    }
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item){
	    	switch(item.getItemId()){
	    	case R.id.action_settings:
	    		openSettings();
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
	    	}
	    }

}

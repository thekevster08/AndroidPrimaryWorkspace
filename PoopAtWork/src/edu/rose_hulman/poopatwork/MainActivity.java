package edu.rose_hulman.poopatwork;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
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
	
	PoopDatabaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new PoopDatabaseHandler(this);
		
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	//Listeners
	View.OnClickListener mStopListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.stop();
			//TODO: createPoop();
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
			amountEarned.setText(baseFormat.format(earned));
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

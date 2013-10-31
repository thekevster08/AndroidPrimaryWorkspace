package edu.rose_hulman.poopatwork;

import java.text.NumberFormat;

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
	SharedPreferences mSharedPref;
	SharedPreferences.Editor mEditor;
	double salary;
	double earned;
	int secondsElapsed;
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
		
		salary = 30.00;
		earned = 0;
		secondsElapsed = 0;
		
		baseFormat = NumberFormat.getCurrencyInstance();
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
			mChronometer.setBase(SystemClock.elapsedRealtime());
			mChronometer.start();
		}
	};
	
	OnChronometerTickListener mChronometerTickListener = new OnChronometerTickListener(){
		@Override
		public void onChronometerTick(Chronometer mChronometer) {
			secondsElapsed ++;
			earned = (secondsElapsed * (salary/60/60));
			
			amountEarned.setText(baseFormat.format(Double.toString(earned)));
		}
	};

}

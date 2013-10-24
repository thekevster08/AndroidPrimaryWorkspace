package com.example.chronometer;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends Activity {
	Chronometer mChronometer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button startButton, resetButton, stopButton;
		
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		
		startButton = (Button) findViewById(R.id.start);
		stopButton = (Button) findViewById(R.id.stop);
		resetButton = (Button) findViewById(R.id.reset);
		
		startButton.setOnClickListener(mStartListener);
		resetButton.setOnClickListener(mResetListener);
		stopButton.setOnClickListener(mStopListener);
	}

	View.OnClickListener mStopListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.stop();
		}
	};
	
	View.OnClickListener mStartListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.start();
		}
	};
	
	View.OnClickListener mResetListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.setBase(SystemClock.elapsedRealtime());
		}
	};
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

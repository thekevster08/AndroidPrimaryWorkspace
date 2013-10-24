package edu.roseHulman.clock;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		Button button;
		
		mChronometer = (Chronometer) findViewById(R.id.chronometer);

	}
}

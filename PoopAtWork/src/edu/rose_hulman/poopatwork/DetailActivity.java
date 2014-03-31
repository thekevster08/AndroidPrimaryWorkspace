package edu.rose_hulman.poopatwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DetailActivity extends Activity {

	TextView mDateText, mDurationText, mAmountEarnedText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Bundle extras = getIntent().getExtras();
		
		mDateText = (TextView) findViewById(R.id.dateTextView);
		mDurationText = (TextView) findViewById(R.id.durationTextView);
		mAmountEarnedText = (TextView) findViewById(R.id.amountEarnedTextView);
				
	    mDateText.setText(extras.getString("date"));
	    mDurationText.setText(Integer.toString(extras.getInt("duration")));
	    mAmountEarnedText.setText(Integer.toString(extras.getInt("amountEarned")));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}

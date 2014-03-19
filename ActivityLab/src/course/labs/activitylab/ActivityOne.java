package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";
	
	// Lifecycle counters

	// DONE:
	// Create counter variables for onCreate(), onRestart(), onStart() and
	// onResume(), called mCreate, etc.
	int mCreate;
	int mRestart;
	int mStart;
	int mResume;
	
	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called



	// DONE: Create variables for each of the TextViews, called
        // mTvCreate, etc. 
	TextView mTvCreate;
	TextView mTvRestart;
	TextView mTvStart;
	TextView mTvResume;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		
		// DONE: Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		mTvCreate = (TextView) findViewById(R.id.create); 
		mTvRestart = (TextView) findViewById(R.id.restart);
		mTvStart = (TextView) findViewById(R.id.start);
		mTvResume = (TextView) findViewById(R.id.resume);




		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo); 
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO:
				// Launch Activity Two
				// Hint: use Context's startActivity() method

				// Create an intent stating which Activity you would like to start
				Intent intent = new Intent(v.getContext(), ActivityTwo.class);
				
				// Launch the Activity using the intent
				startActivity(intent);
			
			}
		});
		
		// Check for previously saved state
		if (savedInstanceState != null) {

			// DONE:
			// Restore value of counters from saved state
			// Only need 4 lines of code, one for every count variable
			savedInstanceState.getInt(CREATE_KEY, mCreate);
			savedInstanceState.getInt(RESTART_KEY, mRestart);
			savedInstanceState.getInt(RESUME_KEY, mResume);
			savedInstanceState.getInt(START_KEY, mStart);		
		}

		// DONE: Emit LogCat message
		Log.i(null, "Entered the onCreate() method");

		// DONE:
		// Update the appropriate count variable
		mCreate++;
		// Update the user interface via the displayCounts() method
		displayCounts();


	}

	// Lifecycle callback overrides

	
	@Override
	public void onStart() {
		super.onStart();

		// DONE: Emit LogCat message
		Log.i(null, "Entered the onStart() method");

		// DONE:
		// Update the appropriate count variable
		mStart++;
		// Update the user interface
		displayCounts();


	}

	@Override
	public void onResume() {
		super.onResume();

		// DONE: Emit LogCat message
		Log.i(null, "Entered the onResume() method");

		// DONE:
		// Update the appropriate count variable
		mResume++;
		// Update the user interface
		displayCounts();

	}

	@Override
	public void onPause() {
		super.onPause();

		// DONE: Emit LogCat message
		Log.i(null, "Entered the onPause() method");

	}

	@Override
	public void onStop() {
		super.onStop();

		// DONE: Emit LogCat message
		Log.i(null, "Entered the onStop() method");

	}

	@Override
	public void onRestart() {
		super.onRestart();

		// DONE: Emit LogCat message
		Log.i(null, "Entered the onRestart() method");

		// DONE:
		// Update the appropriate count variable
		mRestart++;
		// Update the user interface
		displayCounts();


	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// DONE: Emit LogCat message
		Log.i(null, "destroyed");

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// DONE:
		// Save state information with a collection of key-value pairs
		// 4 lines of code, one for every count variable
		savedInstanceState.putInt(CREATE_KEY, mCreate);
		savedInstanceState.putInt(RESTART_KEY, mRestart);
		savedInstanceState.putInt(RESUME_KEY, mResume);
		savedInstanceState.putInt(START_KEY, mStart);	
	}
	
	// Updates the displayed counters
	public void displayCounts() {
		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	
	}
}
package course.labs.activitylab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityTwo";

	// Lifecycle counters

	// DONE:
	// Create counter variables for onCreate(), onRestart(), onStart() and
	// onResume(), called mCreate, etc.
	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called
	int mCreate;
	int mRestart;
	int mStart;
	int mResume;


	// DONE: Create variables for each of the TextViews, called
        // mTvCreate, etc. 
	TextView mTvCreate;
	TextView mTvRestart;
	TextView mTvStart;
	TextView mTvResume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

		// DONE: Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		mTvCreate = (TextView) findViewById(R.id.create); 
		mTvRestart = (TextView) findViewById(R.id.restart);
		mTvStart = (TextView) findViewById(R.id.start);
		mTvResume = (TextView) findViewById(R.id.resume);




		Button closeButton = (Button) findViewById(R.id.bClose); 
		closeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// DONE:
				// This function closes Activity Two
				// Hint: use Context's finish() method
				ActivityTwo.this.finish();

			
			}
		});

		// Check for previously saved state
		if (savedInstanceState != null) {
			savedInstanceState.getInt(CREATE_KEY, mCreate);
			savedInstanceState.getInt(RESTART_KEY, mRestart);
			savedInstanceState.getInt(RESUME_KEY, mResume);
			savedInstanceState.getInt(START_KEY, mStart);
		}

		// DONE: Emit LogCat message
		Log.i(null, "onCreate called");

		// DONE:
		// Update the appropriate count variable
		mCreate++;
		// Update the user interface via the displayCounts() method
		displayCounts();
		
	}

	// Lifecycle callback methods overrides

	@Override
	public void onStart() {
		super.onStart();

		// DONE: Emit LogCat message
		Log.i(null, "onstartCalled");

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
		Log.i(null, "onResume called");

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
		Log.i(null, "onPause called");


	}

	@Override
	public void onStop() {
		super.onStop();

		// DONE: Emit LogCat message
		Log.i(null, "onStop called");


	}

	@Override
	public void onRestart() {
		super.onRestart();

		// DONE: Emit LogCat message
		Log.i(null, "onRestartCalled");

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
		Log.i(null, "onDestroy called");

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		// DONE:
		// Save counter state information with a collection of key-value pairs
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

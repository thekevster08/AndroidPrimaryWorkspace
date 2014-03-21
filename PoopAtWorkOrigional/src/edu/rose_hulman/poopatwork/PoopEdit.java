package edu.rose_hulman.poopatwork;

import edu.rose_hulman.poopatwork.PoopsDbAdapter;
import edu.rose_hulman.poopatwork.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class PoopEdit extends Activity {
	String mDate = "11/12/12";
    String mTimeStart = "123";
    String mTimeEnd = "456";
    String mDuration = "00:15";
    String mAmountEarned = "$45";
    String mRating = "5";    
    
    private Long mRowId;
    private PoopsDbAdapter mDbHelper;
    
    Button startButton, stopButton;
	Chronometer mChronometer;
	TextView amountEarned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poop_edit);
        setTitle("New Poop");

        mChronometer = (Chronometer) findViewById(R.id.chronometer);
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		
        mDbHelper = new PoopsDbAdapter(this);
        mDbHelper.open();
        
        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(NotesDbAdapter.KEY_ROWID);
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID)
                                    : null;
        }
        
        populateFields();
        
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {                
                setResult(RESULT_OK);
                finish();
            }

        });
    }
    private void populateFields(){
    	 if (mRowId != null) {
    	        Cursor note = mDbHelper.fetchNote(mRowId);
    	        startManagingCursor(note);
    	        mTitleText.setText(note.getString(
    	                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TITLE)));
    	        mBodyText.setText(note.getString(
    	                note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
    	    }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }
    
    private void saveState() {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();

        if (mRowId == null) {
            long id = mDbHelper.createNote(title, body);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateNote(mRowId, title, body);
        }
    }
}
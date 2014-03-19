package com.example.poopsdatabasetesttwo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView mTextView, mTextView2, mTextView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatabaseHandler db = new DatabaseHandler(this);
		
	//	Poop poop = new Poop(3,4,5);
	//	db.addPoop(poop);
	//	poop = new Poop(6,7,8);
	//	Log.i("aaaaa","duration is " + Integer.toString(db.getPoop(1).getDuration()));
	//	db.addPoop(poop);
		mTextView = (TextView) findViewById(R.id.text);
		mTextView.setText(db.getPoop(1).toString());
		mTextView1 = (TextView) findViewById(R.id.text1);
		mTextView1.setText(db.getPoop(2).toString());
		//db.getAllPoops();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

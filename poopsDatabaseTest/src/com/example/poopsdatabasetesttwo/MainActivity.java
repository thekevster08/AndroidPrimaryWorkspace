package com.example.poopsdatabasetesttwo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	TextView mTextView, mTextView2, mTextView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatabaseHandler db = new DatabaseHandler(this);
		
	//	populateDatabase();
		mTextView = (TextView) findViewById(R.id.text);
		mTextView.setText(db.getPoop(1).toString());
		mTextView1 = (TextView) findViewById(R.id.text1);
		mTextView1.setText(db.getPoop(2).toString());
		
		List<Poop> values = db.getAllPoops();
		List<String> dates = db.getAllDates();
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_list_item_1, dates);
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void populateDatabase(){
		DatabaseHandler db = new DatabaseHandler(this);
		Poop poop = new Poop(3,4,5);
		db.addPoop(poop);
		poop = new Poop(6,7,8);
		db.addPoop(poop);
		poop = new Poop(1,3,4);
		db.addPoop(poop);
		poop = new Poop(8,9,4);
		db.addPoop(poop);
	}
	
	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}

}

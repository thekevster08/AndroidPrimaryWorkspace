package com.example.savedata;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView mMessage, mDataView, mDataWrite;
	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sharedPref = this.getPreferences(MODE_PRIVATE);
		editor = sharedPref.edit();
		
		mMessage = (TextView) findViewById(R.id.textView1);
		mDataWrite = (TextView) findViewById(R.id.editText1);
		mDataView = (TextView) findViewById(R.id.textView2);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void saveClick(View view){		
		editor.putString("storedMessage", mDataWrite.getText().toString());	
		editor.commit();
		mMessage.setText("saved!");
	}
	
	public void loadClick(View view){
		mDataView.setText(sharedPref.getString("storedMessage", "---"));
	}
}

package edu.rose_hulman.poopatwork;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	static SharedPreferences myPrefs;
	SharedPreferences.Editor prefsEditor;
	EditText editText_salary; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		myPrefs = getSharedPreferences("myPrefs", 0);
		prefsEditor = myPrefs.edit();
		
		editText_salary = (EditText) findViewById(R.id.editText_salary);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void saveButtonClick(View view){
		prefsEditor.putInt("salary", Integer.parseInt(editText_salary.getText().toString()));
		prefsEditor.commit();
	}

}
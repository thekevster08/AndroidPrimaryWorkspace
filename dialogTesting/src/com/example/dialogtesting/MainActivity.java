package com.example.dialogtesting;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	final Context context = this;

	FragmentTransaction ft;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//ft = getFragmentManager().beginTransaction();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void buttonClick(View view){
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View promptView = layoutInflater.inflate(R.layout.prompts, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setView(promptView);
		
		final EditText input = (EditText) promptView.findViewById(R.id.userInput);
		
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id){
					//editTextMainScreen.setText(input.getText());
				}
			})
			.setNegativeButton("Cancel", 
					new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							dialog.cancel();
						}
			});
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();
	}

}

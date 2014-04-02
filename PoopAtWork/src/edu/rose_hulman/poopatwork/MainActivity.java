package edu.rose_hulman.poopatwork;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener{

	Button mStartButton, mStopButton;
	Chronometer mChronometer;
	TextView mAmountEarnedText;
	
	SharedPreferences mPrefs;
	SharedPreferences.Editor mPrefsEditor;
	
	int salary;
	double secondsElapsed;	
	double amountEarned;
	
	String date;
	
	NumberFormat baseFormat;
	
	PoopDatabaseHandler db;
	
	Context context = this;
	
	ArrayAdapter<String> listAdapter;
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 //testing
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
        actionBar.addTab(actionBar.newTab().setText("Home").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("New Poop").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("History").setTabListener(this));

        mStartButton = (Button) findViewById(R.id.startButton);
        mStopButton = (Button) findViewById(R.id.stopButton);
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        
        mStartButton.setOnClickListener(mStartListener);
        mStopButton.setOnClickListener(mStopListener);
        mChronometer.setOnChronometerTickListener(mChronometerTickListener);
		
		db = new PoopDatabaseHandler(this);
		
		amountEarned = 0;
		secondsElapsed = 0;
		
		baseFormat = NumberFormat.getCurrencyInstance(Locale.US);
		
	    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	    	 
	        @Override
	        public void onPageSelected(int position) {
	            // on changing the page
	            // make respected tab selected
	            actionBar.setSelectedNavigationItem(position);
	        }
	     
	        @Override
	        public void onPageScrolled(int arg0, float arg1, int arg2) {
	        }
	     
	        @Override
	        public void onPageScrollStateChanged(int arg0) {
	        }
	    });
	    
	  
		//This is all you need:
//		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//		String syncConnPref = sharedPref.getString(SettingsActivity.KEY_PREF_SYNC_CONN, "");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.action_settings:
    		openSettings();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
	private void openSettings() {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}

	//Listeners
	View.OnClickListener mStopListener = new OnClickListener(){
		public void onClick (View v){
			mChronometer.stop();
			date = getDateTime();			
			Poop poop = new Poop(date, (int) secondsElapsed, (int) amountEarned);
			db.addPoop(poop);
		}
	};

	
	View.OnClickListener mStartListener = new OnClickListener(){
		public void onClick (View v){
			salary = Integer.valueOf(mPrefs.getString("pref_wage", "0"));
			mChronometer.setBase(SystemClock.elapsedRealtime());
			mChronometer.start();
		}
	};
	
	OnChronometerTickListener mChronometerTickListener = new OnChronometerTickListener(){
		@Override
		public void onChronometerTick(Chronometer mChronometer) {
			secondsElapsed ++;
			amountEarned = (secondsElapsed * salary/(2080*60*60));
			mAmountEarnedText.setText(baseFormat.format(amountEarned));
		}
	};
	
	//Helper Methods
	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}
   
	@Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

}


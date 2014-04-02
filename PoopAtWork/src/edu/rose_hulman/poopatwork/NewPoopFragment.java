package edu.rose_hulman.poopatwork;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.rose_hulman.poopatwork.R.id;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;
import android.widget.Toast;
 

public class NewPoopFragment extends Fragment implements OnClickListener{
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
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_newpoop, container, false);
        
        db = new PoopDatabaseHandler(this.getActivity());
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        salary = Integer.valueOf(mPrefs.getString("pref_wage", "0"));
		amountEarned = 0;
		secondsElapsed = 0;
		
		
		baseFormat = NumberFormat.getCurrencyInstance(Locale.US);

		mStartButton = (Button) rootView.findViewById(R.id.startButton);
		mStopButton = (Button) rootView.findViewById(R.id.stopButton);
		mChronometer = (Chronometer) rootView.findViewById(R.id.chronometer);
		mAmountEarnedText = (TextView) rootView.findViewById(R.id.amountEarned);
		
		mStartButton.setOnClickListener(this);
		mStopButton.setOnClickListener(this);
		mChronometer.setOnChronometerTickListener(mChronometerTickListener);
		
//		mStartButton.setOnClickListener(new OnClickListener(){
//			public void onClick (View v){
//			//	int salary = Integer.valueOf(NewPoopFragment.this.mPrefs.getString("pref_wage", "0"));
//			//	mChronometer.setBase(SystemClock.elapsedRealtime());
//			//	mChronometer.start();
//			}
//		});
		//Toast.makeText(getActivity(),"made it here", Toast.LENGTH_SHORT).show();
        return rootView;
    }
    
    
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}
    

	//Listeners
//	OnClickListener mStopListener = new OnClickListener(){
//		public void onClick (View v){
//			mChronometer.stop();
//			date = getDateTime();			
//			Poop poop = new Poop(date, (int) secondsElapsed, (int) amountEarned);
//			db.addPoop(poop);
//		}
//	};

	
	
	
	OnChronometerTickListener mChronometerTickListener = new OnChronometerTickListener(){
		@Override
		public void onChronometerTick(Chronometer mChronometer) {
			secondsElapsed ++;
			amountEarned = (secondsElapsed * salary/(2080*60*60));
			mAmountEarnedText.setText(baseFormat.format(amountEarned));
		}
	};

	@Override
	public void onClick(View v) {
        switch (v.getId()) {
        	case R.id.startButton:
        		salary = Integer.valueOf(mPrefs.getString("pref_wage", "0"));
        		mChronometer.setBase(SystemClock.elapsedRealtime());
        		mChronometer.start();
        		break;
        	case R.id.stopButton:
        		mChronometer.stop();
    			date = getDateTime();			
    			Poop poop = new Poop(date, (int) secondsElapsed, (int) amountEarned);
    			db.addPoop(poop);
    			break;
        }	
	}
}
package com.example.asdf;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Random mRand = new Random();
        
        final TextView xText = (TextView) findViewById(R.id.textView_XCoor);
        final TextView yText = (TextView) findViewById(R.id.textView_YCoor);
        
        xText.setText("---");
        yText.setText("---");
        
        ((Button) findViewById(R.id.button_X)).setOnClickListener(
    		new Button.OnClickListener(){
    			@Override
    			public void onClick(View arg0) {    				
					xText.setText(String.valueOf(mRand.nextInt(200)));
    			}
    		}
    		);
        ((Button) findViewById(R.id.button_Y)).setOnClickListener(
        		new Button.OnClickListener(){
        			@Override
        			public void onClick(View arg0) {    				
    					yText.setText(String.valueOf(mRand.nextInt(200)));
        			}
        		}
        		);
    }
    
    
    
}

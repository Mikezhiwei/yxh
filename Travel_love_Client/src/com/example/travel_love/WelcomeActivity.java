package com.example.travel_love;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	  
	  
	  
	  private  Intent welcomeIntent;
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  super.onCreate(savedInstanceState);
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  this.setContentView(R.layout.welcome_actvitiy);
          this.intiail();
	  }
	  private void intiail()
	  {
		   Timer timer=new Timer();
		   TimerTask task=new TimerTask(){
			         public void run()
			         {
			        	    welcomeIntent=new Intent();
			        	    welcomeIntent.setClass(getApplicationContext(),mPreferenceActivity.class);//激活本地缓存的组件
			        	    WelcomeActivity.this.startActivity(welcomeIntent);
			        	    WelcomeActivity.this.finish();
			         }
		   };
		   timer.schedule(task, 3*1000);
	  }
	  

}

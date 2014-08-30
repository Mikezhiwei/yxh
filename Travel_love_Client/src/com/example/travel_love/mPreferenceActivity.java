package com.example.travel_love;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class mPreferenceActivity extends PreferenceActivity {//缓存本地的某些数据的,在过场动画开始时候热启动
	
	
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		        super.onCreate(savedInstanceState);
		        this.addPreferencesFromResource(R.xml.perference);
		        this.startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
		        this.finish();
		        
	  }

}

package com.example.travel_love;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class mPreferenceActivity extends PreferenceActivity {//���汾�ص�ĳЩ���ݵ�,�ڹ���������ʼʱ��������
	
	
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		        super.onCreate(savedInstanceState);
		        this.addPreferencesFromResource(R.xml.perference);
		        this.startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
		        this.finish();
		        
	  }

}

package com.example.travel_love;

import android.os.Bundle;
import android.preference.PreferenceManager;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;


public class MainActivity extends TabActivity {

	
	private TabHost tabhost;
	private TabHost.TabSpec space;
	
	private RadioGroup choosegroup;
	private RadioButton firstpage;
	private RadioButton tipspage;
	private RadioButton messagepage;
	private RadioButton strategypage;
	private RadioButton userinfopaage;
	
	private Intent  chooseintent;
	private SharedPreferences mSprefer;
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	      //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      this.setContentView(R.layout.index_main_activity);
	    
	      this.tabhost=this.getTabHost();
	      this.mSprefer=PreferenceManager.getDefaultSharedPreferences(this);
	      this.choosegroup=(RadioGroup)this.findViewById(R.id.choosegroup);
	      this.firstpage=(RadioButton)this.findViewById(R.id.firstpage);
	      this.tipspage=(RadioButton)this.findViewById(R.id.tipspage);
	      this.messagepage=(RadioButton)this.findViewById(R.id.messagepage);
	      this.userinfopaage=(RadioButton)this.findViewById(R.id.userinfopage);
	      this.strategypage=(RadioButton)this.findViewById(R.id.strategypage);
	      
	      this.chooseintent=new Intent().setClass(getApplicationContext(),FirstPageActivity.class);
	      this.space=this.tabhost.newTabSpec("firstpage").setIndicator("firstpage").setContent(this.chooseintent);
	      this.tabhost.addTab(space);
	      //加载首页
	      this.chooseintent=new Intent().setClass(getApplicationContext(),TipsActivity.class);
	      this.space=this.tabhost.newTabSpec("tipspage").setIndicator("tipspage").setContent(this.chooseintent);
	      this.tabhost.addTab(space);
	      //加载贴士界面
	      this.chooseintent=new Intent().setClass(getApplicationContext(),MessageActivity.class);
	      this.space=this.tabhost.newTabSpec("messagepage").setIndicator("messagepage").setContent(this.chooseintent);
	      this.tabhost.addTab(space);
	      //加载发送消息界面
	      this.chooseintent=new Intent().setClass(getApplicationContext(),StrategyActivity.class);
	      this.space=this.tabhost.newTabSpec("strategypage").setIndicator("strategypage").setContent(this.chooseintent);
	      this.tabhost.addTab(space);
	      //加载攻略界面
	      this.chooseintent=new Intent().setClass(getApplicationContext(),UserInfoActivity.class);
	      this.space=this.tabhost.newTabSpec("userinfopage").setIndicator("userinfopage").setContent(this.chooseintent);
	      this.tabhost.addTab(space);
	      //加载我的信息的界面
	      this.chooseintent=new Intent().setClass(getApplicationContext(), LoginActiviy.class);
	      this.space=this.tabhost.newTabSpec("login").setIndicator("login").setContent(this.chooseintent);
	      this.tabhost.addTab(space);
	      //加载我的登陆的界面
	      /*this.chooseintent=new Intent().setClass(getApplicationContext(), mPreferenceActivity.class);
	      this.space=this.tabhost.newTabSpec("mPreference").setIndicator("mPreference").setContent(chooseintent);
	      this.tabhost.addTab(space);*/
	      //加载本地缓存
	      this.choosegroup.setOnCheckedChangeListener(tab_listener);
	      this.tabhost.setCurrentTab(0);//设置为随即获取消息作为首页
	      this.firstpage.setChecked(true);
	}
	private  OnCheckedChangeListener tab_listener=new OnCheckedChangeListener(){
              public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			   
            	if(arg1==R.id.firstpage)
  			    {
  			    	  tabhost.setCurrentTabByTag("firstpage");
  			    }else if(arg1==R.id.tipspage)
  			    {
		    	      tabhost.setCurrentTabByTag("tipspage");
  			    }else if(arg1==R.id.messagepage)
  			    {
  			    	if(mSprefer.getString("islogin", "").equals("unlogin"))
  			    	{
  			    		tabhost.setCurrentTabByTag("login");
  			    		Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();

  			    	}else{
  			    		tabhost.setCurrentTabByTag("messagepage");
  			    	}
  			    }else if(arg1==R.id.strategypage)
  			    {
			          tabhost.setCurrentTabByTag("strategypage");
  			    }
  			    else if(arg1==R.id.userinfopage)
  			    {
  			    	 
  			    	if(mSprefer.getString("islogin", "").equals("unlogin"))
  			    	{
  			    		tabhost.setCurrentTabByTag("login");
  			    		Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
  			    	}else{
  			    	    tabhost.setCurrentTabByTag("userinfopage");
  			    	}
  			    }
			    
		}
		
	};
	

	

}

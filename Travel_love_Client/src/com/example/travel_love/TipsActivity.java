package com.example.travel_love;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class TipsActivity extends Activity {
	
	
	    private TextView pre_text;
	    private Button   pre_btn;
	    private Button  pre_xianshi;
	    
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  super.onCreate(savedInstanceState);
          this.setContentView(R.layout.tips_activity);
          //this.initial();
      }
	  /*private void initial()
	  {
		  this.pre_btn=(Button)this.findViewById(R.id.prebtn);
		  this.pre_xianshi=(Button)this.findViewById(R.id.pre_xianshibtn);
		  this.pre_btn.setOnClickListener(new OnClickListener(){
			       public void onClick(View v)
			       {
			    	     //startActivity(new Intent().setClass(getApplicationContext(), .class));
			       }
		  });
		  this.pre_xianshi.setOnClickListener(new OnClickListener(){
			      public void onClick(View v) 
			      {
			    	     SharedPreferences spc1=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			    	     pre_text.setText(spc1.getString("name", ""));
			      }
		  });
		  this.pre_text=(TextView)this.findViewById(R.id.prename);
		  SharedPreferences spc=PreferenceManager.getDefaultSharedPreferences(this);
		  String name=spc.getString("name", "");
		  SharedPreferences.Editor e=spc.edit();
		  e.putString("name","lzw");
		  e.commit();
		  this.pre_text.setText(name);
				  
	  }*/
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
	 		// TODO Auto-generated method stub
	    	 //实现按下两次退出
	    	    if(keyCode==KeyEvent.KEYCODE_BACK)//选择事件
	    	    {
	    	    	   long currentTime=System.currentTimeMillis();
	    	    	   if((currentTime-com.travel_love.paramters.CommonParams.touchTime)>=3000)
	    	    	   {
	    	    		      Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
	    	    	          com.travel_love.paramters.CommonParams.touchTime=currentTime;
	    	    	          return(true);
	    	    	   }else{
	    	    		     return(false);//注：return为true表示系统拦截这个按下的事件,false表示不拦截事件,执行按键
	    	    	   }
	    	    }
	 	        return(super.onKeyDown(keyCode,event));
	 		    
	 		   
	 		
	 	}
	  
}

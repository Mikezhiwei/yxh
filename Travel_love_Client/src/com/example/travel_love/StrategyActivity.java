package com.example.travel_love;

import java.util.HashMap;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class StrategyActivity extends Activity {
	
       
	 private Button btn;
	 private TextView text;
	 
	 private  Map<String,String> map;//参数
	 public static Map<String,String> res_map;//返回结果,作为静态
	 
	 private  ProgressDialog dialog;
	 
	 private  String path=com.travel_love.paramters.CommonParams.URL+"/JSONtest/testjson.php";
	 private  Load_Data_Thread mThread;
	protected void onCreate(Bundle savedInstanceState) 
	{
		  super.onCreate(savedInstanceState);
		  this.setContentView(R.layout.strategy_activity);
		 this.dialog=new ProgressDialog(this);
		  this.btn=(Button)this.findViewById(R.id.test);
		  this.text=(TextView)this.findViewById(R.id.testtext);
		  this.btn.setOnClickListener(new OnClickListener(){
			       public void onClick(View v) 
			       {
			    	    dialog.setTitle("提示信息");
			    	    dialog.setMessage("loading.......");
			    	    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			    	    dialog.show();
			    	   // Map<String,String> params=new HashMap<String,String>();
					    //params.put("name", "lzw");
			    	    map=new HashMap<String,String>();
			    	    map.put("name", "lzw");
			    	    mThread=new Load_Data_Thread(handler,path,map,1);
			    	    mThread.start();
			       }

             });
	}
	/*private class M_Thread extends Thread
	{
		            private Map<String,String> map;
		            
		            private Handler handler;
		            
		            private Bundle bundle=new Bundle();
		            private Message msg;
		            private String JSON_RES;
		            
		            public M_Thread(Handler handler,Map<String,String> map)
		            {
		            	 this.map=map;  
		            	 this.handler=handler;
		                 this.msg=this.handler.obtainMessage();
		            }
		            public void run()
		            {
		            	try{
		                 this.JSON_RES=com.travel_love.utils.HttpUtils.sendHttpCLientPost(path, map, com.travel_love.paramters.CommonParams.encode2);
		                 this.sleep(500);
		                 res_map=com.travel_love.utils.JSONUtils.getJSONObject_islogin(JSON_RES);
		            	 } catch(InterruptedException e){
		            	     this.bundle.putInt("state", 1);
		            	     msg.setData(bundle);
		            	     this.handler.sendMessage(msg);
		            	}
		                this.bundle.putInt("state", 2);
		            	msg.setData(bundle);
		            	this.handler.sendMessage(msg);
		            }
	}*/
	private final Handler handler=new Handler(Looper.getMainLooper()){
		            public void handleMessage(Message msg)
		            {
		            	     int state=msg.getData().getInt("state");
		            	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR){
		            	    	// dialog.dismiss();
		            	    	// Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
		            	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
		            	     {
		            	    	// dialog.dismiss();
		            	    	 //text.setText(res_map.get("islogin"));
		            	    	 //Toast.makeText(getApplicationContext(), "agege", Toast.LENGTH_SHORT).show();
			            	 }
		            }
	};
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

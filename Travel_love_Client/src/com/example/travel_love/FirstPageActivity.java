package com.example.travel_love;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FirstPageActivity extends Activity {
    
	
	 private  ListView  message_random_list;
	 private  TextView  filter;
	 private  ImageView refresh;
	 private  SimpleAdapter  adapter;
	 
	 public static  List<HashMap<String,Object>> res_list;
	 private Map<String,String> params;
	 private Load_Data_Thread mThread;
	 private ProgressDialog dialog;  
	 private  String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_random_msg.php";
	 
	 public static String mNickname;
	 protected void onCreate(Bundle savedInstanceState) 
	 {
		 super.onCreate(savedInstanceState);
		 this.setContentView(R.layout.firstpage_activity);
		 this.initial();
	 }
	 private void initial()
	 { 
		   this.dialog=new ProgressDialog(this);
		   dialog.setTitle("加载最新消息中");
   	       dialog.setMessage("loading.......");
   	       dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		   this.message_random_list=(ListView)this.findViewById(R.id.messagelist_random);
		   this.filter=(TextView)this.findViewById(R.id.filter);
		   this.refresh=(ImageView)this.findViewById(R.id.refresh);
		   this.filter.setOnClickListener(filter_listener);
		   this.refresh.setOnClickListener(refresh_listener);
		   params=new HashMap<String,String>();
	       params.put("name", "lzw");
	       mThread=new Load_Data_Thread(handler,path,params,7);
	       mThread.start();
	       dialog.show();
	 }
	 
	 private OnClickListener filter_listener=new OnClickListener(){
		 public void onClick(View arg0) 
		 {
			   Intent sortintent=new Intent().setClass(getApplicationContext(), SortActivtiy.class);
			  startActivity(sortintent);
         }
     };
	 private  OnClickListener refresh_listener=new OnClickListener(){
		    public void onClick(View v)
		    {
		    	   mThread=new Load_Data_Thread(handler,path,params,7);
			       mThread.start();
			       dialog.show();
		    }
	 };
	 private final Handler handler=new Handler(Looper.getMainLooper()){
         public void handleMessage(Message msg)
         {
         	     int state=msg.getData().getInt("state");
         	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR)
         	     {
         	    	  dialog.dismiss();
         	          Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
         	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
         	     {
         	    	
         	    adapter=new SimpleAdapter(FirstPageActivity.this,res_list,R.layout.message_random_list2,
         	    new String[]{"userhead","req","desnetion","starting","during","location","user_id","start_time","end_time","msg_id"}
         	    ,new int[]{R.id.user_head,R.id.message_article,R.id.destination,R.id.starting,R.id.duringdays,R.id.locattion,R.id.user_id_msg,R.id.startdays_msg,R.id.enddays_msg,R.id.msg_id});
         	    	  dialog.dismiss();
         	    	  message_random_list.setAdapter(adapter);
         	    	  message_random_list.setOnItemClickListener(listview_listenr);
	            }
         }
     };
     private   OnItemClickListener  listview_listenr=new OnItemClickListener(){
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
		           Intent detail_msg_intent=new Intent().setClass(getApplicationContext(), DetaiedMessageActivity.class);
		           Bundle bd=new Bundle();
		           bd.putString("desnetion", res_list.get(arg2).get("desnetion")+"");
		           bd.putString("starting", res_list.get(arg2).get("starting")+"");
		           bd.putString("location",res_list.get(arg2).get("location")+"");
		           bd.putString("user_id", res_list.get(arg2).get("user_id")+"");
		           bd.putString("end_time", res_list.get(arg2).get("end_time")+"");
		           bd.putString("start_time", res_list.get(arg2).get("start_time")+"");
		           bd.putString("req", res_list.get(arg2).get("req")+"");
		           bd.putString("during", res_list.get(arg2).get("during")+"");
		           bd.putString("msg_id", res_list.get(arg2).get("msg_id")+"");
		           detail_msg_intent.putExtras(bd);
		           startActivity(detail_msg_intent);
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

package com.example.travel_love;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyHistoryMessageActivity extends Activity {
	
	    
	    private  Map<String,String> params;
	    private String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_message_person.php";
	    public static   List<HashMap<String,Object>> res_list;
	    private Load_Data_Thread mThread;
	    private ProgressDialog dialog;
	    private SharedPreferences mSprefer;
	    
	    private ListView myhistory_msg;
	    private SimpleAdapter  Madapter;
	   
	    
	    
	    protected void onCreate(Bundle savedInstanceState) 
	    {
		     super.onCreate(savedInstanceState);
		     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     this.setContentView(R.layout.myhistory_message);
		     this.initial();
	    } 
	    private void initial()
	    {     
	           	   this.myhistory_msg=(ListView)this.findViewById(R.id.myhistory_messages_list);
	           	   this.dialog=new ProgressDialog(this);
		    	   dialog.setTitle("加载个人历史信息中");
		    	   dialog.setMessage("loading.......");
		    	   dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		    	   this.mSprefer=PreferenceManager.getDefaultSharedPreferences(this);
	           	   this.params=new HashMap<String,String>();
	           	   this.params.put("user_id",this.mSprefer.getString("user_id", ""));
	           	   this.mThread=new Load_Data_Thread(this.handler,this.path,this.params,8);
	           	   this.mThread.start();
	           	   dialog.show();
	    }
		private final Handler handler=new Handler(Looper.getMainLooper()){
            public void handleMessage(Message msg)
            {
            	     int state=msg.getData().getInt("state");
            	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR){
            	    	  dialog.dismiss();
            	    	  Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
            	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
            	     {
            	            
            	            Madapter=new SimpleAdapter(MyHistoryMessageActivity.this,res_list,R.layout.message_history_list,
            	            		new String[]{"userhead","req","desnetion","starting","location","start_time","end_time","during","msg_id"},
            	            		new  int []{R.id.user_head_mine,R.id.message_req_mine,R.id.destination_mine,R.id.starting_mine,R.id.locattion_mine,R.id.startdays_msg_mine,R.id.enddays_msg_mine,R.id.during_msg_mine,R.id.msg_id_mine});
            	            dialog.dismiss();
            	            myhistory_msg.setAdapter(Madapter);
            	      }
            }
         };


}

package com.example.travel_love;
import com.travel_love.utils.Load_Data_Thread;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DetaiedMessageActivity extends Activity {
	
	
	private  TextView  desnetion;
	private  TextView  starting;
	private  TextView  during;
	private  TextView  location;
	private  TextView  req;
    private  TextView  user_id;//消息发布者的数据库的主键
    private  TextView  start_time;
    private  TextView  end_time;
    private Intent res_intent;
    private  TextView msg_id;
    //以上组件是一条消息所含有的信息,包括数据库主键，外键
    private ListView comment_list;
    
    private  TextView comments;
    private  TextView leave_msg;
    private  TextView  others_userinfo;
    
    private  Map<String,String> params;
    public static  List<HashMap<String,Object>> res_list;
    private String path=com.travel_love.paramters.CommonParams.URL;
    private Load_Data_Thread mThread;
    private SharedPreferences mSprefer;
    private ProgressDialog  dialog;
    
    public static  String m_nickname="";
  
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);	
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.setContentView(R.layout.message_history_list);
	    this.initail();
	}
	private void initail()
	{
		 this.mSprefer=PreferenceManager.getDefaultSharedPreferences(this);
	     m_nickname=this.mSprefer.getString("nick_name", "");
		 this.dialog=new ProgressDialog(this);
	     dialog.setTitle("提示信息");
 	     dialog.setMessage("loading.......");
 	     dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
 	     
		 
 	     this.msg_id=(TextView)this.findViewById(R.id.msg_id_mine);
		 
 	     this.comments=(TextView)this.findViewById(R.id.comments);
		 this.leave_msg=(TextView)this.findViewById(R.id.leavemessage);
		 this.others_userinfo=(TextView)this.findViewById(R.id.others_user_info);
		 
		 this.comment_list=(ListView)this.findViewById(R.id.comments_list);
		 this.desnetion=(TextView)this.findViewById(R.id.destination_mine);
		 this.starting=(TextView)this.findViewById(R.id.starting_mine);
		 this.location=(TextView)this.findViewById(R.id.locattion_mine);
		 this.req=(TextView)this.findViewById(R.id.message_req_mine);
		 this.start_time=(TextView)this.findViewById(R.id.startdays_msg_mine);
		 this.end_time=(TextView)this.findViewById(R.id.enddays_msg_mine);
		 this.user_id=(TextView)this.findViewById(R.id.user_id_mine);//发布消息的人数据库的主键
		 this.during=(TextView)this.findViewById(R.id.during_msg_mine);
		 this.res_intent=this.getIntent();
		 Bundle bd=this.res_intent.getExtras();
		 this.desnetion.setText(bd.getString("desnetion"));
		 this.starting.setText(bd.getString("starting"));
		 this.during.setText(bd.getString("during"));
		 this.location.setText(bd.getString("location"));
		 this.req.setText(bd.getString("req"));
		 this.user_id.setText(bd.getString("user_id"));
		 this.start_time.setText(bd.getString("start_time"));
		 this.end_time.setText(bd.getString("end_time"));
		 this.msg_id.setText(bd.getString("msg_id"));
		 if(this.mSprefer.getString("user_id", "").equals(this.user_id.getText()))//自己查看自己的消息
		 {
			      this.comments.setVisibility(View.GONE);//z暂时设置
			      this.leave_msg.setVisibility(View.GONE);//不留言
			      this.others_userinfo.setVisibility(View.GONE);//不查看资料
		 }else{
			      this.comments.setOnClickListener(load_listener);
			      this.leave_msg.setOnClickListener(comment_listener);
			      this.others_userinfo.setOnClickListener(userinfo_listener);
	    }
    }

	private OnClickListener load_listener =new OnClickListener(){
		       public void onClick(View v)
		       {
		    	        params=new HashMap<String,String>();
		    	        path+="/Res_to_client/Deal_get_comments.php";
		    	        params.put("com_user_id", user_id.getText()+"");
		    	        params.put("com_reply_id", mSprefer.getString("user_id", ""));
		    	        params.put("msg_id", msg_id.getText()+"");
		    	        mThread=new Load_Data_Thread(handler,path,params,9);
		    	        mThread.start();
		    	        dialog.show();
		    	}
	};
	private OnClickListener userinfo_listener=new OnClickListener(){
		      public void onClick(View v)
		       {
		    	     Toast.makeText(getApplicationContext(), "未设置", Toast.LENGTH_SHORT).show();
		       }
	};
	private  OnClickListener  comment_listener=new OnClickListener(){
		      public void onClick(View v)
		      {
		    	      
		      }
	};
	private  Handler handler=new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg)
        {
        	     int state=msg.getData().getInt("state");
        	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR){
        	    	 dialog.dismiss(); 
        	    	 Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
        	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
        	     {
        	    	   SimpleAdapter  adapter=new SimpleAdapter(DetaiedMessageActivity.this,res_list,R.layout.comments_item,
        	    	   new String[]{"com_name","com_reply_name","com_detail","com_time","com_id"},
        	    	   new  int[]{R.id.com_nickname,R.id.com_reply_nickname,R.id.commet_detail,R.id.com_time,R.id.com_id});
            	       comment_list.setAdapter(adapter);
            	       dialog.dismiss();
            	 }
        }
};
}

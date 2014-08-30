package com.example.travel_love;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends Activity{

      
	  private  ListView options;
	  private  ImageButton update_info;
	  private  TextView  nickname;
	  
	  private SimpleAdapter adapter;
	  private ArrayList<HashMap<String,Object>> mylist;
	  
	  private Load_Data_Thread  mThread;
	  private Map<String,String>  params;
	  public  static Map<String,String> res_map;
	  private String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_getinfo.php";
	  private SharedPreferences mSprefer;
	  
	  private int hascache;//表示是否有信息填写好,1表示未完成，2表示完成
	  
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  super.onCreate(savedInstanceState);
          this.setContentView(R.layout.userinfo_activity);
          this.initial();
	  }
	  private void initial()
	  {
		  this.options=(ListView)this.findViewById(R.id.myoption_olist);
		  this.nickname=(TextView)this.findViewById(R.id.nickname);
		  this.update_info=(ImageButton)this.findViewById(R.id.updateinfo_btn);
		  this.mSprefer=PreferenceManager.getDefaultSharedPreferences(this);
		  this.update_info.setOnClickListener(update_info_listener);
		  this.params=new HashMap<String,String>();
		  this.params.put("user_id", mSprefer.getString("user_id", ""));
		  this.mThread=new Load_Data_Thread(handler,path,params,3);
		  this.mThread.start();
	      this.options.setAdapter(getAdapter());
	      this.options.setClickable(true);
	      this.options.setOnItemClickListener(listview_listenr);
	      
	  }
	  private  ArrayList<HashMap<String,Object>> getList()
	  {
		      this.mylist=new ArrayList<HashMap<String,Object>>();
		      
		      HashMap<String,Object> loadmap=new HashMap<String,Object>();
		      loadmap.put("option_icon", R.drawable.message);
		      loadmap.put("option", "我的历史发布");
		      this.mylist.add(loadmap);
		      
		      loadmap=new HashMap<String,Object>();
		      loadmap.put("option_icon", R.drawable.levae_msg);
		      loadmap.put("option", "我的留言");
		      this.mylist.add(loadmap);
		      
		      loadmap=new HashMap<String,Object>();
		      loadmap.put("option", "退出当前帐号");
		      loadmap.put("option_icon", R.drawable.sign_out);
		      this.mylist.add(loadmap); 
		      
		      return(this.mylist);
	  }
	  private SimpleAdapter getAdapter()
	  {
		     this.adapter=new SimpleAdapter(this,this.getList(),
		    		                              R.layout.userinfo_list,
		    		                              new String[]{"option_icon","option"},
		    		                              new int[]{R.id.option_icon,R.id.userinfo_option});
		     return(this.adapter);
	  }
	  private OnClickListener update_info_listener=new OnClickListener(){
             public void onClick(View arg0) {
			// TODO Auto-generated method stub
			     Intent updateintent=new Intent().setClass(getApplicationContext(), UpdateUserInfoActivity.class);
			      updateintent.putExtra("hascache", hascache);
		         startActivity(updateintent);
		         finish();
             }
		     
	  };
	  private Handler handler=new Handler(Looper.getMainLooper()){
          public void handleMessage(Message msg)
          {
          	     int state=msg.getData().getInt("state");
          	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR){
          	       Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
          	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
          	     {
          	    	 if(res_map.get("age").equals("noage"))
          	    	 {
          	    		   Toast.makeText(getApplicationContext(), "你的个人信息未填", Toast.LENGTH_SHORT).show();
          	    		   hascache=1;
          	    	 }else{
          	    		  if(res_map.get("age").equals("null"))
          	    		  {
             	    		   Toast.makeText(getApplicationContext(), "你的个人信息未完善，请完善", Toast.LENGTH_SHORT).show();
             	    		   hascache=1;
                          }else if(res_map.get("nick_name").equals("null"))
          	    		  {
            	    		   Toast.makeText(getApplicationContext(), "你的个人信息未完善，请完善", Toast.LENGTH_SHORT).show();
            	    		   hascache=1;
                          }else if(res_map.get("job").equals("null"))
          	    		  {
            	    		   Toast.makeText(getApplicationContext(), "你的个人信息未完善，请完善", Toast.LENGTH_SHORT).show();
            	    		   hascache=1;
          	    		  }else if(res_map.get("sex").equals("null"))
          	    		  {
            	    		   Toast.makeText(getApplicationContext(), "你的个人信息未完善，请完善", Toast.LENGTH_SHORT).show();
            	    		   hascache=1;
          	    		  }else{
            	        	   SharedPreferences.Editor meditor=mSprefer.edit();
            	        	   meditor.putString("nick_name", res_map.get("nick_name"));
            	        	   meditor.putString("age", res_map.get("age"));
            	        	   meditor.putString("sex", res_map.get("sex"));
            	        	   meditor.putString("job", res_map.get("job"));
            	        	   meditor.commit();
            	        	   nickname.setText(res_map.get("nick_name"));
            	        	   hascache=2;
            	          }
            	     }
	             }
          }
      } ;
      private   OnItemClickListener  listview_listenr=new OnItemClickListener(){
  		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
  				long arg3) {
  		          // List<HashMap<String,Object>> listener_list=res_list;
  		         if(arg2==0)
  		         {
  		        	   startActivity(new Intent().setClass(getApplicationContext(), MyHistoryMessageActivity.class));
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

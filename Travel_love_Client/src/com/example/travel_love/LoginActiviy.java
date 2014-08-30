package com.example.travel_love;

import java.util.HashMap;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;

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
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActiviy extends Activity {
	
	
	  private ImageButton enrol_text;
	  private EditText usermail;
	  private EditText userpassword;
	  private Button   loginbtn;
	  
	  public static  Map<String,String> res_map;
	  private  Map<String,String> params;
	  
	  private  Load_Data_Thread m_Thread;
	  private ProgressDialog dialog;
	  private SharedPreferences mSprefer;
	  private  String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_login.php";
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		     super.onCreate(savedInstanceState);
		     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	         this.setContentView(R.layout.login_activity);
	         this.initial();
	  }
	  private  OnClickListener enroll_text_listener=new OnClickListener(){
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent enrollintent=new Intent();
					enrollintent.setClass(getApplicationContext(),EnrollActivity.class);
					startActivity(enrollintent);
					finish();
					}
	  };
	  private OnClickListener loginbtn_listener=new OnClickListener(){
		         public void onClick(View arg0)
		         {
		        	    dialog.setTitle("登录中");
			    	    dialog.setMessage("loading.......");
			    	    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			    	    dialog.show();
		        	    params=new HashMap<String,String>();
		        	    params.put("usermail",usermail.getText().toString());
		        	    params.put("userpassword", userpassword.getText().toString());
		        	    m_Thread=new Load_Data_Thread(handler,path,params,1);
		        	    m_Thread.start();
		         }
	  };
	  private void initial()
	  {
		          this.dialog=new ProgressDialog(this);
		          this.mSprefer=PreferenceManager.getDefaultSharedPreferences(this);
                  this.loginbtn=(Button)this.findViewById(R.id.loginbtn);
		          this.usermail=(EditText)this.findViewById(R.id.usermail);
		          this.userpassword=(EditText)this.findViewById(R.id.userpassword);
		          this.enrol_text=(ImageButton)this.findViewById(R.id.enrolltext);
		          this.enrol_text.setOnClickListener(enroll_text_listener);
		          this.loginbtn.setOnClickListener(loginbtn_listener);
	  }
      private Handler handler=new Handler(Looper.getMainLooper()){
    	      
    	    	   public void handleMessage(Message msg)
		            {
		            	     int state=msg.getData().getInt("state");
		            	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR)
		            	     {
		            	    	 dialog.dismiss();
		            	    	 Toast.makeText(getApplicationContext(), "登录失败请求查看网络设置", Toast.LENGTH_SHORT).show();
		            	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
		            	     {
		            	    	   if(res_map.get("islogin").equals("nousername"))
		            	    	   {       
		            	    		     dialog.dismiss();
		            	    		     usermail.setText("");
		            	    		     userpassword.setText("");
				            	    	 Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                                   }
		            	    	   else if(res_map.get("islogin").equals("wrongpassword"))
		            	    	   {
		            	    		     dialog.dismiss();
		            	    		     userpassword.setText("");
				            	    	 Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
				            	   }
		            	    	   else if(res_map.get("islogin").equals("login"))
		            	    	   {
		            	    		   dialog.dismiss(); 
		            	    		   Toast.makeText(getApplicationContext(), "登录成功",Toast.LENGTH_SHORT).show();
		            	    		   SharedPreferences.Editor meditor=mSprefer.edit();
		            	    		   usermail.setText("");
		            	    		   userpassword.setText("");
		            	    		   meditor.putString("islogin", "login");
		            	    		   meditor.putString("user_id", res_map.get("user_id"));
		            	    		   meditor.commit();
		            	    		   startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
                    	    		   finish();
		            	    	   }
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

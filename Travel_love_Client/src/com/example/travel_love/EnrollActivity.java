package com.example.travel_love;

import java.util.HashMap;
import java.util.Map;

import com.example.travel_love.R.id;
import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

public class EnrollActivity extends Activity{
	
	   private EditText enrollusername;
	   private EditText enrollpassword1;
	   private EditText enrollpassword2;
	   private Button   enrollbtn;
	   private ImageButton logintext;
	   
	   private Map<String,String> params;
	   public  static Map<String,String> res_map;
	   
	   private Load_Data_Thread mThread;
	   private String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_enroll.php";
	   private  ProgressDialog  dialog;
	   
  	   protected void onCreate(Bundle savedInstanceState) 
	   {
		    super.onCreate(savedInstanceState);
		    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        this.setContentView(R.layout.enroll_activity2);
	        this.initial();
	   }
  	   private  OnClickListener logintext_listener=new OnClickListener()
  	   {
  		        public void onClick(View arg0)
  		        {
  		        	   Intent loginintent=new Intent().setClass(getApplicationContext(), MainActivity.class);
  		        	   startActivity(loginintent);
  		        	   finish();
  		        }
  	   };
  	   private void initial()
  	   {    
  		       this.dialog=new ProgressDialog(this);
  		       this.enrollpassword1=(EditText)this.findViewById(R.id.enrollpassword1);
  		       this.enrollpassword2=(EditText)this.findViewById(R.id.enrollpassword2);
  		       this.enrollusername=(EditText)this.findViewById(R.id.enrollusername);
  		       this.enrollbtn=(Button)this.findViewById(R.id.enrollbtn);
  		       this.logintext=(ImageButton)this.findViewById(R.id.logintext);
  		       this.logintext.setOnClickListener(logintext_listener);
  		       this.enrollbtn.setOnClickListener(new OnClickListener(){
  		    	         public void onClick(View v)
  		    	         {
  		    	        	if(!(enrollpassword1.getText().toString().equals(enrollpassword2.getText().toString())))//两次输入密码不同
  		    	        	{
  		    	        		  Toast.makeText(getApplicationContext(), "两次密码不同", Toast.LENGTH_SHORT).show();
  		    	        		  enrollpassword1.setText("");
  		    	        		  enrollpassword2.setText("");
  		    	            }
  		    	        	else
  		    	            {
  		    	            	if((enrollusername.getText().toString().indexOf("@")<=0))//用户输入错误邮件格式
  		    	            	{
    		    	        		  Toast.makeText(getApplicationContext(), "用户名格式不正确", Toast.LENGTH_SHORT).show();
    		    	                  enrollusername.setText("");
  		    	            	}else if(enrollusername.getText().toString().indexOf("@")==(enrollusername.getText().toString().length()-1))
    		    	            {
  		    	        		      Toast.makeText(getApplicationContext(), "用户名格式不正确", Toast.LENGTH_SHORT).show();
  		    	        		      enrollusername.setText("");
    		    	            }
    		    	             else{//检验格式都正确后进行注册
    		    	            	dialog.setTitle("注册中");
    	  				    	    dialog.setMessage("loading.......");
    	  				    	    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	  				    	    dialog.show();
    	  				    	    params=new HashMap<String,String>();
    	  				    	    params.put("usermail", enrollusername.getText().toString());
    	  				    	    params.put("userpassword", enrollpassword1.getText().toString());
    	  				    	    mThread=new Load_Data_Thread(handler,path,params,2);
    	  				    	    mThread.start();
    		    	            }
  		    	             }
  				    	  }
  		       });
  	    }
  		private  Handler handler=new Handler(Looper.getMainLooper()){
            public void handleMessage(Message msg)
            {
            	     int state=msg.getData().getInt("state");
            	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR)
            	     {
            	    	    dialog.dismiss();
            	            Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
            	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
            	     {
            	    	    dialog.dismiss();
            	    	    if(res_map.get("isEnroll").equals("duplicateusername"))
            	    	    {
                	            Toast.makeText(getApplicationContext(), "已经有用户注册过了，尝试其他用户名注册", Toast.LENGTH_SHORT).show();
                	            enrollusername.setText("");
                	        }else{
                	            Toast.makeText(getApplicationContext(), "注册成功,请尝试登录", Toast.LENGTH_SHORT).show();
                                enrollusername.setText("");
                                enrollpassword1.setText("");
                                enrollpassword2.setText("");
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

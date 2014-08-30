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
  		    	        	if(!(enrollpassword1.getText().toString().equals(enrollpassword2.getText().toString())))//�����������벻ͬ
  		    	        	{
  		    	        		  Toast.makeText(getApplicationContext(), "�������벻ͬ", Toast.LENGTH_SHORT).show();
  		    	        		  enrollpassword1.setText("");
  		    	        		  enrollpassword2.setText("");
  		    	            }
  		    	        	else
  		    	            {
  		    	            	if((enrollusername.getText().toString().indexOf("@")<=0))//�û���������ʼ���ʽ
  		    	            	{
    		    	        		  Toast.makeText(getApplicationContext(), "�û�����ʽ����ȷ", Toast.LENGTH_SHORT).show();
    		    	                  enrollusername.setText("");
  		    	            	}else if(enrollusername.getText().toString().indexOf("@")==(enrollusername.getText().toString().length()-1))
    		    	            {
  		    	        		      Toast.makeText(getApplicationContext(), "�û�����ʽ����ȷ", Toast.LENGTH_SHORT).show();
  		    	        		      enrollusername.setText("");
    		    	            }
    		    	             else{//�����ʽ����ȷ�����ע��
    		    	            	dialog.setTitle("ע����");
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
            	            Toast.makeText(getApplicationContext(), "����ʧ��", Toast.LENGTH_SHORT).show();
            	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
            	     {
            	    	    dialog.dismiss();
            	    	    if(res_map.get("isEnroll").equals("duplicateusername"))
            	    	    {
                	            Toast.makeText(getApplicationContext(), "�Ѿ����û�ע����ˣ����������û���ע��", Toast.LENGTH_SHORT).show();
                	            enrollusername.setText("");
                	        }else{
                	            Toast.makeText(getApplicationContext(), "ע��ɹ�,�볢�Ե�¼", Toast.LENGTH_SHORT).show();
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
           	 //ʵ�ְ��������˳�
           	    if(keyCode==KeyEvent.KEYCODE_BACK)//ѡ���¼�
           	    {
           	    	   long currentTime=System.currentTimeMillis();
           	    	   if((currentTime-com.travel_love.paramters.CommonParams.touchTime)>=3000)
           	    	   {
           	    		      Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�", Toast.LENGTH_SHORT).show();
           	    	          com.travel_love.paramters.CommonParams.touchTime=currentTime;
           	    	          return(true);
           	    	   }else{
           	    		     return(false);//ע��returnΪtrue��ʾϵͳ����������µ��¼�,false��ʾ�������¼�,ִ�а���
           	    	   }
           	    }
        	        return(super.onKeyDown(keyCode,event));
        }


  		
}

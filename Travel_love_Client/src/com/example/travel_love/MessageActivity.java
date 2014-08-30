package com.example.travel_love;

import java.util.Calendar;

import java.util.HashMap;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;
import android.content.Intent;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.PopupWindow;
import android.widget.ImageView;
public class MessageActivity extends Activity {
	
	
    	 private Button  startdays_btn;
    	 private Button  enddays_btn;
    	 private TextView  show_calendar;
    	 
    	 private EditText  desnetion;
    	 private EditText  startring;
    	 private EditText  requestments;
    	 
    	 private  Button  decalrtionbtn;
    	 private  ImageView  cancelbtn;
    	 
    	 public static  Map<String,String> res_map;
    	 private Map<String,String> params;
    	 
    	 private Load_Data_Thread mThread;
    	 private  SharedPreferences mSperfer;
    	 private String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_insert_message.php";
    	 private  ProgressDialog  dialog;
	protected void onCreate(Bundle savedInstanceState) 
	{
              super.onCreate(savedInstanceState);
	          this.setContentView(R.layout.message_send_activity);
	          this.initial();
	}
	private void initial()
	{
		   this.startdays_btn=(Button)this.findViewById(R.id.startday);
		   this.enddays_btn=(Button)this.findViewById(R.id.endday);
		   this.cancelbtn=(ImageView)this.findViewById(R.id.cancel_msg_btn);
		   this.decalrtionbtn=(Button)this.findViewById(R.id.decaltionbtn);
		   this.desnetion=(EditText)this.findViewById(R.id.destination);
		   this.startring=(EditText)this.findViewById(R.id.starting);
		   this.dialog=new ProgressDialog(this);
		   dialog.setTitle("发布中");
   	       dialog.setMessage("loading.......");
   	       dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
   	 
		   this.requestments=(EditText)this.findViewById(R.id.requestment);
		   this.mSperfer=PreferenceManager.getDefaultSharedPreferences(this);
		   this.params=new HashMap<String,String>();
		   this.show_calendar=(TextView)this.findViewById(R.id.show_calendar_text);
		   this.startdays_btn.setOnClickListener(new OnClickListener(){
			     public void onClick(View v)
			     {
			    	       new mPopupWindow(MessageActivity.this,show_calendar,startdays_btn); 
			     }
		   });
		   this.enddays_btn.setOnClickListener(new OnClickListener(){
			     public void onClick(View v)
			     {
			    	       new mPopupWindow(MessageActivity.this,show_calendar,enddays_btn);
			     }
		   });
		   this.cancelbtn.setOnClickListener(new OnClickListener(){
			    public void onClick(View v)
			    {
			    	startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
                    finish();
			    }
		   });
		   this.decalrtionbtn.setOnClickListener(new OnClickListener(){
                public void onClick(View arg0) 
                {
                	 if(desnetion.getText().toString().equals(""))
                	 {
                		  Toast.makeText(getApplicationContext(), "目的地未填写", Toast.LENGTH_SHORT).show(); 
                	 }else if(startring.getText().toString().equals(""))
                	 {
                		Toast.makeText(getApplicationContext(), "出发地未填写", Toast.LENGTH_SHORT).show();
                	 }else if(startdays_btn.getText().toString().equals("起始时间")) 
                	 {
                	    Toast.makeText(getApplicationContext(), "起始时间未填写", Toast.LENGTH_SHORT).show(); 
                	 }else  if(enddays_btn.getText().toString().equals("结束时间"))
                	 {
                		 Toast.makeText(getApplicationContext(),"结束时间未填写" , Toast.LENGTH_SHORT).show();
                     }else 
                     {
                    	String startday="20"+startdays_btn.getText().toString();
                    	String endday="20"+enddays_btn.getText().toString();
                    	String start_elements[]=startday.split("-");
                    	String end_elements[]=endday.split("-");
                    	Calendar cal1=Calendar.getInstance();
                        cal1.set(Calendar.YEAR, Integer.valueOf(start_elements[0]));
                        cal1.set(Calendar.MONTH,Integer.valueOf(start_elements[1]));
                        cal1.set(Calendar.DAY_OF_MONTH, Integer.valueOf(start_elements[2]));
                        Calendar cal2=Calendar.getInstance();
                        cal2.set(Calendar.YEAR, Integer.valueOf(end_elements[0]));
                        cal2.set(Calendar.MONTH, Integer.valueOf(end_elements[1]));
                        cal2.set(Calendar.DAY_OF_MONTH,Integer.valueOf(end_elements[2]));
                        Calendar current=Calendar.getInstance();
                        current.set(Calendar.MONTH, current.get(Calendar.MONTH)+1);
                        current.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)-1);
                        if(cal1.after(cal2))
                        {
                        	
                        	Toast.makeText(getApplicationContext(), "终止日期选择错误", Toast.LENGTH_SHORT).show();
                        }
                        else if(!(cal1.after(current)))
                        {
                        	Toast.makeText(getApplicationContext(), "起始日期选择错误", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {//一切检验无误后才能向服务提交数据
                        	params.put("user_id", mSperfer.getString("user_id",""));
                    	    params.put("desnetion", desnetion.getText().toString());
                    	    params.put("starting",startring.getText().toString());
                    	    params.put("start_time",startday);
                    	    params.put("end_time", endday);
                    	    if(requestments.getText().toString().equals(""))
                    	    {
                    	    	 params.put("req", "无");
                    	    }else{
                    	    	params.put("req", requestments.getText().toString());
                    	    }
                    	    params.put("location", "电子科技大学清水河校区");
                    	    params.put("state", String.valueOf(1));//代表状态正确
                    	    mThread=new Load_Data_Thread(handler,path,params,6);
                        	mThread.start();
                        	dialog.show();
                        }
                	  }
			    }
			       
		   });
	}
   
    private  class mPopupWindow extends PopupWindow{
    	          private CalendarView mCalendar;
    	          private Button  finsh_btn;
    	          public mPopupWindow (Context mContext,View parent,final View showdate)
                  {  
    	        	    View contentView=View.inflate(mContext, R.layout.popupwindow, null);
    	        	    this.setWidth(LayoutParams.FILL_PARENT);
    	        	    this.setHeight(LayoutParams.FILL_PARENT);
    	        	    this.setTouchable(true);
    	        	    this.setFocusable(true);
    	        	    this.setContentView(contentView);
    	        	    this.setBackgroundDrawable(new BitmapDrawable());//必须设置Popupwindow的背景色
    	        	    // this.setBackgroundDrawable(MessageActivity.this.getResources().getDrawable(R.drawable.btn_add_accounts_normal));
    	        	    this.showAsDropDown(parent);
    	        	    this.update();
    	        	    this.mCalendar=(CalendarView)contentView.findViewById(R.id.traveldate_calendar);
    	        	    this.finsh_btn=(Button)contentView.findViewById(R.id.achieve_choose_btn);
    	        	    this.mCalendar.setOnDateChangeListener(new OnDateChangeListener(){
                              public void onSelectedDayChange(CalendarView arg0,
							      int year, int month, int day) {
							      month+=1;//java里面默认月份是从0开始的
							      year-=2000;
							      String date=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
							      
							      if(showdate==MessageActivity.this.startdays_btn)
							      {
							    	  MessageActivity.this.startdays_btn.setText(date);
							      }else if(showdate==MessageActivity.this.enddays_btn)
							      {
							    	  MessageActivity.this.enddays_btn.setText(date);
							      }
                              }
    	        	   });
    	        	   this.finsh_btn.setOnClickListener(new OnClickListener(){
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							    dismiss();
						}
    	                });
    	          }
    };
    private  Handler handler=new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg)
        {
        	     int state=msg.getData().getInt("state");
        	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR){
        	    	     Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
        	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
        	     {
                       desnetion.setText("");
                       startring.setText("");
                       requestments.setText("");
                       startdays_btn.setText("起始时间");
                       enddays_btn.setText("结束时间");
                       dialog.dismiss();
                       if(res_map.get("isInsert").equals("insert"))
                       {
                    	     Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                       }
                       try {
						Thread.sleep(1000);
					   } catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					  }
                       startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
                       finish();
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

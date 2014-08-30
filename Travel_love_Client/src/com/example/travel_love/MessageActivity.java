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
		   dialog.setTitle("������");
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
                		  Toast.makeText(getApplicationContext(), "Ŀ�ĵ�δ��д", Toast.LENGTH_SHORT).show(); 
                	 }else if(startring.getText().toString().equals(""))
                	 {
                		Toast.makeText(getApplicationContext(), "������δ��д", Toast.LENGTH_SHORT).show();
                	 }else if(startdays_btn.getText().toString().equals("��ʼʱ��")) 
                	 {
                	    Toast.makeText(getApplicationContext(), "��ʼʱ��δ��д", Toast.LENGTH_SHORT).show(); 
                	 }else  if(enddays_btn.getText().toString().equals("����ʱ��"))
                	 {
                		 Toast.makeText(getApplicationContext(),"����ʱ��δ��д" , Toast.LENGTH_SHORT).show();
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
                        	
                        	Toast.makeText(getApplicationContext(), "��ֹ����ѡ�����", Toast.LENGTH_SHORT).show();
                        }
                        else if(!(cal1.after(current)))
                        {
                        	Toast.makeText(getApplicationContext(), "��ʼ����ѡ�����", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {//һ�м�����������������ύ����
                        	params.put("user_id", mSperfer.getString("user_id",""));
                    	    params.put("desnetion", desnetion.getText().toString());
                    	    params.put("starting",startring.getText().toString());
                    	    params.put("start_time",startday);
                    	    params.put("end_time", endday);
                    	    if(requestments.getText().toString().equals(""))
                    	    {
                    	    	 params.put("req", "��");
                    	    }else{
                    	    	params.put("req", requestments.getText().toString());
                    	    }
                    	    params.put("location", "���ӿƼ���ѧ��ˮ��У��");
                    	    params.put("state", String.valueOf(1));//����״̬��ȷ
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
    	        	    this.setBackgroundDrawable(new BitmapDrawable());//��������Popupwindow�ı���ɫ
    	        	    // this.setBackgroundDrawable(MessageActivity.this.getResources().getDrawable(R.drawable.btn_add_accounts_normal));
    	        	    this.showAsDropDown(parent);
    	        	    this.update();
    	        	    this.mCalendar=(CalendarView)contentView.findViewById(R.id.traveldate_calendar);
    	        	    this.finsh_btn=(Button)contentView.findViewById(R.id.achieve_choose_btn);
    	        	    this.mCalendar.setOnDateChangeListener(new OnDateChangeListener(){
                              public void onSelectedDayChange(CalendarView arg0,
							      int year, int month, int day) {
							      month+=1;//java����Ĭ���·��Ǵ�0��ʼ��
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
        	    	     Toast.makeText(getApplicationContext(), "����ʧ��", Toast.LENGTH_SHORT).show();
        	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
        	     {
                       desnetion.setText("");
                       startring.setText("");
                       requestments.setText("");
                       startdays_btn.setText("��ʼʱ��");
                       enddays_btn.setText("����ʱ��");
                       dialog.dismiss();
                       if(res_map.get("isInsert").equals("insert"))
                       {
                    	     Toast.makeText(getApplicationContext(), "�����ɹ�", Toast.LENGTH_SHORT).show();
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

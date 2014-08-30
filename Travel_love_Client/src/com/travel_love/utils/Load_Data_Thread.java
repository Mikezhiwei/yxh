package com.travel_love.utils;

import java.util.HashMap;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.example.travel_love.DetaiedMessageActivity;
import com.example.travel_love.EnrollActivity;
import com.example.travel_love.FirstPageActivity;
import com.example.travel_love.LoginActiviy;
import com.example.travel_love.MessageActivity;
import com.example.travel_love.MyHistoryMessageActivity;
import com.example.travel_love.StrategyActivity;
import com.example.travel_love.UpdateUserInfoActivity;
import com.example.travel_love.UserInfoActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Load_Data_Thread extends Thread {
	
	
	              private int  judement;//�ж�����JSON����������ʽ
	              private String path;
	              
	              private String JSON_RES;
	              private Map<String,String> request_map;
	              private Handler mHandler;
	              private Message msg;
	              
	              private List<HashMap<String,Object>> res_list;
	              
	              private Map<String,String>  res_map;
	              private Bundle bundle=new Bundle();
	              
	              private Context mContent;
	              public Load_Data_Thread()
	              {
	            	  
	              }
	              public Load_Data_Thread(Handler mhandler,String path,Map<String,String> params,int way)
	              {
	            	 
	            	      this.mHandler=mhandler;
	            	      this.msg=this.mHandler.obtainMessage();
	            	      this.path=path;
	            	      this.request_map=params;
	            	      this.judement=way;
	              }
	              public Load_Data_Thread(Context mContent,Handler mhandler,String path,Map<String,String> params,int way)
	              {
	            	    this.mContent=mContent;
	            	    this.mHandler=mhandler;
	            	    this.msg=this.mHandler.obtainMessage();
	            	    this.path=path;
	            	    this.request_map=params;
	            	    this.judement=way;
	              }
	              public void run()
		          {
		            try{
		            	 //�õ�����JSON���ַ���
		                  this.JSON_RES=com.travel_love.utils.HttpUtils.sendHttpCLientPost(this.path, this.request_map, com.travel_love.paramters.CommonParams.encode2);
		                  this.sleep(500);
		                  if(this.judement==1)//1�����¼����JSON����
		                  {
		                	 res_map=com.travel_love.utils.JSONUtils.getJSONObject_islogin(JSON_RES);
		                	 LoginActiviy.res_map=res_map;//����ľ�̬�������ø�ֵ
		                   }else if(this.judement==2){//2����ע����������
		                	 res_map=com.travel_love.utils.JSONUtils.getJSONObject_isenroll(JSON_RES);
		                	 EnrollActivity.res_map=res_map;
		                   }else if(this.judement==3){//3�����ȡ������Ϣ
		                	  res_map=com.travel_love.utils.JSONUtils.getJSONObject_userinfo(JSON_RES);
		                	  UserInfoActivity.res_map=res_map;
		                   }else if(this.judement==4){//4�����״�д�������Ϣ
		                	   res_map=com.travel_love.utils.JSONUtils.getJSONObject_isInsert(JSON_RES);
		                	   UpdateUserInfoActivity.res_map=res_map;
		                   }else if(this.judement==5){//5�����޸ĸ�������
		                	   res_map=com.travel_love.utils.JSONUtils.getJSONObject_isUpadte(JSON_RES);
		                	   UpdateUserInfoActivity.res_map=res_map;
		                   }else if(this.judement==6){//6��������Ϣ
		                	   res_map=com.travel_love.utils.JSONUtils.getJSONObject_isInsert(JSON_RES);
		                	   MessageActivity.res_map=res_map;
		                   }else if(this.judement==7){//��ҳ���������
		                	   res_list=com.travel_love.utils.JSONUtils.getJSONArray_messages(JSON_RES);
		                	   FirstPageActivity.res_list=res_list;
		                   }else if(this.judement==8){//�ҵ���ʷ��������Ϣ�Ķ�ȡ
		                	   res_list=com.travel_love.utils.JSONUtils.getJSONArray_messages(JSON_RES);
		                	   MyHistoryMessageActivity.res_list=res_list;
		                   }else if(this.judement==9){//�鿴�Һ����˵������˵���Ϣ�����ۺͻظ�
		                	   res_list=com.travel_love.utils.JSONUtils.getJSONArray_Comments(JSON_RES);
		                       DetaiedMessageActivity.res_list=res_list;
		                   }
		                    
		               } catch(InterruptedException e){
		            	     this.bundle.putInt("state", com.travel_love.paramters.CommonParams.STATE_ERROR);
		            	     msg.setData(bundle);
		            	     this.mHandler.sendMessage(msg);
		            	}
		                this.bundle.putInt("state", com.travel_love.paramters.CommonParams.STATE_RIGHT);
		            	msg.setData(bundle);
		            	this.mHandler.sendMessage(msg);
		           }

}

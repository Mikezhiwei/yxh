package com.travel_love.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.travel_love.DetaiedMessageActivity;
import com.example.travel_love.R;

public class JSONUtils {
	
	   
	     public static List<HashMap<String,Object>> getJSONArray_messages(String params)//获取消息的json解析
	     {
	    	 List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	      try {
				 JSONArray messages=new JSONObject(params).getJSONArray("messages");
			     for(int i=0;i<messages.length();i++)
			     {
			    	   HashMap<String,Object> loadmap=new HashMap<String,Object>();
			    	   JSONObject one_message=(JSONObject)messages.get(i);
			    	   loadmap.put("userhead", R.drawable.p2);//HashMap使用object的泛型的原因
			    	   loadmap.put("user_id", one_message.getString("user_id"));
			    	   loadmap.put("msg_id", one_message.getString("msg_id"));
			    	   loadmap.put("desnetion","目的地  :"+one_message.getString("desnetion"));
			    	   loadmap.put("starting", "出发地  :"+one_message.getString("starting"));
			    	   loadmap.put("start_time", "起始时间 :"+one_message.getString("start_time"));
			    	   loadmap.put("end_time", "结束时间  :"+one_message.getString("end_time"));
			    	   loadmap.put("req", "个人要求   : "+one_message.getString("req"));
			    	   loadmap.put("location", "发布人所在地 :"+one_message.getString("location"));
			    	   String start_elements[]=one_message.getString("start_time").split("-");
			    	   String end_elements[]=one_message.getString("end_time").split("-");
		    		   
			    	   if(Integer.valueOf(start_elements[0])<Integer.valueOf(end_elements[0]))//跨年计算天数，不用数据返回结果
			    	   {
			    		      int startyear=Integer.valueOf(start_elements[0]);
			    		      int endyear=Integer.valueOf(end_elements[0]);
			    		      int starmonth=Integer.valueOf(start_elements[1]);
			    		      int endmonth=Integer.valueOf(end_elements[1]);
			    		      int startday=Integer.valueOf(start_elements[2]);
			    		      int  endday=Integer.valueOf(end_elements[2]);
			    		      int sum=com.travel_love.utils.DateUtils.getDayofYear(startyear, starmonth, startday);
			    		      int sum1=com.travel_love.utils.DateUtils.getDayofYear(endyear, endmonth, endday);
			    		      int sub=sum1+(endyear-startyear)*366-sum;
			    		      loadmap.put("during", "预期天数:"+sub);
			    		      
			    	   }else{//不跨年
			    		  loadmap.put("during", "预期天数 :"+one_message.getString("during"));
			    	   }
			    		//loadmap.put("during", "预期天数 :"+one_message.getString("during"));
                        loadmap.put("start_piont",one_message.getString("start_piont"));//起始时间在一年中的第几天
			    	   list.add(loadmap);//加载信息
			     }
			     return(list);
			   } catch (JSONException e) {
				       e.printStackTrace();
			}
	    	  
	    	  return(list);
	     }
	     public  static Map<String,String> getJSONObject_userinfo(String params)//获取个人信息的json数据解析
	     {
	    	 Map<String,String> loadmap=new HashMap<String,String>();
	         try {
				JSONObject userinfo=new JSONObject(params);
				loadmap.put("job", userinfo.getString("job"));
				loadmap.put("sex", userinfo.getString("sex"));
				loadmap.put("age", userinfo.getString("age"));
				loadmap.put("nick_name", userinfo.getString("nick_name"));
				loadmap.put("user_id", userinfo.getString("user_id"));//数据库表中的外键
				return(loadmap);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 return(loadmap);
	     }
	     public static Map<String,String> getJSONObject_islogin(String params)//解析是否登录的json
	     {
	    	 Map<String,String> loadmap=new HashMap<String,String>();
	    	 try{
	    		 JSONObject login_message=new JSONObject(params);
	    		 loadmap.put("islogin",login_message.getString("islogin"));
	    		 loadmap.put("user_id", login_message.getString("user_id"));
	    		 return(loadmap);
	    	 }catch(JSONException e){
	    		 e.printStackTrace();
	    	 }
	    	 return(loadmap);
	     }
	     public static List<HashMap<String,String>> getJSONArray_tips(String params)//贴士的json数据解析
	     {
	    	 List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	      try {
				 JSONArray messages=new JSONObject(params).getJSONArray("tips");
			     for(int i=0;i<messages.length();i++)
			     {
			    	   HashMap<String,String> loadmap=new HashMap<String,String>();
			    	   JSONObject one_message=(JSONObject)messages.get(i);
			    	   loadmap.put("article", one_message.getString("article"));
			    	   loadmap.put("title", one_message.getString("title"));
			    	   loadmap.put("type",one_message.getString("type"));
			    	   list.add(loadmap);//加载信息
			     }
			     return(list);
			   } catch (JSONException e){
				   e.printStackTrace();
			}
	    	  return(list);
	     }
	     
	     public static List<HashMap<String,String>> getJSONArray_strategies(String params)//攻略json数据解析
	     {
	    	 List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	      try {
				JSONArray messages=new JSONObject(params).getJSONArray("strategies");
			     for(int i=0;i<messages.length();i++)
			     {
			    	   HashMap<String,String> loadmap=new HashMap<String,String>();
			    	   JSONObject one_message=(JSONObject)messages.get(i);
			    	   loadmap.put("interest", one_message.getString("interest"));
			    	   loadmap.put("article", one_message.getString("article"));
			    	   loadmap.put("time",one_message.getString("time"));
			    	   loadmap.put("attention", one_message.getString("attention"));
			    	   list.add(loadmap);//加载信息
			     }
			     return(list);
			   } catch (JSONException e){
				   e.printStackTrace();
			}
	    	  return(list);
	     }
	     
	     public static Map<String,String> getJSONObject_isenroll(String params)//解析是否注册的json
	     {
	    	 Map<String,String> loadmap=new HashMap<String,String>();
	    	 try{
	    		 JSONObject login_message=new JSONObject(params);
	    		 loadmap.put("isEnroll",login_message.getString("isEnroll"));
	    		 return(loadmap);
	    	 }catch(JSONException e){
	    		 e.printStackTrace();
	    	 }
	    	 return(loadmap);
	     }
	     
	     public static Map<String,String> getJSONObject_isUpadte(String params)//解析是否完成个人信息修改的json
	     {
	    	 Map<String,String> loadmap=new HashMap<String,String>();
	    	 try{
	    		 JSONObject login_message=new JSONObject(params);
	    		 loadmap.put("isUpdate",login_message.getString("isUpdate"));
	    		 return(loadmap);
	    	 }catch(JSONException e){
	    		 e.printStackTrace();
	    	 }
	    	 return(loadmap);
	     }
	     public static Map<String,String> getJSONObject_isInsert(String params)//解析是否完成个人信息的json
	     {
	    	 Map<String,String> loadmap=new HashMap<String,String>();
	    	 try{
	    		 JSONObject login_message=new JSONObject(params);
	    		 loadmap.put("isInsert",login_message.getString("isInsert"));
	    		 loadmap.put("encode", login_message.getString("encode"));
	    		 return(loadmap);
	    	 }catch(JSONException e){
	    		 e.printStackTrace();
	    	 }
	    	 return(loadmap);
	     }
	     
	     public static  List<HashMap<String,Object>>  getJSONArray_Comments(String params)//解析评论和回复的json
	     {
	    	    List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	    	    try{
	    	    	JSONArray comments=new JSONObject(params).getJSONArray("comments");
	    	    	for(int i=0;i<comments.length();i++)
	    	    	{
	    	    		  HashMap<String,Object>  loadmap=new HashMap<String,Object>();
	    	    		  JSONObject one_comment=(JSONObject)comments.get(i);
	    	    		  loadmap.put("com_detail",one_comment.get("com_detail"));
	    	    		  loadmap.put("com_time", one_comment.get("com_time"));
	    	    		  if(DetaiedMessageActivity.m_nickname.equals(one_comment.get("com_name")))//区别谁在回复我
	    	    		  {
	    	    			   loadmap.put("com_name", "我");
	    	    		  }else{
	    	    			   loadmap.put("com_name", one_comment.get("com_name"));
	    	    		  }
	    	    		  if(DetaiedMessageActivity.m_nickname.equals(one_comment.get("com_reply_name")))
	    	    		  {
	    	    			  loadmap.put("com_reply_name", "我");
	    	    		  }else{
	    	    			  loadmap.put("com_reply_name", one_comment.get("com_reply_name"));
	    	    		  }
	    	    		  loadmap.put("com_id",one_comment.get("com_id"));
	    	    		  list.add(loadmap);
	    	    	}
	    	    	return(list);
	    	    }catch(JSONException e){
	    	    	e.printStackTrace();
	    	    }
	    	    return(list);
	     }

}

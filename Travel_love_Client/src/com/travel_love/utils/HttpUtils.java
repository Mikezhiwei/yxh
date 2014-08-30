package com.travel_love.utils;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpUtils {

	          public static String sendHttpCLientPost(String path,Map<String,String> params,String encode)
	          {
	        	      List<NameValuePair> list=new ArrayList<NameValuePair>();
	        	      if(params!=null && !params.isEmpty())
	        	      {
	        	    	      for(Map.Entry<String,String> entry:params.entrySet())//foreach语句，将params的键值全部对应与Entry变量中
	        	    	      {
	        	    	    	    //每次迭代中list都添加一个匿名类BasicNameValuePair，作为封装体
	        	    	    	    list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
	        	    	      }
	        	    	      try {
	        	    	    	  //实现一个类似于WEB的表单请求
	        	    	    	//ANDROID 平台的编码格式是UTF-8,但是服务器的数据库编码格式是中文编码,因此,如果使用UTF-8的编码格式来编码，会导致服务器出现乱码问题
								UrlEncodedFormEntity post_entity=new UrlEncodedFormEntity(list,"gb2312");//第二个参数是表单的编码格式
								//使用HTTP_POST方法来请求,path=URL(需要请求的网页)
								HttpPost httpPost=new HttpPost(path);
								//封装实体于表单中
								httpPost.setEntity(post_entity);
								//使用到HTTPCLIENT来作为中间方来发送请求
								try {
									//到此，完成了POST的提交
									HttpResponse response=com.travel_love.utils.CustomsHttpClient.getHttpClient().execute(httpPost);
	                                if(response.getStatusLine().getStatusCode()==200)//判定返回的请求的网页状态编码
	                                {
	                                	 HttpEntity back_entity=response.getEntity();//获得返回对象
	                                	  //调用下列方法将输入流转化为字符串，由于服务器是gbk编码的字符串 ,此处获取从服务器的字符串都要用GBK的编码格式
	                                	 return(getStrem_to_string(back_entity.getContent(),encode));
	                                }
								} catch (ClientProtocolException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        	     }
	        	      return("");    
	          }
	          //此方法将输入流转化为一个字符串
	          public static String getStrem_to_string (InputStream m_inputstream,String encode)
	          {
	        	  try {
	        		   String one_line_string=new String();
	        		   //由于不是读取文件，所以直接使用Reader字符输入,并且InputStreamReader转化字节流
	        	       BufferedReader m_bufferedreader=new BufferedReader(new InputStreamReader(m_inputstream,encode));
	        	       StringBuffer m_stringbuffer=new StringBuffer();
	        	       while((one_line_string=m_bufferedreader.readLine())!=null)
					   {
						     m_stringbuffer.append(one_line_string);//缓存String
					   }
	        	       return(m_stringbuffer.toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				
					}
	        	     return("");    
	          }
	
}

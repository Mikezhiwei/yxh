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
	        	    	      for(Map.Entry<String,String> entry:params.entrySet())//foreach��䣬��params�ļ�ֵȫ����Ӧ��Entry������
	        	    	      {
	        	    	    	    //ÿ�ε�����list�����һ��������BasicNameValuePair����Ϊ��װ��
	        	    	    	    list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
	        	    	      }
	        	    	      try {
	        	    	    	  //ʵ��һ��������WEB�ı�����
	        	    	    	//ANDROID ƽ̨�ı����ʽ��UTF-8,���Ƿ����������ݿ�����ʽ�����ı���,���,���ʹ��UTF-8�ı����ʽ�����룬�ᵼ�·�����������������
								UrlEncodedFormEntity post_entity=new UrlEncodedFormEntity(list,"gb2312");//�ڶ��������Ǳ��ı����ʽ
								//ʹ��HTTP_POST����������,path=URL(��Ҫ�������ҳ)
								HttpPost httpPost=new HttpPost(path);
								//��װʵ���ڱ���
								httpPost.setEntity(post_entity);
								//ʹ�õ�HTTPCLIENT����Ϊ�м䷽����������
								try {
									//���ˣ������POST���ύ
									HttpResponse response=com.travel_love.utils.CustomsHttpClient.getHttpClient().execute(httpPost);
	                                if(response.getStatusLine().getStatusCode()==200)//�ж����ص��������ҳ״̬����
	                                {
	                                	 HttpEntity back_entity=response.getEntity();//��÷��ض���
	                                	  //�������з�����������ת��Ϊ�ַ��������ڷ�������gbk������ַ��� ,�˴���ȡ�ӷ��������ַ�����Ҫ��GBK�ı����ʽ
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
	          //�˷�����������ת��Ϊһ���ַ���
	          public static String getStrem_to_string (InputStream m_inputstream,String encode)
	          {
	        	  try {
	        		   String one_line_string=new String();
	        		   //���ڲ��Ƕ�ȡ�ļ�������ֱ��ʹ��Reader�ַ�����,����InputStreamReaderת���ֽ���
	        	       BufferedReader m_bufferedreader=new BufferedReader(new InputStreamReader(m_inputstream,encode));
	        	       StringBuffer m_stringbuffer=new StringBuffer();
	        	       while((one_line_string=m_bufferedreader.readLine())!=null)
					   {
						     m_stringbuffer.append(one_line_string);//����String
					   }
	        	       return(m_stringbuffer.toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				
					}
	        	     return("");    
	          }
	
}

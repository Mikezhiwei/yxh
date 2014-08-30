package com.travel_love.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class CustomsHttpClient {
	    
	       private static HttpClient customsHttpClient;
	       
	       public static synchronized HttpClient getHttpClient()//�������ģʽ
	       {
	    	    if(null==customsHttpClient)
	    	    {
	    	    	  customsHttpClient=new DefaultHttpClient();
	    	     }
	    	    return(customsHttpClient);   
	       }
	       
	       

}

package com.travel_love.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
          
	public static int  getDayofYear(int year,int month,int day)
	{
		int sum=0;
		for(int i=0;i<month;i++)
		{
			if(i==1)
			{
				sum+=31;
			}else if(i==3){
				sum+=31;
			}else if(i==5){
				sum+=31;
			}else if(i==7){
				sum+=31;
			}else if(i==8){
				sum+=31;
			}else if(i==10){
				sum+=31;
			}else if(i==12){
				sum+=31;
			}else if(i==4){
				sum+=30;
			}else if(i==6){
				sum+=30;
			}else if(i==9){
				sum+=30;
			}else if(i==11){
				sum+=30;
			}else if(i==2){
				if(year%4==0&&year%100!=0)
				{
					sum+=29;
					
				}else{
					sum+=28;
				}
			}
		}
		sum+=day;
		return(sum);
	}
	public static String getCurrentTime()
	{      
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String currenttime = dateFormat.format( now ); 
		return(currenttime);
	}
	
}

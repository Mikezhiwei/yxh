package com.example.travel_love;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SortActivtiy extends Activity {
	
	    
	   private  TextView backto_firstpage;
	   private  ListView sort_list;
	   private  SimpleAdapter adapter;
	   private  ArrayList<HashMap<String,Object>> sort_arraylist;
	   
	   protected void onCreate(Bundle savedInstanceState) 
	   {
		    super.onCreate(savedInstanceState);
		    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		   // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		    this.setContentView(R.layout.sort_activity2);
		    this.initial();
	   }
	   private void initial()
	   {
		      this.backto_firstpage=(TextView)this.findViewById(R.id.back_to_firstpage);
              this.sort_list=(ListView)this.findViewById(R.id.sort_list);
              this.sort_list.setAdapter(getAdapter());
              this.backto_firstpage.setOnClickListener(new OnClickListener(){
            	        public void onClick(View v)
            	        {
            	        	    Intent bacttofirstpage_intent=new Intent().setClass(getApplicationContext(),MainActivity.class);
            	        	    startActivity( bacttofirstpage_intent);
            	        	    finish();
            	        }
              });
	   }
	   private SimpleAdapter getAdapter()
	   {
		      this.adapter=new SimpleAdapter(this,this.getList(),R.layout.userinfo_list,new String[]{"option"},new int[]{R.id.userinfo_option});
		      return(this.adapter);
	   }
	   private ArrayList<HashMap<String,Object>> getList()
	   {
		     this.sort_arraylist=new ArrayList<HashMap<String,Object>>();
		     HashMap<String,Object> loadmap=new HashMap<String,Object>();
		     loadmap.put("option", "按照目的地");
		     this.sort_arraylist.add(loadmap);
		     loadmap=new HashMap<String,Object>();
		     loadmap.put("option", "按照预期天数");
		     this.sort_arraylist.add(loadmap);
		     loadmap=new HashMap<String,Object>();
		     loadmap.put("option", "按照所在省市");
		     this.sort_arraylist.add(loadmap);
		     return(this.sort_arraylist);
	   }

}

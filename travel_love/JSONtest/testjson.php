<?php
     $testJSON=array('name'=>"��־ΰ",'value'=>"test");
     foreach($testJSON as $key=>$value)
     {
     	   $testJSON[$key]=urlencode($value);
     }
      $encode = mb_detect_encoding($testJSON['name'], array("ASCII","gbk","utf-8","big5")); 
      echo $encode;
       foreach($testJSON as &$value)
       {
       	   $value=urlencode($value);
       	   $encode = mb_detect_encoding($testJSON['name'], array("ASCII","gbk","utf-8","big5")); 
           echo $encode;  
       }
        
     echo urldecode(json_encode($testJSON));
     
     
     class Person{
     	  public $islogin;
     }
    /*$obj1=new Person();
     $obj1->article="����";
     $obj2=new Person();
     $obj2->article="С˵";
     $arr=array($obj1,$obj2);
     foreach($arr as $key=>$value)
     {
     	  $arr[$key]->article=urlencode($value->article);
     }
     echo  urldecode(json_encode($arr));*/
     $obj=new Person();
     $arr=array();
     $obj->islogin="yes";
     echo  json_encode($obj);
     /*$obj=new Person();
       $obj->name="����2";
     $obj->job="ѧ��2" ;
     $obj->nickname="2";
     $obj->age=20;
     $obj->sex="Ů";
     $obj->user_id="1";;
     $arr[1]=$obj;*/
     
 ?>
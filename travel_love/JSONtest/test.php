<?php
     /*$arr=array('name'=>"��־ΰ ",'nick'=>"lzw",'email'=>"lzw00000@qq.com");
     $arr['name']=iconv("gbk","utf-8",$arr['name']);
     $encode = mb_detect_encoding($arr['name'], array("ASCII","gbk","utf-8","big5")); 
     echo $encode."<br>";
     $json_string=json_encode($arr);
     echo $json_string;*/
   // $testJSON=array('name'=>'�����ַ���','value'=>'test');  
    //echo json_encode($testJSON);  
    /*foreach ( $testJSON as $key => $value ) {  
        $testJSON[$key] = urlencode ( $value );  
    }  
    echo urldecode ( json_encode ( $testJSON ) ); */
     /*$testJSON=array('name'=>"��־ΰ",'value'=>"test");
     foreach($testJSON as $key=>$value)
     {
     	   $testJSON[$key]=urlencode($value);
     }
     echo urldecode(json_encode($testJSON));//php����jsonʱ�����������*/
   $json_string='{"id":1,"name":"foo","email":"foo@foobar.com","interest":["wordpress","php"]}'; 
   $obj=json_decode($json_string); 
   echo $obj->name; 
   echo $obj->id;
   echo $obj->email;
   echo print_r($obj->interest);
   
     
?>
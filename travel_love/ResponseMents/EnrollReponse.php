<?php
include_once("../db_info/User_Mapper.php");
include_once("../Common_parameter/commom_parameter.php");
include_once("Repsonse.php");
include_once("../JSONUtils/isEnroll.php");
class EnrollReponse{
          
               private  $user_mapper;
               private  $user;
               private  $is_enroll;
               public function __construct()
               { 
               	    $this->user_mapper=new User_Mapper(Com_Parameter::DB_NAME);
               	    $this->user=new User();
               	    $this->is_enroll=new isEnroll();
               }
               public function  ensure_enroll($username,$password)
               {
               	   $this->user->SetUser_mail($username);
               	   $this->user->SetPassword($password);
               	   if($this->user_mapper->Insert($this->user)==-1)
               	   {
               	   	     $this->is_enroll->isEnroll="duplicateusername";
               	   	     echo  json_encode($this->is_enroll);
               	   }else{
               	   	     $this->is_enroll->isEnroll="enroll";
               	   	     echo  json_encode($this->is_enroll);
               	   }
               }
               
}
?>
<?php
include_once("../db_info/User_Mapper.php");
include_once("../Common_parameter/commom_parameter.php");
include_once("Repsonse.php");
include_once("../JSONUtils/isLogin.php");
class  LoginResponse {
	       
	           private  $user_mapper;
	           private  $login_info;
	           private  $user;
	           public function __construct()
	           {
	           	  $this->user_mapper=new  User_Mapper(Com_Parameter::DB_NAME);
	           	  $this->login_info=new isLogin();
	           }
	           public function ensure_login($username,$password)
	           {
	                	$this->user=$this->user_mapper->GetById($username);
	                	if(empty($this->user))
	                	{
	                		 $this->login_info->islogin="nousername";
	                		 echo  json_encode($this->login_info);
	                   }else{
	                   	     if(strcmp($password,$this->user->GetPassword())!=0)
	                   	     {
	                   	     	$this->login_info->islogin="wrongpassword";
	                   	     	echo json_encode($this->login_info);
	                   	     }else{
	                   	     	$this->login_info->islogin="login";
	                   	     	$this->login_info->user_id=$this->user->GetId();
	                   	     	echo json_encode($this->login_info);
	                   	     }
	                   }  
	           }
	            
}
?>
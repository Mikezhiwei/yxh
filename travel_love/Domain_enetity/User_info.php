<?php
include_once("Domain_object.php");

class User_info extends  DomainObject{
	    
	         private  $user_id;
	         private  $nick_name;
	         private  $user_sex;
	         private  $user_age;
	         private  $user_job;
	         
	         
	         public function SetUser_id($userid)
	         {
	         	   $this->user_id=$userid;
	         }
	         public function SetNick_name($nickname)
	         {
	         	   $this->nick_name=$nickname;
	         }
	         public function SetUser_sex($usersex)
	         {
	         	   $this->user_sex=$usersex;
	         }
	         public function SetUser_age($useage)
	         {
	         	   $this->user_age=$useage;
	         }
	         public function SetUser_job($userjob)
	         {
	         	   $this->user_job=$userjob;
	         }
	         public function GetUser_id()
	         {
	         	return($this->user_id);//数据库中表的外键
	         }
	         public function GetUser_age()
	         {
	         	  return($this->user_age);
	         }
	         public function GetUser_sex()
	         {
	         	  return($this->user_sex);
	         }
	         public function GetUser_job()
	         {
	         	  return($this->user_job);
	         }
	         public function GetNick_name()
	         {
	         	return($this->nick_name);
	         }
	         public function addSpace(DomainObject $object)
	         {
	         	   
	         }
	         public function getSpace()
	         {
	         	 
	         }
}
?>
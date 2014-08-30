<?php
include_once("../db_info/UserInfo_Mapper.php");
include_once("../Common_parameter/commom_parameter.php");
include_once("Repsonse.php");
include_once("../JSONUtils/Userinfo.php");
include_once("../JSONUtils/isInert.php");
include_once("../JSONUtils/isUpade.php");
class    UserinfoResponse{
	         
	             private $user_info;
	             private $userinfo_mapper;
	             private $user_info_json;
	             private $isupdate;
	             private $isinsert;
	             
	             public function __construct()
	             {
	             	  $this->userinfo_mapper=new UserInfo_Mapper(Com_Parameter::DB_NAME);
	             	  $this->user_info_json=new Userinfo();
	             	  $this->isinsert=new isInert();
	             	  $this->isupdate=new isUpdate();
	             }
	             public function response_userinfo($user_id)
	             {
	                   $this->user_info=$this->userinfo_mapper->GetById($user_id);
	                   if(empty($this->user_info))
	                   {
	                   	        $this->user_info_json->age="noage";
	                   	        echo  json_encode($this->user_info_json);
	                   }else{
	                   	        $this->user_info_json->user_id=urlencode($this->user_info->GetUser_id());//╦Ёж╣╡╒грв╙бК
	                   	        $this->user_info_json->job=urlencode($this->user_info->GetUser_job());
	                   	        $this->user_info_json->age=urlencode($this->user_info->GetUser_age());
	                   	        $this->user_info_json->sex=urlencode($this->user_info->GetUser_sex());
	                   	        $this->user_info_json->nick_name=urlencode($this->user_info->GetNick_name());
	                   	        echo  urldecode(json_encode($this->user_info_json));  
	                   }	     
	             }
	             public function repsonse_insertinfo($user_id,$user_job,$user_age,$user_sex,$user_nick_name)
	             {
	             	   $this->user_info=new User_info();
	             	   $this->user_info->SetUser_job($user_job);
	                   $this->user_info->SetUser_sex($user_sex);
	                   $this->user_info->SetUser_id($user_id);
	                   $this->user_info->SetUser_age($user_age);
	                   $this->user_info->SetNick_name($user_nick_name);
	                   if($this->userinfo_mapper->Insert($this->user_info)==1)
	                   {
	                   	     $this->isinsert->isInsert="insert";
	                   	     $this->isinsert->encode=$encode = mb_detect_encoding($user_job, array("ASCII","gbk","utf-8","big5"));
	                   	     echo  json_encode($this->isinsert);
	                   }else {
	                   	     $this->isinsert->isInsert="noinsert";
	                   	     echo json_encode($this->isinsert);
	                   }
	             }
	             public function  reponse_updateinfo($user_id,$user_job,$user_age,$user_sex,$user_nick_name)
	             {
	             	    $param=array('nickname'=>$user_nick_name,'user_sex'=>$user_sex,'user_age'=>$user_age,'user_job'=>$user_job);
	             	    if($this->userinfo_mapper->Update($param,$user_id)==1)
	             	    {
	             	    	 $this->isupdate->isUpdate="update";
	             	    	 echo  json_encode($this->isupdate);
	             	    }else{
	             	    	 $this->isupdate->isUpdate="noupdate";
	             	    	 echo  json_encode($this->isupdate);
	             	    }
	             }
}
                 
?>
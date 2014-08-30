<?php
include_once("../db_info/Message_mapper.php");
include_once("../Common_parameter/commom_parameter.php");
include_once("Repsonse.php");
include_once("../JSONUtils/isInert.php");
include_once("../JSONUtils/Message.php");
class  MessagesReponse {
	            
	              private $message_mapper;
	              private $message;
	              private $isInsert;
	              private $message_json;
	              
	              public function __construct()
	              {
	              	   $this->message_mapper=new Message_Mapper(Com_Parameter::DB_NAME);
	              	   $this->isInsert=new isInert();
	              }
	              public function  response_insert_message($user_id,$desnetion,$starting,$start_time,$end_time,$requesmments,$loaction,$state)
	              {
	              	   $this->message=new Message();
	              	   $this->message->SetUser_id($user_id);
	              	   $this->message->SetDesnetion($desnetion);
	              	   $this->message->SetStarting($starting);
	              	   $this->message->SetStart_time($start_time);
	              	   $this->message->SetEnd_time($end_time);
	              	   $this->message->Setrequestments($requesmments);
	              	   $this->message->SetLoaction($loaction);
	              	   $this->message->SetState($state);
	              	   if($this->message_mapper->Insert($this->message)==1)
	              	   {
	              	   	        $this->isInsert->isInsert="insert";
	              	   	        echo json_encode($this->isInsert);
	              	   }else{
	              	   	        $this->isInsert->isInsert="noinsert";
	              	   	        echo  json_encode($this->isInsert);
	              	   }
	              }
	              public function response_random_messages()//请求随机的消息
	              {
	              	     $this->message_mapper->doGetCluster_serval(array('params'=>"1"),4);
	              	     $pos=0;
	              	     $msg_arr=array();
	              	     foreach ($this->message_mapper->GetCollection() as  $value)
                        {
              	           $this->message_json=new Message_json();
              	           $this->message_json->msg_id=urlencode($value->GetId());
              	           $this->message_json->user_id=urlencode($value->GetUser_id());
              	           $this->message_json->desnetion=urlencode($value->GetDesnetion());
              	           $this->message_json->starting=urlencode($value->GetStarting());
              	           $this->message_json->start_time=urlencode($value->GetStart_time());
              	           $this->message_json->end_time=urlencode($value->GetEnd_time());
              	           $this->message_json->req=urlencode($value->Getrequestments());
              	           $this->message_json->location=urlencode($value->GetLoaction());
              	           $this->message_json->during=urlencode($value->GetStart_piont());///
              	           $this->message_json->start_piont=urlencode($value->GetDuring_day());///
              	           $msg_arr[$pos]=$this->message_json;
              	           $pos++;
              	         }
              	         $Arr['messages']=$msg_arr;
              	         echo urldecode(json_encode($Arr));
	              }
	              public function  response_specieal_message($param,$way)//按照目的地 (一级查询))1, 按照预期天数(二级查询) 2, 按照个人自己所发布的 3
	              {
	              	     $this->message_mapper->doGetCluster_serval(array('params'=>$param),$way);
	              	     $pos=0;//加载json数组
	              	     $msg_arr=array();
	              	     foreach ($this->message_mapper->GetCollection() as  $value)
                        {
              	           $this->message_json=new Message_json();
              	           $this->message_json->msg_id=urlencode($value->GetId());
              	           $this->message_json->user_id=urlencode($value->GetUser_id());
              	           $this->message_json->desnetion=urlencode($value->GetDesnetion());
              	           $this->message_json->starting=urlencode($value->GetStarting());
              	           $this->message_json->start_time=urlencode($value->GetStart_time());
              	           $this->message_json->end_time=urlencode($value->GetEnd_time());
              	           $this->message_json->req=urlencode($value->Getrequestments());
              	           $this->message_json->location=urlencode($value->GetLoaction());
              	           $this->message_json->during=urlencode($value->GetStart_piont());///
              	           $this->message_json->start_piont=urlencode($value->GetDuring_day());///
              	           $msg_arr[$pos]=$this->message_json;
              	           $pos++;
              	         }
              	         $Arr['messages']=$msg_arr;
              	         echo urldecode(json_encode($Arr));
	              }
	              
	            
}
?>
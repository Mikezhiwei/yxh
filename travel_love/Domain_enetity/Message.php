<?php
include_once("Domain_object.php");
class Message extends DomainObject{
	         
	         private  $user_id;
	         private  $desnetion;
	         private  $starting;
	         private  $start_time;
	         private  $end_time;
	         private  $requestments;
	         private  $location;
	         private  $state;
	         
	         private  $during_day;//持续时间段
	         private  $start_piont;//开始的时间点
	         
	         
	         public function addSpace(DomainObject $object)
	         {
	         	
	         }
	         public  function getSpace()
	         {
	         	
	         }
	         public function  SetUser_id($user_id)
	         {
	         	     $this->user_id=$user_id;
	         }
	         public function  GetUser_id()
	         {
	         	  return($this->user_id);
	         }
	         public  function  SetDesnetion($desnetion)
	         {
	         	   $this->desnetion=$desnetion;
	         }
	         public  function  GetDesnetion()
	         {
	         	  return($this->desnetion);
	         }
	         public function  SetStarting($starting)
	         {
	         	  $this->starting=$starting;
	         }
	         public function  GetStarting()
	         {
	         	  return($this->starting);
	         }
	         public  function  SetStart_time($start_time)
	         {
	         	  $this->start_time=$start_time;
	         }
	         public function  GetStart_time()
	         {
	         	   return($this->start_time);
	         }
	         public  function SetEnd_time($end_time)
	         {
	         	  $this->end_time=$end_time;
	         }
	         public  function GetEnd_time()
	         {
	         	  return($this->end_time);
	         }
	         public  function  Setrequestments($requesements)
	         {
	             $this->requestments=$requesements;	  
	         }
	         public function  Getrequestments()
	         {
	         	return($this->requestments);
	         }
	         public function  SetLoaction($loaction)
	         {
	         	 $this->location=$loaction;
	         }
	         public function  GetLoaction()
	         {
	         	return($this->location);
	         }
	         public function   SetState($state)
	         { 
	         	   $this->state=$state;
	         }
	         public function  GetState()
	         {
	         	  return($this->state);
	         }
	         public function  SetDuring_day($during_day)
	         {
	         	$this->during_day=$during_day;
	         }
	         public function  GetDuring_day()
	         {
	         	 return($this->during_day);
	         }
	         public function   SetStart_piont($start_piont)
	         {
	         	  $this->start_piont=$start_piont;
	         }
	         public function   GetStart_piont()
	         {
   	         	 return($this->start_piont);
	         }
	     }
?>
<?php
include_once("Domain_object.php");
class Commuication extends DomainObject{
	
	
	       private  $detail_com;//回复的具体内容
	       private  $com_time;//回复的具体时间
	       private  $replyname;//被回复人的呢称,查询得到的
	       private  $comment_name;//评论人的呢陈,查询得到的
	       
	       private  $msg_id;//插入时候用的
	       private  $com_user_id;//插入时候用的
	       private  $com_reply_id;//插入时候用的
	       
	       public   function addSpace(DomainObject $object)
	       {
	       	
	       }
	       public  function getSpace()
	       {
	       	   
	       }
	       public function  SetDetail_com($detail_com)
	       {
	       	    $this->detail_com=$detail_com;
	       }
	       public  function  GetDeatail_com()
	       {
	       	 return($this->detail_com);
	       }
	       public function  SetCom_time($detail_time)
	       {
	       	   $this->com_time=$detail_time;
	       }
	       public function GetCom_time()
	       {
	       	  return($this->com_time);
	       }
	       public function  SetReplyName($replyname)
	       {
	       	   $this->replyname=$replyname;
	       }
	       public function  GetRelyName()
	       {
	       	  return($this->replyname);
	       }
	       public function SetCommentName($commentname)
	       {
	       	   $this->comment_name=$commentname;
	       }
	       public function  GetCommentName()
	       {
	       	   return($this->comment_name);
	       }
	       public function  SetMsg_id($msg_id)
	       {
	       	   $this->msg_id=$msg_id;
	       }
	       public function  GetMsg_id()
	       {
	       	  return($this->msg_id);
	       }
	       public function  SetCom_user_id($com_user_id)
	       {
	       	     $this->com_user_id=$com_user_id;
	       }
	       public  function  GetCom_user_id()
	       {
	       	    return($this->com_user_id);
	       }
	       public function  SetCom_rely_id($com_reply_id)
	       {
	       	   $this->com_reply_id=$com_reply_id;
	       }
	       public function GetCom_Reply_id()
	       {
	       	  return($this->com_reply_id);
	       }
}
?>
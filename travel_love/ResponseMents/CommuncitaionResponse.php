<?php
include_once("../db_info/Communication_Mapper.php");
include_once("../Common_parameter/commom_parameter.php");
include_once("Repsonse.php");
include_once("../JSONUtils/comment.php");
include_once("../JSONUtils/isInert.php");
 class  CommentReponse {
 	    
 	       private  $comment;
 	       private  $coment_json;
 	       private  $comment_mapper;
 	       private  $isinert;
 	       
 	       public function __construct()
 	       {
 	       	     $this->coment_json=new comment_json();
 	       	     $this->isinert=new isInert();
 	       	     $this->comment_mapper=new Communication_Mapper(Com_Parameter::DB_NAME);
 	       }
 	       public function response_comments($com_user_id,$com_reply_id,$msg_id)//�����˵�ID�����ǻظ��˵�ID��˳����ʵ�����
           {
           	   // $this->comment_mapper->doGetCluster(array('com_user_id'=>1,'com_reply_id'=>24));
        	    $this->comment_mapper->doGetCluster(array('com_user_id'=>$com_user_id,'com_reply_id'=>$com_reply_id));       	
                $pos=0;
                $arr=array();
                foreach($this->comment_mapper->GetCollection() as $value)
                {
                    if($value->GetMsg_id()!=$msg_id){
                    	continue;
                    }
                    else
                    {                  	 
                 	   $this->coment_json=new comment_json();//**����һ��˼����
                 	   $this->coment_json->com_id=urlencode($value->GetId());//���۵�����
                 	   $this->coment_json->com_name=urlencode($value->GetCommentName());//�������۵��˵��س�
                 	   $this->coment_json->com_detail=urlencode($value->GetDeatail_com());//���۵�����
                 	   $this->coment_json->com_reply_name=urlencode($value->GetRelyName());//���ظ����˵��س�
                 	   $this->coment_json->com_time=urlencode($value->GetCom_time());//���۵�ʱ��
                 	   $arr[$pos]=$this->coment_json;
                       $pos++;
                    }
                 }
                 $Arr['comments']=$arr;
                 echo  urldecode(json_encode($Arr));
           }
           public function response_insert_comments($com_user_id,$com_reply_id,$com_detail,$com_time,$msg_id)
           {
           	     $this->comment=new  Commuication();
           	     $this->comment->SetCom_user_id($com_user_id);
           	     $this->comment->SetCom_rely_id($com_reply_id);
           	     $this->comment->SetDetail_com($com_detail);
           	     $this->comment->SetCom_time($com_time);
           	     $this->comment->SetMsg_id($msg_id);
           	     if($this->comment_mapper->Insert($this->comment)==1)
           	     {
                       $this->isinert->isInsert="insert";
                       echo json_encode($this->isinert);	     	       
           	     }else
           	     {
           	     	  $this->isinert->isInsert="noinsert";
           	     	  echo json_encode($this->isinert);
           	     }
           }
 }                
                 
?>
<?php
include_once("Mapper.php");
include_once("../Domain_enetity/Commuication.php");
include_once("Communciation_Collection.php");
class  Communication_Mapper extends Mapper{
	       
	         private $commments;
	         private $comments_colletion;
	         private static $INSERT_SQL="INSERT INTO comments VALUES('',?,?,?,?,?,1)";//插入评论的SQL语句
	         private static $SELECT_SQL="SELECT com_id,com_detail,t1.nickname,t2.nickname,com_time,msg_id from comments,use_info t1,use_info t2 where
                                         (com_user_id=? AND com_reply_id=? AND com_user_id=t1.user_id AND com_reply_id=t2.user_id )or
                                         (com_user_id=? AND com_reply_id=? AND com_user_id=t1.user_id AND com_reply_id=t2.user_id) order by com_time desc" ;
                                         //多表查询,用到了单表的镜像问题,查询一条消息的个人的评论和恢复
             private static $SELECT_SQL2="select t1.nickname,t1.user_id,t2.nickname,t2.user_id,comments.com_user_id,comments.com_reply_id,comments.com_detail
                                          FROM use_info t1,use_info t2,comments
                                          WHERE msg_id=? AND (comments.com_user_id=t1.user_id  AND comments.com_reply_id=t2.user_id)
                                          order by comments.com_time;"; //查询一个消息的所有评论者和回复者
             private $select_statme2;                           
	         public function __construct($db_name)
	         {
	         	   parent::__construct($db_name);
	         	   $this->collection=new  Communciation_Collection();
	         	   $this->select_statme=$this->dbmanager->db_query->prepare(self::$SELECT_SQL);
	         	   $this->insert_statme=$this->dbmanager->db_query->prepare(self::$INSERT_SQL);
	               $this->select_statme2=$this->dbmanager->db_query->prepare(self::$SELECT_SQL2);
	         }
	         public  function CreateObject(array $raw)
	         {
	         	  $this->commments=new Commuication();
	         	  $this->commments->SetId($raw['com_id']);
	         	  $this->commments->SetCom_time($raw['com_time']) ;
	         	  $this->commments->SetDetail_com($raw['com_detail']);
	         	  $this->commments->SetReplyName($raw['replyname']);
	         	  $this->commments->SetCommentName($raw['commentname']);
	         	  $this->commments->SetMsg_id($raw['msg_id']);
	         	  /*echo $this->commments->GetId();
	         	  echo $this->commments->GetCom_time();
	         	  echo $this->commments->GetDeatail_com();
	         	  echo $this->commments->GetRelyName();
	         	  echo $this->commments->GetCommentName();*/
	         	  
	         	  return($this->commments);
	         }
	         public   function doGetCluster(array $raw)
	         {
	         	  $this->select_statme->bind_param('iiii',$raw['com_user_id'],$raw['com_reply_id'],$raw['com_reply_id'],$raw['com_user_id']);
	         	  $this->select_statme->execute();
	         	  $this->select_statme->store_result();
	         	  $this->select_statme->bind_result($com_id,$com_detail,$commentname,$reply_name,$com_time,$msg_id);
	         	  while($this->select_statme->fetch())
	         	  {
	         	  	    $tempraw=array('com_id'=>$com_id,'com_detail'=>$com_detail,'commentname'=>$commentname,'replyname'=>$reply_name,'com_time'=>$com_time,'msg_id'=>$msg_id);
	                    $this->collection->add($this->CreateObject($tempraw));
	               }
	         }
	         protected  function DoUpdate(array $raw,$id)
	         {
	         	  
	         }
	         protected  function doInsert(DomainObject $object)
	         {
	         	   $this->insert_statme->bind_param('iissi',$object->GetCom_user_id(),$object->GetCom_Reply_id(),$object->GetDeatail_com(),$object->GetCom_time(),$object->GetMsg_id());
	               $this->insert_statme->execute();
	               return($this->getRows($this->insert_statme->affected_rows));
	         }
	         protected  function doGetById($id)
	         {//置空
	         	  
	         }
             protected  function doDeleteAt($id)
             {
             	   
             }
}
?>
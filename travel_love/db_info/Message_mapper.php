<?php
include_once("Mapper.php");
include_once("../Domain_enetity/Message.php");
include_once("Message_collection.php");
class Message_Mapper extends Mapper{
	           
	            private  $messgae;
	            private  $message_collection;
	            
	            private  $select_statme2;//查询语句2的SQL预执行
	            private  $select_statme3;//查询语句3的SQL预执行
	            private  $select_statme4;//查询语句4的SQL预执行
	            //按照地点和时间来筛选的信息的预编译的SQL语句
	            private static  $SELECT_SQL1="SELECT *,DAYOFyear(start_time), (DAYOFyear(end_time)-DAYOFyear(start_time)) FROM message WHERE desnetion=? AND state=1  ORDER BY (DAYOFyear(end_time)-DAYOFyear(start_time)) ";
	            //按照地点和初始时间来筛选信息的预编译的SQL语句
	            private static  $SELECT_SQL2="SELECT *,DAYOFyear(start_time) ,(DAYOFyear(end_time)-DAYOFyear(start_time)) FROM message WHERE desnetion=? AND state=1 ORDER BY (DAYOFyear(start_time))  ";
	            //查询用户个人的所发的消息
	            private static  $SELECT_SQL3="SELECT * ,DAYOFyear(start_time), (DAYOFyear(end_time)-DAYOFyear(start_time)) FROM  message WHERE  user_id=? ORDER BY msg_id  DESC ";
	            //随机查询
	            private static  $SELECT_SQL4="SELECT *,DAYOFyear(start_time) ,(DAYOFyear(end_time)-DAYOFyear(start_time)) FROM message WHERE state=? ORDER BY msg_id  DESC LIMIT 20" ;
	            
	            private static  $INSERT_SQL="INSERT INTO message VALUES('',?,?,?,?,?,?,?,?) ";
	            
	            private static  $DELETE_SQL="DELETE FROM message WHERE msg_id=?";
	            public function __construct($db_name)
	            {
	            	parent::__construct($db_name);
	            	$this->collection=new  Message_collection();
	            	$this->select_statme=$this->dbmanager->db_query->prepare(self::$SELECT_SQL1);
	            	$this->select_statme2=$this->dbmanager->db_query->prepare(self::$SELECT_SQL2);
	            	$this->insert_statme=$this->dbmanager->db_query->prepare(self::$INSERT_SQL);
	            	$this->select_statme3=$this->dbmanager->db_query->prepare(self::$SELECT_SQL3);
	            	$this->delete_statme=$this->dbmanager->db_query->prepare(self::$DELETE_SQL);
	            	$this->select_statme4=$this->dbmanager->db_query->prepare(self::$SELECT_SQL4);
	            }
	            public  function  CreateObject(array $raw)
	            {
	            	   $this->messgae=new  Message();
	            	   $this->messgae->SetStarting($raw['starting']);
	            	   $this->messgae->SetDesnetion($raw['desnetion']);
	            	   $this->messgae->SetStart_time($raw['start_time']);
	            	   $this->messgae->SetUser_id($raw['user_id']);
	            	   $this->messgae->SetEnd_time($raw['end_time']);
	            	   $this->messgae->SetLoaction($raw['location']);
	            	   $this->messgae->Setrequestments($raw['requestments']);
	            	   $this->messgae->SetId($raw['msg_id']);
	            	   $this->messgae->SetState($raw['state']);
	            	   //以上都是数据自身的数据
	            	   $this->messgae->SetDuring_day($raw['during_day']);
	            	   $this->messgae->SetStart_piont($raw['start_piont']);
	            	  //这两个数据MYSQL计算出来的
	            	   return($this->messgae);
	            }
	            public function  doGetCluster(array $raw)
	            {
	                 
	            }
	            public  function doGetCluster_serval(array $raw,$type)//通过选择来看那个变量被预执行
	            {
	         	        if($type==3)//SQL3
	         	        {
	         	        	    $this->form_messages($raw,$this->select_statme3);
	         	        }
	         	        else if($type==2)//SQL2
	         	        {
	         	        	    $this->form_messages($raw,$this->select_statme2);
	         	        }
	         	        else if($type==1)//SQL1
	         	        {
	         	                $this->form_messages($raw,$this->select_statme);	
	         	        }
	         	        else if($type==4)//SQL4
	         	        {
	         	        	    $this->form_messages($raw,$this->select_statme4);
	         	        }
	            }
	            protected function DoUpdate(array $raw,$id)
	            {
	            	 
	            }
	            protected function  doInsert(DomainObject $object)
	            {
	            	   $this->insert_statme->bind_param("ssssssss",$object->GetUser_id(),$object->GetDesnetion(),$object->GetStarting(),$object->GetStart_time(),$object->GetEnd_time(),$object->Getrequestments(),$object->GetLoaction(),$object->GetState());
	            	   $this->insert_statme->execute();
	            	   return($this->getRows($this->insert_statme->affected_rows));
	            }
	            protected function   doGetById($id)
	            {//这个方法在这类中置空体
	            	
	            }
	            protected function  doDeleteAt($id)
	            {
	            	   $this->delete_statme->bind_param("s",$id);
	            	   $this->delete_statme->execute();
	            	   return($this->getRows($this->delete_statme->affected_rows));
	            }
	            private function  form_messages(array $raw,$statements)
	            {
	            	          $statements->bind_param("s",$raw['params']);//这个参数可能会变化
	         	        	  $statements->execute();
	         	        	  $statements->store_result();
	         	        	  $statements->bind_result($msg_id,$user_id,$desnetion,$starting,$start_time,$end_time,$requestments,$location,$state,$during_days,$start_piont);
	         	        	  while($statements->fetch())
	         	        	  {
	         	        	  	      $tempraw=array( 'msg_id'=>$msg_id,
                                                      'user_id'=>$user_id,
                                                     'desnetion'=>$desnetion,
                                                     'starting'=>$starting,
                                                     'start_time'=>$start_time,
                                                      'end_time'=>$end_time,
                                                      'requestments'=>$requestments,
                                                      'location'=>$location,
                                                      'state'=>$state,
                                                      'during_day'=>$during_days,
                                                      'start_piont'=>$start_piont);
                                     $this->collection->add($this->CreateObject($tempraw));               
	         	        	  }
	            }
	            
}
?>
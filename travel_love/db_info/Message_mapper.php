<?php
include_once("Mapper.php");
include_once("../Domain_enetity/Message.php");
include_once("Message_collection.php");
class Message_Mapper extends Mapper{
	           
	            private  $messgae;
	            private  $message_collection;
	            
	            private  $select_statme2;//��ѯ���2��SQLԤִ��
	            private  $select_statme3;//��ѯ���3��SQLԤִ��
	            private  $select_statme4;//��ѯ���4��SQLԤִ��
	            //���յص��ʱ����ɸѡ����Ϣ��Ԥ�����SQL���
	            private static  $SELECT_SQL1="SELECT *,DAYOFyear(start_time), (DAYOFyear(end_time)-DAYOFyear(start_time)) FROM message WHERE desnetion=? AND state=1  ORDER BY (DAYOFyear(end_time)-DAYOFyear(start_time)) ";
	            //���յص�ͳ�ʼʱ����ɸѡ��Ϣ��Ԥ�����SQL���
	            private static  $SELECT_SQL2="SELECT *,DAYOFyear(start_time) ,(DAYOFyear(end_time)-DAYOFyear(start_time)) FROM message WHERE desnetion=? AND state=1 ORDER BY (DAYOFyear(start_time))  ";
	            //��ѯ�û����˵���������Ϣ
	            private static  $SELECT_SQL3="SELECT * ,DAYOFyear(start_time), (DAYOFyear(end_time)-DAYOFyear(start_time)) FROM  message WHERE  user_id=? ORDER BY msg_id  DESC ";
	            //�����ѯ
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
	            	   //���϶����������������
	            	   $this->messgae->SetDuring_day($raw['during_day']);
	            	   $this->messgae->SetStart_piont($raw['start_piont']);
	            	  //����������MYSQL���������
	            	   return($this->messgae);
	            }
	            public function  doGetCluster(array $raw)
	            {
	                 
	            }
	            public  function doGetCluster_serval(array $raw,$type)//ͨ��ѡ�������Ǹ�������Ԥִ��
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
	            {//����������������ÿ���
	            	
	            }
	            protected function  doDeleteAt($id)
	            {
	            	   $this->delete_statme->bind_param("s",$id);
	            	   $this->delete_statme->execute();
	            	   return($this->getRows($this->delete_statme->affected_rows));
	            }
	            private function  form_messages(array $raw,$statements)
	            {
	            	          $statements->bind_param("s",$raw['params']);//����������ܻ�仯
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
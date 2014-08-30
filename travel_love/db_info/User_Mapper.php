<?php
include_once("Mapper.php");
include_once("../Domain_enetity/User.php");
include_once("UserInfo_Mapper.php");
class User_Mapper extends Mapper{
	    
	          private  $user;
	          
	          //�û������ֲ���:��ѯ��֤��¼������ʵ��ע�Ṧ��,�޸ĸ����������Ϣ 
	          private static $select_SQL="SELECT * FROM login WHERE User_mail=?";//Ԥִ�У������û���������ִ�еĲ�ѯ���
	         
	          private static $delete_SQL="SELECT * FROM login WHERE Uesr_id=?";
	          private static $insert_SQL="INSERT INTO login VALUES('',?,?)";
	          private static $update_SQL="UPDATE login SET User_password=? WHERE Uesr_id=? ";
	          
	         // private $infomapper;
	          
	          public function __construct($db_name)
	          {
	          	   parent::__construct($db_name);
	          	   $this->select_statme=$this->dbmanager->db_query->prepare(self::$select_SQL);
	          	   $this->delete_statme=$this->dbmanager->db_query->prepare(self::$delete_SQL);
	          	   $this->insert_statme=$this->dbmanager->db_query->prepare(self::$insert_SQL);
	          	   $this->update_statme=$this->dbmanager->db_query->prepare(self::$update_SQL);
	          	   //$this->infomapper=new  UserInfo_Mappe($db_name);
	          }
	          protected function doGetById($id)
	          {
	                $this->select_statme->bind_param("s",$id);
	                $this->select_statme->execute();
	                $this->select_statme->store_result();
	                $this->select_statme->bind_result($userid,$username,$password);
	                while($this->select_statme->fetch())
	                {
	                     $record=array('User_id'=>$userid,'User_mail'=>$username,'User_Password'=>$password);
	                     return($this->CreateObject($record));
	                }
	                return(null);
	          }
	          public function CreateObject(array $raw)
	          {
	          	      $this->user=new User();//���ϵش���ռ����������,ԭ��������һ��ָ��
	          	      $this->user->SetId($raw['User_id']);
	          	      $this->user->SetUser_mail($raw['User_mail']);
	          	      $this->user->SetPassword($raw['User_Password']);
	          	     // $this->user->addSpace($this->infomapper->CreateObject($raw['User_id']));
	          	      return ($this->user);
	          }
	          protected function DoDeleteAt($id)
	          {
	          	  return(null);
	          }
	          protected function DoInsert(DomainObject $object)
	          {
	          	   $this->insert_statme->bind_param("ss",$object->GetUser_mail(),$object->GetPassword());
	          	   $this->insert_statme->execute();
	               $num=$this->insert_statme->affected_rows;
	               return($this->getRows($num));
	          }
	          protected function DoUpdate(array $raw,$id)
	          {
	               $this->update_statme->bind_param("si",$raw['password'],$id);
	               $this->update_statme->execute();
	               $num=$this->update_statme->affected_rows;	
	                return($this->getRows($num)); 
	          }
	          public function  doGetCluster(array $raw)
	          {
	           	
	          } 
}
?>
<?php
include_once("Mapper.php");
include_once("../Domain_enetity/User_info.php");
class UserInfo_Mapper extends Mapper{
	   
	          private $userinfo;
	          //个人信息只有插入，修改，查询三种操作
	          private static $select_SQL="SELECT * FROM use_info WHERE user_id=?"; 
	          private static $update_SQL="UPDATE use_info SET nickname=?,user_sex=?,user_age=?,user_job=? WHERE user_id=?";
	          private static $insert_SQL="INSERT INTO use_info VALUES('',?,?,?,?,?) ";
	          
	          public function __construct($db_name)
	          {
	          	  parent::__construct($db_name);
	              $this->select_statme=$this->dbmanager->db_query->prepare(self::$select_SQL);
	              $this->insert_statme=$this->dbmanager->db_query->prepare(self::$insert_SQL);
	              $this->update_statme=$this->dbmanager->db_query->prepare(self::$update_SQL);
	          }
	          protected function doGetById($id)
	          {  
	          	       $this->select_statme->bind_param("i",$id);
	          	       $this->select_statme->execute();
	          	       $this->select_statme->store_result();
	          	       $this->select_statme->bind_result($info_id,$user_id,$nickname,$user_sex,$user_age,$user_job);
	          	       while($this->select_statme->fetch())
	          	       {
	          	       	  $record=array('Info_id'=>$info_id,'user_id'=>$user_id,'nickname'=>$nickname,'user_age'=>$user_age,'user_sex'=>$user_sex,'user_job'=>$user_job);
	          	          return($this->CreateObject($record));
	          	       }
	          	       return(null);
	          } 
	          protected function doDeleteAt($id)
	          {
	          	 
	          } 
	          protected function doUpdate(array $raw,$id)
	          {
	          	    $this->update_statme->bind_param("ssisi",$raw['nickname'],$raw['user_sex'],$raw['user_age'],$raw['user_job'],$id);
	                $this->update_statme->execute();
	                return($this->getRows($this->update_statme->affected_rows));
	          }
	          protected function doInsert(DomainObject $object)
	          {
	                   $this->insert_statme->bind_param("issis",$object->GetUser_id(),$object->GetNick_name(),$object->GetUser_sex(),$object->GetUser_age(),$object->GetUser_job());             	 
	                   $this->insert_statme->execute();
	                   return($this->getRows($this->insert_statme->affected_rows));
	          }
	          public function CreateObject(array $raw)
	          {
	          	        $this->userinfo=new User_info();
	          	        $this->userinfo->SetId($raw['Info_id']);
	          	        $this->userinfo->SetNick_name($raw['nickname']);
	          	        $this->userinfo->SetUser_age($raw['user_age']);
	          	        $this->userinfo->SetUser_job($raw['user_job']);
	          	        $this->userinfo->SetUser_id($raw['user_id']);
	          	        $this->userinfo->SetUser_sex($raw['user_sex']);
	          	        return($this->userinfo);
	          }
	          public function  doGetCluster(array $raw)
	          {
	                       	
	          }
	         
}
?>
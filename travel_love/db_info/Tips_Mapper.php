<?php
include_once("Mapper.php");
include_once("../Domain_enetity/Tip.php");
include_once("Tips_Collection.php");
class Tips_Mapper extends   Mapper{//贴士也仅有查询的功能
	   
	           private  $tip;
	           
	           private   static  $SELECT_SQL_ONE="SELECT * FROM sent_to_loginuser WHERE gift_id=?";//随机获取贴士
	           private   static  $SELECT_SQL_CLUSTER="SELECT * FROM sent_to_loginuser WHERE type=?";//按照贴士的类型来获取贴士
	           
	           private  $select_statme_all;
	           public  function __construct($db_name)
	           {
	           	     parent::__construct($db_name);
	           	     $this->collection=new tips_collection();
	           	     $this->select_statme=$this->dbmanager->db_query->prepare(self::$SELECT_SQL_ONE);
	           	     $this->select_statme_all=$this->dbmanager->db_query->prepare(self:: $SELECT_SQL_CLUSTER);
	           }
	           public  function CreateObject(array $raw)
	           {
	           	    $this->tip=new tip();
	           	    $this->tip->SetArticle($raw['article']);
	           	    $this->tip->SetTitle($raw['title']);
	           	    $this->tip->SetId($raw['gift_id']);
	           	    $this->tip->Settip_type($raw['type']);
	           	    return($this->tip);
	           }
	           protected function DoUpdate(array $raw,$id)
	           {
	           	 
	           }
	           protected function doInsert(DomainObject $object)
	           {
	           	 
	           }
	           protected function  doGetById($id)
	           {
	           	        $this->select_statme->bind_param('i',$id);
	           	        $this->select_statme->execute();
	           	        $this->select_statme->store_result();
	           	        $this->select_statme->bind_result($gift_id,$type,$title,$article);
	           	        while($this->select_statme->fetch())
	           	        {
	           	        	  $record=array('gift_id'=>$gift_id,'type'=>$type,'title'=>$title,'article'=>$article);
	           	        	  return($this->CreateObject($record));
	           	        }
	           	        return(null);
	           }
	           protected function  doDeleteAt($id)
	           {
	           	
	           }
	           public function  doGetCluster(array $raw)
	           {
	               $this->select_statme_all->bind_param('i',$raw['type']);
	           	   $this->select_statme_all->execute();
	           	   $this->select_statme_all->store_result();
	           	   $this->select_statme_all->bind_result($gift_id,$type,$title,$article);
	               while($this->select_statme_all->fetch())//预执行的SQL语句
	               {
	               	      $tempraw=array('gift_id'=>$gift_id,'type'=>$type,'title'=>$title,'article'=>$article);
	               	      $this->collection->add($this->CreateObject($tempraw));//加入迭代的容器中去
	               }
	           }
	          
} 
?>
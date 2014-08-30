<?php
include_once("../db_manager/Dbconnector.php");
include_once("Collection.php");
include_once("../Domain_enetity/Domain_object.php");
abstract class Mapper{
	    
	         protected  $dbmanager;//作用于数据库的连接
	         
	         protected  $db_name;
	         
	         protected $select_statme;
	         protected $update_statme;
	         protected $delete_statme;
	         protected $insert_statme;
	         
	         protected $collection; //某些子类可能会使用的集合类
	         
	         public function __construct($db_name)
	         {
	               if(empty($this->dbmanager))
	               {
	               	      $this->dbmanager= db_connector::getInstance($db_name);
	               }
	         }
	         public function GetById($id)
	         {
	             return($this->doGetById($id));	    
	         }
	         public function DeleteAt($id)
	         {
	         	  return($this->doDeleteAt($id));
	         }
	         public function Insert(DomainObject $object)
	         {
	         	 return($this->doInsert($object));
	         }
	         public function Update(array $raw,$id)
	         {
	         	 return($this->doUpdate($raw,$id));
	         }
	         protected function getRows($num)//判断是否有效执行
	         {
	         	    if($num<=0)
	         	    {
	         	    	return(-1);
	         	    }else{
	         	    	 return(1);
	         	    }
	         }
	         public  function  SetCollection(Collection $collection)
	         {
	         	        $this->collection=$collection;
	         }
	         public function  GetCollection()
	         {
	         	   return($this->collection);
	         }
	         public  function doGetCluster_serval(array $raw,$type)
	         {
	        
	         }
	         public  abstract function CreateObject(array $raw);
	         public  abstract function doGetCluster(array $raw);
	         protected abstract function DoUpdate(array $raw,$id);
	         protected abstract function doInsert(DomainObject $object);
	         protected abstract function doGetById($id);
             protected abstract function doDeleteAt($id);
           	       
}           
?>
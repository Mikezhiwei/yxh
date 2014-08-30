<?php
abstract class DomainObject{
	       
	       protected $id;//数据库自增主键
	       
	       public function GetId()
	       {
	       	   return($this->id);
	       }
	       public function  SetId($id)
	       {
	       	  $this->id=$id;
	       } 
	       public abstract function addSpace(DomainObject $object);
	       public abstract function getSpace();
}
?>
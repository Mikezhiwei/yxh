<?php
abstract class DomainObject{
	       
	       protected $id;//���ݿ���������
	       
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
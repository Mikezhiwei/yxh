<?php
include_once("../Domain_enetity/Domain_object.php");
abstract class Collection implements Iterator{
	       
	       private  $total;
	       private  $pos;
	       public  $objects=array();
	       
	       private $targetclass="";
	       
	       public function __construct()
	       {
	       	  $this->total=0;
	       	  $this->pos=0;
	       }
	       public function rewind()
	       {
	       	    $this->pos=0;      
	       }
	       public function key()
	       {
	       	   return($this->pos);
	       }
	       public function current()
	       {
	       	    return($this->objects[$this->pos]);
	       }
	       public function next()
	       {
	       	   $this->pos++;
	       }
	       public function valid()
	       {
	       	  return(isset($this->objects[$this->pos]));
	       }
	       public function add(DomainObject $object)
	       {
	       	      $class=$this->targetClass();
	       	      if(!($object instanceof $class))//检验是否有继承关系
	       	      {
	       	      	  echo "null";
	       	      	  return;
	       	      }
	       	     $this->objects[$this->total]=$object;
	       	     $this->total++;
	       }
	       protected abstract function targetClass();
	      
}
?>
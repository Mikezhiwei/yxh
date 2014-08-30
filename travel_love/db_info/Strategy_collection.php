<?php
include_once("Collection.php");

class  Strategy_colletion extends Collection{
	   
	         public function __construct()
	         {
	         	 parent::__construct();
	         	 $this->targetclass="Strategy";
	         }
	         protected  function targetClass()
	         {
	         	   return($this->targetclass);
	         }
	         
}
?>
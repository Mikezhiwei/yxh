<?php
include_once("Collection.php");
class  tips_collection extends Collection{
	
	       public function __construct()
	       {
	       	    parent::__construct();
	       	    $this->targetclass="tip";
	       }
	       protected function targetClass()
	       {
	       	    return($this->targetclass);
	       }   
}
?>
<?php
include_once("Collection.php");
class Communciation_Collection extends Collection{
	   
	        public function __construct()
	        {
	        	  parent::__construct();
	        	   $this->targetclass="Commuication";
	        }
	        protected function targetClass()
	        {
	        	return($this->targetclass);
	        }
	        
}
?>
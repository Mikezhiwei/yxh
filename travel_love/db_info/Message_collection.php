<?php
include_once("Collection.php");
class  Message_collection extends  Collection{
          
            public function __construct()
            {
            	   parent::__construct();
            	   $this->targetclass= "Message";
            }
            protected   function targetClass()
            {
            	   return($this->targetclass);
            }
}

?>
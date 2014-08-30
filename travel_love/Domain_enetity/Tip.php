<?php
include_once("Domain_object.php");
class  tip extends  DomainObject{
	   
	        
	        private  $tip_type;
	        private  $title;
	        private  $article;
	        
	        public  function addSpace( DomainObject $object)
	        {
	        	
	        }
	        public  function getSpace()
	        {
	        }
	        public function Settip_type($type)
	        {
	        	   $this->tip_type=$type;
	        }
	        public function  Gettip_type()
	        {
	        	  return($this->tip_type);
	        }
	        public function  SetTitle($title)
	        {
	        	   $this->title=$title;
	        }
	        public function GetTitle()
	        {
	        	  return($this->title);
	        }
	        public function SetArticle($article)
	        {
	        	  $this->article=$article;
	        }
	        public function  GetArticle()
	        {
	        	  return($this->article);
	        }
	        
}
?>
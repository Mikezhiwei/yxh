<?php
include_once("Domain_object.php");
class Strategy extends DomainObject{
	   
	         private  $interest;
	         private $article;
	         private  $fited_time;
	         private  $attention;
	         
	         public function SetInterest($insterest)
	         {
	         	    $this->interest=$insterest;
	         }
	         public function GetInsterest()
	         {
	         	   return($this->interest);
	         }
	         public function  SetArticle($article)
	         {
	         	   $this->article=$article;
	         }
	         public function  GetArticle()
	         {
	         	  return($this->article);
	         }
	         public  function SetFited_time($fited_time)
	         { 
	         	  $this->fited_time=$fited_time;  
	         }
	         public function GetFited_time()
	         { 
	         	   return($this->fited_time);
	         }
	         public function  SetAttention($attention)
	         {
	            	 $this->attention=$attention;  
	         }
	         public function  GetAttention()
	         {
	         	  return($this->attention);
	         }
	          public  function addSpace( DomainObject $object)
	        {
	        }
	        public  function getSpace()
	        {
	        }
}
?>
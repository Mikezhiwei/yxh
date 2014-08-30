<?php
include_once("Domain_object.php");
include_once("User_info.php");
class User extends DomainObject{
	        
	        private $user_mail;
	        private $password;
	        
	        private $my_info;//与userinfo有联系.
	        
	        public function SetUser_mail($user_mail)
	        {
	        	    $this->user_mail=$user_mail;
	        }
	        public function SetPassword($password)
	        {
	        	   $this->password=$password;
	        }
	        public function GetUser_mail()
	        {
	        	   return($this->user_mail);
	        }
	        public function GetPassword()
	        {   
	        	   return($this->password);
	        }
	        public function addSpace(DomainObject $object)
	        {
	        	 $this->my_info=$object;
	        }
	        public function getSpace()
	        {
	        	 return($this->my_info);
	        }
	        
	           
}
?>
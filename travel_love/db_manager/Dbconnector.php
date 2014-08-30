<?php
include_once("../Common_parameter/commom_parameter.php");
class db_connector {//做数据库的连接操作
	     
	         private $db_name="";
	         
	         public  $db_query;//一个php和mysql的面向对象的查询功能
	         
	         private static $instance;
	         
	         public function __construct($db_name)
	         {
	         	   $this->db_name=$db_name;
	         	   if(empty($this->db_query))
	         	   {
	         	   	    $this->db_query=new mysqli( Com_Parameter::DB_IP, Com_Parameter::DB_USERNAME, Com_Parameter::DB_PASSWORD,$this->db_name);
	         	       // echo "mysql connect is successful";
	         	   }
	         	   if(mysqli_connect_errno())
	         	   {
	         	   	   echo "db link failed";
	         	   }  
	         	   
	         }
	         public static function  getInstance($db_name)
	         {
	         	    if(empty(self::$instance))
	         	    {
	         	    	  self::$instance=new db_connector($db_name);
	         	    	  return(self::$instance);
	         	    }else{
	         	    	 return(self::$instacne);
	         	    }
	         }
}
?>
<?php
include_once("Collection.php");
include_once("../Domain_enetity/Strategy.php");
include_once("Strategy_collection.php");
include_once("Mapper.php");
class Strategy_Mapper extends Mapper{//攻略仅有查询的功能
	               
	               private static $SELECT_SQL="SELECT * FROM send_to_user WHERE insterest=?";
	               private static $SELECT_SQL_TIME="SELECT * FROM send_to_user WHERE time REGEXP ? ";
	               //使用正则表达式,注意，将正则表达式的符号全部都放入参数中作为字符串
	               
	               private  $select_statme_time;
	               
	               private  $strategy;
	                
	               public function  __construct($db_name)
	               { 
  	               	    parent::__construct($db_name);
  	               	    $this->collection=new Strategy_colletion();
  	               	    $this->select_statme=$this->dbmanager->db_query->prepare(self::$SELECT_SQL);
  	               	    $this->select_statme_time=$this->dbmanager->db_query->prepare(self::$SELECT_SQL_TIME);
  	               }
	               public  function CreateObject(array $raw)
	               {
	                      $this->strategy=new Strategy();
	                      $this->strategy->SetId($raw['gift_id']);
	                      $this->strategy->SetArticle($raw['article']);
	                      $this->strategy->SetFited_time($raw['time']);
	                      $this->strategy->SetAttention($raw['attention']);
	                      $this->strategy->SetInterest($raw['insterest']);
	                      return($this->strategy);	    
	               }
	               public  function doGetCluster(array $raw)
	               {
	               	      $fit_time=$raw['time'];
	               	      $fit_time="[".$fit_time."]";//补全正确的格式
	               	      $this->select_statme_time->bind_param('s',$fit_time);
	               	      $this->select_statme_time->execute();
	               	      $this->select_statme_time->store_result();
	               	      $this->select_statme_time->bind_result($gift_id,$insterest,$article,$time,$attention);
	               	      while($this->select_statme_time->fetch())
	               	      {     
	               	            $tempraw=array('gift_id'=>$gift_id,'insterest'=>$insterest,'article'=>$article,'time'=>$time,'attention'=>$attention);
	               	            $this->collection->add($this->CreateObject($tempraw));
	               	      }
	               }
	               protected function DoUpdate(array $raw,$id)
	               {
	               	
	               }
	               protected  function doInsert(DomainObject $object)
	               {
	               	
	               }
	               protected function  doGetById($id)
	               {
	               	       $this->select_statme->bind_param('s',$id);
	               	       $this->select_statme->execute();
	               	       $this->select_statme->store_result();
	               	       $this->select_statme->bind_result($gift_id,$insterest,$article,$time,$attention);
	               	       while($this->select_statme->fetch())
	               	       {
	               	       	      $tempraw=array('gift_id'=>$gift_id,'insterest'=>$insterest,'article'=>$article,'time'=>$time,'attention'=>$attention);
	               	      	      $this->collection->add($this->CreateObject($tempraw));
                           }
	               	       return(null);
	               }
	               protected function  doDeleteAt($id)
	               {
	               	          
	               }
	              
}
?>
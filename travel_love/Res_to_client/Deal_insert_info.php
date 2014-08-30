<?php
include_once("../ResponseMents/UserinfoResponse.php");
             $res_info=new UserinfoResponse();
             $res_info->repsonse_insertinfo($_POST['user_id'],$_POST['user_job'],$_POST['user_age'],$_POST['user_sex'],$_POST['nick_name'])
?>
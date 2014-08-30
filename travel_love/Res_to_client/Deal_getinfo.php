<?php
include_once("../ResponseMents/UserinfoResponse.php");
                $res_info=new UserinfoResponse();
                $res_info->response_userinfo($_POST['user_id']);

?>
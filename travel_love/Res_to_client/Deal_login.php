<?php
include("../ResponseMents/LoginResponse.php");//响应客户端的登录界面
                  $res=new  LoginResponse();
                  $res->ensure_login($_POST['usermail'],$_POST['userpassword']);
                
?>
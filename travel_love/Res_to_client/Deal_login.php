<?php
include("../ResponseMents/LoginResponse.php");//��Ӧ�ͻ��˵ĵ�¼����
                  $res=new  LoginResponse();
                  $res->ensure_login($_POST['usermail'],$_POST['userpassword']);
                
?>
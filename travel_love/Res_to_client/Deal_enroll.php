<?php
include_once("../ResponseMents/EnrollReponse.php");
            $res=new EnrollReponse();
            $res->ensure_enroll($_POST['usermail'],$_POST['userpassword']);
                        
?>
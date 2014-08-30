<?php
include_once("LoginResponse.php");
include_once("EnrollReponse.php");
include_once("UserinfoResponse.php");      
include_once("MessagesResponse.php");               
include_once("CommuncitaionResponse.php");             
                   //$test=new  MessagesReponse();
                  // $test->response_insert_message("2","青城山","简阳","2014-8-20","2014-8-21","一个妹子","电子科技大学清水河校区","1"); 
                  //$test->response_specieal_message("青城山",1);
                  $test=new CommentReponse();
                  $test->response_comments(1,24,6);
                 // $test->response_insert_comments(1,24,"去死去死","2014/08/10 10:43:50",6);
                 
               
?>
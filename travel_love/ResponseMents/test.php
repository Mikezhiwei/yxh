<?php
include_once("LoginResponse.php");
include_once("EnrollReponse.php");
include_once("UserinfoResponse.php");      
include_once("MessagesResponse.php");               
include_once("CommuncitaionResponse.php");             
                   //$test=new  MessagesReponse();
                  // $test->response_insert_message("2","���ɽ","����","2014-8-20","2014-8-21","һ������","���ӿƼ���ѧ��ˮ��У��","1"); 
                  //$test->response_specieal_message("���ɽ",1);
                  $test=new CommentReponse();
                  $test->response_comments(1,24,6);
                 // $test->response_insert_comments(1,24,"ȥ��ȥ��","2014/08/10 10:43:50",6);
                 
               
?>
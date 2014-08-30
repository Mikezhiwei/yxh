<?php
include_once("Tip.php");
include_once("Strategy.php");
include_once("Message.php");
         
              /*$test=new Strategy();
              $test->SetArticle("EGHAUOGEE");
              $test->SetAttention("ghouahoge");
              $test->SetFited_time("xia");
              $test->SetInterest("qingchengshan");
              echo  $test->GetArticle();
              echo  $test->GetAttention();
              echo  $test->GetFited_time();
              echo  $test->GetInsterest();*/
              $test=new  Message();
              $test->SetEnd_time("14-8-23");
              $test->SetStart_time("14-8-16");
              $test->SetStarting("成都");
              $test->SetDesnetion("重庆");
              $test->SetUser_id("1");
              $test->SetLoaction("电子科技大学清水河校区");
              $test->Setrequestments("女生多点好");
              $test->SetState(1);
              echo $test->GetUser_id();
              
              
              
?>
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
              $test->SetStarting("�ɶ�");
              $test->SetDesnetion("����");
              $test->SetUser_id("1");
              $test->SetLoaction("���ӿƼ���ѧ��ˮ��У��");
              $test->Setrequestments("Ů������");
              $test->SetState(1);
              echo $test->GetUser_id();
              
              
              
?>
<?php
include_once("../ResponseMents/MessagesResponse.php");
                 //������ѯ(����ʱ�䳤��)
                  $res_message_desnetion_first=new MessagesReponse();
                  $res_message_desnetion_first->response_specieal_message($_POST['desnetion'],2);
?>
<?php
include_once("../ResponseMents/MessagesResponse.php");
                  //������������Ϣ
                  $res_message_desnetion_first=new MessagesReponse();
                  $res_message_desnetion_first->response_specieal_message($_POST['user_id'],3);
?>
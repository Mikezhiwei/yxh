<?php
include_once("../ResponseMents/MessagesResponse.php");
                  //一级查询(按照相同的目的地)
                  $res_message_desnetion_first=new MessagesReponse();
                  $res_message_desnetion_first->response_specieal_message($_POST['desnetion'],1);
?>
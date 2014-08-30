<?php
include_once("../ResponseMents/MessagesResponse.php");
                 //二级查询(按照时间长短)
                  $res_message_desnetion_first=new MessagesReponse();
                  $res_message_desnetion_first->response_specieal_message($_POST['desnetion'],2);
?>
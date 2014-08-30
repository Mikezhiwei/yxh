<?php
     header("content-Type:text/html; charset=gb2312");
      include_once("../ResponseMents/MessagesResponse.php");
                         $res_random_message=new MessagesReponse();
                         $res_random_message->response_random_messages();
                  
  
?>
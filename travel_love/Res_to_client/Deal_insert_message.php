<?php
include_once("../ResponseMents/MessagesResponse.php");
                         $res_insert_message=new MessagesReponse();
                         $res_insert_message->response_insert_message($_POST['user_id'],$_POST['desnetion'],
                                                                       $_POST['starting'],$_POST['start_time'],
                                                                       $_POST['end_time'],$_POST['req'],
                                                                       $_POST['location'],$_POST['state']);  
?>
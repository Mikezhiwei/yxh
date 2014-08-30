<?php
include_once("../ResponseMents/CommuncitaionResponse.php");
                    $res_get_comments=new CommentReponse();
                    $res_get_comments->response_comments($_POST['com_user_id'],$_POST['com_reply_id'],$_POST['msg_id']);
?>
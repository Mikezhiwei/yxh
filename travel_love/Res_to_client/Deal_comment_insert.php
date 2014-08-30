<?php
include_once("../ResponseMents/CommuncitaionResponse.php");
                  $res_comment_insert=new CommentReponse();
                  $res_comment_insert->response_insert_comments($_POST['com_user_id'],$_POST['com_reply_id']
                                                                ,$_POST['com_detail'],$_POST['com_time'],
                                                                 $_POST['msg_id']);
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Language" content="en" />
	<meta name="GENERATOR" content="PHPEclipse 1.2.0" />
	<title>title</title>
</head>
<body>
   <?php
       $test="你好";
       
       echo $test;
       $encode = mb_detect_encoding($test, array("ASCII","gbk","utf-8","big5"));
       echo $encode;
   ?>
</body>
</html>

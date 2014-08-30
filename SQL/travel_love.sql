-- MySQL dump 10.13  Distrib 6.0.11-alpha, for Win64 (unknown)
--
-- Host: localhost    Database: travel_love
-- ------------------------------------------------------
-- Server version	6.0.11-alpha-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `com_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `com_user_id` int(10) unsigned NOT NULL,
  `com_reply_id` int(10) unsigned NOT NULL,
  `com_detail` varchar(100) NOT NULL,
  `com_time` datetime NOT NULL,
  `msg_id` int(10) unsigned NOT NULL,
  `type` int(10) unsigned NOT NULL,
  PRIMARY KEY (`com_id`,`com_user_id`,`com_reply_id`,`msg_id`),
  KEY `FK_Comments_1` (`msg_id`),
  KEY `FK_comments_2` (`com_user_id`),
  KEY `FK_comments_3` (`com_reply_id`),
  CONSTRAINT `FK_Comments_1` FOREIGN KEY (`msg_id`) REFERENCES `message` (`msg_id`),
  CONSTRAINT `FK_comments_2` FOREIGN KEY (`com_user_id`) REFERENCES `login` (`Uesr_id`),
  CONSTRAINT `FK_comments_3` FOREIGN KEY (`com_reply_id`) REFERENCES `login` (`Uesr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk COMMENT='消息评论的表格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,2,1,'你好啊','2014-08-09 17:48:46',6,1),(2,1,2,'你也好啊','2014-08-09 17:49:10',6,2),(3,3,1,'你妹的','2014-08-09 17:51:51',6,1),(4,3,1,'靠','2014-08-09 17:52:33',6,1),(5,24,1,'你的电话','2014-08-09 17:56:42',7,1),(6,1,24,'这个还不能给哦','2014-08-09 17:58:01',7,2),(7,1,3,'都不认识你哪来的你妹啊','2014-08-10 08:57:37',6,2),(8,3,1,'你TM在逗我？','2014-08-10 08:58:15',6,1),(9,24,1,'这个也不错啦','2014-08-10 09:32:02',6,1),(10,1,24,'到底要哪样啊，你，','2014-08-10 09:33:19',6,2),(11,24,1,'不想哪样啊，就要的你的电话，有意见吗','2014-08-10 09:46:56',6,1),(12,1,24,'只给邮箱啊','2014-08-10 09:47:39',6,2),(13,24,1,'那你倒是给啊','2014-08-10 09:48:08',6,1),(14,1,24,'lzw@qq.com','2014-08-10 09:48:59',6,2),(15,1,24,'屁事儿多','2014-08-10 10:12:05',6,1),(16,24,1,'滚','2014-08-10 10:18:33',6,1),(18,24,1,'爬','2014-08-10 10:43:50',6,1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `Uesr_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `User_mail` varchar(45) NOT NULL,
  `User_password` varchar(30) NOT NULL,
  PRIMARY KEY (`Uesr_id`),
  UNIQUE KEY `mail_Index` (`User_mail`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=gbk COMMENT='用户登录信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'lzw00000@qq.com','lzw159753'),(2,'lzw15828539365@163.com','159753'),(3,'loveling@163.com','lzw15973'),(9,'lzw15892320186@163.com','123'),(10,'zal15892320186@163.com','123'),(11,'lzwusetc@gmail.com','lzw123456'),(13,'usetc@gmail.com','123456'),(14,'huawei@163.com','chw123456'),(17,'chuanda@gmail.com','hgaouegea'),(20,'huaei@163.com','ghaug'),(21,'lzw@qq.com','lzw'),(22,'lzwuestc@qq.com','lzw123456'),(24,'loveling@qq.com','lzw'),(25,'123@qq.com','123'),(26,'332406732@QQ.zzz','123456'),(27,'fuck@u','foo');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `msg_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `desnetion` varchar(60) NOT NULL,
  `starting` varchar(60) NOT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `requestments` varchar(100) NOT NULL,
  `loaction` varchar(60) NOT NULL,
  `state` int(2) unsigned NOT NULL,
  PRIMARY KEY (`msg_id`,`user_id`),
  KEY `FK_message_1` (`user_id`),
  CONSTRAINT `FK_message_1` FOREIGN KEY (`user_id`) REFERENCES `login` (`Uesr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=gbk COMMENT='用户所发的信息的表格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,1,'青城山','成都','2014-08-16','2014-08-20','女生多','电子科技大学清水河校区',1),(2,2,'都江堰','重庆','2014-08-20','2014-08-22','男生多，自驾游','四川大学江安校区',1),(3,1,'青城山','成都','2014-08-07','2014-08-08','一个妹子','电子科技大学清水河校区',2),(4,3,'青城山','成都','2014-08-07','2014-08-09','一对情侣','电子科技大学清水河校区',2),(5,2,'峨眉山','成都','2014-08-10','2014-08-13','三个人，最好都是妹子','电子科技大学清水河校区',1),(6,1,'九寨沟','成都','2014-08-10','2014-08-13','妹子多更好','电子科技大学清水河校区',1),(7,1,'重庆','成都','2014-08-16','2014-08-23','女生多点好','电子科技大学清水河校区',1),(8,2,'青城山','简阳','2014-08-20','2014-08-21','一个妹子','电子科技大学清水河校区',1),(9,2,'青城山','成都','2014-08-16','2014-08-20','人多就好','电子科技大学',1),(13,13,'重庆','绵阳','2014-08-16','2014-08-20','妹子多最好','电子科技大学清水河校区',1),(14,13,'九寨沟','成都','2014-08-18','2014-08-20','无','电子科技大学清水河校区',1),(15,24,'韩国','威海','2014-08-13','2014-08-18','一个男生','电子科技大学清水河校区',1),(16,24,'威海','成都','2014-08-11','2014-08-14','无','电子科技大学清水河校区',1),(17,24,'青城山','简阳','2014-08-18','2014-08-20','八个人一起最好','电子科技大学清水河校区',1),(18,24,'成都','简阳','2014-08-11','2014-08-14','没要求','电子科技大学清水河校区',1),(21,25,'成都','湖南','2014-08-14','2015-02-18','百日游','电子科技大学清水河校区',1),(23,26,'中南海','中南海','2014-08-15','2014-08-30','问问你','电子科技大学清水河校区',1),(27,24,'重庆','成都','2014-08-08','2014-08-11','无','电子科技大学清水河校区',1),(28,24,'重庆','成都','2014-10-01','2014-10-04','无','电子科技大学清水河校区',1),(29,24,'九寨沟','成都','2014-12-31','2015-01-03','一个妹子有木有','电子科技大学清水河校区',1),(30,24,'九寨沟','成都','2014-10-04','2014-10-06','一个妹子有没有 情侣游啊 单身苦逼不解释!','电子科技大学清水河校区',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `send_to_user`
--

DROP TABLE IF EXISTS `send_to_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `send_to_user` (
  `gift_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `insterest` varchar(45) NOT NULL,
  `article` text NOT NULL,
  `time` varchar(10) NOT NULL,
  `attention` varchar(45) CHARACTER SET gbk COLLATE gbk_bin NOT NULL,
  PRIMARY KEY (`gift_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk COMMENT='推送的旅游攻略';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `send_to_user`
--

LOCK TABLES `send_to_user` WRITE;
/*!40000 ALTER TABLE `send_to_user` DISABLE KEYS */;
INSERT INTO `send_to_user` VALUES (1,'青城山','适合避暑的好地方','夏，秋','运动装，登山工具'),(2,'重庆','山城，好吃的很多','秋，冬','相机，晕船药'),(3,'成都','天赋之国','秋','会打麻将，品味成都的古镇风味');
/*!40000 ALTER TABLE `send_to_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sent_to_loginuser`
--

DROP TABLE IF EXISTS `sent_to_loginuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sent_to_loginuser` (
  `gift_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(6) unsigned NOT NULL,
  `title` varchar(45) NOT NULL,
  `article` text NOT NULL,
  PRIMARY KEY (`gift_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk COMMENT='推送旅游贴士';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sent_to_loginuser`
--

LOCK TABLES `sent_to_loginuser` WRITE;
/*!40000 ALTER TABLE `sent_to_loginuser` DISABLE KEYS */;
INSERT INTO `sent_to_loginuser` VALUES (1,1,'七夕情人节旅游','注意避雨，情侣可去比较晴朗的地方，注意避暑'),(2,2,'八一建军节旅游','红色旅游，'),(3,2,'七一建党节旅游','红色路线');
/*!40000 ALTER TABLE `sent_to_loginuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `User_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nini` varchar(45) NOT NULL,
  PRIMARY KEY (`User_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'abc'),(2,'abe'),(3,'abh');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `use_info`
--

DROP TABLE IF EXISTS `use_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `use_info` (
  `Info_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `nickname` varchar(60) NOT NULL,
  `user_sex` char(4) NOT NULL,
  `user_age` int(10) unsigned NOT NULL,
  `user_job` varchar(60) NOT NULL,
  PRIMARY KEY (`Info_id`,`user_id`) USING BTREE,
  KEY `FK_use_info_1` (`user_id`),
  CONSTRAINT `FK_use_info_1` FOREIGN KEY (`user_id`) REFERENCES `login` (`Uesr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=gbk COMMENT='用户个人信息表格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `use_info`
--

LOCK TABLES `use_info` WRITE;
/*!40000 ALTER TABLE `use_info` DISABLE KEYS */;
INSERT INTO `use_info` VALUES (5,3,'栩栩一生','女',21,'学生'),(6,1,'你都如何回忆我','男',20,'大学生 半个程序员'),(7,2,'长街不敌千里雪','女',21,'作家'),(10,10,'HR','男',20,'学生'),(11,11,'许我一身轻薄色','男',20,'学生'),(17,9,'许我一身轻薄色','男',20,'学生'),(52,21,'淡淡墨香','男',20,'学生'),(56,13,'下辈子做你一课牙齿','男',20,'大学生'),(57,24,'ailing','女',19,'大学生'),(58,25,'陈仁义','男',20,'学生'),(59,26,'litkk','男',0,'ty'),(60,27,'fcku','女',99,'ffff');
/*!40000 ALTER TABLE `use_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-30  1:58:50

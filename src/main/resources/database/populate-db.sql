-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: website
-- ------------------------------------------------------
-- Server version	5.7.8-rc-log

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
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'pepsik','$2a$10$mVKCQ4Y9/asZM0QbbZOUUeamfA/sdg6h9l44TBz5n7mK0tje28FI.'),(2,'444','$2a$10$G52uld.J0hMiq2l4p/4Lgem3oadOSefEKsxz9b4BvtNNbLcB3kVWm');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (6,'<iframe src=\"http://gfycat.com/ifr/JealousFondDamselfly\" frameborder=\"0\" scrolling=\"no\" width=\"854\" height=\"480\" style=\"-webkit-backface-visibility: hidden;-webkit-transform: scale(1);\"></iframe>',2,2,'2015-10-11 16:59:16'),(7,'<p><span style=\"font-size: 36px;\">﻿</span><span style=\"font-size: 36px; line-height: 51.4286px;\">(~˘▾˘)~</span><br></p>',1,1,'2015-10-11 17:01:55'),(8,'<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 36px;\">﻿</span><span style=\"font-size: 36px; line-height: 51.4286px;\">~(˘▾˘~)</span><br></p>',1,1,'2015-10-11 17:02:16');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Уиии','<p>Первый пост который не удалится после перекомпиляциии&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 24px; line-height: 34.2857px;\">( ͡° ͜ʖ ͡° )</span></p>','2015-10-11 15:15:01',1),(2,'html5','<iframe src=\"http://gfycat.com/ifr/NeedyLikelyAegeancat\" frameborder=\"0\" scrolling=\"no\" width=\"854\" height=\"480\" style=\"-webkit-backface-visibility: hidden;-webkit-transform: scale(1);\"></iframe>','2015-10-11 15:18:56',1),(3,'\\ (•◡•) /','<iframe width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/16530782&amp;color=0066cc&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false\"></iframe>','2015-10-11 20:07:49',1);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-14 13:07:56

-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: boop
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (197,196),(198,196),(199,196),(200,195),(201,195);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (42,1,196,'2015-06-07 20:42:21'),(43,1,195,'2015-06-07 20:42:22'),(44,1,194,'2015-06-07 20:42:23');
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (173,1,'<iframe frameborder=\"0\" src=\"//www.youtube.com/embed/Czy0pXRRZcs\" width=\"640\" height=\"360\"></iframe>&nbsp;','2015-06-03 10:44:28',NULL),(183,1,'<img src=\"/uploads/images/xpt_xpiaYFg.jpg\" style=\"width: 604px;\">&nbsp;','2015-06-05 16:02:41',NULL),(189,1,'test','2015-06-06 16:45:09',NULL),(190,1,'Text_Text_Text_Text_Text_Text_Text_Text_Text_','2015-06-07 17:08:58',NULL),(191,1,'Text_Text_Text_Text_Text_','2015-06-07 17:09:43',NULL),(192,1,'Text_Text_Text_Text_Text_Text_Text_','2015-06-07 17:09:50',NULL),(193,1,'Text_Text_Text_Text_Text_Text_','2015-06-07 17:09:56',NULL),(194,1,'Text_Text_Text_Text_Text_Text_','2015-06-07 17:10:03',NULL),(195,1,'Text_Text_Text_Text_Text_Text_Text_','2015-06-07 17:10:13',NULL),(196,1,'Text_Text_Text_Text_Text_Text_','2015-06-07 17:10:22',NULL),(197,1,'comment1','2015-06-07 17:41:59',NULL),(198,1,'comment2','2015-06-07 17:42:03',NULL),(199,1,'comment3','2015-06-07 17:42:06',NULL),(200,1,'comment4','2015-06-07 17:42:11',NULL),(201,1,'comment5','2015-06-07 17:42:17',NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `passwords`
--

LOCK TABLES `passwords` WRITE;
/*!40000 ALTER TABLE `passwords` DISABLE KEYS */;
INSERT INTO `passwords` VALUES (1,'$2a$10$OplVbDv8y9A0foIQeR/Lc.WXDfTjoDh1SvZNZwZwlLwyusWwv2.qq');
/*!40000 ALTER TABLE `passwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (173,'Jetman',0),(183,'....',0),(190,'Post1',0),(191,'post2',0),(192,'post3',0),(193,'post4',0),(194,'post5',1),(195,'post6',1),(196,'post7',1);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `private_messages`
--

LOCK TABLES `private_messages` WRITE;
/*!40000 ALTER TABLE `private_messages` DISABLE KEYS */;
INSERT INTO `private_messages` VALUES (7,1,1,'text_text_text_text_text_text_text_text_text_','2015-06-06 19:30:55',0);
/*!40000 ALTER TABLE `private_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (1,'pepsik1990@gmail.com','admin local','1990-02-22','','','','HomeAlone','');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_MODERATOR');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tag_owners`
--

LOCK TABLES `tag_owners` WRITE;
/*!40000 ALTER TABLE `tag_owners` DISABLE KEYS */;
INSERT INTO `tag_owners` VALUES (183,62),(173,64);
/*!40000 ALTER TABLE `tag_owners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (62,'bug',2,'text_text_text_text_text_text_text_text_text_text_text_text_text_text','2015-06-06 19:28:31',1,''),(63,'Р В±Р ВµР В·Р Р…Р В°Р Т‘Р ВµР ',1,NULL,'2015-06-06 19:28:31',1,NULL),(64,'youtube',1,'<p>YouTube is a video-sharing website headquartered in San Bruno, California, United States. The service was created by three former PayPal employees in February 2005. In November 2006, it was bought by Google for US$1.65 billion.[4] YouTube now operates as one of Google\'s subsidiaries.[5] The site allows users to upload, view, and share videos, and it makes use of WebM, H.264, and Adobe Flash Video technology to display a wide variety of user-generated and corporate media video. Available content includes video clips, TV clips, music videos, and other content such as video blogging, short original videos, and educational videos.</p><p>\r\n\r\nMost of the content on YouTube has been uploaded by individuals, but media corporations including CBS, the BBC, Vevo, Hulu, and other organizations offer some of their material via YouTube, as part of the YouTube partnership program.[6] Unregistered users can watch videos, and registered users can upload videos to their channels. Videos considered to contain potentially offensive content are available only to registered users affirming themselves to be at least 18 years old.</p>','2015-06-06 19:28:48',1,'http://cg-school.org/wp-content/uploads/2015/01/jpg');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users_authority`
--

LOCK TABLES `users_authority` WRITE;
/*!40000 ALTER TABLE `users_authority` DISABLE KEYS */;
INSERT INTO `users_authority` VALUES (1,1),(1,2),(1,3);
/*!40000 ALTER TABLE `users_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-07 20:43:04

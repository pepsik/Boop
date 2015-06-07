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
-- Table structure for table `comments`
--
SET NAMES utf8;

CREATE DATABASE IF NOT EXISTS boop;
GRANT ALL PRIVILEGES ON boop.* TO pc@localhost IDENTIFIED BY 'pc';

USE boop;

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `comment_id` int(11) DEFAULT NULL,
  `post_id_fk` int(11) NOT NULL,
  KEY `comment_id` (`comment_id`),
  KEY `comments_ibfk_2` (`post_id_fk`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `messages` (`message_id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`post_id_fk`) REFERENCES `posts` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorites` (
  `favorite_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_fk` int(11) NOT NULL,
  `post_id_fk` int(11) DEFAULT NULL,
  `added_date` datetime NOT NULL,
  PRIMARY KEY (`favorite_id`),
  KEY `users_favorites_ibfk_1` (`user_id_fk`),
  KEY `post_id_fk` (`post_id_fk`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`post_id_fk`) REFERENCES `posts` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_fk` int(11) DEFAULT NULL,
  `text` varchar(20000) NOT NULL,
  `posted_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `account_id` (`user_id_fk`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `passwords`
--

DROP TABLE IF EXISTS `passwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passwords` (
  `user_id_fk` int(11) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`user_id_fk`),
  CONSTRAINT `passwords_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `post_id` int(11) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `favorite_count` int(11) NOT NULL DEFAULT '0',
  KEY `post_id` (`post_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `messages` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `private_messages`
--

DROP TABLE IF EXISTS `private_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `private_messages` (
  `pmessage_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id_fk` int(11) NOT NULL,
  `recipient_id_fk` int(11) DEFAULT NULL,
  `text` varchar(1000) NOT NULL,
  `dispatch_date` datetime NOT NULL,
  `isRead` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`pmessage_id`),
  KEY `sender_id_fk` (`sender_id_fk`),
  KEY `receiver_id_fk` (`recipient_id_fk`),
  CONSTRAINT `private_messages_ibfk_1` FOREIGN KEY (`sender_id_fk`) REFERENCES `users` (`user_id`),
  CONSTRAINT `private_messages_ibfk_2` FOREIGN KEY (`recipient_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiles` (
  `user_id_fk` int(11) NOT NULL DEFAULT '0',
  `email` varchar(40) DEFAULT NULL,
  `fullname` varchar(60) NOT NULL,
  `birthdate` date NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `country` varchar(40) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `job` varchar(120) DEFAULT NULL,
  `about` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_id_fk`),
  CONSTRAINT `profiles_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratings` (
  `message_id_fk` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  KEY `message_id_fk` (`message_id_fk`),
  CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`message_id_fk`) REFERENCES `messages` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `account_id_fk` int(11) NOT NULL,
  `posts_per_page` int(11) NOT NULL,
  PRIMARY KEY (`account_id_fk`),
  UNIQUE KEY `account_id_fk_UNIQUE` (`account_id_fk`),
  CONSTRAINT `settings_ibfk_1` FOREIGN KEY (`account_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tag_owners`
--

DROP TABLE IF EXISTS `tag_owners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag_owners` (
  `post_id_fk` int(11) NOT NULL,
  `tag_id_fk` int(11) NOT NULL,
  KEY `post_id_fk` (`post_id_fk`),
  KEY `tag_id_fk` (`tag_id_fk`),
  CONSTRAINT `tag_owners_ibfk_1` FOREIGN KEY (`post_id_fk`) REFERENCES `posts` (`post_id`),
  CONSTRAINT `tag_owners_ibfk_2` FOREIGN KEY (`tag_id_fk`) REFERENCES `tags` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `posts_count` int(11) DEFAULT '0',
  `description` varchar(2000) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `author_id_fk` int(11) NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `unique_name` (`name`),
  KEY `author_id_fk` (`author_id_fk`),
  CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`author_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_authority`
--

DROP TABLE IF EXISTS `users_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_authority` (
  `user_id_fk` int(11) DEFAULT NULL,
  `role_id_fk` int(11) NOT NULL,
  KEY `role_id` (`role_id_fk`),
  KEY `account_id_fk` (`user_id_fk`),
  CONSTRAINT `users_authority_ibfk_2` FOREIGN KEY (`role_id_fk`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `users_authority_ibfk_3` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-07 14:10:28

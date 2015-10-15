DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`account_id`)
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(2000) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `when` datetime NOT NULL,
  PRIMARY KEY (`comment_id`)
) ;

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `text` varchar(10000) NOT NULL,
  `when` datetime NOT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`)
);

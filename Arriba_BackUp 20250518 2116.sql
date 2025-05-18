-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema imfinal
--

CREATE DATABASE IF NOT EXISTS imfinal;
USE imfinal;

--
-- Temporary table structure for view `event_archive`
--
DROP TABLE IF EXISTS `event_archive`;
DROP VIEW IF EXISTS `event_archive`;
CREATE TABLE `event_archive` (
  `archive_id` int(11),
  `event_id` int(11),
  `event_name` varchar(50),
  `event_date` datetime,
  `end_time` time,
  `event_category` varchar(50),
  `location` varchar(50),
  `status` enum('Active','Inactive')
);

--
-- Temporary table structure for view `upcoming_events`
--
DROP TABLE IF EXISTS `upcoming_events`;
DROP VIEW IF EXISTS `upcoming_events`;
CREATE TABLE `upcoming_events` (
  `eventId` int(11),
  `event_name` varchar(50),
  `event_category` varchar(50),
  `event_date` datetime,
  `end_time` time,
  `location` varchar(50),
  `status` enum('Active','Inactive')
);

--
-- Definition of table `archive`
--

DROP TABLE IF EXISTS `archive`;
CREATE TABLE `archive` (
  `archive_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`archive_id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `archive_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`eventId`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `archive`
--

/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;


--
-- Definition of table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `eventId` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) NOT NULL,
  `event_category` varchar(50) NOT NULL,
  `event_date` datetime NOT NULL,
  `end_time` time NOT NULL,
  `location` varchar(50) NOT NULL,
  `status` enum('Active','Inactive') DEFAULT NULL,
  PRIMARY KEY (`eventId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `events`
--

/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`,`first_name`,`last_name`,`username`,`password`) VALUES 
 (1,'admin','admin','admin123','admin123'),
 (2,'arriba','arriba','arriba','arriba123'),
 (3,'jyn','loquire','loquire','loquire123'),
 (4,'kim','kim','kim','kim12345'),
 (5,'jude','arriba','jude','jude123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of view `event_archive`
--

DROP TABLE IF EXISTS `event_archive`;
DROP VIEW IF EXISTS `event_archive`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `event_archive` AS select `a`.`archive_id` AS `archive_id`,`a`.`event_id` AS `event_id`,`e`.`event_name` AS `event_name`,`e`.`event_date` AS `event_date`,`e`.`end_time` AS `end_time`,`e`.`event_category` AS `event_category`,`e`.`location` AS `location`,`e`.`status` AS `status` from (`archive` `a` join `events` `e` on(`a`.`event_id` = `e`.`eventId`));

--
-- Definition of view `upcoming_events`
--

DROP TABLE IF EXISTS `upcoming_events`;
DROP VIEW IF EXISTS `upcoming_events`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `upcoming_events` AS select `events`.`eventId` AS `eventId`,`events`.`event_name` AS `event_name`,`events`.`event_category` AS `event_category`,`events`.`event_date` AS `event_date`,`events`.`end_time` AS `end_time`,`events`.`location` AS `location`,`events`.`status` AS `status` from `events` where `events`.`status` = 'Active';



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

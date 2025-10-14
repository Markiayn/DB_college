CREATE DATABASE  IF NOT EXISTS `music_streaming_platform` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `music_streaming_platform`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: music_streaming_platform
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `release_date` date DEFAULT NULL,
  `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `artist_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_album_artist` (`artist_id`),
  CONSTRAINT `fk_album_artist` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'30','2021-11-19','https://upload.wikimedia.org/wikipedia/en/6/6f/Adele_-_30.png',1),(2,'25','2015-11-20','https://upload.wikimedia.org/wikipedia/en/6/6f/Adele_-_30.png',1),(3,'24K Magic','2016-11-18','https://upload.wikimedia.org/wikipedia/en/1/1e/Bruno_Mars_-_24K_Magic.png',2),(4,'Unorthodox Jukebox','2012-12-07','https://upload.wikimedia.org/wikipedia/en/1/1e/Bruno_Mars_-_24K_Magic.png',2),(5,'The Life of a Showgirl','2025-10-03','https://upload.wikimedia.org/wikipedia/en/4/4b/Taylor_Swift_-_The_Life_of_a_Showgirl.png',3),(6,'1989','2014-10-27','https://upload.wikimedia.org/wikipedia/en/4/4b/Taylor_Swift_-_The_Life_of_a_Showgirl.png',3),(7,'Red','2012-10-22','https://upload.wikimedia.org/wikipedia/en/4/4b/Taylor_Swift_-_The_Life_of_a_Showgirl.png',3),(8,'Future Nostalgia','2020-03-27','https://upload.wikimedia.org/wikipedia/en/4/4f/Dua_Lipa_-_Future_Nostalgia.png',4),(9,'Dua Lipa','2017-06-02','https://upload.wikimedia.org/wikipedia/en/4/4f/Dua_Lipa_-_Future_Nostalgia.png',4),(10,'Ти [романтика]','2021-06-15','https://upload.wikimedia.org/wikipedia/uk/2/2b/MUR_-_Я_романтика.png',5),(11,'Ребелія','2022-11-10','https://upload.wikimedia.org/wikipedia/uk/2/2b/MUR_-_Я_романтика.png',5),(12,'Dawn FM','2022-01-07','https://upload.wikimedia.org/wikipedia/en/3/3b/The_Weeknd_-_Dawn_FM.png',6),(13,'After Hours','2020-03-20','https://upload.wikimedia.org/wikipedia/en/3/3b/The_Weeknd_-_Dawn_FM.png',6),(14,'Beauty Behind the Madness','2015-08-28','https://upload.wikimedia.org/wikipedia/en/3/3b/The_Weeknd_-_Dawn_FM.png',6),(15,'Mercury – Acts 1 & 2','2022-07-01','https://upload.wikimedia.org/wikipedia/en/4/4b/Imagine_Dragons_-_Mercury_Acts_1_%26_2.png',7),(16,'Evolve','2017-06-23','https://upload.wikimedia.org/wikipedia/en/4/4b/Imagine_Dragons_-_Mercury_Acts_1_%26_2.png',7),(17,'Music of the Spheres','2021-10-15','https://upload.wikimedia.org/wikipedia/en/0/0e/Coldplay_-_Music_of_the_Spheres.png',8),(18,'Everyday Life','2019-11-22','https://upload.wikimedia.org/wikipedia/en/0/0e/Coldplay_-_Music_of_the_Spheres.png',8),(19,'Happier Than Ever','2021-07-30','https://upload.wikimedia.org/wikipedia/en/0/0f/Billie_Eilish_-_Happier_Than_Ever.png',9),(20,'When We All Fall Asleep, Where Do We Go?','2019-03-29','https://upload.wikimedia.org/wikipedia/en/0/0f/Billie_Eilish_-_Happier_Than_Ever.png',9),(21,'=','2021-10-29','https://upload.wikimedia.org/wikipedia/en/4/4e/Ed_Sheeran_-_Equals.png',10),(22,'x','2014-06-20','https://upload.wikimedia.org/wikipedia/en/4/4e/Ed_Sheeran_-_Equals.png',10),(23,'+','2011-09-09','https://upload.wikimedia.org/wikipedia/en/4/4e/Ed_Sheeran_-_Equals.png',10);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_year` int DEFAULT NULL,
  `bio` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'Adele','UK',2006,'English singer-songwriter known for her powerful vocals and emotional ballads.'),(2,'Bruno Mars','USA',2004,'American singer, songwriter, and producer famous for his blend of pop, funk, and R&B.'),(3,'Taylor Swift','USA',2006,'American singer-songwriter recognized for her narrative songwriting and genre versatility.'),(4,'Dua Lipa','UK',2015,'British singer-songwriter famous for her dance-pop hits and distinctive vocal tone.'),(5,'МУР','Україна',2021,'Український інді-гурт, відомий своїм атмосферним звучанням та поєднанням електроніки з живими інструментами.'),(6,'The Weeknd','Canada',2010,'Canadian singer and record producer known for his dark R&B sound.'),(7,'Imagine Dragons','USA',2008,'American pop rock band formed in Las Vegas, Nevada.'),(8,'Coldplay','UK',1996,'British rock band known for atmospheric and melodic sound.'),(9,'Billie Eilish','USA',2015,'American singer-songwriter known for her unique voice and style.'),(10,'Ed Sheeran','UK',2011,'English singer-songwriter known for acoustic and pop hits.');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `subscription_id` bigint NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `currency` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `paid_at` datetime DEFAULT NULL,
  `method` enum('credit_card','paypal','apple_pay','google_pay','crypto') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'credit_card',
  `status` enum('pending','completed','failed','declined','refunded') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`id`),
  KEY `idx_payment_user` (`user_id`),
  KEY `idx_payment_subscription` (`subscription_id`),
  CONSTRAINT `fk_payment_subscription` FOREIGN KEY (`subscription_id`) REFERENCES `subscription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,1,0.00,'USD','2023-12-01 10:05:00','google_pay','completed'),(2,2,2,9.99,'USD','2024-06-10 12:25:00','credit_card','pending'),(3,2,2,9.99,'USD','2024-06-10 12:31:00','credit_card','completed'),(4,2,2,9.99,'USD','2024-07-10 12:30:00','credit_card','completed'),(5,3,3,4.99,'USD','2024-09-01 08:58:00','paypal','failed'),(6,3,3,4.99,'USD','2024-09-01 09:03:00','apple_pay','completed'),(7,4,4,15.99,'USD','2023-05-15 14:01:00','credit_card','completed'),(8,4,4,15.99,'USD','2023-06-15 14:00:00','credit_card','completed'),(9,5,5,9.99,'USD','2024-07-20 08:10:00','google_pay','pending'),(10,5,5,9.99,'USD','2024-07-20 08:16:00','google_pay','completed'),(11,5,5,9.99,'USD','2024-07-21 10:00:00','google_pay','refunded'),(12,6,6,0.00,'USD','2022-10-01 11:02:00','paypal','completed'),(13,7,7,15.99,'USD','2024-01-25 13:40:00','credit_card','failed'),(14,7,7,15.99,'USD','2024-01-25 13:44:00','credit_card','pending'),(15,7,7,15.99,'USD','2024-01-25 13:46:00','credit_card','completed'),(16,8,8,9.99,'USD','2024-10-05 17:59:00','apple_pay','pending'),(17,8,8,9.99,'USD','2024-10-05 18:02:00','apple_pay','declined'),(18,8,8,9.99,'USD','2024-10-06 09:10:00','paypal','completed'),(19,9,9,4.99,'USD','2024-03-11 15:25:00','credit_card','pending'),(20,9,9,4.99,'USD','2024-03-11 15:31:00','credit_card','completed'),(21,9,9,4.99,'USD','2024-09-11 10:00:00','credit_card','declined'),(22,10,10,9.99,'USD','2024-04-22 06:55:00','crypto','pending'),(23,10,10,9.99,'USD','2024-04-22 07:02:00','crypto','completed'),(24,10,10,9.99,'USD','2024-05-22 07:00:00','credit_card','completed'),(25,11,11,15.99,'USD','2023-08-08 16:01:00','google_pay','completed'),(26,11,11,15.99,'USD','2023-09-08 16:00:00','google_pay','completed'),(27,12,12,0.00,'USD','2024-11-01 12:01:00','apple_pay','completed'),(28,13,13,4.99,'USD','2024-05-03 10:25:00','paypal','pending'),(29,13,13,4.99,'USD','2024-05-03 10:32:00','paypal','completed'),(30,13,13,4.99,'USD','2024-05-10 09:00:00','paypal','refunded');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_public` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_playlist_user` (`user_id`),
  CONSTRAINT `fk_playlist_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,1,'Morning Vibes ☀️',1,'2023-02-14 08:30:00'),(2,1,'Coding Focus ?',0,'2024-06-22 21:15:00'),(3,2,'Road Trip Mix ?',1,'2022-09-10 14:45:00'),(4,2,'Rainy Days ☔',1,'2023-11-05 10:20:00'),(5,2,'Jazz for Coffee ☕',0,'2024-04-12 07:05:00'),(6,3,'Workout Pump ?',1,'2023-05-01 17:30:00'),(7,3,'Chill at Home ?',0,'2024-01-28 22:10:00'),(8,4,'Best of 2020s ?',1,'2024-07-03 13:42:00'),(9,4,'Deep Sleep ?',0,'2022-12-12 23:59:00'),(10,5,'Party All Night ?',1,'2023-03-18 20:00:00'),(11,5,'Acoustic Feel ?',0,'2024-02-25 19:11:00'),(12,6,'Study Beats ?',1,'2023-08-21 15:00:00'),(13,6,'Travel Mood ✈️',1,'2024-03-10 09:35:00'),(14,7,'Love Songs ❤️',1,'2022-11-17 16:00:00'),(15,7,'Lo-Fi Dreams ?',0,'2024-06-06 01:00:00'),(16,8,'Indie Discoveries ?',1,'2023-10-02 18:23:00'),(17,8,'Late Night Drive ?',0,'2024-05-15 00:20:00'),(18,9,'Best of Rock ?',1,'2023-09-12 11:05:00'),(19,9,'Melancholy Mood ?',0,'2024-07-28 02:10:00'),(20,10,'Summer 2024 ☀️',1,'2024-06-01 12:00:00'),(21,10,'Winter Vibes ❄️',0,'2023-12-14 21:50:00'),(22,11,'Driving Classics ?',1,'2023-01-09 13:37:00'),(23,11,'Focus & Flow ?',0,'2024-09-09 08:10:00'),(24,12,'Electronic Energy ⚡',1,'2022-10-31 17:00:00'),(25,12,'Sad Hours ?',0,'2023-08-08 22:45:00'),(26,13,'Legends Never Die ?',1,'2024-02-18 10:30:00'),(27,13,'Hidden Gems ?',0,'2023-04-07 15:05:00');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_item`
--

DROP TABLE IF EXISTS `playlist_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `added_at` datetime NOT NULL,
  `position` int NOT NULL,
  `playlist_id` bigint NOT NULL,
  `track_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_playlist_position` (`playlist_id`,`position`),
  KEY `idx_pi_playlist` (`playlist_id`),
  KEY `idx_pi_track` (`track_id`),
  CONSTRAINT `FKiu5c96nfg5diuwib1w2phveii` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`),
  CONSTRAINT `FKlcvq2e9lq0r788sbymq1bl189` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_item`
--

LOCK TABLES `playlist_item` WRITE;
/*!40000 ALTER TABLE `playlist_item` DISABLE KEYS */;
INSERT INTO `playlist_item` VALUES (1,'2024-02-02 09:41:37',1,1,15),(2,'2024-01-09 22:39:49',2,1,41),(3,'2024-06-04 01:55:08',3,1,47),(4,'2024-09-27 15:56:50',4,1,84),(5,'2024-07-30 18:03:45',1,2,10),(6,'2025-06-09 16:51:02',2,2,60),(7,'2024-02-17 19:33:19',3,2,74),(8,'2025-05-23 14:41:48',4,2,72),(9,'2024-11-18 22:44:27',5,2,99),(10,'2024-12-30 12:07:39',6,2,40),(11,'2025-06-16 10:21:22',1,3,44),(12,'2025-02-06 03:26:13',2,3,22),(13,'2024-01-23 05:07:22',3,3,94),(14,'2024-04-09 10:54:11',4,3,103),(15,'2025-04-22 18:29:36',5,3,3),(16,'2024-02-24 07:34:37',6,3,28),(17,'2024-08-28 11:30:36',1,4,55),(18,'2024-05-29 16:57:59',2,4,50),(19,'2024-08-07 07:32:50',3,4,37),(20,'2025-01-21 20:28:54',4,4,35),(21,'2024-02-15 15:43:52',5,4,11),(22,'2024-07-12 19:26:40',1,5,99),(23,'2025-03-27 23:37:42',2,5,91),(24,'2024-10-27 13:58:00',3,5,13),(25,'2025-06-02 05:31:22',4,5,92),(26,'2025-05-08 09:11:11',5,5,102),(27,'2025-05-02 18:18:58',1,6,48),(28,'2024-07-14 23:14:33',2,6,36),(29,'2025-01-04 08:24:19',3,6,80),(30,'2024-10-28 03:09:58',4,6,14),(31,'2024-08-14 12:32:21',5,6,24),(32,'2024-04-20 04:39:26',1,7,50),(33,'2024-01-18 06:17:18',2,7,18),(34,'2024-01-10 21:53:36',3,7,34),(35,'2025-01-24 23:46:35',1,8,26),(36,'2024-11-02 23:37:22',2,8,86),(37,'2024-02-23 05:23:19',3,8,95),(38,'2024-12-11 02:04:39',4,8,97),(39,'2025-01-07 09:49:13',5,8,96),(40,'2024-09-29 14:00:43',6,8,78),(41,'2025-01-20 05:05:03',1,9,93),(42,'2024-12-14 11:14:09',2,9,46),(43,'2025-02-17 23:50:33',3,9,87),(44,'2025-06-29 12:17:34',4,9,17),(45,'2024-11-20 13:40:34',5,9,44),(46,'2024-12-20 12:54:28',6,9,7),(47,'2024-08-12 17:19:56',1,10,83),(48,'2024-11-02 07:39:47',2,10,79),(49,'2024-09-20 03:27:43',3,10,33),(50,'2024-09-29 19:15:38',4,10,17),(51,'2024-09-11 12:04:29',5,10,14),(52,'2024-02-20 05:59:38',1,11,76),(53,'2024-09-09 19:34:37',2,11,86),(54,'2025-03-21 19:15:19',3,11,32),(55,'2024-04-12 12:31:11',1,12,63),(56,'2024-05-22 17:02:24',2,12,85),(57,'2024-11-25 12:38:42',3,12,81),(58,'2025-04-17 04:30:54',1,13,21),(59,'2024-08-06 20:03:37',2,13,30),(60,'2024-04-02 15:45:01',3,13,23),(61,'2024-04-17 04:19:02',1,14,98),(62,'2024-09-10 04:19:48',2,14,68),(63,'2025-06-04 20:11:23',3,14,75),(64,'2024-12-17 11:50:13',4,14,54),(65,'2025-01-03 01:15:52',5,14,90),(66,'2024-10-17 18:14:49',1,15,13),(67,'2024-06-10 21:57:55',2,15,16),(68,'2025-02-01 02:14:06',3,15,59),(69,'2025-06-17 16:37:18',1,16,61),(70,'2024-04-20 03:08:08',2,16,10),(71,'2025-06-17 20:37:04',3,16,26),(72,'2024-02-23 16:34:54',1,17,88),(73,'2025-03-22 10:00:02',2,17,53),(74,'2024-08-29 20:07:36',3,17,2),(75,'2025-01-14 23:14:51',4,17,42),(76,'2025-03-08 12:23:21',5,17,60),(77,'2024-08-25 11:25:38',1,18,58),(78,'2025-06-10 08:28:44',2,18,29),(79,'2024-06-06 02:50:52',3,18,5),(80,'2024-03-10 16:24:05',4,18,4),(81,'2024-10-22 17:29:54',5,18,12),(82,'2024-12-15 02:59:01',1,19,31),(83,'2024-11-22 16:58:10',2,19,20),(84,'2024-11-22 07:02:18',3,19,38),(85,'2025-05-06 00:00:45',4,19,1),(86,'2025-05-11 17:15:21',5,19,79),(87,'2025-01-01 09:25:03',1,20,62),(88,'2024-05-16 21:49:04',2,20,43),(89,'2024-03-31 01:08:11',3,20,39),(90,'2024-10-19 00:40:59',1,21,38),(91,'2024-05-18 10:14:10',2,21,78),(92,'2024-08-20 18:51:41',3,21,83),(93,'2025-06-06 12:30:58',4,21,60),(94,'2024-04-20 13:02:51',1,22,101),(95,'2025-04-21 18:05:04',2,22,19),(96,'2024-05-13 20:21:50',3,22,45),(97,'2024-04-22 17:37:12',4,22,52),(98,'2024-04-26 02:08:19',5,22,6),(99,'2024-11-26 03:20:08',1,23,56),(100,'2025-01-18 21:19:42',2,23,78),(101,'2025-02-18 00:01:08',3,23,71),(102,'2025-06-18 04:02:12',4,23,9),(103,'2024-10-28 15:35:21',1,24,67),(104,'2024-12-23 11:57:28',2,24,89),(105,'2025-05-01 13:10:51',3,24,57),(106,'2024-03-16 10:50:30',4,24,49),(107,'2024-09-20 12:53:28',5,24,69),(108,'2025-06-12 21:52:50',6,24,27),(109,'2024-07-29 07:00:00',1,25,77),(110,'2025-06-21 21:21:16',2,25,64),(111,'2024-04-05 15:57:23',3,25,8),(112,'2024-11-25 00:17:08',1,26,73),(113,'2024-04-08 12:22:07',2,26,25),(114,'2024-03-01 23:16:40',3,26,82),(115,'2025-05-24 21:57:20',4,26,70),(116,'2024-04-11 08:27:27',5,26,100),(117,'2024-06-15 21:35:46',1,27,51),(118,'2025-02-19 19:32:28',2,27,66),(119,'2025-02-05 10:53:21',3,27,85),(120,'2024-03-13 08:48:01',4,27,65);
/*!40000 ALTER TABLE `playlist_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `plan_code` enum('free','premium','family','student') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'free',
  `started_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ends_at` datetime DEFAULT NULL,
  `status` enum('trial','active','expired','canceled','pending','suspended') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'trial',
  PRIMARY KEY (`id`),
  KEY `idx_sub_user` (`user_id`),
  CONSTRAINT `fk_subscription_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (1,1,'free','2023-12-01 10:00:00','2024-12-01 10:00:00','expired'),(2,2,'premium','2024-06-10 12:30:00','2025-06-10 12:30:00','active'),(3,3,'student','2024-09-01 09:00:00','2025-03-01 09:00:00','active'),(4,4,'family','2023-05-15 14:00:00','2024-05-15 14:00:00','expired'),(5,5,'premium','2024-07-20 08:15:00','2025-07-20 08:15:00','active'),(6,6,'free','2022-10-01 11:00:00','2023-10-01 11:00:00','expired'),(7,7,'family','2024-01-25 13:45:00','2025-01-25 13:45:00','active'),(8,8,'premium','2024-10-05 18:00:00','2025-10-05 18:00:00','pending'),(9,9,'student','2024-03-11 15:30:00','2024-09-11 15:30:00','canceled'),(10,10,'premium','2024-04-22 07:00:00','2025-04-22 07:00:00','active'),(11,11,'family','2023-08-08 16:00:00','2024-08-08 16:00:00','expired'),(12,12,'free','2024-11-01 12:00:00','2025-11-01 12:00:00','trial'),(13,13,'student','2024-05-03 10:30:00','2025-05-03 10:30:00','suspended');
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `duration_sec` int NOT NULL,
  `audio_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `explicit_flag` tinyint(1) NOT NULL DEFAULT '0',
  `album_id` bigint NOT NULL,
  `artist_id` bigint NOT NULL,
  `audio_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_track_album` (`album_id`),
  KEY `idx_track_artist` (`artist_id`),
  CONSTRAINT `fk_track_album` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_track_artist` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,'Strangers By Nature',182,'adele_strangers_by_nature',0,1,1,'https://cloudflare.com/adele_strangers_by_nature.mp3'),(2,'Easy On Me',224,'adele_easy_on_me',0,1,1,'https://cloudflare.com/adele_easy_on_me.mp3'),(3,'My Little Love',389,'adele_my_little_love',0,1,1,'https://cloudflare.com/adele_my_little_love.mp3'),(4,'Cry Your Heart Out',255,'adele_cry_your_heart_out',0,1,1,'https://cloudflare.com/adele_cry_your_heart_out.mp3'),(5,'Hello',295,'hello',0,2,1,'https://cloudflare.com/hello.mp3'),(6,'Send My Love (To Your New Lover)',223,'send_my_love_(to_your_new_lover)',0,2,1,'https://cloudflare.com/send_my_love_(to_your_new_lover).mp3'),(7,'I Miss You',348,'i_miss_you',0,2,1,'https://cloudflare.com/adele_my_little_love.mp3'),(8,'24K Magic',225,'24k_magic',0,3,2,'https://cloudflare.com/24k_magic.mp3'),(9,'Chunky',186,'chunky',0,3,2,'https://cloudflare.com/chunky.mp3'),(10,'Perm',210,'perm',0,3,2,'https://cloudflare.com/perm.mp3'),(11,'That\'s What I Like',206,'thats_what_i_like',0,3,2,'https://cloudflare.com/thats_what_i_like.mp3'),(12,'Versace on the Floor',261,'versace_on_the_floor',0,3,2,'https://cloudflare.com/versace_on_the_floor.mp3'),(13,'Young Girls',228,'young_girls',0,4,2,'https://cloudflare.com/young_girls.mp3'),(14,'Locked out of Heaven',233,'locked_out_of_heaven',0,4,2,'https://cloudflare.com/locked_out_of_heaven.mp3'),(15,'Gorilla',244,'gorilla',1,4,2,'https://cloudflare.com/gorilla.mp3'),(16,'Treasure',178,'treasure',1,4,2,'https://cloudflare.com/treasure.mp3'),(17,'The Fate of Ophelia',226,'the_fate_of_ophelia',0,5,3,'https://cloudflare.com/the_fate_of_ophelia.mp3'),(18,'Elizabeth Taylor',208,'elizabeth_taylor',0,5,3,'https://cloudflare.com/elizabeth_taylor.mp3'),(19,'Opalite',235,'opalite',0,5,3,'https://cloudflare.com/opalite.mp3'),(20,'Welcome To New York',212,'welcome_to_new_york',0,6,3,'https://cloudflare.com/welcome_to_new_york.mp3'),(21,'Blank Space',231,'blank_space',0,6,3,'https://cloudflare.com/blank_space.mp3'),(22,'Style',231,'style',0,6,3,'https://cloudflare.com/style.mp3'),(23,'Out Of The Woods',235,'out_of_the_woods',0,6,3,'https://cloudflare.com/out_of_the_woods.mp3'),(24,'All You Had To Do Was Stay',193,'all_you_had_to_do_was_stay',0,6,3,'https://cloudflare.com/all_you_had_to_do_was_stay.mp3'),(25,'Shake It Off',219,'shake_it_off',0,6,3,'https://cloudflare.com/shake_it_off.mp3'),(26,'State Of Grace',295,'state_of_grace',0,7,3,'https://cloudflare.com/state_of_grace.mp3'),(27,'Red',220,'red',0,7,3,'https://cloudflare.com/red.mp3'),(28,'Treacherous',240,'treacherous',0,7,3,'https://cloudflare.com/treacherous.mp3'),(29,'Future Nostalgia',184,'future_nostalgia',0,8,4,'https://cloudflare.com/future_nostalgia.mp3'),(30,'Don\'t Start Now',183,'dont_start_now',0,8,4,'https://cloudflare.com/dont_start_now.mp3'),(31,'Cool',209,'cool',0,8,4,'https://cloudflare.com/cool.mp3'),(32,'Physical',193,'physical',0,8,4,'https://cloudflare.com/physical.mp3'),(33,'Levitating',203,'levitating',0,8,4,'https://cloudflare.com/levitating.mp3'),(34,'Genesis',205,'genesis',0,9,4,'https://cloudflare.com/genesis.mp3'),(35,'Lost In Your Light (feat. Miguel)',203,'lost_in_your_light_feat_miguel',0,9,4,'https://cloudflare.com/lost_in_your_light_feat_miguel.mp3'),(36,'Hotter Than Hell',187,'hotter_than_hell',0,9,4,'https://cloudflare.com/hotter_than_hell.mp3'),(37,'Ще до того як колись I (feat. Yevhenii Dubovyk)',150,'shche_do_togo_yak_kolys_i_feat_yevhenii_dubovyk',0,10,5,'https://cloudflare.com/shche_do_togo_yak_kolys_i_feat_yevhenii_dubovyk.mp3'),(38,'Ще до того як колись II (feat. Yevhenii Dubovyk)',145,'shche_do_togo_yak_kolys_ii_feat_yevhenii_dubovyk',0,10,5,'https://cloudflare.com/shche_do_togo_yak_kolys_ii_feat_yevhenii_dubovyk.mp3'),(39,'19:18 (feat. Женя Янович)',55,'19_18_feat_zhenia_yanovych',0,10,5,'https://cloudflare.com/19_18_feat_zhenia_yanovych.mp3'),(40,'Третя Рота (feat. Сергій Жадан)',260,'tretia_rota_feat_serhii_zhadan',0,10,5,'https://cloudflare.com/tretia_rota_feat_serhii_zhadan.mp3'),(41,'В Колізеї (feat. NAZVA)',205,'v_kolizei_feat_nazva',0,10,5,'https://cloudflare.com/v_kolizei_feat_nazva.mp3'),(42,'19:30 (feat. Женя Янович)',59,'19_30_feat_zhenia_yanovych',0,10,5,'https://cloudflare.com/19_30_feat_zhenia_yanovych.mp3'),(43,'Бі Бо Бу (feat. хейспіч)',136,'bi_bo_bu_feat_kheispich',1,10,5,'https://cloudflare.com/bi_bo_bu_feat_kheispich.mp3'),(44,'Не Незалежність',81,'ne_nezalezhnist',0,11,5,'https://cloudflare.com/ne_nezalezhnist.mp3'),(45,'ЦІ СТІНИ МАЮТЬ ВУХА',309,'tsi_stiny_majut_vukha',0,11,5,'https://cloudflare.com/tsi_stiny_majut_vukha.mp3'),(46,'19:56',41,'19_56',0,11,5,'https://cloudflare.com/19_56.mp3'),(47,'XX З\'їзд',237,'xx_zyizd',0,11,5,'https://cloudflare.com/xx_zyizd.mp3'),(48,'Черкаська Правда',209,'cherkaska_pravda',0,11,5,'https://cloudflare.com/cherkaska_pravda.mp3'),(49,'Приходь у KTM!',422,'prykhod_u_ktm',0,11,5,'https://cloudflare.com/prykhod_u_ktm.mp3'),(50,'Більше за всіх нас',302,'bilshe_za_vsikh_nas',0,11,5,'https://cloudflare.com/bilshe_za_vsikh_nas.mp3'),(51,'Dawn FM',96,'dawn_fm',0,12,6,'https://cloudflare.com/dawn_fm.mp3'),(52,'Gasoline',212,'gasoline',0,12,6,'https://cloudflare.com/gasoline.mp3'),(53,'How Do I Make You Love Me?',214,'how_do_i_make_you_love_me',0,12,6,'https://cloudflare.com/how_do_i_make_you_love_me.mp3'),(54,'Take My Breath',339,'take_my_breath',0,12,6,'https://cloudflare.com/take_my_breath.mp3'),(55,'Sacrifice',188,'sacrifice',1,12,6,'https://cloudflare.com/sacrifice.mp3'),(56,'A Tale By Quincy',96,'a_tale_by_quincy',1,12,6,'https://cloudflare.com/a_tale_by_quincy.mp3'),(57,'Alone Again',250,'alone_again',1,13,6,'https://cloudflare.com/alone_again.mp3'),(58,'Too Late',239,'too_late',1,13,6,'https://cloudflare.com/too_late.mp3'),(59,'Hardest To Love',211,'hardest_to_love',1,13,6,'https://cloudflare.com/hardest_to_love.mp3'),(60,'Scared To Live',191,'scared_to_live',1,13,6,'https://cloudflare.com/scared_to_live.mp3'),(61,'Real Life',223,'real_life',1,14,6,'https://cloudflare.com/real_life.mp3'),(62,'Losers',281,'losers',1,14,6,'https://cloudflare.com/losers.mp3'),(63,'Tell Your Friends',334,'tell_your_friends',1,14,6,'https://cloudflare.com/tell_your_friends.mp3'),(64,'Often',249,'often',1,14,6,'https://cloudflare.com/often.mp3'),(65,'Enemy (with JID) - from the series Arcane League of Legends',173,'enemy_with_jid_from_the_series_arcane_league_of_legends',0,15,7,'https://cloudflare.com/enemy_with_jid_from_the_series_arcane_league_of_legends.mp3'),(66,'My Life',224,'my_life',0,15,7,'https://cloudflare.com/my_life.mp3'),(67,'Lonely',159,'lonely',0,15,7,'https://cloudflare.com/lonely.mp3'),(68,'Wrecked',244,'wrecked',0,15,7,'https://cloudflare.com/wrecked.mp3'),(69,'Monday',187,'monday',0,15,7,'https://cloudflare.com/monday.mp3'),(70,'Next To Me',230,'next_to_me',0,16,7,'https://cloudflare.com/next_to_me.mp3'),(71,'I Don’t Know Why',190,'i_dont_know_why',0,16,7,'https://cloudflare.com/i_dont_know_why.mp3'),(72,'Whatever It Takes',201,'whatever_it_takes',0,16,7,'https://cloudflare.com/whatever_it_takes.mp3'),(73,'Believer',204,'believer',0,16,7,'https://cloudflare.com/believer.mp3'),(74,'?',53,'earth_intro',0,17,8,'https://cloudflare.com/earth_intro.mp3'),(75,'Higher Power',206,'higher_power',0,17,8,'https://cloudflare.com/higher_power.mp3'),(76,'Humankind',266,'humankind',0,17,8,'https://cloudflare.com/humankind.mp3'),(77,'✨',53,'sparkle_outro',0,17,8,'https://cloudflare.com/sparkle_outro.mp3'),(78,'Sunrise',151,'sunrise',0,18,8,'https://cloudflare.com/sunrise.mp3'),(79,'Church',230,'church',0,18,8,'https://cloudflare.com/church.mp3'),(80,'Trouble In Town',278,'trouble_in_town',1,18,8,'https://cloudflare.com/trouble_in_town.mp3'),(81,'BrokEn',150,'broken',0,18,8,'https://cloudflare.com/broken.mp3'),(82,'Getting Older',244,'getting_older',0,19,9,'https://cloudflare.com/getting_older.mp3'),(83,'I Didn\'t Change My Number',158,'i_didnt_change_my_number',1,19,9,'https://cloudflare.com/i_didnt_change_my_number.mp3'),(84,'Billie Bossa Nova',196,'billie_bossa_nova',0,19,9,'https://cloudflare.com/billie_bossa_nova.mp3'),(85,'my future',210,'my_future',0,19,9,'https://cloudflare.com/my_future.mp3'),(86,'Oxytocin',210,'oxytocin',0,19,9,'https://cloudflare.com/oxytocin.mp3'),(87,'my strange addiction',179,'my_strange_addiction',0,20,9,'https://cloudflare.com/my_strange_addiction.mp3'),(88,'bury a friend',193,'bury_a_friend',0,20,9,'https://cloudflare.com/bury_a_friend.mp3'),(89,'ilomilo',156,'ilomilo',0,20,9,'https://cloudflare.com/ilomilo.mp3'),(90,'listen before i go',242,'listen_before_i_go',0,20,9,'https://cloudflare.com/listen_before_i_go.mp3'),(91,'i love you',291,'i_love_you',0,20,9,'https://cloudflare.com/i_love_you.mp3'),(92,'Tides',195,'tides',0,21,10,'https://cloudflare.com/tides.mp3'),(93,'Shivers',207,'shivers',0,21,10,'https://cloudflare.com/shivers.mp3'),(94,'First Times',185,'first_times',0,21,10,'https://cloudflare.com/first_times.mp3'),(95,'One',252,'one',0,22,10,'https://cloudflare.com/one.mp3'),(96,'I\'m a Mess',244,'im_a_mess',0,22,10,'https://cloudflare.com/im_a_mess.mp3'),(97,'Sing',235,'sing',0,22,10,'https://cloudflare.com/sing.mp3'),(98,'Don\'t',219,'dont',0,22,10,'https://cloudflare.com/dont.mp3'),(99,'The A Team',258,'the_a_team',0,23,10,'https://cloudflare.com/the_a_team.mp3'),(100,'Drunk',200,'drunk',0,23,10,'https://cloudflare.com/drunk.mp3'),(101,'U.N.I.',228,'u_n_i',0,23,10,'https://cloudflare.com/u_n_i.mp3'),(102,'Grade 8',179,'grade_8',0,23,10,'https://cloudflare.com/grade_8.mp3'),(103,'Wake Me Up',229,'wake_me_up',1,23,10,'https://cloudflare.com/wake_me_up.mp3');
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('ACTIVE','BANNED','DELETED','INACTIVE','SUSPENDED') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'m@example.com','$2a$10$7RYR1W1kACoz/OdsbmGvcOAPVVa6lksx.cvPYqZKu4zolJOfV8232','Markiyan','UA','2025-10-03 18:15:32','ACTIVE'),(2,'markiyankobiletskiy2@gmail.com','$2a$10$BvymaalFFzJfveDc8AI.b.MyPf5CD.wqb5227IfI4ryp/kZY.h/bq','Oleg','UA','2025-10-03 18:16:28','ACTIVE'),(3,'markiyankobiletskiy3@gmail.com','$2a$10$4LH26hP62d87tTL/kClooehnsaQkj//EpCdd8NznWiim5bYXtMEGq','Ira','EN','2025-10-04 18:25:23','ACTIVE'),(4,'olena.kovalenko89@gmail.com','$2a$10$syzZPiUlAbI.3sMKSxuJxeiona4Yvszqs2Gc6o.P4hicPPA.YNmf6','Olena Kovalenko','UA','2025-10-12 15:53:38','ACTIVE'),(5,'petro.ivanchuk@outlook.com','$2a$10$MhMSKl8qdSl6wVBhUtVbNuHr8DBViufWEGin2hV6.IB2/5drMQQP2','Petro Ivanchuk','PL','2025-10-12 15:53:47','ACTIVE'),(6,'sofia.martinez@yahoo.com','$2a$10$MCJCdGkBLL8x7GgNSxuHQe7ls8avK7rGHNoRO5xTNVC.YG1X3IchS','Sofia Martinez','ES','2025-10-12 15:53:55','ACTIVE'),(7,'andriy.bereznyak@mail.com','$2a$10$a8/yeNVpZdoy5fx6av572uljtTrFJV6AfRHbg22aGwB15gdrvhp9m','Andriy Bereznyak','UA','2025-10-12 15:54:04','ACTIVE'),(8,'marta.nowak@protonmail.com','$2a$10$z9v/vaaXlLTj9EXASInhcOUHZOtNgfKVgOwBP2Qfp9X2OQnwba1tW','Marta Nowak','PL','2025-10-12 15:54:10','ACTIVE'),(9,'liam.johnson@gmail.com','$2a$10$y0CRKE6jWrNY.FaPQrAnvul9vwowB3GBYV7VR087sXZa0gRiLAKKK','Liam Johnson','US','2025-10-12 15:54:16','ACTIVE'),(10,'emma.thompson@outlook.com','$2a$10$.pRGfWDDXOSeMXZwJkdhhumSjMM1pb0N/.OLiW3aOxbBwHw1ye4rW','Emma Thompson','GB','2025-10-12 15:54:23','ACTIVE'),(11,'noah.schneider@hotmail.com','$2a$10$LDHGNSBskexBcTqgiR6BfeGEYZ/JFJJfs0CD44UjyzBebIVzuEscS','Noah Schneider','DE','2025-10-12 15:54:29','ACTIVE'),(12,'isabella.rossi@gmail.com','$2a$10$6IJ8r9VjCpU/OBX6IrI5a.z.t.5ZB30BJMb2fGu2JrX4kiunq4jWC','Isabella Rossi','IT','2025-10-12 15:54:39','ACTIVE'),(13,'carlos.santos@icloud.com','$2a$10$AS7Vly/Cu7TFH4VDJhasquZ/PnyqRvvuWCtKrTs7G4Ck.bRx7jJOe','Carlos Santos','BR','2025-10-12 15:54:45','ACTIVE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-14 10:41:49

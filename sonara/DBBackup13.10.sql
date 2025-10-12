CREATE DATABASE  IF NOT EXISTS `music_streaming_platform` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `music_streaming_platform`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: music_streaming_platform
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
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `release_date` date DEFAULT NULL,
  `cover_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `artist_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_album_artist` (`artist_id`),
  CONSTRAINT `fk_album_artist` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'After Hours','2020-03-20','https://cdn.sonara.cloudflare.com/covers/after_hours.jpg',1),(2,'Evolve','2017-06-23','https://cdn.sonara.cloudflare.com/covers/evolve.jpg',2),(3,'A Head Full of Dreams','2015-12-04','https://cdn.sonara.cloudflare.com/covers/a_head_full_of_dreams.jpg',3),(4,'Happier Than Ever','2021-07-30','https://cdn.sonara.cloudflare.com/covers/happier_than_ever.jpg',4),(5,'Divide (÷)','2017-03-03','https://cdn.sonara.cloudflare.com/covers/divide.jpg',5);
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
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_year` int DEFAULT NULL,
  `bio` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'The Weeknd','Canada',2010,'Canadian singer and record producer known for his dark R&B sound.'),(2,'Imagine Dragons','USA',2008,'American pop rock band formed in Las Vegas, Nevada.'),(3,'Coldplay','UK',1996,'British rock band known for atmospheric and melodic sound.'),(4,'Billie Eilish','USA',2015,'American singer-songwriter known for her unique voice and style.'),(5,'Ed Sheeran','UK',2011,'English singer-songwriter known for acoustic and pop hits.');
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
  `currency` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL,
  `paid_at` datetime DEFAULT NULL,
  `method` enum('credit_card','paypal','apple_pay','google_pay','crypto') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'credit_card',
  `status` enum('pending','completed','failed','declined','refunded') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`id`),
  KEY `idx_payment_user` (`user_id`),
  KEY `idx_payment_subscription` (`subscription_id`),
  CONSTRAINT `fk_payment_subscription` FOREIGN KEY (`subscription_id`) REFERENCES `subscription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
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
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_public` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_playlist_user` (`user_id`),
  CONSTRAINT `fk_playlist_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_item`
--

LOCK TABLES `playlist_item` WRITE;
/*!40000 ALTER TABLE `playlist_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlistitem`
--

DROP TABLE IF EXISTS `playlistitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlistitem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `playlist_id` bigint NOT NULL,
  `track_id` bigint NOT NULL,
  `position` int NOT NULL,
  `added_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_playlist_position` (`playlist_id`,`position`),
  KEY `idx_pi_playlist` (`playlist_id`),
  KEY `idx_pi_track` (`track_id`),
  CONSTRAINT `fk_pi_playlist` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pi_track` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlistitem`
--

LOCK TABLES `playlistitem` WRITE;
/*!40000 ALTER TABLE `playlistitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlistitem` ENABLE KEYS */;
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
  `plan_code` enum('free','premium','family','student') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'free',
  `started_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ends_at` datetime DEFAULT NULL,
  `status` enum('trial','active','expired','canceled','pending','suspended') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'trial',
  PRIMARY KEY (`id`),
  KEY `idx_sub_user` (`user_id`),
  CONSTRAINT `fk_subscription_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
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
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `duration_sec` int NOT NULL,
  `audio_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `explicit_flag` tinyint(1) NOT NULL DEFAULT '0',
  `album_id` bigint NOT NULL,
  `artist_id` bigint NOT NULL,
  `audio_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_track_album` (`album_id`),
  KEY `idx_track_artist` (`artist_id`),
  CONSTRAINT `fk_track_album` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_track_artist` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (3,'Blinding Lights',200,'audio/blinding_lights.mp3',0,1,1,NULL),(4,'Save Your Tears',215,'audio/save_your_tears.mp3',0,1,1,NULL),(5,'Believer',204,'audio/believer.mp3',0,2,2,NULL),(6,'Thunder',187,'audio/thunder.mp3',0,2,2,NULL),(7,'Adventure of a Lifetime',263,'audio/adventure_of_a_lifetime.mp3',0,3,3,NULL),(8,'Happier Than Ever',298,'audio/happier_than_ever.mp3',1,4,4,NULL),(9,'Bad Guy',194,'audio/bad_guy.mp3',1,4,4,NULL),(10,'Shape of You',233,'audio/shape_of_you.mp3',0,5,5,NULL),(11,'Perfect',263,'audio/perfect.mp3',0,5,5,NULL);
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
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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

-- Dump completed on 2025-10-13  0:35:54

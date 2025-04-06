-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 192.168.0.158    Database: oneteams
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `U_ID` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `U_pass` varchar(16) DEFAULT NULL,
  `U_name` varchar(16) NOT NULL,
  `U_admin` int NOT NULL DEFAULT '0',
  `U_color` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`U_ID`),
  UNIQUE KEY `U_pass_UNIQUE` (`U_pass`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (001,'ryoichi1','お疲れ様でした。',1,0),(002,'nanae','平社員　なな',0,0),(003,'nishikawa','退職者の　西',2,0),(004,'murata','退職者の　村',2,0),(006,'chihiro','平社員　ひろ',0,0),(007,'aiai','平社員　木',0,0),(008,'1111','管理者　諒一',1,0),(009,'6666','平社員　こんばんは',0,0),(010,'iiii','平社員　おーいお茶',0,0),(011,'1219','平社員　ペク',1,0),(012,'useraddtest','管理者　NO2',2,0),(013,'ikura','平社員　さよなら',0,0),(014,'aaii','平社員　ぼうや',0,0),(015,'momo','平社員　momo',0,0),(016,'123','管理者　123',0,0),(017,'1223','1226新規',0,0),(018,'5555','管理者　さな',1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-27 12:48:39

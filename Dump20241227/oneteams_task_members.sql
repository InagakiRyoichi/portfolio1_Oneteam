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
-- Table structure for table `task_members`
--

DROP TABLE IF EXISTS `task_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_members` (
  `TSK_ID` int(5) unsigned zerofill NOT NULL,
  `U_ID` int(3) unsigned zerofill NOT NULL,
  PRIMARY KEY (`TSK_ID`,`U_ID`),
  KEY `task_members_U_ID_idx` (`U_ID`),
  CONSTRAINT `task_members_TSK_ID` FOREIGN KEY (`TSK_ID`) REFERENCES `tasks` (`TSK_ID`),
  CONSTRAINT `task_members_U_ID` FOREIGN KEY (`U_ID`) REFERENCES `users` (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_members`
--

LOCK TABLES `task_members` WRITE;
/*!40000 ALTER TABLE `task_members` DISABLE KEYS */;
INSERT INTO `task_members` VALUES (00006,001),(00008,001),(00017,001),(00018,001),(00020,001),(00025,001),(00026,001),(00027,001),(00029,001),(00030,001),(00033,001),(00036,001),(00037,001),(00038,001),(00040,001),(00041,001),(00043,001),(00044,001),(00046,001),(00047,001),(00050,001),(00054,001),(00057,001),(00058,001),(00067,001),(00001,002),(00002,002),(00003,002),(00004,002),(00005,002),(00007,002),(00008,002),(00010,002),(00012,002),(00013,002),(00014,002),(00015,002),(00023,002),(00027,002),(00028,002),(00031,002),(00032,002),(00033,002),(00034,002),(00037,002),(00038,002),(00040,002),(00041,002),(00051,002),(00052,002),(00038,003),(00012,004),(00017,004),(00022,004),(00023,004),(00024,004),(00028,004),(00048,004),(00011,006),(00016,006),(00018,006),(00019,006),(00020,006),(00021,006),(00024,006),(00040,006),(00042,006),(00048,006),(00035,007),(00039,007),(00040,007),(00042,007),(00055,007),(00060,007),(00065,007),(00049,008),(00056,008),(00063,008),(00064,008),(00065,008),(00066,008),(00053,009),(00022,010),(00039,010),(00040,010),(00045,010),(00049,010),(00051,010),(00052,010),(00050,011),(00059,011),(00061,011),(00062,015);
/*!40000 ALTER TABLE `task_members` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-27 12:48:38

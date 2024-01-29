-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: bdconsultorio
-- ------------------------------------------------------
-- Server version	5.5.62

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
-- Table structure for table `tabla_citas`
--

DROP TABLE IF EXISTS `tabla_citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_citas` (
  `cm_idcita` int(11) DEFAULT NULL,
  `cm_idpaciente` int(11) DEFAULT NULL,
  `cm_idfecha` date DEFAULT NULL,
  `cm_fechahora` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cm_servicio` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cm_analisispac` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cm_idunidadmedica` int(11) DEFAULT NULL,
  `cm_idrecetas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_citas`
--

LOCK TABLES `tabla_citas` WRITE;
/*!40000 ALTER TABLE `tabla_citas` DISABLE KEYS */;
INSERT INTO `tabla_citas` VALUES (4699725,8894839,'2020-05-01','10:00','Dentista','Sangre',7833867,4121926),(5571795,8115317,'2020-09-26','10:00','Dentista','Caries',4438593,4010206),(4509584,7593540,'2020-05-31','10:00','Dentista','Sangre',473678,9644559),(8725984,8115317,'2023-10-08','06:00','','',4438593,7256936),(3129963,6216454,'2024-01-28','19:00','Odontologico','Infección Dental',7844161,3340997),(5165032,8894839,'2024-01-11','06:00','Medico General','Cuidados por gripe',7833867,4397046),(2065209,6300334,'2024-01-12','06:00','dasdsa','dadada',9801593,7477553),(3928920,6300334,'2024-01-23','06:00','xxxxx','dsacxa',9801593,2852555);
/*!40000 ALTER TABLE `tabla_citas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-29  2:58:06

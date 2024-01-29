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
-- Table structure for table `tabla_pacientes`
--

DROP TABLE IF EXISTS `tabla_pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_pacientes` (
  `id_paciente` int(11) NOT NULL,
  `pac_nombres` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_apellidopaterno` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_apellidomaterno` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_edad` int(11) DEFAULT NULL,
  `pac_sexo` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_curp` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_lugar` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_direccion` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_numcasa` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_codigopostal` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_colonia` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pac_fechanac` date DEFAULT NULL,
  `pac_idunidadmedica` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_paciente`),
  UNIQUE KEY `id_paciente_UNIQUE` (`id_paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_pacientes`
--

LOCK TABLES `tabla_pacientes` WRITE;
/*!40000 ALTER TABLE `tabla_pacientes` DISABLE KEYS */;
INSERT INTO `tabla_pacientes` VALUES (18,'','','',0,'','','','','','','','2024-01-29',93753),(76,'','','',0,'','','','','','','','2024-01-29',23829),(109,'','','',0,'','','','','','','','2024-01-29',44862),(3028676,'','','',0,'','','','','',NULL,'','2024-01-28',5520301),(3310004,'PP','PP','PP',21,'Hombre','PP','PP','PP','21',NULL,'PP','2020-05-01',1405283),(4105644,'','','',0,'','','','','','','','2024-01-29',1086281),(5514226,'daadda','dadsad','dasda',22,'Hombre','adsdasda','daad','adad','22','111','daadsdas','2024-01-06',9045160),(6216454,'A Positivo','Caamal','Ic',99,'Hombre','CAIL930285HYNCS08','Peto','Calle 45','140',NULL,'Miraflores','1993-01-24',7844161),(6227716,'','','',0,'','','','','','','','2024-01-29',7699406),(6300334,'xxccvvbb','dsadadds','adsda',22,'Hombre','adas','xxx','xxxx','22','122222','cccxxxxccc','2024-01-13',9801593),(7593540,'Luis','Fernando','Mederos',25,'Hombre','CAIL930828ALIKMS09','Peto','Av. Iman','25',NULL,'Caracol','1999-05-01',473678),(8115317,'Maria','Ortiz','Lopez',20,'Mujer','MARIL230824MYNMCS08','Merida','Los Caracoles','156',NULL,'Komchen','1993-08-24',4438593),(8440719,'','','',0,'','','','','',NULL,'','2023-10-29',20000),(8894839,'Jose','Caamal','Ic',25,'Hombre','CAIL930824HYNMCS08','Peto, Yucatán','Calle 5A','156',NULL,'Fovisste','1993-08-24',7833867);
/*!40000 ALTER TABLE `tabla_pacientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-29  2:58:15

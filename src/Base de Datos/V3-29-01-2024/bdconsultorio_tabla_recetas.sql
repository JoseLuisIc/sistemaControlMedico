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
-- Table structure for table `tabla_recetas`
--

DROP TABLE IF EXISTS `tabla_recetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_recetas` (
  `rec_idreceta` int(11) NOT NULL,
  `rec_idpaciente` int(11) DEFAULT NULL,
  `rec_idunidadmedica` int(11) DEFAULT NULL,
  `rec_descripcion` varchar(10000) COLLATE utf8_spanish_ci DEFAULT NULL,
  `rec_alergias` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `rec_estatura` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `rec_peso` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `rec_presion` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `rec_tiposangre` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `rec_idcita` int(11) DEFAULT NULL,
  PRIMARY KEY (`rec_idreceta`),
  UNIQUE KEY `rec_idreceta_UNIQUE` (`rec_idreceta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_recetas`
--

LOCK TABLES `tabla_recetas` WRITE;
/*!40000 ALTER TABLE `tabla_recetas` DISABLE KEYS */;
INSERT INTO `tabla_recetas` VALUES (2852555,6300334,9801593,'Receta','1xx','1.0m','10kg','11','O Postivio',3928920),(4010206,8115317,4438593,'Hola Hoy Es Lunes','Ninguna','1.4m','15kg','Baja','O Positivo',5571795),(4121926,8894839,7833867,'mos incluir el jar que contiene ue hacer es asegurarnos que el Driver se inicializa y se registra, para ello.','Ninguna','2.5m','11kg','Baja','O Positivo',4699725),(4397046,8894839,7833867,'Dizovil 100 ml cada 5 horas','Ninguna','3m','155kg','1.9','A Postivo',5165032),(9644559,7593540,473678,'Extraccion de Un Diente\n\n:D','No','2.7m','35kg','Normal','O Positivo',4509584);
/*!40000 ALTER TABLE `tabla_recetas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-29  2:58:13

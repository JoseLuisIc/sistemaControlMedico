-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bdconsultorio
-- ------------------------------------------------------
-- Server version	5.5.62-log

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
INSERT INTO `tabla_citas` VALUES (4699725,8894839,'2020-05-01','10:00','Dentista','Sangre',7833867,4121926),(5571795,8115317,'2020-09-26','10:00','Dentista','Caries',4438593,4010206),(4509584,7593540,'2020-05-31','10:00','Dentista','Sangre',473678,9644559);
/*!40000 ALTER TABLE `tabla_citas` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `tabla_pacientes` VALUES (3310004,'PP','PP','PP',21,'Hombre','PP','PP','PP','21','PP','2020-05-01',1405283),(7593540,'Luis','Fernando','Mederos',25,'Hombre','CAIL930828ALIKMS09','Peto','Av. Iman','25','Caracol','1999-05-01',473678),(8115317,'Maria','Ortiz','Lopez',20,'Mujer','MARIL230824MYNMCS08','Merida','Los Caracoles','156','Komchen','1993-08-24',4438593),(8894839,'Jose','Caamal','Ic',25,'Hombre','CAIL930824HYNMCS08','Peto, Yucatán','Calle 5A','156','Fovisste','1993-08-24',7833867);
/*!40000 ALTER TABLE `tabla_pacientes` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `tabla_recetas` VALUES (4010206,8115317,4438593,'Instalar el Driver\nEn nuestro programa java, todos los import que necesitamos para manejar la base de datos están en java.sql.*. Puesto que casi todas los métodos relativos \na base de datos pueden lanzar la excepción SQLException, meteremos todo nuestro programa en un try-catch.\nAdemás, necesitamos la clase org.gjt.mm.mysql.Driver que viene con el driver de MySQL. Por ello, en nuestro\n CLASSPATH o incluido en nuestro proyecto con nuestro IDE favorito, debemos incluir el jar que contiene el driver MySQL (mysql-connector-java-3.1.7-bin.jar) o\n la versión más moderna y compatible con la versión de nuestro servidor de MySQL.\nLo primero que tenemos que hacer es asegurarnos que el Driver se inicializa y se registra, para ello.','Ninguna','1.4m','15kg','Baja','O Positivo',5571795),(4121926,8894839,7833867,'Instalar el Driver\nEn nuestro programa java, todos los import que necesitamos para manejar la base de datos están en java.sql.*. Puesto que casi todas los métodos relativos \na base de datos pueden lanzar la excepción SQLException, meteremos todo nuestro programa en un try-catch.\nAdemás, necesitamos la clase org.gjt.mm.mysql.Driver que viene con el driver de MySQL. Por ello, en nuestro\n CLASSPATH o incluido en nuestro proyecto con nuestro IDE favorito, debemos incluir el jar que contiene el driver MySQL (mysql-connector-java-3.1.7-bin.jar) o\n la versión más moderna y compatible con la versión de nuestro servidor de MySQL.\nLo primero que tenemos que hacer es asegurarnos que el Driver se inicializa y se registra, para ello.','Ninguna','2.5m','11kg','Baja','O Positivo',4699725),(9644559,7593540,473678,'Extraccion de Un Diente\n\n:D','No','2.7m','35kg','Normal','O Positivo',4509584);
/*!40000 ALTER TABLE `tabla_recetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabla_unidadmedica`
--

DROP TABLE IF EXISTS `tabla_unidadmedica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_unidadmedica` (
  `id_unidadmedica` int(11) NOT NULL,
  `um_paciente` int(11) DEFAULT NULL,
  `um_folio` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `um_medico` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `um_consultorio` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_unidadmedica`),
  UNIQUE KEY `id_unidadmedica_UNIQUE` (`id_unidadmedica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_unidadmedica`
--

LOCK TABLES `tabla_unidadmedica` WRITE;
/*!40000 ALTER TABLE `tabla_unidadmedica` DISABLE KEYS */;
INSERT INTO `tabla_unidadmedica` VALUES (473678,7593540,'8072250','Jose Luis Caamal Ic','2'),(1405283,3310004,'2726457','Juan Guillermo Gonzalez','1'),(4438593,8115317,'406424','Juan Guillermo Gonzalez','2'),(7833867,8894839,'1812666','Juan Guillermo Gonzalez','1');
/*!40000 ALTER TABLE `tabla_unidadmedica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bdconsultorio'
--

--
-- Dumping routines for database 'bdconsultorio'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-04  4:52:06

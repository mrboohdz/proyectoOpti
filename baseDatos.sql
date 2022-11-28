-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: poocatalogo
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `precio` float NOT NULL,
  `stock` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (25,'Tank Top Dama',215,149,NULL),(26,'Playera Dama MC',179.99,129,NULL),(27,'Vestido Dama ',431,299,NULL),(28,'Blusa Dama ',258,179,NULL),(29,'Vestido Mezclilla',429.99,299,NULL),(30,'Playera Dama ML',272.5,189,NULL),(31,'Sudadera Dama ML',329.6,229,NULL),(32,'Playera Caballero ML',143,99,NULL),(33,'Playera Cabellero MC',186.99,129,NULL),(34,'Tank Top Caballero ',215,230,NULL),(35,'Playera Polo Caballlero',330,229,NULL),(36,'Camisa Caballero ML',459.9,319,NULL),(37,'Camisa Caballero MC',359.99,235,NULL),(38,'Sudadera Caballero',359.9,179,NULL),(39,'Playera Niño Mc',143,99,NULL),(40,'Sudadera Niño ML',299,124,NULL),(41,'Pantalón Mezclilla Dama',299,432,NULL),(42,'Pantalón Dama ',299,213,NULL),(43,'Jogger Caballero',402.5,432,NULL),(44,'Jogger Dama',287.45,199,NULL),(45,'Collar Metal ',432.43,343,NULL),(46,'Pulsera y Collar',123.54,54,NULL),(47,'Gorra Batman',53.89,544,NULL);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizacion` (
  `idcotizacion` int NOT NULL AUTO_INCREMENT,
  `tipoPersona` enum('FISICA','MORAL') NOT NULL,
  `total` float NOT NULL,
  `fechaEmision` varchar(20) NOT NULL,
  `fechaVencimiento` varchar(20) NOT NULL,
  `responsable` varchar(100) NOT NULL,
  `empresa` varchar(45) DEFAULT NULL,
  `direccion` varchar(200) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `rfc` varchar(45) DEFAULT NULL,
  `razonSocial` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcotizacion`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
INSERT INTO `cotizacion` VALUES (14,'FISICA',609.99,'02/02/2021','16/02/2021','Nelly Hernández','','Iztacalco','5560662232','HEMN990926',''),(15,'MORAL',545,'02/02/2021','16/02/2021','Diana Márquez','Sonrisas SA de CV','Iztacalco','554874829','MAMD660124','Diana Márquez Medina'),(16,'FISICA',15809.8,'02/02/2021','16/02/2021','Miguel Torres','','Xochimilco','556543456','','');
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipoUsuario` enum('ADMINISTRADOR','USUARIO') NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (18,'admin','0000','admin','ADMINISTRADOR'),(19,'six','0000','Ernesto Muñoz','USUARIO'),(20,'mrbohdz','0000','Nelly Hernández','ADMINISTRADOR'),(21,'miketorres','0000','Miguel Torres','USUARIO'),(22,'elias','0000','Elias Ortega','USUARIO'),(23,'veracruz','0000','Adrian Barradas','ADMINISTRADOR'),(24,'dyana','0000','Dyana Erika','ADMINISTRADOR');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-02 22:53:07

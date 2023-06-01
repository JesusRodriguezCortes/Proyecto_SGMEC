-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: SGMECDB
-- ------------------------------------------------------
-- Server version	8.0.32
CREATE DATABASE SGEMEC;
USE SGEMEC;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `apellidoPaterno` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `apellidoMaterno` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `telefono` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `correoElectronico` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `idUsuario` int DEFAULT NULL,
  `idDireccion` int DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idDireccion` (`idDireccion`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`idDireccion`) REFERENCES `direccion` (`idDireccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `codigopostal`
--

DROP TABLE IF EXISTS `codigopostal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `codigopostal` (
  `idCodigoPostal` int NOT NULL AUTO_INCREMENT,
  `codigoPostal` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `idMunicipio` int DEFAULT NULL,
  PRIMARY KEY (`idCodigoPostal`),
  KEY `idMunicipio` (`idMunicipio`),
  CONSTRAINT `codigopostal_ibfk_1` FOREIGN KEY (`idMunicipio`) REFERENCES `municipio` (`idMunicipio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codigopostal`
--

LOCK TABLES `codigopostal` WRITE;
/*!40000 ALTER TABLE `codigopostal` DISABLE KEYS */;
/*!40000 ALTER TABLE `codigopostal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colonia`
--

DROP TABLE IF EXISTS `colonia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colonia` (
  `idColonia` int NOT NULL AUTO_INCREMENT,
  `nombreColonia` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `idCodigoPostal` int DEFAULT NULL,
  `idDireccion` int DEFAULT NULL,
  PRIMARY KEY (`idColonia`),
  KEY `idCodigoPostal` (`idCodigoPostal`),
  KEY `idDireccion` (`idDireccion`),
  CONSTRAINT `colonia_ibfk_1` FOREIGN KEY (`idCodigoPostal`) REFERENCES `codigopostal` (`idCodigoPostal`),
  CONSTRAINT `colonia_ibfk_2` FOREIGN KEY (`idDireccion`) REFERENCES `direccion` (`idDireccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colonia`
--

LOCK TABLES `colonia` WRITE;
/*!40000 ALTER TABLE `colonia` DISABLE KEYS */;
/*!40000 ALTER TABLE `colonia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizacion` (
  `idCotizacion` int NOT NULL AUTO_INCREMENT,
  `reparacionSoftware` double DEFAULT NULL,
  `reparacionHardware` double DEFAULT NULL,
  `actualizaciones` double DEFAULT NULL,
  `servicioLimpieza` double DEFAULT NULL,
  `costoRepuesto` double DEFAULT NULL,
  `manoDeObra` double DEFAULT NULL,
  `garantia` double DEFAULT NULL,
  `idDiagnostico` int DEFAULT NULL,
  PRIMARY KEY (`idCotizacion`),
  KEY `idDiagnostico` (`idDiagnostico`),
  CONSTRAINT `cotizacion_ibfk_1` FOREIGN KEY (`idDiagnostico`) REFERENCES `diagnostico` (`idDiagnostico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostico`
--

DROP TABLE IF EXISTS `diagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnostico` (
  `idDiagnostico` int NOT NULL AUTO_INCREMENT,
  `fechaInicio` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `resultadoDiagnostico` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `idDispositivo` int DEFAULT NULL,
  `idServicio` int DEFAULT NULL,
  `idSucursal` int DEFAULT NULL,
  PRIMARY KEY (`idDiagnostico`),
  KEY `idDispositivo` (`idDispositivo`),
  KEY `idServicio` (`idServicio`),
  KEY `idSucursal` (`idSucursal`),
  CONSTRAINT `diagnostico_ibfk_1` FOREIGN KEY (`idDispositivo`) REFERENCES `dispositivo` (`idDispositivo`),
  CONSTRAINT `diagnostico_ibfk_2` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`),
  CONSTRAINT `diagnostico_ibfk_3` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico`
--

LOCK TABLES `diagnostico` WRITE;
/*!40000 ALTER TABLE `diagnostico` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `idDireccion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `numeroExt` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `numeroInt` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idDireccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dispositivo` (
  `idDispositivo` int NOT NULL AUTO_INCREMENT,
  `marca` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `modelo` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `usuarioDispositivo` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `passwordDispositivo` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `errorDispositivo` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `imagenDispositivo` blob,
  `caracteristicas` text,
  `idCliente` int DEFAULT NULL,
  `idEstadoDispositivo` int DEFAULT NULL,
  PRIMARY KEY (`idDispositivo`),
  KEY `idCliente` (`idCliente`),
  KEY `idEstadoDispositivo` (`idEstadoDispositivo`),
  CONSTRAINT `dispositivo_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `dispositivo_ibfk_2` FOREIGN KEY (`idEstadoDispositivo`) REFERENCES `estadodispositivo` (`idEstadoDispositivo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo`
--

LOCK TABLES `dispositivo` WRITE;
/*!40000 ALTER TABLE `dispositivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `idEstado` int NOT NULL AUTO_INCREMENT,
  `nombreEstado` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadodispositivo`
--

DROP TABLE IF EXISTS `estadodispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadodispositivo` (
  `idEstadoDispositivo` int NOT NULL AUTO_INCREMENT,
  `nombreEstadoDispositivo` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idEstadoDispositivo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadodispositivo`
--

LOCK TABLES `estadodispositivo` WRITE;
/*!40000 ALTER TABLE `estadodispositivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadodispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `municipio` (
  `idMunicipio` int NOT NULL AUTO_INCREMENT,
  `nombreMunicipio` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `idEstado` int DEFAULT NULL,
  PRIMARY KEY (`idMunicipio`),
  KEY `idEstado` (`idEstado`),
  CONSTRAINT `municipio_ibfk_1` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paqueteria`
--

DROP TABLE IF EXISTS `paqueteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paqueteria` (
  `idPaqueteria` int NOT NULL AUTO_INCREMENT,
  `nombrePaqueteria` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idPaqueteria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paqueteria`
--

LOCK TABLES `paqueteria` WRITE;
/*!40000 ALTER TABLE `paqueteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `paqueteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidorefacciones`
--

DROP TABLE IF EXISTS `pedidorefacciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidorefacciones` (
  `idPedidoRefacciones` int NOT NULL AUTO_INCREMENT,
  `fechaPedido` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `numeroPedido` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `totalPedido` float DEFAULT NULL,
  `direccionEntrega` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idPedidoRefacciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidorefacciones`
--

LOCK TABLES `pedidorefacciones` WRITE;
/*!40000 ALTER TABLE `pedidorefacciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidorefacciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `correoElectronico` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `telefono` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refaccion`
--

DROP TABLE IF EXISTS `refaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refaccion` (
  `idRefaccion` int NOT NULL AUTO_INCREMENT,
  `nombreRefaccion` varchar(50) DEFAULT NULL,
  `codigoRefaccion` varchar(50) DEFAULT NULL,
  `idDiagnostico` int DEFAULT NULL,
  `idTipoRefaccion` int DEFAULT NULL,
  PRIMARY KEY (`idRefaccion`),
  KEY `idDiagnostico` (`idDiagnostico`),
  KEY `idTipoRefaccion` (`idTipoRefaccion`),
  CONSTRAINT `refaccion_ibfk_1` FOREIGN KEY (`idDiagnostico`) REFERENCES `diagnostico` (`idDiagnostico`),
  CONSTRAINT `refaccion_ibfk_2` FOREIGN KEY (`idTipoRefaccion`) REFERENCES `tiporefaccion` (`idTipoRefaccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refaccion`
--

LOCK TABLES `refaccion` WRITE;
/*!40000 ALTER TABLE `refaccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `refaccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refaccionescompradas`
--

DROP TABLE IF EXISTS `refaccionescompradas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refaccionescompradas` (
  `idPedidoRefacciones` int DEFAULT NULL,
  `idRefaccion` int DEFAULT NULL,
  `refaccionesCompradas` int DEFAULT NULL,
  `precioNetoRefacciones` float DEFAULT NULL,
  KEY `idPedidoRefacciones` (`idPedidoRefacciones`),
  KEY `idRefaccion` (`idRefaccion`),
  CONSTRAINT `refaccionescompradas_ibfk_1` FOREIGN KEY (`idPedidoRefacciones`) REFERENCES `pedidorefacciones` (`idPedidoRefacciones`),
  CONSTRAINT `refaccionescompradas_ibfk_2` FOREIGN KEY (`idRefaccion`) REFERENCES `refaccion` (`idRefaccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refaccionescompradas`
--

LOCK TABLES `refaccionescompradas` WRITE;
/*!40000 ALTER TABLE `refaccionescompradas` DISABLE KEYS */;
/*!40000 ALTER TABLE `refaccionescompradas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refaccionproveedor`
--

DROP TABLE IF EXISTS `refaccionproveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refaccionproveedor` (
  `idProveedor` int DEFAULT NULL,
  `idRefaccion` int DEFAULT NULL,
  `pzasDisponiblesCompra` int DEFAULT NULL,
  `precioCompra` float DEFAULT NULL,
  KEY `idProveedor` (`idProveedor`),
  KEY `idRefaccion` (`idRefaccion`),
  CONSTRAINT `refaccionproveedor_ibfk_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`),
  CONSTRAINT `refaccionproveedor_ibfk_2` FOREIGN KEY (`idRefaccion`) REFERENCES `refaccion` (`idRefaccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refaccionproveedor`
--

LOCK TABLES `refaccionproveedor` WRITE;
/*!40000 ALTER TABLE `refaccionproveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `refaccionproveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refaccionsucursal`
--

DROP TABLE IF EXISTS `refaccionsucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refaccionsucursal` (
  `idRefaccion` int DEFAULT NULL,
  `idSucursal` int DEFAULT NULL,
  `pzasDisponiblesVenta` int DEFAULT NULL,
  `precioVenta` float DEFAULT NULL,
  KEY `idRefaccion` (`idRefaccion`),
  KEY `idSucursal` (`idSucursal`),
  CONSTRAINT `refaccionsucursal_ibfk_1` FOREIGN KEY (`idRefaccion`) REFERENCES `refaccion` (`idRefaccion`),
  CONSTRAINT `refaccionsucursal_ibfk_2` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refaccionsucursal`
--

LOCK TABLES `refaccionsucursal` WRITE;
/*!40000 ALTER TABLE `refaccionsucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `refaccionsucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idRol` int NOT NULL AUTO_INCREMENT,
  `nivelAcceso` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `idServicio` int NOT NULL AUTO_INCREMENT,
  `nombreServicio` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitudservicio`
--

DROP TABLE IF EXISTS `solicitudservicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudservicio` (
  `idSolicitudServicio` int NOT NULL AUTO_INCREMENT,
  `numeroGuia` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `idDiagnostico` int DEFAULT NULL,
  `idPaqueteria` int DEFAULT NULL,
  PRIMARY KEY (`idSolicitudServicio`),
  KEY `idDiagnostico` (`idDiagnostico`),
  KEY `idPaqueteria` (`idPaqueteria`),
  CONSTRAINT `solicitudservicio_ibfk_1` FOREIGN KEY (`idDiagnostico`) REFERENCES `diagnostico` (`idDiagnostico`),
  CONSTRAINT `solicitudservicio_ibfk_2` FOREIGN KEY (`idPaqueteria`) REFERENCES `paqueteria` (`idPaqueteria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudservicio`
--

LOCK TABLES `solicitudservicio` WRITE;
/*!40000 ALTER TABLE `solicitudservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitudservicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sucursal` (
  `idSucursal` int NOT NULL AUTO_INCREMENT,
  `direccionSucursal` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idSucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiporefaccion`
--

DROP TABLE IF EXISTS `tiporefaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiporefaccion` (
  `idTipoRefaccion` int NOT NULL AUTO_INCREMENT,
  `nombreTipoRefaccion` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoRefaccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiporefaccion`
--

LOCK TABLES `tiporefaccion` WRITE;
/*!40000 ALTER TABLE `tiporefaccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiporefaccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `idRol` int DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-28 13:33:33
/*----------------------------------------------DATA CREATION----------------------------------------------*/
/*---------------ROLES---------------*/
INSERT INTO rol (nivelAcceso) VALUES ('cliente');
INSERT INTO rol (nivelAcceso) VALUES ('administrador');
INSERT INTO rol (nivelAcceso) VALUES ('encargado');
SELECT * FROM rol;

/*---------------ESTADOS DISPOSITIVO---------------*/
INSERT INTO estadodispositivo (nombreEstadoDispositivo) VALUES ('ENVIADO_CLIENTE');
INSERT INTO estadodispositivo (nombreEstadoDispositivo) VALUES ('RECIBIDO_EMPRESA');
INSERT INTO estadodispositivo (nombreEstadoDispositivo) VALUES ('VALORACION');
INSERT INTO estadodispositivo (nombreEstadoDispositivo) VALUES ('REPARACION');
INSERT INTO estadodispositivo (nombreEstadoDispositivo) VALUES ('EMPAQUETADO');
INSERT INTO estadodispositivo (nombreEstadoDispositivo) VALUES ('ENVIADO_EMPRESA');
INSERT INTO estadodispositivo (nombreEstadoDispositivo) VALUES ('RECIBIDO_CLIENTE');
SELECT * FROM estadodispositivo;

/*---------------PAQUETERIAS---------------*/
INSERT INTO paqueteria (nombrePaqueteria) VALUES ('Correos de méxico');
INSERT INTO paqueteria (nombrePaqueteria) VALUES ('DHL');
INSERT INTO paqueteria (nombrePaqueteria) VALUES ('FedEx');
INSERT INTO paqueteria (nombrePaqueteria) VALUES ('UPS');
SELECT * FROM paqueteria;

/*---------------PROVEEDORES---------------*/
INSERT INTO proveedor (nombre, correoElectronico, telefono) VALUES ('Patito', 'patito@gmail.com', '123456789');
INSERT INTO proveedor (nombre, correoElectronico, telefono) VALUES ('Casa ahued', 'casaahued@gmail.com', '987654321');
SELECT * FROM proveedor;

/*---------------USUARIOS---------------*/
INSERT INTO usuario (nombreUsuario, password, idRol) VALUES ('cliente', 'cliente', 1);
INSERT INTO usuario (nombreUsuario, password, idRol) VALUES ('admin', 'admin', 2);
INSERT INTO usuario (nombreUsuario, password, idRol) VALUES ('encargado', 'encargado', 3);
INSERT INTO usuario (nombreUsuario, password, idRol) VALUES ('cliente1', 'cliente1', 1);
INSERT INTO usuario (nombreUsuario, password, idRol) VALUES ('admin1', 'admin1', 2);
INSERT INTO usuario (nombreUsuario, password, idRol) VALUES ('encargado1', 'encargado1', 3);
SELECT * FROM usuario LEFT JOIN rol ON usuario.idRol = rol.idRol;

/*---------------CLIENTES---------------*/
INSERT INTO cliente (nombre, apellidoPaterno, apellidoMaterno, telefono, 
correoElectronico, idUsuario, idDireccion) 
VALUES ('Rodolfo','Fdez','Rguez', '2281234567','correorodolfo@correo.com', 1, NULL);
INSERT INTO cliente (nombre, apellidoPaterno, apellidoMaterno, telefono, 
correoElectronico, idUsuario, idDireccion) 
VALUES ('Cesar','Basilio','Gómez','2281234568','correocesar@correo.com', 4, NULL);
SELECT * FROM cliente LEFT JOIN usuario ON cliente.idUsuario = usuario.idUsuario;

/*---------------DISPOSITIVOS---------------*/
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
imagenDispositivo, caracteristicas, idCliente, idEstadoDispositivo) 
VALUES ('Hp', 'Pavilion', 'Viernes', '123456', 'No sirve la pantalla', NULL, 'Características del equipo', 
1, NULL);
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
imagenDispositivo, caracteristicas, idCliente, idEstadoDispositivo) 
VALUES ('Lenovo', '14IML05', 'Rodolfo Fdez', '123456','No sirve la batería', NULL, 'Características del 
equipo', 1, NULL);
    
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
imagenDispositivo, caracteristicas, idCliente, idEstadoDispositivo) 
VALUES ('Acer', 'coreI5', 'CesarBas', '123456', 'Falla teclado', NULL, 'Características del equipo', 2, NULL);
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
imagenDispositivo, caracteristicas, idCliente, idEstadoDispositivo) 
VALUES ('Patito', 'Apache', 'CesarCUM', '123456','No prende', NULL, 'Características del 
equipo', 2, NULL);
            
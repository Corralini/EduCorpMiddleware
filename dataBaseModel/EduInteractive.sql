CREATE DATABASE  IF NOT EXISTS `educorp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `educorp`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: educorp
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dia`
--

DROP TABLE IF EXISTS `dia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dia` (
  `ID_DIA` int(11) NOT NULL AUTO_INCREMENT,
  `DIA` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_DIA`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia`
--

LOCK TABLES `dia` WRITE;
/*!40000 ALTER TABLE `dia` DISABLE KEYS */;
INSERT INTO `dia` VALUES (1,'Domingo'),(2,'Lunes'),(3,'Martes'),(4,'Miércoles'),(5,'Jueves'),(6,'Viernes'),(7,'Sábado');
/*!40000 ALTER TABLE `dia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estado` (
  `ID_ESTADO` char(1) NOT NULL,
  `ESTADO` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_ESTADO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES ('A','Aceptada'),('C','Cancelada'),('R','Rechazada'),('S','Solicitada'),('T','Rechazada');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estudiante` (
  `ID_ESTUDIANTE` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(256) NOT NULL,
  `ID_PAIS` varchar(8) NOT NULL,
  `PSSWD` varchar(255) NOT NULL,
  `NOMBRE` varchar(45) NOT NULL,
  `APELLIDO1` varchar(45) NOT NULL,
  `APELLIDO2` varchar(45) DEFAULT NULL,
  `ANO_NACIMIENTO` int(11) NOT NULL,
  `FECHA_SUBSCRIPCION` datetime NOT NULL,
  `ID_NIVEL` int(11) NOT NULL,
  `ID_GENERO` varchar(4) NOT NULL,
  `CODIGO_DE_RECUPERACION` int(6) DEFAULT NULL,
  PRIMARY KEY (`ID_ESTUDIANTE`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `fk_ESTUDIANTE_PAIS1_idx` (`ID_PAIS`),
  KEY `fk_ESTUDIANTE_NIVEL_INGLES1_idx` (`ID_NIVEL`),
  KEY `fk_ESTUDIANTE_GENERO1_idx` (`ID_GENERO`),
  CONSTRAINT `fk_ESTUDIANTE_GENERO1` FOREIGN KEY (`ID_GENERO`) REFERENCES `genero` (`id_genero`),
  CONSTRAINT `fk_ESTUDIANTE_NIVEL_INGLES1` FOREIGN KEY (`ID_NIVEL`) REFERENCES `nivel_ingles` (`id_nivel`),
  CONSTRAINT `fk_ESTUDIANTE_PAIS1` FOREIGN KEY (`ID_PAIS`) REFERENCES `pais` (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (1,'MaryCejaVaca@jourrapide.com','ES','brCcQiwecAeWCZ7j5kzjQVl5ekdCWcUBvx/hTQEzj11l4Vym0lPm1u7S5aTIJR+P','Mary','Ceja',NULL,1948,'2019-01-31 10:43:27',2,'M',NULL),(2,'RomanceSaldivarGodoy@dayrep.com','ES','4zIVHj8zgjrQ9aYaVDQDqCE0lldjV17AdxvOQMsFG7pYnHgw07kG2xPc/qKPg0In','Romance','Saldivar',NULL,1993,'2019-01-31 10:43:27',3,'H',NULL),(3,'RaingardaBacaRuvalcaba@cuvox.de','ES','BzNuhXhShHXINnou/dd2hQxDgUY53oix05nkV8z3J1Vo5ZP0HscUu91sMpspfraw','Raingarda','Baca',NULL,1951,'2019-01-31 10:43:27',1,'M',NULL),(4,'NuriaSierraMercado@superrito.com','ES','+476Ebimpv+pjItMJ7Pqqltya+m9hG9M18Goph32k/M2esQ5ppJeu9MZp2/zptgu','Nuria','Sierra',NULL,1995,'2019-01-31 10:43:27',2,'M',NULL),(5,'MaitenaVelizAvalos@einrot.com','ES','SsngkKwqIIow3A5whfntrPsJZzgZVIDLGkGW1XjvlytYMQCG78BzlIkTpC2vjv4t','Maitena','Veliz',NULL,1953,'2019-01-31 10:43:27',4,'M',NULL),(6,'AmberReeves@jourrapide.com','US','MJOFMmjB0pVKFlVt7IuCqv0SSCbUbzukzdklxPtM9xjlL4LvlDTKEVJBN57ABhvB','Amber','Reeves',NULL,1990,'2019-01-31 10:43:27',2,'M',NULL),(7,'AlexNorton@dayrep.com','US','fokm9XnHHx5Q8P/nJ+AVDg9QPmYGW2Mqa8VgA2IMKZcffX5bNpJiHEs3TAF/Qbpj','Alex','Norton',NULL,1987,'2019-01-31 10:43:27',4,'H',NULL),(8,'MollyBentley@jourrapide.com','US','HNhnky/wFKEC6PoZ2qGYOZkdZ8zPDcDne5D1fDevwfJPBR9EQ3Tjvs2gNFvd9aBC','Molly','Bentley',NULL,1984,'2019-01-31 10:43:27',3,'M',NULL),(9,'BenBooth@gustr.com','US','K0AYrJ7x3QUQ2LHqRXzqaOMuQC8bUEEoc+17r3ieJNBE8jtV60rZcx7/x6lu+XFC','Ben','Booth',NULL,1981,'2019-01-31 10:43:27',3,'H',NULL),(10,'TobyOSullivan@jourrapide.com','US','g+US2QB02FbSpAD81oTOlbq5ltWhgKypZ+RQZeQXoBZxKjE4p6fypahwWZe2UMSn','Toby','O\'Sullivan',NULL,1982,'2019-01-31 10:43:27',2,'H',NULL),(11,'eddietuenti@gmail.com','ES','TACVdMA44TSDJEK3vAxkGKQeOZN416rC7vLD6bgxxyLAgZE8G3GkNLutwZlXLDHY','Eddie','Garcia','Garcia',0,'2019-01-31 00:00:00',2,'H',0),(12,'hector.modino.otero@gmail.com','ES','UikPkT2fdH/0P/YgNdNktoyxda9ljBDPL3LjCjT+dRaSruN83sV+ANlkVKpMGlYj','Hector','Modino','Otero',1992,'2019-01-31 00:00:00',2,'H',257228),(23,'dmendez1038@gmail.com','ES','FLy7ouMntEgyqkzGetNo6U0gU53aChyilwoKBJMF/Vedgi7DlfnPZ4xFoqAk0Kk6','David','Mendez','Martinez',1997,'2019-02-01 00:00:00',3,'H',NULL),(43,'JenniferMatthews@dayrep.com','US','0L2nTL54sJYuRFQpEJ+HJ7FETZr0zezhVgjRFrPID1Y/4Xy8vSsoRXlhF4eZj/3c','Jennifer','Matthews','',1995,'2019-02-05 00:00:00',3,'m',NULL),(44,'BennieSalcedoMelgar@jourrapide.com','ES','LGjhcxvBMm8FdbLZSjC0UTWLiYFl8epNFxfTCA4ITyvT7OAGeFx0Ms3tsZnlGwF5','Bennie','Salcedo','Melgar',1936,'2019-02-05 00:00:00',2,'o',NULL),(45,'iago@dayrep.com','ES','Lucn5ixRhcXadadPxlSPSMhIlwBAatBv47ogKtSWK2EydnvywS6GdqJcKtCQJvND','Iago','Seijas','Fern�ndez',1995,'2019-02-07 00:00:00',2,'h',NULL),(46,'martingilipollas@gmail.com','ES','Xg1eG78j4WSgGvBE8wzPwq6zw88f9pPn9AWpqnU0OjJnT0tswEOE702+48hukuXm','Mart�n','Gomez','V�zquez',2000,'2019-02-07 00:00:00',2,'m',NULL),(47,'martingvv@gmail.com','ES','ci1sVtH9D1+eVGK9AVHfdz4zfJXRTlan34T5cQ2OVfj80G9kqdrTSh2FDNRz8twC','Mart�n','Gomez','V�zquez',2004,'2019-02-07 00:00:00',2,'h',NULL),(48,'martingvov@gmail.com','ES','XU3Mm6Y5Xy4NtzSmGILg0CHwuFayBGX2E1caVOyXIVbDpI6+W8vv/2acCknhbSqi','Mart�n','Gomez','V�zquez',2004,'2019-02-07 00:00:00',2,'h',NULL),(53,'acorralfdez@gmail.com','ES','vqROpztKMdBcz06Pl50w51xpszAZulFP9rY5kHjYVBuoXjYfh5r+7ilv9omg/pg7','Alejandro Ricardo','Corral','Fernandez',2000,'2019-03-16 00:00:00',2,'H',NULL),(54,'manolo23@gmail.com','AF','0xBdSPXIxiK/ymUKJnAHh7q5hGCeCFXuNzRuZWd+Yjk2z53AjFcAqGd8v8oHgLyL','Manuel','Fernandez','Gayoso',1999,'2019-03-18 00:00:00',1,'H',NULL),(55,'iago@gmail.com','ES','Krq5xfETh+me6fYMf+dVufqI0+kGPbjmJC4HT5oOqYXZiDqzdChQmkciOT4bQpyW','Iago','Seijas','Perez',2000,'2019-03-22 00:00:00',3,'H',NULL),(57,'rafacervelo@gmail.com','ZM','MnQQgzKBsi8B71j1TkKNwyGtvIQO6JtYCSGeoVaASApoF6/EYpxK/W6N9H76Usia','Rafa','Cervelo','Deus',2000,'2019-03-22 00:00:00',2,'H',0),(58,'alex123@gmail.com','ES','HIRp6jrqto7ZhUfHoxEcBoC6dJv/W8CUdvyeFMDU2x1KNMU0U41EpiB4UQ0YEYoI','Alex','Jorge','Ledo',2007,'2019-03-22 00:00:00',1,'H',NULL),(59,'FidelioQuintanillaLoera@rhyta.com','US','MiVXNE+DIZibKlmGn2TQSdSEFEogl+tTIYUU3Qr+lT9+3c5+YuRHnfI3FfR/WvpB','Fidelio','Quintanilla','Loera',1956,'2019-03-23 00:00:00',1,'H',NULL),(60,'RobertKGray@dayrep.com','AF','HrjDEjnksSqcizFrvPlft8Bwbb560esCmNAjq5G67Om0rccICLgDD62v3lD8X8Z+','Robert','Gray','',2000,'2019-03-25 00:00:00',1,'H',NULL),(61,'hola123@gmail.com','AF','4B8w5+QIztE2RHnXa/bKYinzQuGIHMO7wgHAuAGxkBrfZX+/52pk/ht0gMmC0Uae','Hola','Hoal','aloh',2000,'2019-04-03 00:00:00',2,'H',NULL),(62,'davicinho.rey@gmail.com','JE','qzhw+FswHwZ9t6Jxk0/LWlac133Qa84ZplLBCzpsvtg6UL6meo7HoR2l2TrC4j46','David','Rey','Varela',2000,'2019-04-14 00:00:00',2,'H',NULL),(63,'martifantastico@gmail.com','ES','1uEQa+ox1rBFfv6PXbqaecE9hJXyUhlB7e0nBYg5vYErEUxc8ZFaDlvwaIIFje6O','Martinho','Guapo','Hermoso',2000,'2019-04-17 00:00:00',2,'H',NULL),(64,'pulpix22@gmail.com','ES','jeJfsAef7+t1exBKVmP8lekUdauXNCeFLK5/c5TTyAyX0lTndRpFW8DJpp3yjvcq','Pablo','Varela','Vazquez',1995,'2019-04-30 00:00:00',3,'H',NULL);
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante_puntua_profesor`
--

DROP TABLE IF EXISTS `estudiante_puntua_profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estudiante_puntua_profesor` (
  `ID_ESTUDIANTE` int(11) NOT NULL,
  `ID_PROFESOR` int(11) NOT NULL,
  `FECHA_PUNTUACION` datetime NOT NULL,
  `PUNTUACION` double NOT NULL,
  PRIMARY KEY (`ID_ESTUDIANTE`,`ID_PROFESOR`,`FECHA_PUNTUACION`),
  KEY `fk_USUARIO_PUNTUA_USUARIO_PROFESOR1_idx` (`ID_PROFESOR`),
  KEY `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE1_idx` (`ID_ESTUDIANTE`),
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE10` FOREIGN KEY (`ID_ESTUDIANTE`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_PROFESOR10` FOREIGN KEY (`ID_PROFESOR`) REFERENCES `profesor` (`id_profesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante_puntua_profesor`
--

LOCK TABLES `estudiante_puntua_profesor` WRITE;
/*!40000 ALTER TABLE `estudiante_puntua_profesor` DISABLE KEYS */;
INSERT INTO `estudiante_puntua_profesor` VALUES (53,21,'2019-05-04 00:00:00',2);
/*!40000 ALTER TABLE `estudiante_puntua_profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `genero` (
  `ID_GENERO` varchar(4) NOT NULL,
  `GENERO` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_GENERO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES ('H','Hombre'),('M','Mujer'),('O','Otro');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hora`
--

DROP TABLE IF EXISTS `hora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hora` (
  `ID_HORA` int(11) NOT NULL AUTO_INCREMENT,
  `HORA` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_HORA`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hora`
--

LOCK TABLES `hora` WRITE;
/*!40000 ALTER TABLE `hora` DISABLE KEYS */;
INSERT INTO `hora` VALUES (1,'0:00'),(2,'0:15'),(3,'0:30'),(4,'0:45'),(5,'1:00'),(6,'1:15'),(7,'1:30'),(8,'1:45'),(9,'2:00'),(10,'2:15'),(11,'2:30'),(12,'2:45'),(13,'3:00'),(14,'3:15'),(15,'3:30'),(16,'3:45'),(17,'4:00'),(18,'4:15'),(19,'4:30'),(20,'4:45'),(21,'5:00'),(22,'5:15'),(23,'5:30'),(24,'5:45'),(25,'6:00'),(26,'6:15'),(27,'6:30'),(28,'6:45'),(29,'7:00'),(30,'7:15'),(31,'7:30'),(32,'7:45'),(33,'8:00'),(34,'8:15'),(35,'8:30'),(36,'8:45'),(37,'9:00'),(38,'9:15'),(39,'9:30'),(40,'9:45'),(41,'10:00'),(42,'10:15'),(43,'10:30'),(44,'10:45'),(45,'11:00'),(46,'11:15'),(47,'11:30'),(48,'11:45'),(49,'12:00'),(50,'12:15'),(51,'12:30'),(52,'12:45'),(53,'13:00'),(54,'13:15'),(55,'13:30'),(56,'13:45'),(57,'14:00'),(58,'14:15'),(59,'14:30'),(60,'14:45'),(61,'15:00'),(62,'15:15'),(63,'15:30'),(64,'15:45'),(65,'16:00'),(66,'16:15'),(67,'16:30'),(68,'16:45'),(69,'17:00'),(70,'17:15'),(71,'17:30'),(72,'17:45'),(73,'18:00'),(74,'18:15'),(75,'18:30'),(76,'18:45'),(77,'19:00'),(78,'19:15'),(79,'19:30'),(80,'19:45'),(81,'20:00'),(82,'20:15'),(83,'20:30'),(84,'20:45'),(85,'21:00'),(86,'21:15'),(87,'21:30'),(88,'21:45'),(89,'22:00'),(90,'22:15'),(91,'22:30'),(92,'22:45'),(93,'23:00'),(94,'23:15'),(95,'23:30'),(96,'23:45');
/*!40000 ALTER TABLE `hora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horario` (
  `ID_HORARIO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PROFESOR` int(11) NOT NULL,
  `ID_DIA` int(11) NOT NULL,
  `ID_HORA` int(11) NOT NULL,
  PRIMARY KEY (`ID_HORARIO`),
  UNIQUE KEY `ID_PROFESOR_UNIQUE` (`ID_DIA`,`ID_HORA`,`ID_PROFESOR`),
  KEY `fk_HORARIO_PROFESOR1_idx` (`ID_PROFESOR`),
  KEY `fk_HORARIO_DIA1_idx` (`ID_DIA`),
  KEY `fk_HORARIO_Hora1_idx` (`ID_HORA`),
  CONSTRAINT `fk_HORARIO_DIA1` FOREIGN KEY (`ID_DIA`) REFERENCES `dia` (`id_dia`),
  CONSTRAINT `fk_HORARIO_Hora1` FOREIGN KEY (`ID_HORA`) REFERENCES `hora` (`id_hora`),
  CONSTRAINT `fk_HORARIO_PROFESOR1` FOREIGN KEY (`ID_PROFESOR`) REFERENCES `profesor` (`id_profesor`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (44,22,1,62),(30,21,1,73),(31,21,1,91),(32,21,2,38),(34,21,2,78),(35,21,3,51),(36,21,3,95),(45,22,4,82),(37,21,5,47),(38,21,5,73),(40,21,6,8),(39,21,6,86),(41,21,7,37),(42,21,7,83),(43,21,7,92);
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `idioma` (
  `ID_IDIOMA` varchar(5) NOT NULL,
  `IDIOMA` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_IDIOMA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
INSERT INTO `idioma` VALUES ('en','english');
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma_pagina`
--

DROP TABLE IF EXISTS `idioma_pagina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `idioma_pagina` (
  `ID` varchar(3) NOT NULL,
  `IDIOMA` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma_pagina`
--

LOCK TABLES `idioma_pagina` WRITE;
/*!40000 ALTER TABLE `idioma_pagina` DISABLE KEYS */;
INSERT INTO `idioma_pagina` VALUES ('en','English'),('es','Español');
/*!40000 ALTER TABLE `idioma_pagina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma_pagina_pais`
--

DROP TABLE IF EXISTS `idioma_pagina_pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `idioma_pagina_pais` (
  `ID_IDIOMA_PAGINA` varchar(3) NOT NULL,
  `ID_PAIS` varchar(2) NOT NULL,
  `PAIS` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_IDIOMA_PAGINA`,`ID_PAIS`),
  KEY `fk_IDIOMA_PAGINA_has_PAIS_PAIS1_idx` (`ID_PAIS`),
  KEY `fk_IDIOMA_PAGINA_has_PAIS_IDIOMA_PAGINA1_idx` (`ID_IDIOMA_PAGINA`),
  CONSTRAINT `fk_IDIOMA_PAGINA_has_PAIS_IDIOMA_PAGINA1` FOREIGN KEY (`ID_IDIOMA_PAGINA`) REFERENCES `idioma_pagina` (`id`),
  CONSTRAINT `fk_IDIOMA_PAGINA_has_PAIS_PAIS1` FOREIGN KEY (`ID_PAIS`) REFERENCES `pais` (`id_pais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma_pagina_pais`
--

LOCK TABLES `idioma_pagina_pais` WRITE;
/*!40000 ALTER TABLE `idioma_pagina_pais` DISABLE KEYS */;
INSERT INTO `idioma_pagina_pais` VALUES ('en','AD','Andorra'),('en','AE','United Arab Emirates'),('en','AF','Afghanistan'),('en','AG','Antigua and Barbuda'),('en','AI','Anguilla'),('en','AL','Albania'),('en','AM','Armenia'),('en','AN','Netherlands Antilles'),('en','AO','Angola'),('en','AQ','Antarctica'),('en','AR','Argentina'),('en','AS','American Samoa'),('en','AT','Austria'),('en','AU','Australia'),('en','AW','Aruba'),('en','AX','Ã…land Islands'),('en','AZ','Azerbaijan'),('en','BA','Bosnia and Herzegovina'),('en','BB','Barbados'),('en','BD','Bangladesh'),('en','BE','Belgium'),('en','BF','Burkina Faso'),('en','BG','Bulgaria'),('en','BH','Bahrain'),('en','BI','Burundi'),('en','BJ','Benin'),('en','BL','Saint BarthÃ©lemy'),('en','BM','Bermuda Islands'),('en','BN','Brunei'),('en','BO','Bolivia'),('en','BR','Brazil'),('en','BS','Bahamas'),('en','BT','Bhutan'),('en','BV','Bouvet Island'),('en','BW','Botswana'),('en','BY','Belarus'),('en','BZ','Belize'),('en','CA','Canada'),('en','CC','Cocos (Keeling) Islands'),('en','CD','Congo'),('en','CF','Central African Republic'),('en','CG','Congo'),('en','CH','Switzerland'),('en','CI','Ivory Coast'),('en','CK','Cook Islands'),('en','CL','Chile'),('en','CM','Cameroon'),('en','CN','China'),('en','CO','Colombia'),('en','CR','Costa Rica'),('en','CU','Cuba'),('en','CV','Cape Verde'),('en','CX','Christmas Island'),('en','CY','Cyprus'),('en','CZ','Czech Republic'),('en','DE','Germany'),('en','DJ','Djibouti'),('en','DK','Denmark'),('en','DM','Dominica'),('en','DO','Dominican Republic'),('en','DZ','Algeria'),('en','EC','Ecuador'),('en','EE','Estonia'),('en','EG','Egypt'),('en','EH','Western Sahara'),('en','ER','Eritrea'),('en','ES','Spain'),('en','ET','Ethiopia'),('en','FI','Finland'),('en','FJ','Fiji'),('en','FK','Falkland Islands (Malvinas)'),('en','FM','Estados Federados de'),('en','FO','Faroe Islands'),('en','FR','France'),('en','GA','Gabon'),('en','GB','United Kingdom'),('en','GD','Grenada'),('en','GE','Georgia'),('en','GF','French Guiana'),('en','GG','Guernsey'),('en','GH','Ghana'),('en','GI','Gibraltar'),('en','GL','Greenland'),('en','GM','Gambia'),('en','GN','Guinea'),('en','GP','Guadeloupe'),('en','GQ','Equatorial Guinea'),('en','GR','Greece'),('en','GS','South Georgia and the South Sandwich Islands'),('en','GT','Guatemala'),('en','GU','Guam'),('en','GW','Guinea-Bissau'),('en','GY','Guyana'),('en','HK','Hong Kong'),('en','HM','Heard Island and McDonald Islands'),('en','HN','Honduras'),('en','HR','Croatia'),('en','HT','Haiti'),('en','HU','Hungary'),('en','ID','Indonesia'),('en','IE','Ireland'),('en','IL','Israel'),('en','IM','Isle of Man'),('en','IN','India'),('en','IO','British Indian Ocean Territory'),('en','IQ','Iraq'),('en','IR','Iran'),('en','IS','Iceland'),('en','IT','Italy'),('en','JE','Jersey'),('en','JM','Jamaica'),('en','JO','Jordan'),('en','JP','Japan'),('en','KE','Kenya'),('en','KG','Kyrgyzstan'),('en','KH','Cambodia'),('en','KI','Kiribati'),('en','KM','Comoros'),('en','KN','Saint Kitts and Nevis'),('en','KP','North Korea'),('en','KR','South Korea'),('en','KW','Kuwait'),('en','KY','Cayman Islands'),('en','KZ','Kazakhstan'),('en','LA','Laos'),('en','LB','Lebanon'),('en','LC','Saint Lucia'),('en','LI','Liechtenstein'),('en','LK','Sri Lanka'),('en','LR','Liberia'),('en','LS','Lesotho'),('en','LT','Lithuania'),('en','LU','Luxembourg'),('en','LV','Latvia'),('en','LY','Libya'),('en','MA','Morocco'),('en','MC','Monaco'),('en','MD','Moldova'),('en','ME','Montenegro'),('en','MF','Saint Martin (French part)'),('en','MG','Madagascar'),('en','MH','Marshall Islands'),('en','MK','Macedonia'),('en','ML','Mali'),('en','MM','Myanmar'),('en','MN','Mongolia'),('en','MO','Macao'),('en','MP','Northern Mariana Islands'),('en','MQ','Martinique'),('en','MR','Mauritania'),('en','MS','Montserrat'),('en','MT','Malta'),('en','MU','Mauritius'),('en','MV','Maldives'),('en','MW','Malawi'),('en','MX','Mexico'),('en','MY','Malaysia'),('en','MZ','Mozambique'),('en','NA','Namibia'),('en','NC','New Caledonia'),('en','NE','Niger'),('en','NF','Norfolk Island'),('en','NG','Nigeria'),('en','NI','Nicaragua'),('en','NL','Netherlands'),('en','NO','Norway'),('en','NP','Nepal'),('en','NR','Nauru'),('en','NU','Niue'),('en','NZ','New Zealand'),('en','OM','Oman'),('en','PA','Panama'),('en','PE','Peru'),('en','PF','French Polynesia'),('en','PG','Papua New Guinea'),('en','PH','Philippines'),('en','PK','Pakistan'),('en','PL','Poland'),('en','PM','Saint Pierre and Miquelon'),('en','PN','Pitcairn Islands'),('en','PR','Puerto Rico'),('en','PS','Palestine'),('en','PT','Portugal'),('en','PW','Palau'),('en','PY','Paraguay'),('en','QA','Qatar'),('en','RE','RÃ©union'),('en','RO','Romania'),('en','RS','Serbia'),('en','RU','Russia'),('en','RW','Rwanda'),('en','SA','Saudi Arabia'),('en','SB','Solomon Islands'),('en','SC','Seychelles'),('en','SD','Sudan'),('en','SE','Sweden'),('en','SG','Singapore'),('en','SH','AscensiÃ³n y TristÃ¡n de AcuÃ±a'),('en','SI','Slovenia'),('en','SJ','Svalbard and Jan Mayen'),('en','SK','Slovakia'),('en','SL','Sierra Leone'),('en','SM','San Marino'),('en','SN','Senegal'),('en','SO','Somalia'),('en','SR','Suriname'),('en','ST','Sao Tome and Principe'),('en','SV','El Salvador'),('en','SY','Syria'),('en','SZ','Swaziland'),('en','TC','Turks and Caicos Islands'),('en','TD','Chad'),('en','TF','French Southern Territories'),('en','TG','Togo'),('en','TH','Thailand'),('en','TJ','Tajikistan'),('en','TK','Tokelau'),('en','TL','East Timor'),('en','TM','Turkmenistan'),('en','TN','Tunisia'),('en','TO','Tonga'),('en','TR','Turkey'),('en','TT','Trinidad and Tobago'),('en','TV','Tuvalu'),('en','TW','Taiwan'),('en','TZ','Tanzania'),('en','UA','Ukraine'),('en','UG','Uganda'),('en','UM','United States Minor Outlying Islands'),('en','US','United States of America'),('en','UY','Uruguay'),('en','UZ','Uzbekistan'),('en','VA','Vatican City State'),('en','VC','Saint Vincent and the Grenadines'),('en','VE','Venezuela'),('en','VG','Virgin Islands'),('en','VI','United States Virgin Islands'),('en','VN','Vietnam'),('en','VU','Vanuatu'),('en','WF','Wallis and Futuna'),('en','WS','Samoa'),('en','YE','Yemen'),('en','YT','Mayotte'),('en','ZA','South Africa'),('en','ZM','Zambia'),('en','ZW','Zimbabwe'),('es','AD','Andorra'),('es','AE','Emiratos Ãrabes Unidos'),('es','AF','Afganistan'),('es','AG','Antigua y Barbuda'),('es','AI','Anguila'),('es','AL','Albania'),('es','AM','Armenia'),('es','AN','Antillas Neerlandesas'),('es','AO','Angola'),('es','AQ','Antartida'),('es','AR','Argentina'),('es','AS','Samoa Americana'),('es','AT','Austria'),('es','AU','Australia'),('es','AW','Aruba'),('es','AX','Islas de Ã…land'),('es','AZ','Azerbayan'),('es','BA','Bosnia y Herzegovina'),('es','BB','Barbados'),('es','BD','Bangladesh'),('es','BE','Belgica'),('es','BF','Burkina Faso'),('es','BG','Bulgaria'),('es','BH','Bahrein'),('es','BI','Burundi'),('es','BJ','BenÃ­n'),('es','BL','San BartolomÃ©'),('es','BM','Islas Bermudas'),('es','BN','BrunÃ©i'),('es','BO','Bolivia'),('es','BR','Brasil'),('es','BS','Bahamas'),('es','BT','BhutÃ¡n'),('es','BV','Isla Bouvet'),('es','BW','Botsuana'),('es','BY','Bielorrusia'),('es','BZ','Belice'),('es','CA','CanadÃ¡'),('es','CC','Islas Cocos (Keeling)'),('es','CD','Congo'),('es','CF','RepÃºblica Centroafricana'),('es','CG','Congo'),('es','CH','Suiza'),('es','CI','Costa de Marfil'),('es','CK','Islas Cook'),('es','CL','Chile'),('es','CM','CamerÃºn'),('es','CN','China'),('es','CO','Colombia'),('es','CR','Costa Rica'),('es','CU','Cuba'),('es','CV','Cabo Verde'),('es','CX','Isla de Navidad'),('es','CY','Chipre'),('es','CZ','RepÃºblica Checa'),('es','DE','Alemania'),('es','DJ','Yibuti'),('es','DK','Dinamarca'),('es','DM','Dominica'),('es','DO','RepÃºblica Dominicana'),('es','DZ','Algeria'),('es','EC','Ecuador'),('es','EE','Estonia'),('es','EG','Egipto'),('es','EH','Sahara Occidental'),('es','ER','Eritrea'),('es','ES','EspaÃ±a'),('es','ET','EtiopÃ­a'),('es','FI','Finlandia'),('es','FJ','Fiyi'),('es','FK','Islas Malvinas'),('es','FM','Micronesia'),('es','FO','Islas Feroe'),('es','FR','Francia'),('es','GA','GabÃ³n'),('es','GB','Reino Unido'),('es','GD','Granada'),('es','GE','Georgia'),('es','GF','Guayana Francesa'),('es','GG','Guernsey'),('es','GH','Ghana'),('es','GI','Gibraltar'),('es','GL','Groenlandia'),('es','GM','Gambia'),('es','GN','Guinea'),('es','GP','Guadalupe'),('es','GQ','Guinea Ecuatorial'),('es','GR','Grecia'),('es','GS','Islas Georgias del Sur y Sandwich del Sur'),('es','GT','Guatemala'),('es','GU','Guam'),('es','GW','Guinea-Bissau'),('es','GY','Guyana'),('es','HK','Hong kong'),('es','HM','Islas Heard y McDonald'),('es','HN','Honduras'),('es','HR','Croacia'),('es','HT','HaitÃ­'),('es','HU','HungrÃ­a'),('es','ID','Indonesia'),('es','IE','Irlanda'),('es','IL','Israel'),('es','IM','Isla de Man'),('es','IN','India'),('es','IO','Territorio BritÃ¡nico del OcÃ©ano Ãndico'),('es','IQ','Irak'),('es','IR','IrÃ¡n'),('es','IS','Islandia'),('es','IT','Italia'),('es','JE','Jersey'),('es','JM','Jamaica'),('es','JO','Jordania'),('es','JP','JapÃ³n'),('es','KE','Kenia'),('es','KG','KirgizstÃ¡n'),('es','KH','Camboya'),('es','KI','Kiribati'),('es','KM','Comoras'),('es','KN','San CristÃ³bal y Nieves'),('es','KP','Corea del Norte'),('es','KR','Corea del Sur'),('es','KW','Kuwait'),('es','KY','Islas CaimÃ¡n'),('es','KZ','KazajistÃ¡n'),('es','LA','Laos'),('es','LB','LÃ­bano'),('es','LC','Santa LucÃ­a'),('es','LI','Liechtenstein'),('es','LK','Sri lanka'),('es','LR','Liberia'),('es','LS','Lesoto'),('es','LT','Lituania'),('es','LU','Luxemburgo'),('es','LV','Letonia'),('es','LY','Libia'),('es','MA','Marruecos'),('es','MC','MÃ³naco'),('es','MD','Moldavia'),('es','ME','Montenegro'),('es','MF','San MartÃ­n (Francia)'),('es','MG','Madagascar'),('es','MH','Islas Marshall'),('es','MK','MacedÃ´nia'),('es','ML','Mali'),('es','MM','Birmania'),('es','MN','Mongolia'),('es','MO','Macao'),('es','MP','Islas Marianas del Norte'),('es','MQ','Martinica'),('es','MR','Mauritania'),('es','MS','Montserrat'),('es','MT','Malta'),('es','MU','Mauricio'),('es','MV','Islas Maldivas'),('es','MW','Malawi'),('es','MX','MÃ©xico'),('es','MY','Malasia'),('es','MZ','Mozambique'),('es','NA','Namibia'),('es','NC','Nueva Caledonia'),('es','NE','Niger'),('es','NF','Isla Norfolk'),('es','NG','Nigeria'),('es','NI','Nicaragua'),('es','NL','PaÃ­ses Bajos'),('es','NO','Noruega'),('es','NP','Nepal'),('es','NR','Nauru'),('es','NU','Niue'),('es','NZ','Nueva Zelanda'),('es','OM','OmÃ¡n'),('es','PA','PanamÃ¡'),('es','PE','PerÃº'),('es','PF','Polinesia Francesa'),('es','PG','PapÃºa Nueva Guinea'),('es','PH','Filipinas'),('es','PK','PakistÃ¡n'),('es','PL','Polonia'),('es','PM','San Pedro y MiquelÃ³n'),('es','PN','Islas Pitcairn'),('es','PR','Puerto Rico'),('es','PS','Palestina'),('es','PT','Portugal'),('es','PW','Palau'),('es','PY','Paraguay'),('es','QA','Qatar'),('es','RE','ReuniÃ³n'),('es','RO','RumanÃ­a'),('es','RS','Serbia'),('es','RU','Rusia'),('es','RW','Ruanda'),('es','SA','Arabia Saudita'),('es','SB','Islas SalomÃ³n'),('es','SC','Seychelles'),('es','SD','SudÃ¡n'),('es','SE','Suecia'),('es','SG','Singapur'),('es','SH','Santa Elena'),('es','SI','Eslovenia'),('es','SJ','Svalbard y Jan Mayen'),('es','SK','Eslovaquia'),('es','SL','Sierra Leona'),('es','SM','San Marino'),('es','SN','Senegal'),('es','SO','Somalia'),('es','SR','SurinÃ¡m'),('es','ST','Santo TomÃ© y PrÃ­ncipe'),('es','SV','El Salvador'),('es','SY','Siria'),('es','SZ','Swazilandia'),('es','TC','Islas Turcas y Caicos'),('es','TD','Chad'),('es','TF','Territorios Australes y AntÃ¡rticas Franceses'),('es','TG','Togo'),('es','TH','Tailandia'),('es','TJ','TadjikistÃ¡n'),('es','TK','Tokelau'),('es','TL','Timor Oriental'),('es','TM','Turkmenistan'),('es','TN','Tunez'),('es','TO','Tonga'),('es','TR','Turquia'),('es','TT','Trinidad y Tobago'),('es','TV','Tuvalu'),('es','TW','TaiwÃ¡n'),('es','TZ','Tanzania'),('es','UA','Ucrania'),('es','UG','Uganda'),('es','UM','Islas Ultramarinas Menores de Estados Unidos'),('es','US','Estados Unidos de AmÃ©rica'),('es','UY','Uruguay'),('es','UZ','Uzbekistan'),('es','VA','Ciudad del Vaticano'),('es','VC','San Vicente y las Granadinas'),('es','VE','Venezuela'),('es','VG','Islas VÃ­rgenes BritÃ¡nicas'),('es','VI','Islas VÃ­rgenes de los Estados Unidos'),('es','VN','Vietnam'),('es','VU','Vanuatu'),('es','WF','Wallis y Futuna'),('es','WS','Samoa'),('es','YE','Yemen'),('es','YT','Mayotte'),('es','ZA','SudÃ¡frica'),('es','ZM','Zambia'),('es','ZW','Zimbabue');
/*!40000 ALTER TABLE `idioma_pagina_pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_ingles`
--

DROP TABLE IF EXISTS `nivel_ingles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nivel_ingles` (
  `ID_NIVEL` int(11) NOT NULL AUTO_INCREMENT,
  `NIVEL` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_NIVEL`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_ingles`
--

LOCK TABLES `nivel_ingles` WRITE;
/*!40000 ALTER TABLE `nivel_ingles` DISABLE KEYS */;
INSERT INTO `nivel_ingles` VALUES (1,'A1'),(2,'A2'),(3,'B1'),(4,'B2'),(5,'C1');
/*!40000 ALTER TABLE `nivel_ingles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pais` (
  `ID_PAIS` varchar(2) NOT NULL,
  PRIMARY KEY (`ID_PAIS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES ('AD'),('AE'),('AF'),('AG'),('AI'),('AL'),('AM'),('AN'),('AO'),('AQ'),('AR'),('AS'),('AT'),('AU'),('AW'),('AX'),('AZ'),('BA'),('BB'),('BD'),('BE'),('BF'),('BG'),('BH'),('BI'),('BJ'),('BL'),('BM'),('BN'),('BO'),('BR'),('BS'),('BT'),('BV'),('BW'),('BY'),('BZ'),('CA'),('CC'),('CD'),('CF'),('CG'),('CH'),('CI'),('CK'),('CL'),('CM'),('CN'),('CO'),('CR'),('CU'),('CV'),('CX'),('CY'),('CZ'),('DE'),('DJ'),('DK'),('DM'),('DO'),('DZ'),('EC'),('EE'),('EG'),('EH'),('ER'),('ES'),('ET'),('FI'),('FJ'),('FK'),('FM'),('FO'),('FR'),('GA'),('GB'),('GD'),('GE'),('GF'),('GG'),('GH'),('GI'),('GL'),('GM'),('GN'),('GP'),('GQ'),('GR'),('GS'),('GT'),('GU'),('GW'),('GY'),('HK'),('HM'),('HN'),('HR'),('HT'),('HU'),('ID'),('IE'),('IL'),('IM'),('IN'),('IO'),('IQ'),('IR'),('IS'),('IT'),('JE'),('JM'),('JO'),('JP'),('KE'),('KG'),('KH'),('KI'),('KM'),('KN'),('KP'),('KR'),('KW'),('KY'),('KZ'),('LA'),('LB'),('LC'),('LI'),('LK'),('LR'),('LS'),('LT'),('LU'),('LV'),('LY'),('MA'),('MC'),('MD'),('ME'),('MF'),('MG'),('MH'),('MK'),('ML'),('MM'),('MN'),('MO'),('MP'),('MQ'),('MR'),('MS'),('MT'),('MU'),('MV'),('MW'),('MX'),('MY'),('MZ'),('NA'),('NC'),('NE'),('NF'),('NG'),('NI'),('NL'),('NO'),('NP'),('NR'),('NU'),('NZ'),('OM'),('PA'),('PE'),('PF'),('PG'),('PH'),('PK'),('PL'),('PM'),('PN'),('PR'),('PS'),('PT'),('PW'),('PY'),('QA'),('RE'),('RO'),('RS'),('RU'),('RW'),('SA'),('SB'),('SC'),('SD'),('SE'),('SG'),('SH'),('SI'),('SJ'),('SK'),('SL'),('SM'),('SN'),('SO'),('SR'),('ST'),('SV'),('SY'),('SZ'),('TC'),('TD'),('TF'),('TG'),('TH'),('TJ'),('TK'),('TL'),('TM'),('TN'),('TO'),('TR'),('TT'),('TV'),('TW'),('TZ'),('UA'),('UG'),('UM'),('US'),('UY'),('UZ'),('VA'),('VC'),('VE'),('VG'),('VI'),('VN'),('VU'),('WF'),('WS'),('YE'),('YT'),('ZA'),('ZM'),('ZW');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `profesor` (
  `ID_PROFESOR` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(256) NOT NULL,
  `PSSWD` varchar(255) NOT NULL,
  `ID_PAIS` varchar(8) NOT NULL,
  `NOMBRE` varchar(45) NOT NULL,
  `APELLIDO1` varchar(45) NOT NULL,
  `APELLIDO2` varchar(45) DEFAULT NULL,
  `ANO_NACIMIENTO` int(11) NOT NULL,
  `FECHA_SUBSCRIPCION` datetime NOT NULL,
  `PRECIO_SESION` double NOT NULL,
  `ID_IDIOMA` varchar(5) NOT NULL,
  `ID_GENERO` varchar(4) NOT NULL,
  `ID_NIVEL` int(11) NOT NULL,
  `ACTIVADA` int(1) NOT NULL DEFAULT '0',
  `DESCRIPCION` varchar(255) DEFAULT 'Sin descripcion',
  `CODIGO_DE_RECUPERACION` int(6) DEFAULT NULL,
  PRIMARY KEY (`ID_PROFESOR`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `fk_PROFESOR_PAIS1_idx` (`ID_PAIS`),
  KEY `fk_PROFESOR_Idioma1_idx` (`ID_IDIOMA`),
  KEY `fk_PROFESOR_GENERO1_idx` (`ID_GENERO`),
  KEY `fk_PROFESOR_NIVEL_INGLES1_idx` (`ID_NIVEL`),
  CONSTRAINT `fk_PROFESOR_GENERO1` FOREIGN KEY (`ID_GENERO`) REFERENCES `genero` (`id_genero`),
  CONSTRAINT `fk_PROFESOR_Idioma1` FOREIGN KEY (`ID_IDIOMA`) REFERENCES `idioma` (`id_idioma`),
  CONSTRAINT `fk_PROFESOR_NIVEL_INGLES1` FOREIGN KEY (`ID_NIVEL`) REFERENCES `nivel_ingles` (`id_nivel`),
  CONSTRAINT `fk_PROFESOR_PAIS1` FOREIGN KEY (`ID_PAIS`) REFERENCES `pais` (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (21,'acorralfdez@gmail.com','e5QUlOloWJNlKOy+6BJQa3peAFdUMd1Jdauq2Ya7wWqTbhZfd5hMS/qTXSQ/IEGB','ES','Alejandro Ricardo','Corral','Fernandez',2000,'2019-04-25 00:00:00',7.5,'en','H',2,1,'Good day my name is Alejandro and i think i am the best teacher in the world',NULL),(22,'mikeydonald@gmail.com','KAWuNxOYU7UlliFCXXRUDGwJUhQoyLrTNMlsq9ALqjK1uuWJzCi+lO0u3MM4tbwz','FR','Mikey','Donald','',1978,'2019-04-25 00:00:00',6,'en','H',3,1,'Hey i´m Mickey',NULL),(23,'phatsad666@gmail.com','I5bcCQiSpvY4OqbDHnhJl0zEWIFGWPEDR0p/S+bf27SAqZmbwT7BJpa+YPVQ2BpO','AF','Robert','Matthews','',2000,'2019-04-25 00:00:00',5,'en','O',1,0,'Test user',NULL);
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor_puntua_estudiante`
--

DROP TABLE IF EXISTS `profesor_puntua_estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `profesor_puntua_estudiante` (
  `ID_PROFESOR` int(11) NOT NULL,
  `ID_ESTUDIANTE` int(11) NOT NULL,
  `FECHA_PUNTUACION` datetime NOT NULL,
  `PUNTUACION` double NOT NULL,
  PRIMARY KEY (`ID_PROFESOR`,`ID_ESTUDIANTE`,`FECHA_PUNTUACION`),
  KEY `fk_USUARIO_PUNTUA_USUARIO_PROFESOR1_idx` (`ID_PROFESOR`),
  KEY `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE1_idx` (`ID_ESTUDIANTE`),
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE1` FOREIGN KEY (`ID_ESTUDIANTE`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_PROFESOR1` FOREIGN KEY (`ID_PROFESOR`) REFERENCES `profesor` (`id_profesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor_puntua_estudiante`
--

LOCK TABLES `profesor_puntua_estudiante` WRITE;
/*!40000 ALTER TABLE `profesor_puntua_estudiante` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesor_puntua_estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sesion` (
  `ID_SESION` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PROFESOR` int(11) NOT NULL,
  `ID_ESTUDIANTE` int(11) NOT NULL,
  `FECHA_SESION` datetime NOT NULL,
  `ID_HORARIO` int(11) NOT NULL,
  `FECHA_INICIO` datetime DEFAULT NULL,
  `FECHA_FIN` datetime DEFAULT NULL,
  `PRECIO` double NOT NULL,
  `ID_ESTADO` char(1) NOT NULL,
  `FECHA_CAMBIO_ESTADO` datetime NOT NULL,
  PRIMARY KEY (`ID_SESION`,`ID_PROFESOR`,`ID_ESTUDIANTE`),
  KEY `fk_SESION_ESTADO1_idx` (`ID_ESTADO`),
  KEY `fk_SESION_HORARIO1_idx` (`ID_HORARIO`),
  KEY `fk_SESION_PROFESOR1_idx` (`ID_PROFESOR`),
  KEY `fk_SESION_ESTUDIANTE1_idx` (`ID_ESTUDIANTE`),
  CONSTRAINT `fk_SESION_ESTADO1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `estado` (`id_estado`),
  CONSTRAINT `fk_SESION_ESTUDIANTE1` FOREIGN KEY (`ID_ESTUDIANTE`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `fk_SESION_HORARIO1` FOREIGN KEY (`ID_HORARIO`) REFERENCES `horario` (`id_horario`),
  CONSTRAINT `fk_SESION_PROFESOR1` FOREIGN KEY (`ID_PROFESOR`) REFERENCES `profesor` (`id_profesor`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` VALUES (102,22,53,'2019-04-28 00:00:00',44,NULL,NULL,6,'C','2019-04-25 00:00:00'),(103,21,53,'2019-04-29 00:00:00',34,NULL,NULL,7.5,'C','2019-04-26 00:00:00'),(104,22,53,'2019-04-28 00:00:00',44,NULL,NULL,6,'C','2019-04-26 00:00:00'),(105,21,53,'2019-04-27 00:00:00',42,NULL,NULL,7.5,'C','2019-04-26 00:00:00'),(115,21,53,'2019-04-27 00:00:00',41,NULL,NULL,7.5,'T','2019-04-27 00:00:00'),(116,21,53,'2019-04-27 00:00:00',41,NULL,NULL,7.5,'T','2019-04-27 00:00:00'),(117,21,53,'2019-04-27 00:00:00',43,NULL,NULL,7.5,'T','2019-04-27 00:00:00'),(118,21,53,'2019-04-27 00:00:00',43,NULL,NULL,7.5,'T','2019-04-27 00:00:00'),(119,21,53,'2019-04-27 00:00:00',42,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(120,21,53,'2019-04-30 00:00:00',35,NULL,NULL,7.5,'R','2019-04-30 00:00:00'),(121,21,53,'2019-04-30 00:00:00',35,'2019-04-30 00:00:00','2019-04-30 00:00:00',7.5,'T','2019-04-29 00:00:00'),(122,21,64,'2019-04-30 00:00:00',36,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(123,21,64,'2019-04-30 00:00:00',36,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(124,21,53,'2019-04-30 00:00:00',35,'2019-04-30 14:15:28','2019-04-30 15:15:28',7.5,'T','2019-04-30 00:00:00'),(125,21,53,'2019-04-30 00:00:00',36,'2019-04-30 14:22:13','2019-04-30 15:22:13',7.5,'T','2019-04-30 00:00:00'),(126,21,53,'2019-04-30 00:00:00',35,'2019-04-30 15:29:21','2019-04-30 16:29:21',7.5,'T','2019-04-30 00:00:00'),(127,21,53,'2019-04-30 00:00:00',36,'2019-04-29 23:45:17','2019-04-29 23:45:17',7.5,'T','2019-04-29 00:00:00'),(128,21,53,'2019-04-30 00:00:00',35,'2019-04-29 23:29:25','2019-04-29 23:29:25',7.5,'C','2019-04-30 00:00:00'),(129,21,53,'2019-04-30 00:00:00',36,'2019-04-30 18:44:55','2019-04-30 19:44:55',7.5,'T','2019-04-30 00:00:00'),(130,21,53,'2019-04-30 00:00:00',36,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(131,21,53,'2019-04-30 10:30:00',36,'2019-04-30 20:01:54','2019-04-30 21:01:54',7.5,'T','2019-04-30 00:00:00'),(132,21,53,'2019-04-30 00:30:00',36,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(133,21,53,'2019-04-29 23:30:00',36,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(134,21,53,'2019-04-29 23:30:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(135,21,53,'2019-04-29 23:30:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(136,21,53,'2019-04-29 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(137,21,53,'2019-04-29 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(138,21,53,'2019-04-29 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(139,21,53,'2019-04-29 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(140,21,53,'2019-04-29 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(141,21,53,'2019-04-29 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(142,21,53,'2019-04-29 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(143,21,53,'2019-04-30 00:00:00',35,NULL,NULL,7.5,'C','2019-04-29 00:00:00'),(144,21,53,'2019-04-30 00:00:00',35,NULL,NULL,7.5,'C','2019-04-30 00:00:00'),(145,21,53,'2019-05-02 00:00:00',37,'2019-05-02 11:14:18','2019-05-01 20:14:18',7.5,'T','2019-05-02 00:00:00'),(146,21,53,'2019-05-03 00:00:00',39,'2019-05-03 10:24:19','2019-05-02 20:24:19',7.5,'T','2019-05-03 00:00:00'),(147,21,53,'2019-05-03 00:00:00',40,'2019-05-03 10:25:47','2019-05-02 21:25:47',7.5,'T','2019-05-03 00:00:00'),(148,21,53,'2019-05-03 00:00:00',40,'2019-05-03 10:30:25','2019-05-03 11:30:25',7.5,'T','2019-05-03 00:00:00'),(149,21,53,'2019-05-04 00:00:00',42,'2019-05-04 11:56:30','2019-05-04 12:56:30',7.5,'A','2019-05-03 00:00:00');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-04 12:03:55

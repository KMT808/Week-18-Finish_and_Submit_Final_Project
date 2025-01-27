-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (arm64)
--
-- Host: localhost    Database: art_prompts
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `contributor`
--

DROP TABLE IF EXISTS `contributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contributor` (
  `contributor_id` bigint NOT NULL AUTO_INCREMENT,
  `contributor_email` varchar(255) DEFAULT NULL,
  `contributor_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contributor_id`),
  UNIQUE KEY `UKjkr6b6kxlw72g25weuwhdcmb6` (`contributor_email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor`
--

LOCK TABLES `contributor` WRITE;
/*!40000 ALTER TABLE `contributor` DISABLE KEYS */;
INSERT INTO `contributor` VALUES (1,'Hope.Jones@email.com','Hope Jones'),(2,'AmyATucker@rr.com','Amy A. Tucker');
/*!40000 ALTER TABLE `contributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prompt`
--

DROP TABLE IF EXISTS `prompt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prompt` (
  `contributor_id` bigint NOT NULL,
  `prompt_id` bigint NOT NULL AUTO_INCREMENT,
  `completion_time` varchar(255) DEFAULT NULL,
  `complexity` varchar(255) DEFAULT NULL,
  `prompt_description` varchar(255) DEFAULT NULL,
  `prompt_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prompt_id`),
  KEY `FKgytt150tp98k8ta8is084bmdf` (`contributor_id`),
  CONSTRAINT `FKgytt150tp98k8ta8is084bmdf` FOREIGN KEY (`contributor_id`) REFERENCES `contributor` (`contributor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prompt`
--

LOCK TABLES `prompt` WRITE;
/*!40000 ALTER TABLE `prompt` DISABLE KEYS */;
INSERT INTO `prompt` VALUES (1,1,'15-30 minutes','Medium','Draw a picture of a view out of any window using pencils','View from your window'),(1,2,'5-14 minutes','Low','Find something in your refrigerator and draw it.','Draw something in your frig.'),(2,3,'5-14 minutes','Medium','Draw something with the paper and pencil underneath the table or otherwise hidden.','Draw while not seeing drawing');
/*!40000 ALTER TABLE `prompt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prompt_supply`
--

DROP TABLE IF EXISTS `prompt_supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prompt_supply` (
  `prompt_id` bigint NOT NULL,
  `supply_id` bigint NOT NULL,
  PRIMARY KEY (`prompt_id`,`supply_id`),
  KEY `FK419qa2qltwc43olkbs9gcygsk` (`supply_id`),
  CONSTRAINT `FK419qa2qltwc43olkbs9gcygsk` FOREIGN KEY (`supply_id`) REFERENCES `supply` (`supply_id`),
  CONSTRAINT `FKgodlackwm4ctx0mhmjxrc3abj` FOREIGN KEY (`prompt_id`) REFERENCES `prompt` (`prompt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prompt_supply`
--

LOCK TABLES `prompt_supply` WRITE;
/*!40000 ALTER TABLE `prompt_supply` DISABLE KEYS */;
INSERT INTO `prompt_supply` VALUES (3,1),(3,2),(3,18);
/*!40000 ALTER TABLE `prompt_supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply`
--

DROP TABLE IF EXISTS `supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supply` (
  `supply_id` bigint NOT NULL AUTO_INCREMENT,
  `supply` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply`
--

LOCK TABLES `supply` WRITE;
/*!40000 ALTER TABLE `supply` DISABLE KEYS */;
INSERT INTO `supply` VALUES (1,'Graphite pencils'),(2,'Colored pencils'),(3,'Pens'),(4,'Charcoal'),(5,'White drawing paper'),(6,'Toned drawing paper'),(7,'Watercolor pencils'),(8,'Watercolor tubes or pans'),(9,'Paint brushes'),(10,'Watercolor paper'),(11,'Kneaded eraser'),(12,'White eraser'),(13,'Tracing paper'),(14,'Graphite pencils'),(15,'Colored pencils'),(16,'Pens'),(17,'Charcoal'),(18,'White drawing paper'),(19,'Toned drawing paper'),(20,'Watercolor pencils'),(21,'Watercolor tubes or pans'),(22,'Paint brushes'),(23,'Watercolor paper'),(24,'Kneaded eraser'),(25,'White eraser'),(26,'Tracing paper'),(27,'Graphite pencils'),(28,'Colored pencils'),(29,'Pens'),(30,'Charcoal'),(31,'White drawing paper'),(32,'Toned drawing paper'),(33,'Watercolor pencils'),(34,'Watercolor tubes or pans'),(35,'Paint brushes'),(36,'Watercolor paper'),(37,'Kneaded eraser'),(38,'White eraser'),(39,'Tracing paper'),(40,'Graphite pencils'),(41,'Colored pencils'),(42,'Pens'),(43,'Charcoal'),(44,'White drawing paper'),(45,'Toned drawing paper'),(46,'Watercolor pencils'),(47,'Watercolor tubes or pans'),(48,'Paint brushes'),(49,'Watercolor paper'),(50,'Kneaded eraser'),(51,'White eraser'),(52,'Tracing paper'),(53,'Graphite pencils'),(54,'Colored pencils'),(55,'Pens'),(56,'Charcoal'),(57,'White drawing paper'),(58,'Toned drawing paper'),(59,'Watercolor pencils'),(60,'Watercolor tubes or pans'),(61,'Paint brushes'),(62,'Watercolor paper'),(63,'Kneaded eraser'),(64,'White eraser'),(65,'Tracing paper'),(66,'Graphite pencils'),(67,'Colored pencils'),(68,'Pens'),(69,'Charcoal'),(70,'White drawing paper'),(71,'Toned drawing paper'),(72,'Watercolor pencils'),(73,'Watercolor tubes or pans'),(74,'Paint brushes'),(75,'Watercolor paper'),(76,'Kneaded eraser'),(77,'White eraser'),(78,'Tracing paper'),(79,'Graphite pencils'),(80,'Colored pencils'),(81,'Pens'),(82,'Charcoal'),(83,'White drawing paper'),(84,'Toned drawing paper'),(85,'Watercolor pencils'),(86,'Watercolor tubes or pans'),(87,'Paint brushes'),(88,'Watercolor paper'),(89,'Kneaded eraser'),(90,'White eraser'),(91,'Tracing paper'),(92,'Graphite pencils'),(93,'Colored pencils'),(94,'Pens'),(95,'Charcoal'),(96,'White drawing paper'),(97,'Toned drawing paper'),(98,'Watercolor pencils'),(99,'Watercolor tubes or pans'),(100,'Paint brushes'),(101,'Watercolor paper'),(102,'Kneaded eraser'),(103,'White eraser'),(104,'Tracing paper'),(105,'Graphite pencils'),(106,'Colored pencils'),(107,'Pens'),(108,'Charcoal'),(109,'White drawing paper'),(110,'Toned drawing paper'),(111,'Watercolor pencils'),(112,'Watercolor tubes or pans'),(113,'Paint brushes'),(114,'Watercolor paper'),(115,'Kneaded eraser'),(116,'White eraser'),(117,'Tracing paper');
/*!40000 ALTER TABLE `supply` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-27 15:47:22

-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: ifi_fresher_test
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `answer_id` int NOT NULL,
  `content` varchar(500) NOT NULL,
  `is_correct` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `answer_fk_idx` (`question_id`),
  CONSTRAINT `answer_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'Tham số đầu tiên của danh sách tham số',_binary '',_binary '\0',1),(2,'Tên của chương trình',_binary '\0',_binary '\0',1),(3,'Số lượng tham số',_binary '\0',_binary '\0',1),(4,'Không câu nào đúng',_binary '\0',_binary '\0',1),(5,'Nhập một chuỗi',_binary '',_binary '\0',2),(6,'Nhập một số nguyên',_binary '\0',_binary '\0',2),(7,'Nhập một ký tự',_binary '\0',_binary '\0',2),(8,'Không có phương thức này',_binary '\0',_binary '\0',2),(9,'Java Platform',_binary '',_binary '\0',3),(10,'IntelliJ',_binary '\0',_binary '\0',3),(11,'Eclipse',_binary '\0',_binary '\0',3),(12,'JDK',_binary '\0',_binary '\0',3),(13,'Lỗi biên dịch: Uncompilable source code - incompatible types',_binary '',_binary '\0',4),(14,'x=-1 y=2',_binary '\0',_binary '\0',4),(15,'x=-1 y=-13',_binary '\0',_binary '\0',4),(16,'x=-1 y=4',_binary '\0',_binary '\0',4),(17,'i = 5 and j = 5',_binary '',_binary '\0',5),(18,'i = 6 and j = 5',_binary '\0',_binary '\0',5),(19,'i = 6 and j = 4',_binary '\0',_binary '\0',5),(20,'i = 5 and j = 6',_binary '\0',_binary '\0',5);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-24 15:19:04

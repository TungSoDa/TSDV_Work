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
  `answer_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `is_correct` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `answer_fk_idx` (`question_id`),
  CONSTRAINT `answer_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'Tham số đầu tiên của danh sách tham số',_binary '',_binary '\0',1),(2,'Tên của chương trình',_binary '\0',_binary '\0',1),(3,'Số lượng tham số',_binary '\0',_binary '\0',1),(4,'Không câu nào đúng',_binary '\0',_binary '\0',1),(5,'8',_binary '',_binary '\0',2),(6,'7',_binary '\0',_binary '\0',2),(7,'9',_binary '\0',_binary '\0',2),(8,'5',_binary '\0',_binary '\0',2),(9,'Java Platform',_binary '',_binary '\0',3),(10,'IntelliJ',_binary '\0',_binary '\0',3),(11,'Eclipse',_binary '\0',_binary '\0',3),(12,'JDK',_binary '\0',_binary '\0',3),(13,'Lỗi biên dịch: Uncompilable source code - incompatible types',_binary '',_binary '\0',4),(14,'x=-1 y=2',_binary '\0',_binary '\0',4),(15,'x=-1 y=-13',_binary '\0',_binary '\0',4),(16,'x=-1 y=4',_binary '\0',_binary '\0',4),(17,'i = 5 and j = 5',_binary '',_binary '\0',5),(18,'i = 6 and j = 5',_binary '\0',_binary '\0',5),(19,'i = 6 and j = 4',_binary '\0',_binary '\0',5),(20,'i = 5 and j = 6',_binary '\0',_binary '\0',5),(21,'<h1>',_binary '',_binary '\0',6),(22,'<h6>',_binary '\0',_binary '\0',6),(23,'<head>',_binary '\0',_binary '\0',6),(24,'<heading>',_binary '\0',_binary '\0',6),(25,'<ol>',_binary '',_binary '\0',7),(26,'<ul>',_binary '\0',_binary '\0',7),(27,'<dl>',_binary '\0',_binary '\0',7),(28,'<list>',_binary '\0',_binary '\0',7),(29,'<select>',_binary '',_binary '\0',8),(30,'<list>',_binary '\0',_binary '\0',8),(31,'<input type=\"list\">',_binary '\0',_binary '\0',8),(32,'<input type=\"dropdown\">',_binary '\0',_binary '\0',8),(33,'Tạo một nút lệnh dùng để gửi tin trong form đi',_binary '',_binary '\0',9),(34,'Tạo một ô text để nhập dữ liệu',_binary '\0',_binary '\0',9),(35,'Tạo một nút lệnh dùng để xoá thông tin trong form',_binary '\0',_binary '\0',9),(36,'Tất cả các ý kiến trên',_binary '\0',_binary '\0',9),(37,'Tổ chức World Wide Web Consortium(W3C)',_binary '',_binary '\0',10),(38,'Mozilla',_binary '\0',_binary '\0',10),(39,'Google',_binary '\0',_binary '\0',10),(40,'Microsoft',_binary '\0',_binary '\0',10),(41,'1',_binary '',_binary '\0',11),(42,'2',_binary '\0',_binary '\0',11),(43,'3',_binary '\0',_binary '\0',11),(44,'4',_binary '\0',_binary '\0',11),(45,'Là một kiểu tham chiếu, tương tự như class, chỉ có thể chứa hằng giá trị, khai báo phương thức và kiểu lồng',_binary '',_binary '\0',12),(46,'Là lớp chứa các phương thức rỗng có liên quan với nhau',_binary '\0',_binary '\0',12),(47,'Là một phương thức thực hiện của lớp khác',_binary '\0',_binary '\0',12),(48,'Là lớp nối giữa lớp cơ sở và lớp cha',_binary '\0',_binary '\0',12),(49,'Là các đối tượng được biểu diễn trong phần mềm gồm có 2 thuộc tính trường dữ liệu và các phương thức xử lý dữ liệu',_binary '',_binary '\0',13),(50,'Là một bó phần mềm gồm các hành vi và trạng thái có liên quan với nhau',_binary '\0',_binary '\0',13),(51,'Là vật thể xác định của thế giới thực',_binary '\0',_binary '\0',13),(52,'Là vật thể gồm hành vi và trạng thái',_binary '\0',_binary '\0',13),(53,'Extends',_binary '',_binary '\0',14),(54,'Void',_binary '\0',_binary '\0',14),(55,'Implements',_binary '\0',_binary '\0',14),(56,'Final',_binary '\0',_binary '\0',14),(57,'Dùng để triệu hồi constructor cha',_binary '',_binary '\0',15),(58,'Dùng để trỏ tới class hiện tại',_binary '\0',_binary '\0',15),(59,'Dùng để triệu hồi settercha',_binary '\0',_binary '\0',15),(60,'Dùng để triệu hồi getter cha',_binary '\0',_binary '\0',15),(61,'Hiển thị thông báo nhập thông tin',_binary '',_binary '\0',16),(62,'Hiển thị một thông báo Yes, No',_binary '\0',_binary '\0',16),(63,'Không phương án nào đúng',_binary '\0',_binary '\0',16),(64,'Cả hai dạng trên',_binary '\0',_binary '\0',16),(65,'document.getElementById(\"demo\").innerHTML = \"Welcome to Website!\";',_binary '',_binary '\0',17),(66,'#demo.innerHTML = \"Welcome to Website!\";',_binary '\0',_binary '\0',17),(67,'document.getElement(\"p\").innerHTML = \"Welcome to Website!\";',_binary '\0',_binary '\0',17),(68,'document.getElementByName(\"p\").innerHTML = \"Welcome to Website!\";',_binary '\0',_binary '\0',17),(69,'Khi click chuột vào nút lệnh',_binary '',_binary '\0',18),(70,'Khi một đối tượng trong form nhận focus',_binary '\0',_binary '\0',18),(71,'Khi một đối tượng trong form mất focus',_binary '\0',_binary '\0',18),(72,'Khi click chuột vào một đối tượng trong form',_binary '\0',_binary '\0',18),(73,'Khi chạy thì mở thêm một cửa sổ mới gọi trang Google.com',_binary '',_binary '\0',19),(74,'Không chạy được vì sai',_binary '\0',_binary '\0',19),(75,'Khi kết thúc thì một site khác hiện ra',_binary '\0',_binary '\0',19),(76,'Hiện một trang Google.com duy nhất',_binary '\0',_binary '\0',19),(77,'Server',_binary '',_binary '\0',20),(78,'Client',_binary '\0',_binary '\0',20),(79,'Cả server và client',_binary '\0',_binary '\0',20),(80,'Không ở dạng nào',_binary '\0',_binary '\0',20),(81,'Prototype',_binary '',_binary '\0',21),(82,'Sesstion',_binary '\0',_binary '\0',21),(83,'Request',_binary '\0',_binary '\0',21),(84,'Singleton',_binary '\0',_binary '\0',21),(85,'Object',_binary '',_binary '\0',22),(86,'Component',_binary '\0',_binary '\0',22),(87,'Class',_binary '\0',_binary '\0',22),(88,'Container',_binary '\0',_binary '\0',22),(89,'ApplicationContext',_binary '',_binary '\0',23),(90,'BeanFactory',_binary '\0',_binary '\0',23),(91,'BeanDefinition',_binary '\0',_binary '\0',23),(92,'BeanFactoryAware',_binary '\0',_binary '\0',23),(93,'Tất cả đáp án',_binary '',_binary '\0',24),(94,'Setter',_binary '\0',_binary '\0',24),(95,'Constructor',_binary '\0',_binary '\0',24),(96,'Interface',_binary '\0',_binary '\0',24),(97,'Tất cả đáp án',_binary '',_binary '\0',25),(98,'autodetect',_binary '\0',_binary '\0',25),(99,'contructor',_binary '\0',_binary '\0',25),(100,'by Name & Type',_binary '\0',_binary '\0',25);
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

-- Dump completed on 2021-06-28 15:57:14

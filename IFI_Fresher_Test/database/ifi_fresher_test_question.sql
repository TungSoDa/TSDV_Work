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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  `image` varchar(15) DEFAULT NULL,
  `topic` varchar(50) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `contributor_id` varchar(10) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `question_fk_idx` (`contributor_id`),
  CONSTRAINT `question_fk` FOREIGN KEY (`contributor_id`) REFERENCES `contributor` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'Trong câu lệnh sau: public static void main(String[] agrs) thì phần tử agrs[0] chứa giá trị gì?','null','Java Basic',_binary '\0','tungpv'),(2,'Có bao nhiêu kiểu dữ liệu cơ sở trong Java?','null','Java Basic',_binary '\0','tungpv'),(3,'Muốn chạy được chương trình java, chỉ cần cài phần mền nào sau đây?','null','Java Basic',_binary '\0','tungpv'),(4,'Đọc đoạn mã lệnh sau. Sau khi thực thi chương trình sẽ in ra kết quả gì ?','question4.png','Java Basic',_binary '\0','tungpv'),(5,'Đọc đoạn mã sau. Kết quả in ra của đoạn mã trên là gì?','question5.png','Java Basic',_binary '\0','tungpv'),(6,'Chọn phần tử HTML nào đúng nhất cho định dạng tiêu đề là lớn nhất?','null','HTML',_binary '\0','hoandv'),(7,'Đâu là phần tử HTML tạo ra 1 danh sách đầu mục bằng số?','null','HTML',_binary '\0','hoandv'),(8,'Thẻ HTML nào tạo ra một danh sách đổ xuống (drop-down list)?','null','HTML',_binary '\0','hoandv'),(9,'Thẻ <input type=”Submit” …> dùng để làm gì?','null','HTML',_binary '\0','hoandv'),(10,'Ai đang làm các chuẩn cho Web?','null','HTML',_binary '\0','hoandv'),(11,'Một lớp trong Java có thể có bao nhiêu lớp cha?','null','Java OOP',_binary '\0','tungpv'),(12,'Interface là gì?','null','Java OOP',_binary '\0','tungpv'),(13,'Đối tượng trong phần mềm là gì?','null','Java OOP',_binary '\0','tungpv'),(14,'Sử dụng tính kế thừa trong Java bằng cách dùng từ khóa gì sau đây?','null','Java OOP',_binary '\0','tungpv'),(15,'Từ khóa super dùng để làm gì?','null','Java OOP',_binary '\0','tungpv'),(16,'Hàm prompt(…) trong JavaScript dùng để làm gì?','null','JavaScript',_binary '\0','hoandv'),(17,'Câu lệnh JavaScript nào là đúng để thực hiện thay đổi nội dung trong phần tử HTML bên dưới?','question17.png','JavaScript',_binary '\0','hoandv'),(18,'Trong JavaScript sự kiện Onclick xảy ra khi nào?','null','JavaScript',_binary '\0','hoandv'),(19,'Câu nào nói đúng về lệnh window.open()','question19.png','JavaScript',_binary '\0','hoandv'),(20,'JavaScript là ngôn ngữ xử lý ở đâu?','null','JavaScript',_binary '\0','hoandv'),(21,'Phạm vi mặc định của Beans?','null','Java Spring',_binary '\0','tungpv'),(22,'Bean ở trong Spring là gì?','null','Java Spring',_binary '\0','tungpv'),(23,'Giao diện nào trong Spring chịu trách nhiệm khởi tạo và quản lý Beans?','null','Java Spring',_binary '\0','tungpv'),(24,'Có bao nhiêu kiểu lOC (dependency injection)?','null','Java Spring',_binary '\0','tungpv'),(25,'Các kiểu Autowire?','null','Java Spring',_binary '\0','tungpv');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
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

-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: cozytrain.ckacazfcovre.ap-northeast-2.rds.amazonaws.com    Database: test_train
-- ------------------------------------------------------
-- Server version	8.0.33

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookmark` (
  `els_id` varchar(255) NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`els_id`,`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
INSERT INTO `bookmark` VALUES ('9RXRtosB0ngvu_j-rDEq',21),('AxXRtosB0ngvu_j-rCwk',16),('DxXRtosB0ngvu_j-rCsi',16),('EBXRtosB0ngvu_j-rCsi',58),('GhXRtosB0ngvu_j-rC4n',15),('OhXRtosB0ngvu_j-rCwl',21),('pRXRtosB0ngvu_j-rC0m',15),('uhXRtosB0ngvu_j-rCwl',21),('wxXRtosB0ngvu_j-rC4n',19),('zRXRtosB0ngvu_j-rC4n',15),('zxXRtosB0ngvu_j-rCwl',19),('zxXRtosB0ngvu_j-rCwl',24);
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `chat_room_id` bigint NOT NULL AUTO_INCREMENT,
  `member_first_id` bigint DEFAULT NULL,
  `member_second_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chat_room_id`),
  KEY `FK31a2esdadegc8pcmhpc6rt16k` (`member_first_id`),
  KEY `FKcco2svf2eo7kxccaly17n20x9` (`member_second_id`),
  CONSTRAINT `FK31a2esdadegc8pcmhpc6rt16k` FOREIGN KEY (`member_first_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKcco2svf2eo7kxccaly17n20x9` FOREIGN KEY (`member_second_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` VALUES (1,15,1),(2,15,17),(3,19,20),(6,19,12),(8,19,34),(9,19,36),(10,19,37),(11,19,38),(12,12,34),(13,12,24),(14,41,12),(15,15,41),(16,41,42),(17,41,43),(18,41,44),(19,41,45),(20,19,24),(21,15,16),(22,36,58),(23,59,15),(24,15,12),(25,15,55),(26,42,12),(27,12,10),(28,36,37);
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_list`
--

DROP TABLE IF EXISTS `check_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_list` (
  `check_list_id` bigint NOT NULL AUTO_INCREMENT,
  `check_list_date` date DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `report_id` bigint DEFAULT NULL,
  PRIMARY KEY (`check_list_id`),
  KEY `FK8hh17yfs0qvxl02fggipbaonr` (`member_id`),
  KEY `FKbmhhbbkd0gc6r8nuhyqgnvsre` (`report_id`),
  CONSTRAINT `FK8hh17yfs0qvxl02fggipbaonr` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKbmhhbbkd0gc6r8nuhyqgnvsre` FOREIGN KEY (`report_id`) REFERENCES `report` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_list`
--

LOCK TABLES `check_list` WRITE;
/*!40000 ALTER TABLE `check_list` DISABLE KEYS */;
INSERT INTO `check_list` VALUES (16,'2023-11-10','2023-11-10 10:45:39.449947',15,NULL),(19,'2023-11-13','2023-11-13 10:36:10.670457',19,NULL),(20,'2023-11-13','2023-11-13 17:50:48.888648',24,NULL),(24,'2023-11-14','2023-11-14 18:38:03.417472',12,NULL),(25,'2023-11-15','2023-11-15 09:32:03.408975',24,NULL),(28,'2023-11-15','2023-11-15 11:54:36.519799',15,NULL),(29,'2023-11-16','2023-11-16 00:46:13.401642',56,NULL),(31,'2023-11-16','2023-11-16 11:49:52.179889',15,NULL),(32,'2023-11-16','2023-11-16 12:28:25.383700',59,NULL);
/*!40000 ALTER TABLE `check_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_list_item`
--

DROP TABLE IF EXISTS `check_list_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_list_item` (
  `check_list_item_id` bigint NOT NULL AUTO_INCREMENT,
  `els_id` varchar(255) DEFAULT NULL,
  `check_list_id` bigint DEFAULT NULL,
  PRIMARY KEY (`check_list_item_id`),
  KEY `FK7p9wis9teef8clgjumojsgb8e` (`check_list_id`),
  CONSTRAINT `FK7p9wis9teef8clgjumojsgb8e` FOREIGN KEY (`check_list_id`) REFERENCES `check_list` (`check_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_list_item`
--

LOCK TABLES `check_list_item` WRITE;
/*!40000 ALTER TABLE `check_list_item` DISABLE KEYS */;
INSERT INTO `check_list_item` VALUES (64,'pRXRtosB0ngvu_j-rC0m',16),(70,'zxXRtosB0ngvu_j-rCwl',19),(71,'NRXRtosB0ngvu_j-rCwl',20),(83,'2BXRtosB0ngvu_j-rCsk',24),(84,'2BXRtosB0ngvu_j-rCsk',24),(85,'2BXRtosB0ngvu_j-rCsk',24),(86,'zxXRtosB0ngvu_j-rCwl',25),(89,'AxXRtosB0ngvu_j-rCwk',28),(90,'vxXRtosB0ngvu_j-rCwl',29),(92,'AxXRtosB0ngvu_j-rCwk',31),(93,'pRXRtosB0ngvu_j-rC0m',31),(94,'xBXRtosB0ngvu_j-rC4n',32);
/*!40000 ALTER TABLE `check_list_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `country_id` bigint NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) DEFAULT NULL,
  `country_name_kor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'korea','한국'),(2,'japan','일본'),(3,'thailand','태국'),(4,'china','중국');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dream`
--

DROP TABLE IF EXISTS `dream`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dream` (
  `dream_id` bigint NOT NULL AUTO_INCREMENT,
  `dream_content` varchar(255) DEFAULT NULL,
  `dream_date` date DEFAULT NULL,
  `dream_type` int DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`dream_id`),
  KEY `FKs6ufxbw7hutot3s9r8wl430ja` (`member_id`),
  CONSTRAINT `FKs6ufxbw7hutot3s9r8wl430ja` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dream`
--

LOCK TABLES `dream` WRITE;
/*!40000 ALTER TABLE `dream` DISABLE KEYS */;
INSERT INTO `dream` VALUES (1,'아무런 꿈도 꾸지 않았다.','2023-11-08',5,19),(2,'꿈꾸던꿈','2023-11-10',5,12),(3,'','2023-11-10',0,28),(4,'아침에 일어나면 싸피에 가야하는데\n꿈에서도 싸피에 다녀왔다.\n아무도 없이 조용한 강의실에 불도 켜져 있지 않은 상태였다.\n바람 소리만 들려오는데... 거기에 나 혼자 던져져 있었다.\n\n아침에 잠을 깼는데.. 너무나 소름 끼쳤다.\n(사실 뻥이야)','2023-11-13',4,19),(5,'기싱꿍꼬또','2023-11-13',0,32),(6,'테스트 ㅋㅋ','2023-11-13',0,24),(8,'테스트 ㅋㅋ','2023-11-14',0,24),(9,'테스트 ㅋㅋ','2023-11-14',0,24),(10,'동주오빠를 이겼다.\n\n내가 이제 태국에 와있고 동주오빠는 아직 일본이다\nㅋㅋ','2023-11-15',2,41),(11,'꿈에서 마라탕과 마라샹궈를 같이 먹는 꿈을 꿨다. 후식으로 탕후루까지 먹어서 행복했다.','2023-11-15',1,15),(12,'무슨 꿈?','2023-11-16',0,56),(13,'똥꿈을 꾸었다.','2023-11-16',2,59),(14,'대상을 받아서\n\n돈을 벌었다 !!! ','2023-11-16',0,42),(15,'내가 퀴즈왕이되어 상금 5000만원을 받았다.','2023-11-16',5,12),(16,'오늘 꿈에서 참 신기한 경험을 했다. 나는 비행사가 되어 우주 여행을 떠나 달에 도착했다. 달의 표면에서는 빛나는 작은 생물들이 나를 맞이했는데, 그들과 함께 춤을 추며 음악 속에서 시간을 보냈다.','2023-11-13',3,12);
/*!40000 ALTER TABLE `dream` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend` (
  `friend_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `friend_type` int NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `member_first_id` bigint DEFAULT NULL,
  `member_second_id` bigint DEFAULT NULL,
  PRIMARY KEY (`friend_id`),
  KEY `FK88pw905b1echr9vd8aos8o3k0` (`member_first_id`),
  KEY `FKbwa09bqpcgfxm6xm4vsnq0h3s` (`member_second_id`),
  CONSTRAINT `FK88pw905b1echr9vd8aos8o3k0` FOREIGN KEY (`member_first_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKbwa09bqpcgfxm6xm4vsnq0h3s` FOREIGN KEY (`member_second_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,'2023-11-07 11:19:44.873827',2,'2023-11-07 11:20:59.465294',1,15),(5,'2023-11-08 12:39:40.031413',2,'2023-11-08 17:39:42.279686',12,19),(6,'2023-11-08 12:39:40.031413',2,'2023-11-08 17:22:50.134293',20,19),(7,'2023-11-08 15:49:16.599717',2,'2023-11-16 09:08:43.955929',15,16),(9,'2023-11-09 11:40:15.630413',1,'2023-11-09 11:40:15.630420',14,19),(13,'2023-11-14 10:55:26.328057',2,'2023-11-14 11:02:53.610899',19,34),(14,'2023-11-14 11:04:44.612076',2,'2023-11-14 11:21:57.674928',19,36),(15,'2023-11-14 11:23:03.597748',2,'2023-11-14 12:02:44.981259',19,37),(16,'2023-11-14 12:05:12.920122',2,'2023-11-14 12:05:34.959575',19,38),(17,'2023-11-14 16:39:15.696859',0,'2023-11-14 16:39:15.696868',15,17),(18,'2023-11-14 16:40:05.785527',2,'2023-11-15 17:31:56.806164',19,24),(23,'2023-11-14 16:41:25.404062',2,'2023-11-14 16:41:32.722189',12,24),(26,'2023-11-15 09:58:07.788134',2,'2023-11-15 09:58:19.508432',15,41),(27,'2023-11-15 09:59:48.735641',2,'2023-11-15 09:59:57.926543',41,42),(28,'2023-11-15 10:01:02.580378',2,'2023-11-15 10:01:13.029257',41,43),(29,'2023-11-15 10:02:08.649532',2,'2023-11-15 10:02:14.625330',41,44),(30,'2023-11-15 10:09:03.670951',2,'2023-11-15 10:15:05.028093',41,45),(35,'2023-11-15 20:38:28.223874',0,'2023-11-15 20:38:28.223884',19,21),(36,'2023-11-15 20:45:32.401012',1,'2023-11-15 20:45:32.401023',20,21),(37,'2023-11-16 09:04:50.647221',0,'2023-11-16 09:04:50.647231',15,26),(38,'2023-11-16 09:08:05.796344',0,'2023-11-16 09:08:05.796352',15,21),(41,'2023-11-16 11:02:32.335377',2,'2023-11-16 11:03:16.288781',36,58),(42,'2023-11-16 11:07:19.333498',2,'2023-11-16 11:07:44.923618',15,59),(43,'2023-11-16 11:19:12.540139',2,'2023-11-16 11:31:04.436587',12,15),(44,'2023-11-16 12:45:34.191443',2,'2023-11-16 12:45:34.191443',15,55),(45,'2023-11-16 14:41:12.036135',2,'2023-11-16 14:42:01.113224',12,42),(46,'2023-11-16 15:50:15.258897',0,'2023-11-16 15:50:15.258904',12,32),(47,'2023-11-16 15:54:03.037807',1,'2023-11-16 15:54:03.037814',12,20),(48,'2023-11-16 15:54:40.785167',2,'2023-11-16 16:01:32.048194',10,12),(49,'2023-11-16 15:57:34.139739',1,'2023-11-16 15:57:34.139746',20,42),(50,'2023-11-16 15:57:54.346758',1,'2023-11-16 15:57:54.346766',19,42),(57,'2023-11-16 17:34:05.577875',1,'2023-11-16 17:34:05.577885',5,58),(60,'2023-11-16 17:36:03.150386',2,'2023-11-16 17:36:29.191511',36,37),(61,'2023-11-16 17:55:04.339613',1,'2023-11-16 17:55:04.339621',34,36);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_book`
--

DROP TABLE IF EXISTS `guest_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest_book` (
  `guest_book_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `guest_book_content` varchar(255) DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`guest_book_id`),
  KEY `FK6amudyf2ktd2x6thvkt753adw` (`country_id`),
  KEY `FKcgj13y2yn6g1d1jv22avj6npy` (`member_id`),
  CONSTRAINT `FK6amudyf2ktd2x6thvkt753adw` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`),
  CONSTRAINT `FKcgj13y2yn6g1d1jv22avj6npy` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_book`
--

LOCK TABLES `guest_book` WRITE;
/*!40000 ALTER TABLE `guest_book` DISABLE KEYS */;
INSERT INTO `guest_book` VALUES (4,'2023-11-16','대한민국\n킹세종\n사랑해요 ~~',1,21),(5,'2023-11-16','한국이 최고에요',1,41),(6,'2023-11-16','일본... 가고싶다...',2,41),(7,'2023-11-16','일본\n이치란 라멘\n벨루에요',2,21),(8,'2023-11-16','일본아 독도는 우리땅이야',2,12),(9,'2023-11-16','저는 정말 귀여워요!!',1,10);
/*!40000 ALTER TABLE `guest_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health`
--

DROP TABLE IF EXISTS `health`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health` (
  `health_id` bigint NOT NULL AUTO_INCREMENT,
  `sleep_duration` int NOT NULL,
  `sleep_score` int NOT NULL,
  `steps` int NOT NULL,
  `stress_level` int NOT NULL,
  `report_id` bigint DEFAULT NULL,
  PRIMARY KEY (`health_id`),
  KEY `FK9n6le4v2x7cwoy63siuwrweek` (`report_id`),
  CONSTRAINT `FK9n6le4v2x7cwoy63siuwrweek` FOREIGN KEY (`report_id`) REFERENCES `report` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health`
--

LOCK TABLES `health` WRITE;
/*!40000 ALTER TABLE `health` DISABLE KEYS */;
INSERT INTO `health` VALUES (1,511,87,50,50,1),(2,511,87,50,50,2),(3,511,87,2500,50,3),(6,10,40,2,0,6),(17,344,77,7734,0,17),(18,213,76,1828,0,18),(19,213,76,1828,0,19),(24,335,80,1772,0,24),(25,271,79,1789,0,25),(26,470,79,3450,0,26),(27,470,79,3450,0,27),(28,470,79,3450,0,28),(29,271,40,1783,0,29),(32,312,40,6813,33,32),(33,511,87,7055,25,33),(34,380,40,8355,0,34);
/*!40000 ALTER TABLE `health` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_id` bigint NOT NULL AUTO_INCREMENT,
  `item_img_url` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_description` varchar(255) DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FKf8ba8ik2otwdcilyum728cp6i` (`country_id`),
  CONSTRAINT `FKf8ba8ik2otwdcilyum728cp6i` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/ginseng.svg','고려인삼','인삼 중의 인삼은 고려인삼! 강인한 에너지로 삶에 활력을 불어 넣어줘요.',1),(2,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/hat.svg','갓','갓은 과거 한국 남성이 썼던 모자 중 하나입니다. 사극에서 한번쯤은 본 적 있죠?',1),(3,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/jeju.svg','돌하르방','돌 할아버지는 제주도 어디에서나 반겨주시고 있어요~',1),(4,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/kimchi.svg','김치','한국의 전통 발효 식품으로, 매운 맛과 풍부한 영양을 가진 대표적인 한국 음식이에요.',1),(5,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/korea.svg','태극기','대한민국의 국기로, 흰색 바탕에 중앙의 태극 문양과 네 모서리의 건곤감리.. 뜻을 찾아보세요!',1),(6,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/kpop.svg','K-POP','비스트, 하이라이트, 제로베이스원 Let’s go!?한국이라면 K-POP을 뺄 수 없죠!',1),(7,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/sejong-great.svg','세종대왕','조선시대의 왕으로, 한글을 창제하고 학문, 문화, 과학을 발전시킨 역사적 인물입니다.',1),(8,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/korea/soju.svg','소주','한국의 대표적인 주류로, 술자리를 더욱 즐겁고 활기차게 만들어줍니다. 짠~',1),(9,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/dango.svg','당고','일본 애니메이션에서 잘 보이는 화과자로 말랑 쫄깃한 맛이 일품이에요.',2),(10,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/japan.svg','일장기','일본의 국기입니다.',2),(11,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/koinobori.svg','코이노보리','남자 아이의 성장을 축하하는 의미로, 어린이날에 만나볼 수 있어요.',2),(12,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/maneki-neko.svg','마네키네코','오른발은 돈을, 왼발은 손님을 부르는 고양이에요~',2),(13,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/sakura.svg','벚꽃','일어로 \'사쿠라\'는 많은 사람들이 봄에 일본을 찾는 이유 중 하나에요!',2),(14,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/sushi.svg','스시','일본의 요리로, 밥 위에 해산물, 해조류, 야채 등을 올려 만든 요리입니다.',2),(15,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/torii-gate.svg','도리이','신사 입구에서 가장 흔히 볼 수 있는 일본의 전통적인 문이에요.',2),(16,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/japan/yukata.svg','유카타','유카타는 일본의 전통 의상입니다. 목욕한 후나 불꽃놀이를 할 때 주로 입어요.',2),(17,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/buddha.svg','불상','태국의 국교가 불교인만큼, 모든 불상은 신성하게 다뤄져요. 조심 또 조심',3),(18,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/dance.svg','쑤타이','태국의 전통의상 중의 하나로 연령에 따라 입는게 다르지만 모두 굉장히 화려해요~',3),(19,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/elephant.svg','코끼리','코끼리는 태국을 상징하는 동물이자 공식 국가 동물입니다. 소중히 다뤄주세요!',3),(20,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/loy-krathong.svg','러이끄라통','연꽃 모양의 배 위에 초를 올리고 소원을 빌어요. 촛불이 꺼지지 않으면 소원이 이뤄진다는데..',3),(21,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/temple.svg','사원','종교 의식을 위한 장소 그 이상인 곳. 그 중 찬란한 금빛인 곳들도 많아요',3),(22,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/thai.svg','와이','두 손을 모아 합장하는 태국의 인사에요. 태국에 간다면 이렇게 인사하는 건 어떨까요?',3),(23,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/thailand.svg','뜨라이롱','태국의 국기입니다.',3),(24,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/thailand/tuk-tuk.svg','톡톡이','톡톡 작은 택시로, 단거리 이동 시 주로 이용해요. 흥정하는 재미도 빼놓을 수 없어요!',3),(25,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/china.svg','오성홍기','중국의 국기입니다.',4),(26,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/coins.svg','엽전','(요즘은 사용하지 않지만) 중국이 최초로 엽전을 만들었어요!',4),(27,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/dumpling.svg','딤섬','전 세계에서 인기 있는 중국 요리로 간단하게 먹기 좋아요.',4),(28,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/forbidden-city_.svg','자금성','베이징의 중심에 있는 궁궐로, 세계 최대의 규모에요. ',4),(29,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/hat.svg','중국 전통 모자','특별한 자리나 명절, 행사에서 착용되는 중국의 전통과 문화를 대표하는 아이템 중 하나입니다.',4),(30,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/house.svg','차','중국의 차 문화가 유명해요, 자기 전 마음을 다스리는 차 한 잔의 시간 어떤가요?',4),(31,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/panda-bear.svg','판다','중국에는 판다가 유명하죠. 한국에서도 푸바오의 인기가 엄청나요~~',4),(32,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/collection/china/shirt.svg','치파오','중국 여성을 위한 드레스로, 춘리가 매일 입는 그 옷!',4);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_box`
--

DROP TABLE IF EXISTS `item_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_box` (
  `item_box_id` bigint NOT NULL AUTO_INCREMENT,
  `country_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_box_id`),
  KEY `FK74w0w60d9x8ddfqy1gm3g6jv1` (`country_id`),
  KEY `FK4guheganstuj437jyugwgffni` (`member_id`),
  CONSTRAINT `FK4guheganstuj437jyugwgffni` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FK74w0w60d9x8ddfqy1gm3g6jv1` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_box`
--

LOCK TABLES `item_box` WRITE;
/*!40000 ALTER TABLE `item_box` DISABLE KEYS */;
INSERT INTO `item_box` VALUES (23,2,19),(24,2,19),(25,2,19),(27,3,19),(28,3,19),(29,4,19),(30,4,19),(31,4,19),(32,1,19),(33,1,19),(34,1,19),(105,3,15),(106,3,15),(108,4,15),(109,4,15),(113,2,15),(114,2,15),(115,2,15),(116,3,15),(117,3,15),(118,3,15),(119,4,15),(120,4,15),(121,4,15),(123,1,15),(124,1,15),(125,2,15),(126,2,15),(127,2,15),(128,3,15),(129,3,15),(130,3,15),(131,4,15),(132,4,15),(133,4,15),(134,1,15),(135,1,15),(136,1,15),(137,2,15),(138,2,15),(139,2,15),(140,3,15),(141,3,15),(142,3,15),(143,4,15),(144,4,15),(145,4,15),(146,1,15),(147,1,15),(148,1,15),(149,2,15),(150,2,15),(151,2,15),(152,3,15),(153,3,15),(154,3,15),(155,4,15),(156,4,15),(157,4,15),(158,1,15),(159,1,15),(160,1,15),(161,2,24),(163,3,24),(164,3,24),(166,4,24),(167,4,24),(168,1,24),(169,1,24),(170,1,24),(171,2,24),(172,2,24),(173,2,24),(174,3,24),(175,3,24),(176,3,24),(177,4,24),(178,4,24),(179,4,24),(180,1,24),(181,1,24),(182,1,24),(183,2,24),(184,2,24),(185,2,24),(186,3,24),(187,3,24),(188,3,24),(189,4,24),(190,4,24),(191,4,24),(192,1,24),(193,1,24),(194,1,24),(195,2,24),(196,2,24),(197,2,24),(198,3,24),(199,3,24),(200,3,24),(201,4,24),(202,4,24),(203,4,24),(204,1,24),(205,1,24),(206,1,24),(207,2,24),(208,2,24),(209,2,24),(210,3,24),(211,3,24),(212,3,24),(213,1,21),(214,1,21),(216,2,21),(217,2,21),(218,3,21),(219,3,21),(220,3,21),(222,4,21),(223,4,21),(224,1,21),(225,1,21),(226,1,21),(227,2,21),(228,2,21),(229,2,21),(230,3,21),(231,3,21),(232,3,21),(233,4,21),(234,4,21),(235,4,21),(236,1,21),(237,1,21),(238,1,21),(239,2,21),(240,2,21),(241,2,21),(242,3,21),(243,3,21),(244,3,21),(245,4,21),(246,4,21),(247,4,21),(248,1,21),(249,1,21),(250,1,21),(251,2,21),(252,2,21),(253,2,21),(254,3,21),(255,3,21),(256,3,21),(257,4,21),(258,4,21),(259,4,21),(260,1,21),(261,1,21),(262,1,21),(263,2,21),(264,2,21),(265,4,24),(266,1,41),(267,1,45),(269,2,45),(270,2,45),(272,1,12),(273,1,12),(275,2,12),(276,2,12),(277,3,12);
/*!40000 ALTER TABLE `item_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_list`
--

DROP TABLE IF EXISTS `item_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_list` (
  `item_list_id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_list_id`),
  KEY `FK8esiaeq9ucbfahbv2u8wae93p` (`item_id`),
  KEY `FKccnxkhp9581287cjtb4e3bk4m` (`member_id`),
  CONSTRAINT `FK8esiaeq9ucbfahbv2u8wae93p` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FKccnxkhp9581287cjtb4e3bk4m` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_list`
--

LOCK TABLES `item_list` WRITE;
/*!40000 ALTER TABLE `item_list` DISABLE KEYS */;
INSERT INTO `item_list` VALUES (35,3,21),(36,2,21),(37,7,21),(38,6,21),(39,11,19),(40,1,19),(41,22,19),(42,12,24),(45,6,19),(62,3,24),(63,18,24),(65,27,24),(66,10,24),(67,8,24),(68,10,15),(69,19,15),(70,25,15),(71,13,15),(72,1,15),(73,2,15),(74,4,15),(75,8,21),(76,10,21),(77,31,21),(78,16,15),(83,5,12),(84,13,12);
/*!40000 ALTER TABLE `item_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `member_image_name` varchar(255) DEFAULT NULL,
  `member_image_url` varchar(200) DEFAULT NULL,
  `member_login_id` varchar(255) NOT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `member_password` varchar(100) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `item_list_id` bigint DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FKmcg0rn0njo1k97il9nqloir6v` (`item_list_id`),
  CONSTRAINT `FKmcg0rn0njo1k97il9nqloir6v` FOREIGN KEY (`item_list_id`) REFERENCES `item_list` (`item_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2023-11-06 12:34:18.468865',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','kimjihong','김지홍','$2a$10$tVkXfU8gK.0ljngpgYhnbegNhlY0xX/Ozm86sbTor66XfD4IeMljK','2023-11-06 12:34:18.468876',NULL),(2,'2023-11-06 12:34:38.471565',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','woneee99','주원','$2a$10$lsbTg390t6Y4Yf/HLihhquC9sGX89TyAyDtjjQqfwfT9CjzjTtiTi','2023-11-06 12:34:38.471576',NULL),(4,'2023-11-06 13:43:23.297011',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','kimjihong2','김지홍','$2a$10$l6eXvtXr7hrfdcK/MH.Yp.2BElzPODSde7JBZ4l1OFRQw.5MldUR.','2023-11-06 13:43:23.297022',NULL),(5,'2023-11-06 14:24:04.863203',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','주원','자밀워니','$2a$10$MUUd8MdW1N/Av93mjQfEFOhw2fqZrEvEDnJFOQQjUBURtZtE663Ki','2023-11-06 14:24:04.863215',NULL),(10,'2023-11-06 14:36:38.463174','48514956.jfif','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/10/48514956.jfif','kimjihong3','한모','$2a$10$n2aw2XH2b7f0PQtmhTSDSODpwADbNUbSlDdOorSLZRmUHCD2uhc8.','2023-11-06 14:36:38.463184',NULL),(11,'2023-11-06 14:36:44.016644',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','kimjihong4','김지홍','$2a$10$mWqh0UACKc8KtgbZavg...b5ja9hh0iS6uROIct6L4a1ttzVK3JjS','2023-11-06 14:36:44.016653',NULL),(12,'2023-11-06 14:39:48.046087','minji.jpg','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/12/minji.jpg','juwon','워니','$2a$10$MtOBo5sHSIqPGzV.zhd7QujHeTMXkfSQXp9jzKeSl9TXvAKD59Nem','2023-11-06 14:39:48.046096',83),(13,'2023-11-06 14:49:43.042045',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','ddd','','$2a$10$6wFE1.NFfoOP8mVZpPYRz.kFhE83IBb/FoPSQw.gEgKtVaZVwrRba','2023-11-06 14:49:43.042054',NULL),(14,'2023-11-06 16:03:53.387885',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','woneee99s','주원2','$2a$10$ozaJudS8f619Y.CW84aO7eSEmH997V2HPdGeF0HnbmXi3lQPPHZyO','2023-11-06 16:03:53.387899',NULL),(15,'2023-11-06 16:19:59.571474','hanmo (1).jpg','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/15/hanmo%20%281%29.jpg','hyoin','효인','$2a$10$9iJiIw03xuTFikjeUIQ.quYrhVM4hHTioyLE2kr8oUDtxaGtGDcba','2023-11-06 16:19:59.571483',78),(16,'2023-11-06 16:42:51.764863',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hyoin2','백가연','$2a$10$Hh9Ar8du6BxaZ4vAzhbBZOwbygSs8XXbyASpalxyeCiz3.IUqHkOe','2023-11-06 16:42:51.764872',NULL),(17,'2023-11-07 10:04:11.590945',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hyoin3','최명서','$2a$10$VmmXzh9m/nilcSiKMQ7ac.NfGbML6ne7jtnPj.pse7sBuzAqeUtty','2023-11-07 10:04:11.590959',NULL),(18,'2023-11-07 16:05:07.784309',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','jihong','New 김지홍','$2a$10$iHt7/idSgOn6Y9rnUSAhF.LUcDKjeDu6rcW5hc/6E.zmghry2/vYi','2023-11-07 16:05:07.784320',NULL),(19,'2023-11-07 21:20:21.573847','ㅇㄷㅈ.jfif','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/19/%E3%85%87%E3%84%B7%E3%85%88.jfif','testaccount1','윤두준사랑해','$2a$10$pMtCgXb.Uo45fWWtk5jKaOx1fUi4uOPujexVOQxqr2NBkRo7ggycW','2023-11-07 21:20:21.573859',45),(20,'2023-11-08 12:37:35.462252','ㅇㅇㅅ.jpg','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/20/%E3%85%87%E3%85%87%E3%85%85.jpg','456','양요섭사랑해','$2a$10$3Ag9zwGDaJWGvSIFv7PAu.5iRW5o8ZGLbwTyZjt/s01KL3czyKb86','2023-11-08 12:37:35.462289',NULL),(21,'2023-11-09 12:00:32.482301','기광.jpg','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/21/%EA%B8%B0%EA%B4%91.jpg','qwe','이기광사랑해','$2a$10$QrpY7Y7k6pascw8Kp7wcw.T3Mg.l1JDPVVz0GC0vKCU3hGlTbd/DS','2023-11-09 12:00:32.482312',77),(22,'2023-11-09 14:52:37.956785',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','spiderman','마일스','$2a$10$dkUQjKSuSzbsvVxvvwAVw.0p41v/ZXbjkVxBZUIArQtZzeuJ514Ua','2023-11-09 14:52:37.956798',NULL),(23,'2023-11-09 14:55:54.023032',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','test','tt','$2a$10$LIrgPGONdbMqONI0Wn7QhevFiolUbqYAMPocf4rr0Hi.4c4QzAhQC','2023-11-09 14:55:54.023053',NULL),(24,'2023-11-09 15:48:29.529746','KakaoTalk_20231116_144402102.jpg','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/24/KakaoTalk_20231116_144402102.jpg','harin608','감자할인','$2a$10$VCExmNYqAe9eGVyaS8W8m.7ekjSV7Qsn9zHv/gdGut6kgwMs4GT8u','2023-11-09 15:48:29.529756',42),(25,'2023-11-10 10:39:35.435839',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hyoin4','효인4','$2a$10$uTArwIp.8VAI9MaX74trMOl3DBxBq0Af2yyWMPqiVUUlVsW.3Juzq','2023-11-10 10:39:35.435853',NULL),(26,'2023-11-10 15:25:56.829024',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','potato123','성한빈','$2a$10$P5vqXycJ4bCsJiPLxO6VpOLgbmQZHR9konjObl6JfVbpBqo2KBHUC','2023-11-10 15:25:56.829045',NULL),(27,'2023-11-10 15:41:10.228582',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','test123','esr42','$2a$10$xPNvCLkdVeoNK4sN4VxXZO3pTgQ3ltwVevFzyix7Dg8jbZNY7UZVu','2023-11-10 15:41:10.228593',NULL),(28,'2023-11-10 15:52:24.060232',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','bbookng','보경코치','$2a$10$Zn/YWw2jwLbk1fRP9uqBqOABAYAPofJILIvfacuQTjHfLeeycSZNe','2023-11-10 15:52:24.060244',NULL),(29,'2023-11-11 13:22:25.354658',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','jjjj584','한모주인','$2a$10$T.2D8tGwh.gdsh0KnKNj3eBpTkLKrEni2xu49yRzDnYhSpK.InNHG','2023-11-11 13:22:25.354671',NULL),(30,'2023-11-11 13:24:24.781180',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','1234','1234','$2a$10$UY9.2yOovsHquTligjRoRe4mtwlJSWfvFIdxTFF.nYQUegp7KTJF.','2023-11-11 13:24:24.781191',NULL),(31,'2023-11-13 16:17:58.190249',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','sungjae0512','수면왕만두','$2a$10$Uvsgk/Igkva4BC.RlqHkTuPOYmOYJNwqbXrnrgbV/3XU6kA8KJFde','2023-11-13 16:17:58.190260',NULL),(32,'2023-11-13 17:17:00.533658',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','wjdals00','I\'m always sleepy','$2a$10$xFnsn3ZN4n6d8gMvb/Wq8uu2n2DxIAsTOwkz/q9ddk4INS4T4qVi2','2023-11-13 17:17:00.533668',NULL),(33,'2023-11-13 17:37:56.910738',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','euneun','미소','$2a$10$n3fnlOkxcQgDGI.s7uTMXOYhloxT72/9YAxnVSwViM6zrjmiHhBqu','2023-11-13 17:37:56.910748',NULL),(34,'2023-11-14 10:51:59.416115',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','11','워니','$2a$10$Y.yZ4LJQUlUSI0YjuPflg.7ThFeKqk8dAqQJYHoB69eSx7cG2Bf4W','2023-11-14 10:51:59.416126',NULL),(35,'2023-11-14 11:00:20.071043',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hyoin1','효인1','$2a$10$CCj2YTeZYlk89ibvO0oeh.CdF9d0oxeDnQpHGm3rQy3ElAHxIb2Na','2023-11-14 11:00:20.071053',NULL),(36,'2023-11-14 11:04:28.205770',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','1','1','$2a$10$ZybvhZHabzD8HB7dxBf2A.aoto6IsoYBaIPjGd.DjkljmScFqy6Ee','2023-11-14 11:04:28.205780',NULL),(37,'2023-11-14 11:22:47.614970',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','2','2','$2a$10$mrqqarH0LMlmGUtiUoc0AueYIU.Np/rkfYsfHUXChx.3UHUVmjFPa','2023-11-14 11:22:47.614983',NULL),(38,'2023-11-14 12:04:52.541101',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','23','23','$2a$10$zkNxpkkB.XK.EulP0bFBreD3/jEs3sPrPFRag5y3Wd1fxk97AIvvW','2023-11-14 12:04:52.541110',NULL),(39,'2023-11-14 17:59:32.765008',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','r','r','$2a$10$7y2HkfofsOWvVdS9DhNTdeGQXz.MsKiPBHNG2nAFQ7SFkSiTG2WKq','2023-11-14 17:59:32.765015',NULL),(40,'2023-11-15 01:10:01.533109',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','ho123','호호잇','$2a$10$f6AVjb5Ke/z9yqhPRXerruBI7YuinvJq/fNiosxiTT3FnBEdQoo5u','2023-11-15 01:10:01.533116',NULL),(41,'2023-11-15 09:39:32.158881',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','wndnjs123','황주원','$2a$10$Y1fa1T9Uo2Dsw6z31/E/k.FDh1Rkzw3WRaZgB9wUeDaecqLGNNedy','2023-11-15 09:39:32.158887',NULL),(42,'2023-11-15 09:59:05.376038','hanbin.jpg','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/42/hanbin.jpg','hanbin','성한빈','$2a$10$pmL6PM.3YUyOHp6grDhSM.lS6IE1o7O6Y2cNPZMTMDzEP1cJmqlXi','2023-11-15 09:59:05.376048',NULL),(43,'2023-11-15 10:00:37.396507',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','amuro','아무로 토오루','$2a$10$L7vspDN5pAWstGVxQJmg1uNzEGbUiHoeOA5gZG7klC.jea.ibYj1S','2023-11-15 10:00:37.396515',NULL),(44,'2023-11-15 10:01:49.804701',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','jinsun','박진순','$2a$10$nNGw/FkoU34BQBu.FU9/ROorArntHUrpt8hQoX03aaoRc/YEs8F32','2023-11-15 10:01:49.804708',NULL),(45,'2023-11-15 10:07:34.166881',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','ehdwn123','이동주','$2a$10$PINvZuGFofBl40onebIVH.zoWTUyDiYK1UT25PNMBo9jNMGzCgC36','2023-11-15 10:07:34.166890',NULL),(46,'2023-11-15 10:38:29.742924',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','gamzaru','감자루','$2a$10$yhgupdQp7jvotFv8g7RIA.UzbGzCDN3I2IEvJRVaADC42CdcDzX4O','2023-11-15 10:38:29.742944',NULL),(55,'2023-11-15 18:03:22.216381',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hyoin5','효인5','$2a$10$59Djdx27bweLAprTD.iUIeDP0eVnTO0PONWCN6Kt.gbsIEuJkW9me','2023-11-15 18:03:22.216390',NULL),(56,'2023-11-16 00:42:07.163077',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','ssafy','싸피','$2a$10$zdvrx5dcXqJk34C0HzLKP.vvv30IT63KW77EVcnWpXdfoM8WUtxqS','2023-11-16 00:42:07.163093',NULL),(57,'2023-11-16 09:18:09.845861',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hihello','하이니','$2a$10$eoeS7QG/6/cMlUUpOihvq.hjuh6JoPyBWSAG0b.8SYts2QrjiiJeu','2023-11-16 09:18:09.845872',NULL),(58,'2023-11-16 10:47:02.600732','Frame 401.png','https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/58/Frame%20401.png','123','주원2','$2a$10$APkP9oov.utFejsfLej..u1a/k7OtJ0JSCGYs.g.7prtzMuvZlvTa','2023-11-16 10:47:02.600732',NULL),(59,'2023-11-16 11:02:46.995038',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hongTest','이하린','$2a$10$2.6D0GE52kvFrF0tXoV4DODI/qtS/TeabhSkM3gtSFA2s9YJ/fXrC','2023-11-16 11:02:46.995050',NULL),(60,'2023-11-16 12:00:06.070639',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','12','주원2','$2a$10$kbjUGmyst1p/k/GiEH1LEejf2.3J2A8yu2A6slqW/b5M6Pi9R8/O.','2023-11-16 12:00:06.070639',NULL),(61,'2023-11-16 12:08:58.054711',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','asdasdasd','asdasdasd','$2a$10$GomOSQRHJwgbFuv5pHf2nuK7GSRJ75xIUa4IrY2WsSB.Z8B4fn3Aq','2023-11-16 12:08:58.054722',NULL),(62,'2023-11-16 15:30:32.293866',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hanmo','한모','$2a$10$nZ1l1FwazqDlmQis39tVo.3wxvOKvKydoSsmHlGk2Se8L0QtSfrdG','2023-11-16 15:30:32.293875',NULL),(63,'2023-11-16 15:31:45.891507',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hanmo2','한모','$2a$10$TRqwxCofDevF6hCLCobQwOFlzoRWe0ja0CHv8NMztDrXcTmtl0dxW','2023-11-16 15:31:45.891517',NULL),(64,'2023-11-16 15:32:45.276633',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hi','hihi','$2a$10$0HUMBQIUp7C5shA8rJpVe.9RcD.SByTk1IMAYQqXh8bsf81yy8qba','2023-11-16 15:32:45.276649',NULL),(65,'2023-11-16 15:38:30.133104',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hyoin6','효인6','$2a$10$6tNjMj.BM2VAkf.w4hW/V.gwmqFRue8RahM6jbPQH9c9eNi3o6Nq.','2023-11-16 15:38:30.133114',NULL),(66,'2023-11-16 15:41:57.166530',NULL,'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg','hanmo3','한모','$2a$10$qBKvj.y5PP/wnp25iO7bT.zd5njJ0tJ6mwpyfl/b0qSu.JBs3Yc1i','2023-11-16 15:41:57.166538',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `message_url` varchar(255) DEFAULT NULL,
  `chat_room_id` bigint DEFAULT NULL,
  `sender_member_id` bigint DEFAULT NULL,
  `is_read` int DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK5i8ac68n051032d9ga7gg6i85` (`chat_room_id`),
  KEY `FKjusoilghlrkxd2gam6ly30fdd` (`sender_member_id`),
  CONSTRAINT `FK5i8ac68n051032d9ga7gg6i85` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`chat_room_id`),
  CONSTRAINT `FKjusoilghlrkxd2gam6ly30fdd` FOREIGN KEY (`sender_member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'2023-11-07 11:28:39.033504','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/1e58a2ff-611c-48fe-b573-1cd23684880a',1,15,1),(2,'2023-11-07 11:34:17.998591','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/57d19228-e59f-48a7-ab9c-8390a615d0cd',1,15,1),(3,'2023-11-07 11:39:50.638720','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/5ff2c4cb-d94f-4558-827c-a93eb0d94f6a',1,15,NULL),(4,'2023-11-07 11:39:51.939886','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/3f85c8eb-2408-4e90-818a-6bca5c35376a',1,15,NULL),(5,'2023-11-07 11:40:29.238901','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/a6e2d3d1-3553-4c0e-966e-d449264e7281',2,15,1),(6,'2023-11-07 11:40:31.362551','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/c6441f82-274a-4ade-9ce5-ba3960dd69f8',2,15,1),(7,'2023-11-07 11:42:02.552451','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin3/fc69a10d-7294-446a-aca0-9487a5dd5b1a',2,17,1),(8,'2023-11-07 11:42:04.002116','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin3/b29670b0-1bb9-49d0-8f2d-2720d6545cbb',2,17,1),(9,'2023-11-07 12:49:56.807729','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin3/a8b76089-0861-4026-8851-7cc4f6c39e7b',2,17,1),(10,'2023-11-10 15:02:07.681926','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/6ca26c0a-b1ff-4f7f-9a0a-4a231bc43e28',6,19,1),(11,'2023-11-10 15:02:32.557072','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/482533ec-760b-44fc-a85c-b0231dcb1aa8',6,19,1),(12,'2023-11-10 15:02:33.597961','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/02eede1f-40c6-4165-942b-3a488a61e9b2',6,19,1),(13,'2023-11-10 16:14:26.579232','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/277c3927-6461-43c0-891c-424bad9cb3de',6,19,1),(14,'2023-11-10 16:15:02.494486','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/4e326a38-d707-4740-a268-08bf6b413d7c',6,19,1),(15,'2023-11-10 16:15:07.074745','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/bf1399d2-057f-49d3-99a0-c35379b02820',6,19,1),(16,'2023-11-10 16:15:07.932708','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/a467da68-fd06-44e5-9aa5-58390660a11f',6,19,1),(17,'2023-11-10 16:38:20.646055','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/ed245d55-7c3f-4655-9e32-85be46dfc4ba',6,19,1),(18,'2023-11-10 16:38:21.886964','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/2950e2d2-2b14-42b9-850e-a9ef9346c6c2',6,19,1),(19,'2023-11-10 16:38:22.781473','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/92b3ac40-5ad3-46f4-8d08-5487f8e456b9',6,19,1),(20,'2023-11-10 16:38:23.611187','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/4bb6d816-03fb-4ed5-b996-0d557e97d481',6,19,1),(21,'2023-11-13 15:38:29.031007','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/4708756b-931b-4839-bfa8-06dce6c7ddd6',6,19,1),(22,'2023-11-13 15:39:10.270662','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/fa7ed976-a80f-4010-b2d0-42c16290077b',6,19,1),(23,'2023-11-13 16:43:14.952867','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/286a7892-be43-4c84-8c23-664abc256a2a',6,19,1),(24,'2023-11-13 16:43:59.697595','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/55194e60-1147-4dc4-b2ab-2214402c0869',6,19,1),(25,'2023-11-13 17:40:37.033120','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/39815b61-696b-4ae0-bcb2-027a0e0dbf1c',6,19,1),(26,'2023-11-13 17:45:33.233923','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/6fd3d89a-ff76-4192-add1-9f54422149b7',6,19,1),(27,'2023-11-13 17:46:10.242013','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/ff49fbe3-439b-46e1-a1da-de26a41b11d1',6,19,1),(28,'2023-11-14 12:36:35.554892','https://cozytrain.s3.ap-northeast-2.amazonaws.com/123/272789e8-e8f7-4295-a922-ba4827589312',11,19,0),(29,'2023-11-14 16:41:48.765251','https://cozytrain.s3.ap-northeast-2.amazonaws.com/juwon/972c890b-83e7-4246-8024-4d55e3a5830a',13,12,1),(30,'2023-11-14 16:41:50.813202','https://cozytrain.s3.ap-northeast-2.amazonaws.com/harin608/cf9f0db3-eab4-4878-bc0b-1b671ce54680',13,24,1),(31,'2023-11-14 16:42:04.428340','https://cozytrain.s3.ap-northeast-2.amazonaws.com/harin608/1f9228e6-3db8-48ae-9477-c240581a1ca9',13,24,1),(32,'2023-11-14 16:42:21.763816','https://cozytrain.s3.ap-northeast-2.amazonaws.com/juwon/94edc68e-29ad-4aec-800e-25d2dd4255f8',13,12,1),(33,'2023-11-14 16:44:05.640044','https://cozytrain.s3.ap-northeast-2.amazonaws.com/juwon/d7ec7bd0-b66a-4cdc-987d-c3a4e554b524',13,12,0),(34,'2023-11-15 00:11:31.858842','https://cozytrain.s3.ap-northeast-2.amazonaws.com/juwon/a64fa576-74b9-4041-a721-0c7dc33519da',6,12,1),(35,'2023-11-15 00:41:38.392424','https://cozytrain.s3.ap-northeast-2.amazonaws.com/juwon/24a3ee22-4266-422f-9756-66054e34c658',6,12,1),(36,'2023-11-15 00:43:53.308442','https://cozytrain.s3.ap-northeast-2.amazonaws.com/juwon/1d06e0a7-2715-4093-a5fc-29bbe3380c23',6,12,1),(37,'2023-11-15 00:47:44.805922','https://cozytrain.s3.ap-northeast-2.amazonaws.com/juwon/16446c55-aee0-4f88-975c-4122baa66a50',6,12,1),(38,'2023-11-15 20:49:52.980450','https://cozytrain.s3.ap-northeast-2.amazonaws.com/testaccount1/9f218d7e-5014-4c46-ab9a-fa2aceb399da',6,19,1),(39,'2023-11-16 00:43:16.288787','https://cozytrain.s3.ap-northeast-2.amazonaws.com/wndnjs123/15f2ec02-0d16-406d-afed-4a5fd058bc12',19,41,0),(40,'2023-11-16 00:43:36.236075','https://cozytrain.s3.ap-northeast-2.amazonaws.com/wndnjs123/f68458f8-65ed-49e8-9384-609355a76177',19,41,0),(41,'2023-11-16 09:09:40.102447','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/5391c8b3-e951-4c74-9cf1-adaac2147fb9',21,15,1),(42,'2023-11-16 09:10:36.033696','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin2/875089e1-3879-42b0-b97f-bfc253770983',21,16,1),(43,'2023-11-16 09:11:05.505408','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hyoin/abaad4e3-f471-43fa-b40a-13d95b24a8f4',21,15,0),(44,'2023-11-16 11:08:17.198353','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hongTest/74786bdb-95ff-4dca-80ab-0d8516a6b0af',23,59,1),(45,'2023-11-16 11:13:05.569500','https://cozytrain.s3.ap-northeast-2.amazonaws.com/hongTest/2a9d4b9c-0cd1-44bb-9032-e5bbe3074bf3',23,59,1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_id` bigint NOT NULL AUTO_INCREMENT,
  `sleep_report_date` date DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `caffeine` int NOT NULL,
  `move_dist` int NOT NULL,
  PRIMARY KEY (`report_id`),
  KEY `FKel7y5wyx42a6njav1dbe2torl` (`member_id`),
  CONSTRAINT `FKel7y5wyx42a6njav1dbe2torl` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'2023-11-07','2023-11-07 17:26:59.967674',12,170,157),(2,'2023-11-10','2023-11-10 16:02:12.737174',12,0,157),(3,'2023-11-13','2023-11-13 09:16:28.734977',12,0,157),(6,'2023-11-13','2023-11-13 23:40:25.684739',24,20,0),(17,'2023-11-14','2023-11-14 02:17:10.889866',24,0,0),(18,'2023-11-14','2023-11-14 09:38:31.606159',19,0,114),(19,'2023-11-14','2023-11-14 10:39:49.581784',12,0,114),(24,'2023-11-15','2023-11-15 08:59:55.510674',24,0,0),(25,'2023-11-15','2023-11-15 12:26:42.384615',19,0,119),(26,'2023-11-11','2023-11-15 16:45:10.142427',12,0,119),(27,'2023-11-09','2023-11-15 16:58:34.209266',12,0,119),(28,'2023-11-06','2023-11-15 16:59:45.464201',12,0,119),(29,'2023-11-05','2023-11-15 17:01:45.535260',12,0,40),(32,'2023-11-15','2023-11-16 10:12:16.893703',12,170,40),(33,'2023-11-16','2023-11-16 10:15:20.516412',12,0,157),(34,'2023-11-16','2023-11-16 16:48:01.919003',24,0,40);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sleep_stage`
--

DROP TABLE IF EXISTS `sleep_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sleep_stage` (
  `sleep_stage_id` bigint NOT NULL AUTO_INCREMENT,
  `end_time` datetime(6) NOT NULL,
  `stage` int NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `health_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sleep_stage_id`),
  KEY `FKoo8935vu65au01ak2esldhbmi` (`health_id`),
  CONSTRAINT `FKoo8935vu65au01ak2esldhbmi` FOREIGN KEY (`health_id`) REFERENCES `health` (`health_id`)
) ENGINE=InnoDB AUTO_INCREMENT=811 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sleep_stage`
--

LOCK TABLES `sleep_stage` WRITE;
/*!40000 ALTER TABLE `sleep_stage` DISABLE KEYS */;
INSERT INTO `sleep_stage` VALUES (1,'2023-11-06 21:56:06.729000',4,'2023-11-06 20:56:06.729000',1),(2,'2023-11-06 23:21:06.729000',5,'2023-11-06 21:56:06.729000',1),(3,'2023-11-07 01:58:06.729000',2,'2023-11-07 00:23:06.729000',1),(4,'2023-11-07 04:37:06.729000',5,'2023-11-07 02:55:06.729000',1),(5,'2023-11-07 06:13:06.729000',4,'2023-11-07 04:37:06.729000',1),(6,'2023-11-07 08:15:06.729000',2,'2023-11-07 07:03:06.729000',1),(7,'2023-11-06 21:56:06.729000',4,'2023-11-06 20:56:06.729000',2),(8,'2023-11-06 23:21:06.729000',5,'2023-11-06 21:56:06.729000',2),(9,'2023-11-07 01:58:06.729000',2,'2023-11-07 00:23:06.729000',2),(10,'2023-11-07 04:37:06.729000',5,'2023-11-07 02:55:06.729000',2),(11,'2023-11-07 06:13:06.729000',4,'2023-11-07 04:37:06.729000',2),(12,'2023-11-07 08:15:06.729000',2,'2023-11-07 07:03:06.729000',2),(13,'2023-11-12 21:56:06.729000',4,'2023-11-12 20:56:06.729000',3),(14,'2023-11-12 23:21:06.729000',5,'2023-11-12 21:56:06.729000',3),(15,'2023-11-13 01:58:06.729000',2,'2023-11-13 00:23:06.729000',3),(16,'2023-11-13 04:37:06.729000',5,'2023-11-13 02:55:06.729000',3),(17,'2023-11-13 06:13:06.729000',4,'2023-11-13 04:37:06.729000',3),(18,'2023-11-13 08:15:06.729000',2,'2023-11-13 07:03:06.729000',3),(26,'2023-11-13 01:29:00.000000',0,'2023-11-13 01:21:00.000000',17),(27,'2023-11-13 01:29:00.000000',4,'2023-11-13 01:21:00.000000',17),(28,'2023-11-13 01:50:00.000000',0,'2023-11-13 01:29:00.000000',17),(29,'2023-11-13 01:50:00.000000',5,'2023-11-13 01:29:00.000000',17),(30,'2023-11-13 01:52:00.000000',0,'2023-11-13 01:50:00.000000',17),(31,'2023-11-13 01:52:00.000000',1,'2023-11-13 01:50:00.000000',17),(32,'2023-11-13 01:54:00.000000',0,'2023-11-13 01:52:00.000000',17),(33,'2023-11-13 01:54:00.000000',4,'2023-11-13 01:52:00.000000',17),(34,'2023-11-13 01:55:00.000000',0,'2023-11-13 01:54:00.000000',17),(35,'2023-11-13 01:55:00.000000',1,'2023-11-13 01:54:00.000000',17),(36,'2023-11-13 02:17:00.000000',0,'2023-11-13 01:55:00.000000',17),(37,'2023-11-13 02:17:00.000000',4,'2023-11-13 01:55:00.000000',17),(38,'2023-11-13 02:31:00.000000',0,'2023-11-13 02:17:00.000000',17),(39,'2023-11-13 02:31:00.000000',5,'2023-11-13 02:17:00.000000',17),(40,'2023-11-13 02:38:00.000000',0,'2023-11-13 02:31:00.000000',17),(41,'2023-11-13 02:38:00.000000',4,'2023-11-13 02:31:00.000000',17),(42,'2023-11-13 02:49:00.000000',0,'2023-11-13 02:38:00.000000',17),(43,'2023-11-13 02:49:00.000000',5,'2023-11-13 02:38:00.000000',17),(44,'2023-11-13 02:59:00.000000',0,'2023-11-13 02:49:00.000000',17),(45,'2023-11-13 02:59:00.000000',4,'2023-11-13 02:49:00.000000',17),(46,'2023-11-13 03:04:00.000000',0,'2023-11-13 02:59:00.000000',17),(47,'2023-11-13 03:04:00.000000',5,'2023-11-13 02:59:00.000000',17),(48,'2023-11-13 03:05:00.000000',0,'2023-11-13 03:04:00.000000',17),(49,'2023-11-13 03:05:00.000000',1,'2023-11-13 03:04:00.000000',17),(50,'2023-11-13 03:23:00.000000',0,'2023-11-13 03:05:00.000000',17),(51,'2023-11-13 03:23:00.000000',4,'2023-11-13 03:05:00.000000',17),(52,'2023-11-13 03:44:00.000000',0,'2023-11-13 03:23:00.000000',17),(53,'2023-11-13 03:44:00.000000',6,'2023-11-13 03:23:00.000000',17),(54,'2023-11-13 03:45:00.000000',0,'2023-11-13 03:44:00.000000',17),(55,'2023-11-13 03:45:00.000000',1,'2023-11-13 03:44:00.000000',17),(56,'2023-11-13 04:00:00.000000',0,'2023-11-13 03:45:00.000000',17),(57,'2023-11-13 04:00:00.000000',4,'2023-11-13 03:45:00.000000',17),(58,'2023-11-13 04:02:00.000000',0,'2023-11-13 04:00:00.000000',17),(59,'2023-11-13 04:02:00.000000',6,'2023-11-13 04:00:00.000000',17),(60,'2023-11-13 04:04:00.000000',0,'2023-11-13 04:02:00.000000',17),(61,'2023-11-13 04:04:00.000000',4,'2023-11-13 04:02:00.000000',17),(62,'2023-11-13 04:17:00.000000',0,'2023-11-13 04:04:00.000000',17),(63,'2023-11-13 04:17:00.000000',6,'2023-11-13 04:04:00.000000',17),(64,'2023-11-13 04:44:00.000000',0,'2023-11-13 04:17:00.000000',17),(65,'2023-11-13 04:44:00.000000',4,'2023-11-13 04:17:00.000000',17),(66,'2023-11-13 05:05:00.000000',0,'2023-11-13 04:44:00.000000',17),(67,'2023-11-13 05:05:00.000000',5,'2023-11-13 04:44:00.000000',17),(68,'2023-11-13 05:07:00.000000',0,'2023-11-13 05:05:00.000000',17),(69,'2023-11-13 05:07:00.000000',1,'2023-11-13 05:05:00.000000',17),(70,'2023-11-13 05:14:00.000000',0,'2023-11-13 05:07:00.000000',17),(71,'2023-11-13 05:14:00.000000',4,'2023-11-13 05:07:00.000000',17),(72,'2023-11-13 05:15:00.000000',0,'2023-11-13 05:14:00.000000',17),(73,'2023-11-13 05:15:00.000000',1,'2023-11-13 05:14:00.000000',17),(74,'2023-11-13 05:23:00.000000',0,'2023-11-13 05:15:00.000000',17),(75,'2023-11-13 05:23:00.000000',4,'2023-11-13 05:15:00.000000',17),(76,'2023-11-13 05:25:00.000000',0,'2023-11-13 05:23:00.000000',17),(77,'2023-11-13 05:25:00.000000',1,'2023-11-13 05:23:00.000000',17),(78,'2023-11-13 05:35:00.000000',0,'2023-11-13 05:25:00.000000',17),(79,'2023-11-13 05:35:00.000000',4,'2023-11-13 05:25:00.000000',17),(80,'2023-11-13 05:36:00.000000',0,'2023-11-13 05:35:00.000000',17),(81,'2023-11-13 05:36:00.000000',1,'2023-11-13 05:35:00.000000',17),(82,'2023-11-13 06:07:00.000000',0,'2023-11-13 05:36:00.000000',17),(83,'2023-11-13 06:07:00.000000',4,'2023-11-13 05:36:00.000000',17),(84,'2023-11-13 06:09:00.000000',0,'2023-11-13 06:07:00.000000',17),(85,'2023-11-13 06:09:00.000000',6,'2023-11-13 06:07:00.000000',17),(86,'2023-11-13 06:10:00.000000',0,'2023-11-13 06:09:00.000000',17),(87,'2023-11-13 06:10:00.000000',4,'2023-11-13 06:09:00.000000',17),(88,'2023-11-13 06:28:00.000000',0,'2023-11-13 06:10:00.000000',17),(89,'2023-11-13 06:28:00.000000',6,'2023-11-13 06:10:00.000000',17),(90,'2023-11-13 06:31:00.000000',0,'2023-11-13 06:28:00.000000',17),(91,'2023-11-13 06:31:00.000000',1,'2023-11-13 06:28:00.000000',17),(92,'2023-11-13 06:32:00.000000',0,'2023-11-13 06:31:00.000000',17),(93,'2023-11-13 06:32:00.000000',6,'2023-11-13 06:31:00.000000',17),(94,'2023-11-13 06:33:00.000000',0,'2023-11-13 06:32:00.000000',17),(95,'2023-11-13 06:33:00.000000',4,'2023-11-13 06:32:00.000000',17),(96,'2023-11-13 06:34:00.000000',0,'2023-11-13 06:33:00.000000',17),(97,'2023-11-13 06:34:00.000000',6,'2023-11-13 06:33:00.000000',17),(98,'2023-11-13 06:35:00.000000',0,'2023-11-13 06:34:00.000000',17),(99,'2023-11-13 06:35:00.000000',4,'2023-11-13 06:34:00.000000',17),(100,'2023-11-13 06:38:00.000000',0,'2023-11-13 06:35:00.000000',17),(101,'2023-11-13 06:38:00.000000',1,'2023-11-13 06:35:00.000000',17),(102,'2023-11-13 06:48:00.000000',0,'2023-11-13 06:38:00.000000',17),(103,'2023-11-13 06:48:00.000000',4,'2023-11-13 06:38:00.000000',17),(104,'2023-11-13 06:57:00.000000',0,'2023-11-13 06:48:00.000000',17),(105,'2023-11-13 06:57:00.000000',1,'2023-11-13 06:48:00.000000',17),(106,'2023-11-13 07:03:00.000000',0,'2023-11-13 06:57:00.000000',17),(107,'2023-11-13 07:03:00.000000',4,'2023-11-13 06:57:00.000000',17),(108,'2023-11-13 07:05:00.000000',0,'2023-11-13 07:03:00.000000',17),(109,'2023-11-13 07:05:00.000000',1,'2023-11-13 07:03:00.000000',17),(110,'2023-11-14 04:10:00.000000',0,'2023-11-14 03:37:00.000000',18),(111,'2023-11-14 04:10:00.000000',4,'2023-11-14 03:37:00.000000',18),(112,'2023-11-14 04:20:00.000000',0,'2023-11-14 04:10:00.000000',18),(113,'2023-11-14 04:20:00.000000',5,'2023-11-14 04:10:00.000000',18),(114,'2023-11-14 04:21:00.000000',0,'2023-11-14 04:20:00.000000',18),(115,'2023-11-14 04:21:00.000000',4,'2023-11-14 04:20:00.000000',18),(116,'2023-11-14 04:26:00.000000',0,'2023-11-14 04:21:00.000000',18),(117,'2023-11-14 04:26:00.000000',6,'2023-11-14 04:21:00.000000',18),(118,'2023-11-14 04:27:00.000000',0,'2023-11-14 04:26:00.000000',18),(119,'2023-11-14 04:27:00.000000',1,'2023-11-14 04:26:00.000000',18),(120,'2023-11-14 04:41:00.000000',0,'2023-11-14 04:27:00.000000',18),(121,'2023-11-14 04:41:00.000000',4,'2023-11-14 04:27:00.000000',18),(122,'2023-11-14 04:59:00.000000',0,'2023-11-14 04:41:00.000000',18),(123,'2023-11-14 04:59:00.000000',5,'2023-11-14 04:41:00.000000',18),(124,'2023-11-14 05:01:00.000000',0,'2023-11-14 04:59:00.000000',18),(125,'2023-11-14 05:01:00.000000',1,'2023-11-14 04:59:00.000000',18),(126,'2023-11-14 05:09:00.000000',0,'2023-11-14 05:01:00.000000',18),(127,'2023-11-14 05:09:00.000000',4,'2023-11-14 05:01:00.000000',18),(128,'2023-11-14 05:11:00.000000',0,'2023-11-14 05:09:00.000000',18),(129,'2023-11-14 05:11:00.000000',1,'2023-11-14 05:09:00.000000',18),(130,'2023-11-14 05:41:00.000000',0,'2023-11-14 05:11:00.000000',18),(131,'2023-11-14 05:41:00.000000',4,'2023-11-14 05:11:00.000000',18),(132,'2023-11-14 05:43:00.000000',0,'2023-11-14 05:41:00.000000',18),(133,'2023-11-14 05:43:00.000000',1,'2023-11-14 05:41:00.000000',18),(134,'2023-11-14 05:45:00.000000',0,'2023-11-14 05:43:00.000000',18),(135,'2023-11-14 05:45:00.000000',4,'2023-11-14 05:43:00.000000',18),(136,'2023-11-14 05:46:00.000000',0,'2023-11-14 05:45:00.000000',18),(137,'2023-11-14 05:46:00.000000',1,'2023-11-14 05:45:00.000000',18),(138,'2023-11-14 06:13:00.000000',0,'2023-11-14 05:46:00.000000',18),(139,'2023-11-14 06:13:00.000000',6,'2023-11-14 05:46:00.000000',18),(140,'2023-11-14 06:46:00.000000',0,'2023-11-14 06:13:00.000000',18),(141,'2023-11-14 06:46:00.000000',4,'2023-11-14 06:13:00.000000',18),(142,'2023-11-14 06:48:00.000000',0,'2023-11-14 06:46:00.000000',18),(143,'2023-11-14 06:48:00.000000',1,'2023-11-14 06:46:00.000000',18),(144,'2023-11-14 07:04:00.000000',0,'2023-11-14 06:48:00.000000',18),(145,'2023-11-14 07:04:00.000000',4,'2023-11-14 06:48:00.000000',18),(146,'2023-11-14 07:05:00.000000',0,'2023-11-14 07:04:00.000000',18),(147,'2023-11-14 07:05:00.000000',6,'2023-11-14 07:04:00.000000',18),(148,'2023-11-14 07:06:00.000000',0,'2023-11-14 07:05:00.000000',18),(149,'2023-11-14 07:06:00.000000',1,'2023-11-14 07:05:00.000000',18),(150,'2023-11-14 07:07:00.000000',0,'2023-11-14 07:06:00.000000',18),(151,'2023-11-14 07:07:00.000000',4,'2023-11-14 07:06:00.000000',18),(152,'2023-11-14 07:08:00.000000',0,'2023-11-14 07:07:00.000000',18),(153,'2023-11-14 07:08:00.000000',6,'2023-11-14 07:07:00.000000',18),(154,'2023-11-14 07:10:00.000000',0,'2023-11-14 07:08:00.000000',18),(155,'2023-11-14 07:10:00.000000',1,'2023-11-14 07:08:00.000000',18),(156,'2023-11-14 04:10:00.000000',0,'2023-11-14 03:37:00.000000',19),(157,'2023-11-14 04:10:00.000000',4,'2023-11-14 03:37:00.000000',19),(158,'2023-11-14 04:20:00.000000',0,'2023-11-14 04:10:00.000000',19),(159,'2023-11-14 04:20:00.000000',5,'2023-11-14 04:10:00.000000',19),(160,'2023-11-14 04:21:00.000000',0,'2023-11-14 04:20:00.000000',19),(161,'2023-11-14 04:21:00.000000',4,'2023-11-14 04:20:00.000000',19),(162,'2023-11-14 04:26:00.000000',0,'2023-11-14 04:21:00.000000',19),(163,'2023-11-14 04:26:00.000000',6,'2023-11-14 04:21:00.000000',19),(164,'2023-11-14 04:27:00.000000',0,'2023-11-14 04:26:00.000000',19),(165,'2023-11-14 04:27:00.000000',1,'2023-11-14 04:26:00.000000',19),(166,'2023-11-14 04:41:00.000000',0,'2023-11-14 04:27:00.000000',19),(167,'2023-11-14 04:41:00.000000',4,'2023-11-14 04:27:00.000000',19),(168,'2023-11-14 04:59:00.000000',0,'2023-11-14 04:41:00.000000',19),(169,'2023-11-14 04:59:00.000000',5,'2023-11-14 04:41:00.000000',19),(170,'2023-11-14 05:01:00.000000',0,'2023-11-14 04:59:00.000000',19),(171,'2023-11-14 05:01:00.000000',1,'2023-11-14 04:59:00.000000',19),(172,'2023-11-14 05:09:00.000000',0,'2023-11-14 05:01:00.000000',19),(173,'2023-11-14 05:09:00.000000',4,'2023-11-14 05:01:00.000000',19),(174,'2023-11-14 05:11:00.000000',0,'2023-11-14 05:09:00.000000',19),(175,'2023-11-14 05:11:00.000000',1,'2023-11-14 05:09:00.000000',19),(176,'2023-11-14 05:41:00.000000',0,'2023-11-14 05:11:00.000000',19),(177,'2023-11-14 05:41:00.000000',4,'2023-11-14 05:11:00.000000',19),(178,'2023-11-14 05:43:00.000000',0,'2023-11-14 05:41:00.000000',19),(179,'2023-11-14 05:43:00.000000',1,'2023-11-14 05:41:00.000000',19),(180,'2023-11-14 05:45:00.000000',0,'2023-11-14 05:43:00.000000',19),(181,'2023-11-14 05:45:00.000000',4,'2023-11-14 05:43:00.000000',19),(182,'2023-11-14 05:46:00.000000',0,'2023-11-14 05:45:00.000000',19),(183,'2023-11-14 05:46:00.000000',1,'2023-11-14 05:45:00.000000',19),(184,'2023-11-14 06:13:00.000000',0,'2023-11-14 05:46:00.000000',19),(185,'2023-11-14 06:13:00.000000',6,'2023-11-14 05:46:00.000000',19),(186,'2023-11-14 06:46:00.000000',0,'2023-11-14 06:13:00.000000',19),(187,'2023-11-14 06:46:00.000000',4,'2023-11-14 06:13:00.000000',19),(188,'2023-11-14 06:48:00.000000',0,'2023-11-14 06:46:00.000000',19),(189,'2023-11-14 06:48:00.000000',1,'2023-11-14 06:46:00.000000',19),(190,'2023-11-14 07:04:00.000000',0,'2023-11-14 06:48:00.000000',19),(191,'2023-11-14 07:04:00.000000',4,'2023-11-14 06:48:00.000000',19),(192,'2023-11-14 07:05:00.000000',0,'2023-11-14 07:04:00.000000',19),(193,'2023-11-14 07:05:00.000000',6,'2023-11-14 07:04:00.000000',19),(194,'2023-11-14 07:06:00.000000',0,'2023-11-14 07:05:00.000000',19),(195,'2023-11-14 07:06:00.000000',1,'2023-11-14 07:05:00.000000',19),(196,'2023-11-14 07:07:00.000000',0,'2023-11-14 07:06:00.000000',19),(197,'2023-11-14 07:07:00.000000',4,'2023-11-14 07:06:00.000000',19),(198,'2023-11-14 07:08:00.000000',0,'2023-11-14 07:07:00.000000',19),(199,'2023-11-14 07:08:00.000000',6,'2023-11-14 07:07:00.000000',19),(200,'2023-11-14 07:10:00.000000',0,'2023-11-14 07:08:00.000000',19),(201,'2023-11-14 07:10:00.000000',1,'2023-11-14 07:08:00.000000',19),(228,'2023-11-15 02:54:00.000000',0,'2023-11-15 02:24:00.000000',24),(229,'2023-11-15 02:54:00.000000',4,'2023-11-15 02:24:00.000000',24),(230,'2023-11-15 03:26:00.000000',0,'2023-11-15 02:54:00.000000',24),(231,'2023-11-15 03:26:00.000000',5,'2023-11-15 02:54:00.000000',24),(232,'2023-11-15 03:28:00.000000',0,'2023-11-15 03:26:00.000000',24),(233,'2023-11-15 03:28:00.000000',1,'2023-11-15 03:26:00.000000',24),(234,'2023-11-15 03:34:00.000000',0,'2023-11-15 03:28:00.000000',24),(235,'2023-11-15 03:34:00.000000',4,'2023-11-15 03:28:00.000000',24),(236,'2023-11-15 03:36:00.000000',0,'2023-11-15 03:34:00.000000',24),(237,'2023-11-15 03:36:00.000000',6,'2023-11-15 03:34:00.000000',24),(238,'2023-11-15 03:41:00.000000',0,'2023-11-15 03:36:00.000000',24),(239,'2023-11-15 03:41:00.000000',4,'2023-11-15 03:36:00.000000',24),(240,'2023-11-15 03:42:00.000000',0,'2023-11-15 03:41:00.000000',24),(241,'2023-11-15 03:42:00.000000',6,'2023-11-15 03:41:00.000000',24),(242,'2023-11-15 03:44:00.000000',0,'2023-11-15 03:42:00.000000',24),(243,'2023-11-15 03:44:00.000000',4,'2023-11-15 03:42:00.000000',24),(244,'2023-11-15 03:45:00.000000',0,'2023-11-15 03:44:00.000000',24),(245,'2023-11-15 03:45:00.000000',6,'2023-11-15 03:44:00.000000',24),(246,'2023-11-15 03:47:00.000000',0,'2023-11-15 03:45:00.000000',24),(247,'2023-11-15 03:47:00.000000',4,'2023-11-15 03:45:00.000000',24),(248,'2023-11-15 03:48:00.000000',0,'2023-11-15 03:47:00.000000',24),(249,'2023-11-15 03:48:00.000000',6,'2023-11-15 03:47:00.000000',24),(250,'2023-11-15 03:49:00.000000',0,'2023-11-15 03:48:00.000000',24),(251,'2023-11-15 03:49:00.000000',1,'2023-11-15 03:48:00.000000',24),(252,'2023-11-15 04:05:00.000000',0,'2023-11-15 03:49:00.000000',24),(253,'2023-11-15 04:05:00.000000',4,'2023-11-15 03:49:00.000000',24),(254,'2023-11-15 04:06:00.000000',0,'2023-11-15 04:05:00.000000',24),(255,'2023-11-15 04:06:00.000000',1,'2023-11-15 04:05:00.000000',24),(256,'2023-11-15 04:16:00.000000',0,'2023-11-15 04:06:00.000000',24),(257,'2023-11-15 04:16:00.000000',4,'2023-11-15 04:06:00.000000',24),(258,'2023-11-15 04:17:00.000000',0,'2023-11-15 04:16:00.000000',24),(259,'2023-11-15 04:17:00.000000',1,'2023-11-15 04:16:00.000000',24),(260,'2023-11-15 04:40:00.000000',0,'2023-11-15 04:17:00.000000',24),(261,'2023-11-15 04:40:00.000000',4,'2023-11-15 04:17:00.000000',24),(262,'2023-11-15 04:41:00.000000',0,'2023-11-15 04:40:00.000000',24),(263,'2023-11-15 04:41:00.000000',5,'2023-11-15 04:40:00.000000',24),(264,'2023-11-15 04:43:00.000000',0,'2023-11-15 04:41:00.000000',24),(265,'2023-11-15 04:43:00.000000',1,'2023-11-15 04:41:00.000000',24),(266,'2023-11-15 04:52:00.000000',0,'2023-11-15 04:43:00.000000',24),(267,'2023-11-15 04:52:00.000000',4,'2023-11-15 04:43:00.000000',24),(268,'2023-11-15 05:39:00.000000',0,'2023-11-15 04:52:00.000000',24),(269,'2023-11-15 05:39:00.000000',6,'2023-11-15 04:52:00.000000',24),(270,'2023-11-15 05:40:00.000000',0,'2023-11-15 05:39:00.000000',24),(271,'2023-11-15 05:40:00.000000',4,'2023-11-15 05:39:00.000000',24),(272,'2023-11-15 05:41:00.000000',0,'2023-11-15 05:40:00.000000',24),(273,'2023-11-15 05:41:00.000000',6,'2023-11-15 05:40:00.000000',24),(274,'2023-11-15 05:42:00.000000',0,'2023-11-15 05:41:00.000000',24),(275,'2023-11-15 05:42:00.000000',1,'2023-11-15 05:41:00.000000',24),(276,'2023-11-15 06:26:00.000000',0,'2023-11-15 05:42:00.000000',24),(277,'2023-11-15 06:26:00.000000',4,'2023-11-15 05:42:00.000000',24),(278,'2023-11-15 06:28:00.000000',0,'2023-11-15 06:26:00.000000',24),(279,'2023-11-15 06:28:00.000000',5,'2023-11-15 06:26:00.000000',24),(280,'2023-11-15 06:30:00.000000',0,'2023-11-15 06:28:00.000000',24),(281,'2023-11-15 06:30:00.000000',4,'2023-11-15 06:28:00.000000',24),(282,'2023-11-15 06:31:00.000000',0,'2023-11-15 06:30:00.000000',24),(283,'2023-11-15 06:31:00.000000',1,'2023-11-15 06:30:00.000000',24),(284,'2023-11-15 06:37:00.000000',0,'2023-11-15 06:31:00.000000',24),(285,'2023-11-15 06:37:00.000000',4,'2023-11-15 06:31:00.000000',24),(286,'2023-11-15 06:38:00.000000',0,'2023-11-15 06:37:00.000000',24),(287,'2023-11-15 06:38:00.000000',6,'2023-11-15 06:37:00.000000',24),(288,'2023-11-15 06:39:00.000000',0,'2023-11-15 06:38:00.000000',24),(289,'2023-11-15 06:39:00.000000',1,'2023-11-15 06:38:00.000000',24),(290,'2023-11-15 07:31:00.000000',0,'2023-11-15 06:39:00.000000',24),(291,'2023-11-15 07:31:00.000000',6,'2023-11-15 06:39:00.000000',24),(292,'2023-11-15 07:34:00.000000',0,'2023-11-15 07:31:00.000000',24),(293,'2023-11-15 07:34:00.000000',1,'2023-11-15 07:31:00.000000',24),(294,'2023-11-15 07:39:00.000000',0,'2023-11-15 07:34:00.000000',24),(295,'2023-11-15 07:39:00.000000',6,'2023-11-15 07:34:00.000000',24),(296,'2023-11-15 07:46:00.000000',0,'2023-11-15 07:39:00.000000',24),(297,'2023-11-15 07:46:00.000000',1,'2023-11-15 07:39:00.000000',24),(298,'2023-11-15 07:48:00.000000',0,'2023-11-15 07:46:00.000000',24),(299,'2023-11-15 07:48:00.000000',4,'2023-11-15 07:46:00.000000',24),(300,'2023-11-15 07:53:00.000000',0,'2023-11-15 07:48:00.000000',24),(301,'2023-11-15 07:53:00.000000',1,'2023-11-15 07:48:00.000000',24),(302,'2023-11-15 07:54:00.000000',0,'2023-11-15 07:53:00.000000',24),(303,'2023-11-15 07:54:00.000000',4,'2023-11-15 07:53:00.000000',24),(304,'2023-11-15 07:55:00.000000',0,'2023-11-15 07:54:00.000000',24),(305,'2023-11-15 07:55:00.000000',1,'2023-11-15 07:54:00.000000',24),(306,'2023-11-15 07:59:00.000000',0,'2023-11-15 07:55:00.000000',24),(307,'2023-11-15 07:59:00.000000',4,'2023-11-15 07:55:00.000000',24),(308,'2023-11-15 03:12:00.000000',0,'2023-11-15 02:39:00.000000',25),(309,'2023-11-15 03:12:00.000000',4,'2023-11-15 02:39:00.000000',25),(310,'2023-11-15 03:14:00.000000',0,'2023-11-15 03:12:00.000000',25),(311,'2023-11-15 03:14:00.000000',5,'2023-11-15 03:12:00.000000',25),(312,'2023-11-15 03:15:00.000000',0,'2023-11-15 03:14:00.000000',25),(313,'2023-11-15 03:15:00.000000',4,'2023-11-15 03:14:00.000000',25),(314,'2023-11-15 03:18:00.000000',0,'2023-11-15 03:15:00.000000',25),(315,'2023-11-15 03:18:00.000000',1,'2023-11-15 03:15:00.000000',25),(316,'2023-11-15 03:26:00.000000',0,'2023-11-15 03:18:00.000000',25),(317,'2023-11-15 03:26:00.000000',4,'2023-11-15 03:18:00.000000',25),(318,'2023-11-15 03:27:00.000000',0,'2023-11-15 03:26:00.000000',25),(319,'2023-11-15 03:27:00.000000',1,'2023-11-15 03:26:00.000000',25),(320,'2023-11-15 03:39:00.000000',0,'2023-11-15 03:27:00.000000',25),(321,'2023-11-15 03:39:00.000000',4,'2023-11-15 03:27:00.000000',25),(322,'2023-11-15 03:47:00.000000',0,'2023-11-15 03:39:00.000000',25),(323,'2023-11-15 03:47:00.000000',5,'2023-11-15 03:39:00.000000',25),(324,'2023-11-15 03:49:00.000000',0,'2023-11-15 03:47:00.000000',25),(325,'2023-11-15 03:49:00.000000',4,'2023-11-15 03:47:00.000000',25),(326,'2023-11-15 03:50:00.000000',0,'2023-11-15 03:49:00.000000',25),(327,'2023-11-15 03:50:00.000000',1,'2023-11-15 03:49:00.000000',25),(328,'2023-11-15 04:18:00.000000',0,'2023-11-15 03:50:00.000000',25),(329,'2023-11-15 04:18:00.000000',4,'2023-11-15 03:50:00.000000',25),(330,'2023-11-15 04:36:00.000000',0,'2023-11-15 04:18:00.000000',25),(331,'2023-11-15 04:36:00.000000',5,'2023-11-15 04:18:00.000000',25),(332,'2023-11-15 04:40:00.000000',0,'2023-11-15 04:36:00.000000',25),(333,'2023-11-15 04:40:00.000000',4,'2023-11-15 04:36:00.000000',25),(334,'2023-11-15 04:41:00.000000',0,'2023-11-15 04:40:00.000000',25),(335,'2023-11-15 04:41:00.000000',1,'2023-11-15 04:40:00.000000',25),(336,'2023-11-15 04:45:00.000000',0,'2023-11-15 04:41:00.000000',25),(337,'2023-11-15 04:45:00.000000',4,'2023-11-15 04:41:00.000000',25),(338,'2023-11-15 05:06:00.000000',0,'2023-11-15 04:45:00.000000',25),(339,'2023-11-15 05:06:00.000000',6,'2023-11-15 04:45:00.000000',25),(340,'2023-11-15 05:09:00.000000',0,'2023-11-15 05:06:00.000000',25),(341,'2023-11-15 05:09:00.000000',1,'2023-11-15 05:06:00.000000',25),(342,'2023-11-15 06:05:00.000000',0,'2023-11-15 05:09:00.000000',25),(343,'2023-11-15 06:05:00.000000',4,'2023-11-15 05:09:00.000000',25),(344,'2023-11-15 06:06:00.000000',0,'2023-11-15 06:05:00.000000',25),(345,'2023-11-15 06:06:00.000000',1,'2023-11-15 06:05:00.000000',25),(346,'2023-11-15 06:10:00.000000',0,'2023-11-15 06:06:00.000000',25),(347,'2023-11-15 06:10:00.000000',4,'2023-11-15 06:06:00.000000',25),(348,'2023-11-15 06:43:00.000000',0,'2023-11-15 06:10:00.000000',25),(349,'2023-11-15 06:43:00.000000',6,'2023-11-15 06:10:00.000000',25),(350,'2023-11-15 06:44:00.000000',0,'2023-11-15 06:43:00.000000',25),(351,'2023-11-15 06:44:00.000000',1,'2023-11-15 06:43:00.000000',25),(352,'2023-11-15 07:03:00.000000',0,'2023-11-15 06:44:00.000000',25),(353,'2023-11-15 07:03:00.000000',6,'2023-11-15 06:44:00.000000',25),(354,'2023-11-15 07:05:00.000000',0,'2023-11-15 07:03:00.000000',25),(355,'2023-11-15 07:05:00.000000',4,'2023-11-15 07:03:00.000000',25),(356,'2023-11-15 07:08:00.000000',0,'2023-11-15 07:05:00.000000',25),(357,'2023-11-15 07:08:00.000000',1,'2023-11-15 07:05:00.000000',25),(358,'2023-11-15 07:10:00.000000',0,'2023-11-15 07:08:00.000000',25),(359,'2023-11-15 07:10:00.000000',4,'2023-11-15 07:08:00.000000',25),(360,'2023-11-11 00:34:00.000000',0,'2023-11-11 00:02:00.000000',26),(361,'2023-11-11 00:34:00.000000',4,'2023-11-11 00:02:00.000000',26),(362,'2023-11-11 00:40:00.000000',0,'2023-11-11 00:34:00.000000',26),(363,'2023-11-11 00:40:00.000000',5,'2023-11-11 00:34:00.000000',26),(364,'2023-11-11 00:48:00.000000',0,'2023-11-11 00:40:00.000000',26),(365,'2023-11-11 00:48:00.000000',4,'2023-11-11 00:40:00.000000',26),(366,'2023-11-11 01:09:00.000000',0,'2023-11-11 00:48:00.000000',26),(367,'2023-11-11 01:09:00.000000',5,'2023-11-11 00:48:00.000000',26),(368,'2023-11-11 01:10:00.000000',0,'2023-11-11 01:09:00.000000',26),(369,'2023-11-11 01:10:00.000000',1,'2023-11-11 01:09:00.000000',26),(370,'2023-11-11 01:13:00.000000',0,'2023-11-11 01:10:00.000000',26),(371,'2023-11-11 01:13:00.000000',4,'2023-11-11 01:10:00.000000',26),(372,'2023-11-11 01:14:00.000000',0,'2023-11-11 01:13:00.000000',26),(373,'2023-11-11 01:14:00.000000',1,'2023-11-11 01:13:00.000000',26),(374,'2023-11-11 01:15:00.000000',0,'2023-11-11 01:14:00.000000',26),(375,'2023-11-11 01:15:00.000000',4,'2023-11-11 01:14:00.000000',26),(376,'2023-11-11 01:16:00.000000',0,'2023-11-11 01:15:00.000000',26),(377,'2023-11-11 01:16:00.000000',1,'2023-11-11 01:15:00.000000',26),(378,'2023-11-11 01:27:00.000000',0,'2023-11-11 01:16:00.000000',26),(379,'2023-11-11 01:27:00.000000',4,'2023-11-11 01:16:00.000000',26),(380,'2023-11-11 01:29:00.000000',0,'2023-11-11 01:27:00.000000',26),(381,'2023-11-11 01:29:00.000000',1,'2023-11-11 01:27:00.000000',26),(382,'2023-11-11 01:44:00.000000',0,'2023-11-11 01:29:00.000000',26),(383,'2023-11-11 01:44:00.000000',4,'2023-11-11 01:29:00.000000',26),(384,'2023-11-11 01:58:00.000000',0,'2023-11-11 01:44:00.000000',26),(385,'2023-11-11 01:58:00.000000',5,'2023-11-11 01:44:00.000000',26),(386,'2023-11-11 02:01:00.000000',0,'2023-11-11 01:58:00.000000',26),(387,'2023-11-11 02:01:00.000000',1,'2023-11-11 01:58:00.000000',26),(388,'2023-11-11 02:22:00.000000',0,'2023-11-11 02:01:00.000000',26),(389,'2023-11-11 02:22:00.000000',4,'2023-11-11 02:01:00.000000',26),(390,'2023-11-11 02:25:00.000000',0,'2023-11-11 02:22:00.000000',26),(391,'2023-11-11 02:25:00.000000',5,'2023-11-11 02:22:00.000000',26),(392,'2023-11-11 02:26:00.000000',0,'2023-11-11 02:25:00.000000',26),(393,'2023-11-11 02:26:00.000000',1,'2023-11-11 02:25:00.000000',26),(394,'2023-11-11 02:31:00.000000',0,'2023-11-11 02:26:00.000000',26),(395,'2023-11-11 02:31:00.000000',4,'2023-11-11 02:26:00.000000',26),(396,'2023-11-11 03:00:00.000000',0,'2023-11-11 02:31:00.000000',26),(397,'2023-11-11 03:00:00.000000',6,'2023-11-11 02:31:00.000000',26),(398,'2023-11-11 03:27:00.000000',0,'2023-11-11 03:00:00.000000',26),(399,'2023-11-11 03:27:00.000000',4,'2023-11-11 03:00:00.000000',26),(400,'2023-11-11 03:36:00.000000',0,'2023-11-11 03:27:00.000000',26),(401,'2023-11-11 03:36:00.000000',5,'2023-11-11 03:27:00.000000',26),(402,'2023-11-11 03:37:00.000000',0,'2023-11-11 03:36:00.000000',26),(403,'2023-11-11 03:37:00.000000',1,'2023-11-11 03:36:00.000000',26),(404,'2023-11-11 04:03:00.000000',0,'2023-11-11 03:37:00.000000',26),(405,'2023-11-11 04:03:00.000000',4,'2023-11-11 03:37:00.000000',26),(406,'2023-11-11 04:04:00.000000',0,'2023-11-11 04:03:00.000000',26),(407,'2023-11-11 04:04:00.000000',1,'2023-11-11 04:03:00.000000',26),(408,'2023-11-11 04:18:00.000000',0,'2023-11-11 04:04:00.000000',26),(409,'2023-11-11 04:18:00.000000',4,'2023-11-11 04:04:00.000000',26),(410,'2023-11-11 04:47:00.000000',0,'2023-11-11 04:18:00.000000',26),(411,'2023-11-11 04:47:00.000000',6,'2023-11-11 04:18:00.000000',26),(412,'2023-11-11 04:48:00.000000',0,'2023-11-11 04:47:00.000000',26),(413,'2023-11-11 04:48:00.000000',1,'2023-11-11 04:47:00.000000',26),(414,'2023-11-11 05:40:00.000000',0,'2023-11-11 04:48:00.000000',26),(415,'2023-11-11 05:40:00.000000',4,'2023-11-11 04:48:00.000000',26),(416,'2023-11-11 06:10:00.000000',0,'2023-11-11 05:40:00.000000',26),(417,'2023-11-11 06:10:00.000000',6,'2023-11-11 05:40:00.000000',26),(418,'2023-11-11 06:12:00.000000',0,'2023-11-11 06:10:00.000000',26),(419,'2023-11-11 06:12:00.000000',1,'2023-11-11 06:10:00.000000',26),(420,'2023-11-11 06:21:00.000000',0,'2023-11-11 06:12:00.000000',26),(421,'2023-11-11 06:21:00.000000',4,'2023-11-11 06:12:00.000000',26),(422,'2023-11-11 06:24:00.000000',0,'2023-11-11 06:21:00.000000',26),(423,'2023-11-11 06:24:00.000000',1,'2023-11-11 06:21:00.000000',26),(424,'2023-11-11 06:48:00.000000',0,'2023-11-11 06:24:00.000000',26),(425,'2023-11-11 06:48:00.000000',4,'2023-11-11 06:24:00.000000',26),(426,'2023-11-11 06:54:00.000000',0,'2023-11-11 06:48:00.000000',26),(427,'2023-11-11 06:54:00.000000',5,'2023-11-11 06:48:00.000000',26),(428,'2023-11-11 06:55:00.000000',0,'2023-11-11 06:54:00.000000',26),(429,'2023-11-11 06:55:00.000000',4,'2023-11-11 06:54:00.000000',26),(430,'2023-11-11 06:57:00.000000',0,'2023-11-11 06:55:00.000000',26),(431,'2023-11-11 06:57:00.000000',5,'2023-11-11 06:55:00.000000',26),(432,'2023-11-11 07:04:00.000000',0,'2023-11-11 06:57:00.000000',26),(433,'2023-11-11 07:04:00.000000',4,'2023-11-11 06:57:00.000000',26),(434,'2023-11-11 07:05:00.000000',0,'2023-11-11 07:04:00.000000',26),(435,'2023-11-11 07:05:00.000000',5,'2023-11-11 07:04:00.000000',26),(436,'2023-11-11 07:08:00.000000',0,'2023-11-11 07:05:00.000000',26),(437,'2023-11-11 07:08:00.000000',1,'2023-11-11 07:05:00.000000',26),(438,'2023-11-11 07:09:00.000000',0,'2023-11-11 07:08:00.000000',26),(439,'2023-11-11 07:09:00.000000',4,'2023-11-11 07:08:00.000000',26),(440,'2023-11-11 07:10:00.000000',0,'2023-11-11 07:09:00.000000',26),(441,'2023-11-11 07:10:00.000000',1,'2023-11-11 07:09:00.000000',26),(442,'2023-11-11 07:36:00.000000',0,'2023-11-11 07:10:00.000000',26),(443,'2023-11-11 07:36:00.000000',6,'2023-11-11 07:10:00.000000',26),(444,'2023-11-11 07:37:00.000000',0,'2023-11-11 07:36:00.000000',26),(445,'2023-11-11 07:37:00.000000',1,'2023-11-11 07:36:00.000000',26),(446,'2023-11-11 07:40:00.000000',0,'2023-11-11 07:37:00.000000',26),(447,'2023-11-11 07:40:00.000000',6,'2023-11-11 07:37:00.000000',26),(448,'2023-11-11 07:42:00.000000',0,'2023-11-11 07:40:00.000000',26),(449,'2023-11-11 07:42:00.000000',1,'2023-11-11 07:40:00.000000',26),(450,'2023-11-11 07:50:00.000000',0,'2023-11-11 07:42:00.000000',26),(451,'2023-11-11 07:50:00.000000',4,'2023-11-11 07:42:00.000000',26),(452,'2023-11-11 07:52:00.000000',0,'2023-11-11 07:50:00.000000',26),(453,'2023-11-11 07:52:00.000000',1,'2023-11-11 07:50:00.000000',26),(454,'2023-11-11 00:34:00.000000',0,'2023-11-11 00:02:00.000000',27),(455,'2023-11-11 00:34:00.000000',4,'2023-11-11 00:02:00.000000',27),(456,'2023-11-11 00:40:00.000000',0,'2023-11-11 00:34:00.000000',27),(457,'2023-11-11 00:40:00.000000',5,'2023-11-11 00:34:00.000000',27),(458,'2023-11-11 00:48:00.000000',0,'2023-11-11 00:40:00.000000',27),(459,'2023-11-11 00:48:00.000000',4,'2023-11-11 00:40:00.000000',27),(460,'2023-11-11 01:09:00.000000',0,'2023-11-11 00:48:00.000000',27),(461,'2023-11-11 01:09:00.000000',5,'2023-11-11 00:48:00.000000',27),(462,'2023-11-11 01:10:00.000000',0,'2023-11-11 01:09:00.000000',27),(463,'2023-11-11 01:10:00.000000',1,'2023-11-11 01:09:00.000000',27),(464,'2023-11-11 01:13:00.000000',0,'2023-11-11 01:10:00.000000',27),(465,'2023-11-11 01:13:00.000000',4,'2023-11-11 01:10:00.000000',27),(466,'2023-11-11 01:14:00.000000',0,'2023-11-11 01:13:00.000000',27),(467,'2023-11-11 01:14:00.000000',1,'2023-11-11 01:13:00.000000',27),(468,'2023-11-11 01:15:00.000000',0,'2023-11-11 01:14:00.000000',27),(469,'2023-11-11 01:15:00.000000',4,'2023-11-11 01:14:00.000000',27),(470,'2023-11-11 01:16:00.000000',0,'2023-11-11 01:15:00.000000',27),(471,'2023-11-11 01:16:00.000000',1,'2023-11-11 01:15:00.000000',27),(472,'2023-11-11 01:27:00.000000',0,'2023-11-11 01:16:00.000000',27),(473,'2023-11-11 01:27:00.000000',4,'2023-11-11 01:16:00.000000',27),(474,'2023-11-11 01:29:00.000000',0,'2023-11-11 01:27:00.000000',27),(475,'2023-11-11 01:29:00.000000',1,'2023-11-11 01:27:00.000000',27),(476,'2023-11-11 01:44:00.000000',0,'2023-11-11 01:29:00.000000',27),(477,'2023-11-11 01:44:00.000000',4,'2023-11-11 01:29:00.000000',27),(478,'2023-11-11 01:58:00.000000',0,'2023-11-11 01:44:00.000000',27),(479,'2023-11-11 01:58:00.000000',5,'2023-11-11 01:44:00.000000',27),(480,'2023-11-11 02:01:00.000000',0,'2023-11-11 01:58:00.000000',27),(481,'2023-11-11 02:01:00.000000',1,'2023-11-11 01:58:00.000000',27),(482,'2023-11-11 02:22:00.000000',0,'2023-11-11 02:01:00.000000',27),(483,'2023-11-11 02:22:00.000000',4,'2023-11-11 02:01:00.000000',27),(484,'2023-11-11 02:25:00.000000',0,'2023-11-11 02:22:00.000000',27),(485,'2023-11-11 02:25:00.000000',5,'2023-11-11 02:22:00.000000',27),(486,'2023-11-11 02:26:00.000000',0,'2023-11-11 02:25:00.000000',27),(487,'2023-11-11 02:26:00.000000',1,'2023-11-11 02:25:00.000000',27),(488,'2023-11-11 02:31:00.000000',0,'2023-11-11 02:26:00.000000',27),(489,'2023-11-11 02:31:00.000000',4,'2023-11-11 02:26:00.000000',27),(490,'2023-11-11 03:00:00.000000',0,'2023-11-11 02:31:00.000000',27),(491,'2023-11-11 03:00:00.000000',6,'2023-11-11 02:31:00.000000',27),(492,'2023-11-11 03:27:00.000000',0,'2023-11-11 03:00:00.000000',27),(493,'2023-11-11 03:27:00.000000',4,'2023-11-11 03:00:00.000000',27),(494,'2023-11-11 03:36:00.000000',0,'2023-11-11 03:27:00.000000',27),(495,'2023-11-11 03:36:00.000000',5,'2023-11-11 03:27:00.000000',27),(496,'2023-11-11 03:37:00.000000',0,'2023-11-11 03:36:00.000000',27),(497,'2023-11-11 03:37:00.000000',1,'2023-11-11 03:36:00.000000',27),(498,'2023-11-11 04:03:00.000000',0,'2023-11-11 03:37:00.000000',27),(499,'2023-11-11 04:03:00.000000',4,'2023-11-11 03:37:00.000000',27),(500,'2023-11-11 04:04:00.000000',0,'2023-11-11 04:03:00.000000',27),(501,'2023-11-11 04:04:00.000000',1,'2023-11-11 04:03:00.000000',27),(502,'2023-11-11 04:18:00.000000',0,'2023-11-11 04:04:00.000000',27),(503,'2023-11-11 04:18:00.000000',4,'2023-11-11 04:04:00.000000',27),(504,'2023-11-11 04:47:00.000000',0,'2023-11-11 04:18:00.000000',27),(505,'2023-11-11 04:47:00.000000',6,'2023-11-11 04:18:00.000000',27),(506,'2023-11-11 04:48:00.000000',0,'2023-11-11 04:47:00.000000',27),(507,'2023-11-11 04:48:00.000000',1,'2023-11-11 04:47:00.000000',27),(508,'2023-11-11 05:40:00.000000',0,'2023-11-11 04:48:00.000000',27),(509,'2023-11-11 05:40:00.000000',4,'2023-11-11 04:48:00.000000',27),(510,'2023-11-11 06:10:00.000000',0,'2023-11-11 05:40:00.000000',27),(511,'2023-11-11 06:10:00.000000',6,'2023-11-11 05:40:00.000000',27),(512,'2023-11-11 06:12:00.000000',0,'2023-11-11 06:10:00.000000',27),(513,'2023-11-11 06:12:00.000000',1,'2023-11-11 06:10:00.000000',27),(514,'2023-11-11 06:21:00.000000',0,'2023-11-11 06:12:00.000000',27),(515,'2023-11-11 06:21:00.000000',4,'2023-11-11 06:12:00.000000',27),(516,'2023-11-11 06:24:00.000000',0,'2023-11-11 06:21:00.000000',27),(517,'2023-11-11 06:24:00.000000',1,'2023-11-11 06:21:00.000000',27),(518,'2023-11-11 06:48:00.000000',0,'2023-11-11 06:24:00.000000',27),(519,'2023-11-11 06:48:00.000000',4,'2023-11-11 06:24:00.000000',27),(520,'2023-11-11 06:54:00.000000',0,'2023-11-11 06:48:00.000000',27),(521,'2023-11-11 06:54:00.000000',5,'2023-11-11 06:48:00.000000',27),(522,'2023-11-11 06:55:00.000000',0,'2023-11-11 06:54:00.000000',27),(523,'2023-11-11 06:55:00.000000',4,'2023-11-11 06:54:00.000000',27),(524,'2023-11-11 06:57:00.000000',0,'2023-11-11 06:55:00.000000',27),(525,'2023-11-11 06:57:00.000000',5,'2023-11-11 06:55:00.000000',27),(526,'2023-11-11 07:04:00.000000',0,'2023-11-11 06:57:00.000000',27),(527,'2023-11-11 07:04:00.000000',4,'2023-11-11 06:57:00.000000',27),(528,'2023-11-11 07:05:00.000000',0,'2023-11-11 07:04:00.000000',27),(529,'2023-11-11 07:05:00.000000',5,'2023-11-11 07:04:00.000000',27),(530,'2023-11-11 07:08:00.000000',0,'2023-11-11 07:05:00.000000',27),(531,'2023-11-11 07:08:00.000000',1,'2023-11-11 07:05:00.000000',27),(532,'2023-11-11 07:09:00.000000',0,'2023-11-11 07:08:00.000000',27),(533,'2023-11-11 07:09:00.000000',4,'2023-11-11 07:08:00.000000',27),(534,'2023-11-11 07:10:00.000000',0,'2023-11-11 07:09:00.000000',27),(535,'2023-11-11 07:10:00.000000',1,'2023-11-11 07:09:00.000000',27),(536,'2023-11-11 07:36:00.000000',0,'2023-11-11 07:10:00.000000',27),(537,'2023-11-11 07:36:00.000000',6,'2023-11-11 07:10:00.000000',27),(538,'2023-11-11 07:37:00.000000',0,'2023-11-11 07:36:00.000000',27),(539,'2023-11-11 07:37:00.000000',1,'2023-11-11 07:36:00.000000',27),(540,'2023-11-11 07:40:00.000000',0,'2023-11-11 07:37:00.000000',27),(541,'2023-11-11 07:40:00.000000',6,'2023-11-11 07:37:00.000000',27),(542,'2023-11-11 07:42:00.000000',0,'2023-11-11 07:40:00.000000',27),(543,'2023-11-11 07:42:00.000000',1,'2023-11-11 07:40:00.000000',27),(544,'2023-11-11 07:50:00.000000',0,'2023-11-11 07:42:00.000000',27),(545,'2023-11-11 07:50:00.000000',4,'2023-11-11 07:42:00.000000',27),(546,'2023-11-11 07:52:00.000000',0,'2023-11-11 07:50:00.000000',27),(547,'2023-11-11 07:52:00.000000',1,'2023-11-11 07:50:00.000000',27),(548,'2023-11-11 00:34:00.000000',0,'2023-11-11 00:02:00.000000',28),(549,'2023-11-11 00:34:00.000000',4,'2023-11-11 00:02:00.000000',28),(550,'2023-11-11 00:40:00.000000',0,'2023-11-11 00:34:00.000000',28),(551,'2023-11-11 00:40:00.000000',5,'2023-11-11 00:34:00.000000',28),(552,'2023-11-11 00:48:00.000000',0,'2023-11-11 00:40:00.000000',28),(553,'2023-11-11 00:48:00.000000',4,'2023-11-11 00:40:00.000000',28),(554,'2023-11-11 01:09:00.000000',0,'2023-11-11 00:48:00.000000',28),(555,'2023-11-11 01:09:00.000000',5,'2023-11-11 00:48:00.000000',28),(556,'2023-11-11 01:10:00.000000',0,'2023-11-11 01:09:00.000000',28),(557,'2023-11-11 01:10:00.000000',1,'2023-11-11 01:09:00.000000',28),(558,'2023-11-11 01:13:00.000000',0,'2023-11-11 01:10:00.000000',28),(559,'2023-11-11 01:13:00.000000',4,'2023-11-11 01:10:00.000000',28),(560,'2023-11-11 01:14:00.000000',0,'2023-11-11 01:13:00.000000',28),(561,'2023-11-11 01:14:00.000000',1,'2023-11-11 01:13:00.000000',28),(562,'2023-11-11 01:15:00.000000',0,'2023-11-11 01:14:00.000000',28),(563,'2023-11-11 01:15:00.000000',4,'2023-11-11 01:14:00.000000',28),(564,'2023-11-11 01:16:00.000000',0,'2023-11-11 01:15:00.000000',28),(565,'2023-11-11 01:16:00.000000',1,'2023-11-11 01:15:00.000000',28),(566,'2023-11-11 01:27:00.000000',0,'2023-11-11 01:16:00.000000',28),(567,'2023-11-11 01:27:00.000000',4,'2023-11-11 01:16:00.000000',28),(568,'2023-11-11 01:29:00.000000',0,'2023-11-11 01:27:00.000000',28),(569,'2023-11-11 01:29:00.000000',1,'2023-11-11 01:27:00.000000',28),(570,'2023-11-11 01:44:00.000000',0,'2023-11-11 01:29:00.000000',28),(571,'2023-11-11 01:44:00.000000',4,'2023-11-11 01:29:00.000000',28),(572,'2023-11-11 01:58:00.000000',0,'2023-11-11 01:44:00.000000',28),(573,'2023-11-11 01:58:00.000000',5,'2023-11-11 01:44:00.000000',28),(574,'2023-11-11 02:01:00.000000',0,'2023-11-11 01:58:00.000000',28),(575,'2023-11-11 02:01:00.000000',1,'2023-11-11 01:58:00.000000',28),(576,'2023-11-11 02:22:00.000000',0,'2023-11-11 02:01:00.000000',28),(577,'2023-11-11 02:22:00.000000',4,'2023-11-11 02:01:00.000000',28),(578,'2023-11-11 02:25:00.000000',0,'2023-11-11 02:22:00.000000',28),(579,'2023-11-11 02:25:00.000000',5,'2023-11-11 02:22:00.000000',28),(580,'2023-11-11 02:26:00.000000',0,'2023-11-11 02:25:00.000000',28),(581,'2023-11-11 02:26:00.000000',1,'2023-11-11 02:25:00.000000',28),(582,'2023-11-11 02:31:00.000000',0,'2023-11-11 02:26:00.000000',28),(583,'2023-11-11 02:31:00.000000',4,'2023-11-11 02:26:00.000000',28),(584,'2023-11-11 03:00:00.000000',0,'2023-11-11 02:31:00.000000',28),(585,'2023-11-11 03:00:00.000000',6,'2023-11-11 02:31:00.000000',28),(586,'2023-11-11 03:27:00.000000',0,'2023-11-11 03:00:00.000000',28),(587,'2023-11-11 03:27:00.000000',4,'2023-11-11 03:00:00.000000',28),(588,'2023-11-11 03:36:00.000000',0,'2023-11-11 03:27:00.000000',28),(589,'2023-11-11 03:36:00.000000',5,'2023-11-11 03:27:00.000000',28),(590,'2023-11-11 03:37:00.000000',0,'2023-11-11 03:36:00.000000',28),(591,'2023-11-11 03:37:00.000000',1,'2023-11-11 03:36:00.000000',28),(592,'2023-11-11 04:03:00.000000',0,'2023-11-11 03:37:00.000000',28),(593,'2023-11-11 04:03:00.000000',4,'2023-11-11 03:37:00.000000',28),(594,'2023-11-11 04:04:00.000000',0,'2023-11-11 04:03:00.000000',28),(595,'2023-11-11 04:04:00.000000',1,'2023-11-11 04:03:00.000000',28),(596,'2023-11-11 04:18:00.000000',0,'2023-11-11 04:04:00.000000',28),(597,'2023-11-11 04:18:00.000000',4,'2023-11-11 04:04:00.000000',28),(598,'2023-11-11 04:47:00.000000',0,'2023-11-11 04:18:00.000000',28),(599,'2023-11-11 04:47:00.000000',6,'2023-11-11 04:18:00.000000',28),(600,'2023-11-11 04:48:00.000000',0,'2023-11-11 04:47:00.000000',28),(601,'2023-11-11 04:48:00.000000',1,'2023-11-11 04:47:00.000000',28),(602,'2023-11-11 05:40:00.000000',0,'2023-11-11 04:48:00.000000',28),(603,'2023-11-11 05:40:00.000000',4,'2023-11-11 04:48:00.000000',28),(604,'2023-11-11 06:10:00.000000',0,'2023-11-11 05:40:00.000000',28),(605,'2023-11-11 06:10:00.000000',6,'2023-11-11 05:40:00.000000',28),(606,'2023-11-11 06:12:00.000000',0,'2023-11-11 06:10:00.000000',28),(607,'2023-11-11 06:12:00.000000',1,'2023-11-11 06:10:00.000000',28),(608,'2023-11-11 06:21:00.000000',0,'2023-11-11 06:12:00.000000',28),(609,'2023-11-11 06:21:00.000000',4,'2023-11-11 06:12:00.000000',28),(610,'2023-11-11 06:24:00.000000',0,'2023-11-11 06:21:00.000000',28),(611,'2023-11-11 06:24:00.000000',1,'2023-11-11 06:21:00.000000',28),(612,'2023-11-11 06:48:00.000000',0,'2023-11-11 06:24:00.000000',28),(613,'2023-11-11 06:48:00.000000',4,'2023-11-11 06:24:00.000000',28),(614,'2023-11-11 06:54:00.000000',0,'2023-11-11 06:48:00.000000',28),(615,'2023-11-11 06:54:00.000000',5,'2023-11-11 06:48:00.000000',28),(616,'2023-11-11 06:55:00.000000',0,'2023-11-11 06:54:00.000000',28),(617,'2023-11-11 06:55:00.000000',4,'2023-11-11 06:54:00.000000',28),(618,'2023-11-11 06:57:00.000000',0,'2023-11-11 06:55:00.000000',28),(619,'2023-11-11 06:57:00.000000',5,'2023-11-11 06:55:00.000000',28),(620,'2023-11-11 07:04:00.000000',0,'2023-11-11 06:57:00.000000',28),(621,'2023-11-11 07:04:00.000000',4,'2023-11-11 06:57:00.000000',28),(622,'2023-11-11 07:05:00.000000',0,'2023-11-11 07:04:00.000000',28),(623,'2023-11-11 07:05:00.000000',5,'2023-11-11 07:04:00.000000',28),(624,'2023-11-11 07:08:00.000000',0,'2023-11-11 07:05:00.000000',28),(625,'2023-11-11 07:08:00.000000',1,'2023-11-11 07:05:00.000000',28),(626,'2023-11-11 07:09:00.000000',0,'2023-11-11 07:08:00.000000',28),(627,'2023-11-11 07:09:00.000000',4,'2023-11-11 07:08:00.000000',28),(628,'2023-11-11 07:10:00.000000',0,'2023-11-11 07:09:00.000000',28),(629,'2023-11-11 07:10:00.000000',1,'2023-11-11 07:09:00.000000',28),(630,'2023-11-11 07:36:00.000000',0,'2023-11-11 07:10:00.000000',28),(631,'2023-11-11 07:36:00.000000',6,'2023-11-11 07:10:00.000000',28),(632,'2023-11-11 07:37:00.000000',0,'2023-11-11 07:36:00.000000',28),(633,'2023-11-11 07:37:00.000000',1,'2023-11-11 07:36:00.000000',28),(634,'2023-11-11 07:40:00.000000',0,'2023-11-11 07:37:00.000000',28),(635,'2023-11-11 07:40:00.000000',6,'2023-11-11 07:37:00.000000',28),(636,'2023-11-11 07:42:00.000000',0,'2023-11-11 07:40:00.000000',28),(637,'2023-11-11 07:42:00.000000',1,'2023-11-11 07:40:00.000000',28),(638,'2023-11-11 07:50:00.000000',0,'2023-11-11 07:42:00.000000',28),(639,'2023-11-11 07:50:00.000000',4,'2023-11-11 07:42:00.000000',28),(640,'2023-11-11 07:52:00.000000',0,'2023-11-11 07:50:00.000000',28),(641,'2023-11-11 07:52:00.000000',1,'2023-11-11 07:50:00.000000',28),(642,'2023-11-15 03:12:00.000000',0,'2023-11-15 02:39:00.000000',29),(643,'2023-11-15 03:14:00.000000',0,'2023-11-15 03:12:00.000000',29),(644,'2023-11-15 03:15:00.000000',0,'2023-11-15 03:14:00.000000',29),(645,'2023-11-15 03:18:00.000000',0,'2023-11-15 03:15:00.000000',29),(646,'2023-11-15 03:26:00.000000',0,'2023-11-15 03:18:00.000000',29),(647,'2023-11-15 03:27:00.000000',0,'2023-11-15 03:26:00.000000',29),(648,'2023-11-15 03:39:00.000000',0,'2023-11-15 03:27:00.000000',29),(649,'2023-11-15 03:47:00.000000',0,'2023-11-15 03:39:00.000000',29),(650,'2023-11-15 03:49:00.000000',0,'2023-11-15 03:47:00.000000',29),(651,'2023-11-15 03:50:00.000000',0,'2023-11-15 03:49:00.000000',29),(652,'2023-11-15 04:18:00.000000',0,'2023-11-15 03:50:00.000000',29),(653,'2023-11-15 04:36:00.000000',0,'2023-11-15 04:18:00.000000',29),(654,'2023-11-15 04:40:00.000000',0,'2023-11-15 04:36:00.000000',29),(655,'2023-11-15 04:41:00.000000',0,'2023-11-15 04:40:00.000000',29),(656,'2023-11-15 04:45:00.000000',0,'2023-11-15 04:41:00.000000',29),(657,'2023-11-15 05:06:00.000000',0,'2023-11-15 04:45:00.000000',29),(658,'2023-11-15 05:09:00.000000',0,'2023-11-15 05:06:00.000000',29),(659,'2023-11-15 06:05:00.000000',0,'2023-11-15 05:09:00.000000',29),(660,'2023-11-15 06:06:00.000000',0,'2023-11-15 06:05:00.000000',29),(661,'2023-11-15 06:10:00.000000',0,'2023-11-15 06:06:00.000000',29),(662,'2023-11-15 06:43:00.000000',0,'2023-11-15 06:10:00.000000',29),(663,'2023-11-15 06:44:00.000000',0,'2023-11-15 06:43:00.000000',29),(664,'2023-11-15 07:03:00.000000',0,'2023-11-15 06:44:00.000000',29),(665,'2023-11-15 07:05:00.000000',0,'2023-11-15 07:03:00.000000',29),(666,'2023-11-15 07:08:00.000000',0,'2023-11-15 07:05:00.000000',29),(667,'2023-11-15 07:10:00.000000',0,'2023-11-15 07:08:00.000000',29),(748,'2023-11-16 03:05:00.000000',0,'2023-11-16 02:40:00.000000',32),(749,'2023-11-16 03:07:00.000000',0,'2023-11-16 03:05:00.000000',32),(750,'2023-11-16 03:19:00.000000',0,'2023-11-16 03:07:00.000000',32),(751,'2023-11-16 03:36:00.000000',0,'2023-11-16 03:19:00.000000',32),(752,'2023-11-16 03:43:00.000000',0,'2023-11-16 03:36:00.000000',32),(753,'2023-11-16 03:44:00.000000',0,'2023-11-16 03:43:00.000000',32),(754,'2023-11-16 04:00:00.000000',0,'2023-11-16 03:44:00.000000',32),(755,'2023-11-16 04:47:00.000000',0,'2023-11-16 04:00:00.000000',32),(756,'2023-11-16 04:52:00.000000',0,'2023-11-16 04:47:00.000000',32),(757,'2023-11-16 04:53:00.000000',0,'2023-11-16 04:52:00.000000',32),(758,'2023-11-16 05:25:00.000000',0,'2023-11-16 04:53:00.000000',32),(759,'2023-11-16 05:29:00.000000',0,'2023-11-16 05:25:00.000000',32),(760,'2023-11-16 05:30:00.000000',0,'2023-11-16 05:29:00.000000',32),(761,'2023-11-16 06:02:00.000000',0,'2023-11-16 05:30:00.000000',32),(762,'2023-11-16 06:03:00.000000',0,'2023-11-16 06:02:00.000000',32),(763,'2023-11-16 06:18:00.000000',0,'2023-11-16 06:03:00.000000',32),(764,'2023-11-16 07:04:00.000000',0,'2023-11-16 06:18:00.000000',32),(765,'2023-11-16 07:05:00.000000',0,'2023-11-16 07:04:00.000000',32),(766,'2023-11-16 07:06:00.000000',0,'2023-11-16 07:05:00.000000',32),(767,'2023-11-16 07:08:00.000000',0,'2023-11-16 07:06:00.000000',32),(768,'2023-11-16 07:11:00.000000',0,'2023-11-16 07:08:00.000000',32),(769,'2023-11-16 07:28:00.000000',0,'2023-11-16 07:11:00.000000',32),(770,'2023-11-16 07:30:00.000000',0,'2023-11-16 07:28:00.000000',32),(771,'2023-11-16 07:34:00.000000',0,'2023-11-16 07:30:00.000000',32),(772,'2023-11-16 07:45:00.000000',0,'2023-11-16 07:34:00.000000',32),(773,'2023-11-16 07:48:00.000000',0,'2023-11-16 07:45:00.000000',32),(774,'2023-11-16 07:50:00.000000',0,'2023-11-16 07:48:00.000000',32),(775,'2023-11-16 07:52:00.000000',0,'2023-11-16 07:50:00.000000',32),(776,'2023-11-12 21:56:06.729000',4,'2023-11-12 20:56:06.729000',33),(777,'2023-11-12 23:21:06.729000',5,'2023-11-12 21:56:06.729000',33),(778,'2023-11-13 01:58:06.729000',2,'2023-11-13 00:23:06.729000',33),(779,'2023-11-13 04:37:06.729000',5,'2023-11-13 02:55:06.729000',33),(780,'2023-11-13 06:13:06.729000',4,'2023-11-13 04:37:06.729000',33),(781,'2023-11-13 08:15:06.729000',2,'2023-11-13 07:03:06.729000',33),(782,'2023-11-16 01:59:00.000000',0,'2023-11-16 01:31:00.000000',34),(783,'2023-11-16 02:32:00.000000',0,'2023-11-16 01:59:00.000000',34),(784,'2023-11-16 02:34:00.000000',0,'2023-11-16 02:32:00.000000',34),(785,'2023-11-16 02:35:00.000000',0,'2023-11-16 02:34:00.000000',34),(786,'2023-11-16 02:36:00.000000',0,'2023-11-16 02:35:00.000000',34),(787,'2023-11-16 02:50:00.000000',0,'2023-11-16 02:36:00.000000',34),(788,'2023-11-16 03:14:00.000000',0,'2023-11-16 02:50:00.000000',34),(789,'2023-11-16 03:50:00.000000',0,'2023-11-16 03:14:00.000000',34),(790,'2023-11-16 03:51:00.000000',0,'2023-11-16 03:50:00.000000',34),(791,'2023-11-16 03:55:00.000000',0,'2023-11-16 03:51:00.000000',34),(792,'2023-11-16 03:56:00.000000',0,'2023-11-16 03:55:00.000000',34),(793,'2023-11-16 03:57:00.000000',0,'2023-11-16 03:56:00.000000',34),(794,'2023-11-16 04:26:00.000000',0,'2023-11-16 03:57:00.000000',34),(795,'2023-11-16 05:09:00.000000',0,'2023-11-16 04:26:00.000000',34),(796,'2023-11-16 05:10:00.000000',0,'2023-11-16 05:09:00.000000',34),(797,'2023-11-16 05:26:00.000000',0,'2023-11-16 05:10:00.000000',34),(798,'2023-11-16 05:27:00.000000',0,'2023-11-16 05:26:00.000000',34),(799,'2023-11-16 05:39:00.000000',0,'2023-11-16 05:27:00.000000',34),(800,'2023-11-16 06:22:00.000000',0,'2023-11-16 05:39:00.000000',34),(801,'2023-11-16 06:23:00.000000',0,'2023-11-16 06:22:00.000000',34),(802,'2023-11-16 06:25:00.000000',0,'2023-11-16 06:23:00.000000',34),(803,'2023-11-16 06:26:00.000000',0,'2023-11-16 06:25:00.000000',34),(804,'2023-11-16 07:14:00.000000',0,'2023-11-16 06:26:00.000000',34),(805,'2023-11-16 07:15:00.000000',0,'2023-11-16 07:14:00.000000',34),(806,'2023-11-16 07:17:00.000000',0,'2023-11-16 07:15:00.000000',34),(807,'2023-11-16 07:43:00.000000',0,'2023-11-16 07:17:00.000000',34),(808,'2023-11-16 07:44:00.000000',0,'2023-11-16 07:43:00.000000',34),(809,'2023-11-16 07:49:00.000000',0,'2023-11-16 07:44:00.000000',34),(810,'2023-11-16 07:51:00.000000',0,'2023-11-16 07:49:00.000000',34);
/*!40000 ALTER TABLE `sleep_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `station_id` bigint NOT NULL AUTO_INCREMENT,
  `continent` varchar(255) DEFAULT NULL,
  `dist` int NOT NULL,
  `region` varchar(255) DEFAULT NULL,
  `region_num` int NOT NULL,
  `country_id` bigint DEFAULT NULL,
  `track_id` bigint DEFAULT NULL,
  `region_kor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`station_id`),
  KEY `FKe8re49nrraqoeemypu2p1r3mc` (`country_id`),
  KEY `FKk4xx9jhcoagcqupdk8v9h748a` (`track_id`),
  CONSTRAINT `FKe8re49nrraqoeemypu2p1r3mc` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`),
  CONSTRAINT `FKk4xx9jhcoagcqupdk8v9h748a` FOREIGN KEY (`track_id`) REFERENCES `track` (`track_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'asia',0,'seoul',1,1,1,'서울'),(2,'asia',0,'busan',2,1,1,'부산'),(3,'asia',0,'jeju',3,1,1,'제주도'),(4,'asia',0,'sapporo',1,2,1,'삿포로'),(5,'asia',0,'tokyo',2,2,1,'도쿄'),(6,'asia',0,'osaka',3,2,1,'오사카'),(7,'asia',0,'phuket',1,3,1,'푸켓'),(8,'asia',0,'bangkok',2,3,1,'방콕'),(9,'asia',0,'chiangmai',3,3,1,'치앙마이'),(10,'asia',0,'harbin',1,4,1,'하얼빈'),(11,'asia',0,'shanghai',2,4,1,'상하이'),(12,'asia',0,'beijing',3,4,1,'베이징');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track` (
  `track_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`track_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train` (
  `train_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `train_cur_dist` int NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `station_id` bigint DEFAULT NULL,
  `track_id` bigint DEFAULT NULL,
  `move_dist` int NOT NULL,
  PRIMARY KEY (`train_id`),
  KEY `FKc2e9p13w5b6qighhrry2vcsnc` (`member_id`),
  KEY `FKc72gg7y1mvxv923yl50cj0xkw` (`station_id`),
  KEY `FK1ueanxbmfanr71f8ykv48b6w9` (`track_id`),
  CONSTRAINT `FK1ueanxbmfanr71f8ykv48b6w9` FOREIGN KEY (`track_id`) REFERENCES `track` (`track_id`),
  CONSTRAINT `FKc2e9p13w5b6qighhrry2vcsnc` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKc72gg7y1mvxv923yl50cj0xkw` FOREIGN KEY (`station_id`) REFERENCES `station` (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (1,'2023-11-06 12:34:18.554168',NULL,'2023-11-06',90,'2023-11-06 17:30:52.985091',1,6,1,0),(2,'2023-11-06 12:34:38.483181',NULL,'2023-11-06',0,'2023-11-06 12:34:38.483181',2,1,1,0),(3,'2023-11-06 13:43:23.309027',NULL,'2023-11-06',0,'2023-11-06 13:43:23.309027',4,1,1,0),(4,'2023-11-06 14:24:04.875811',NULL,'2023-11-06',0,'2023-11-06 14:24:04.875811',5,1,1,0),(5,'2023-11-06 14:36:38.533954',NULL,'2023-11-06',0,'2023-11-06 14:36:38.533954',10,10,1,0),(6,'2023-11-06 14:36:44.027809',NULL,'2023-11-06',0,'2023-11-06 14:36:44.027809',11,1,1,0),(7,'2023-11-06 14:39:48.058116',NULL,'2023-11-06',0,'2023-11-16 16:59:06.834750',12,2,1,40),(8,'2023-11-06 14:49:43.052778',NULL,'2023-11-06',0,'2023-11-06 14:49:43.052778',13,1,1,0),(9,'2023-11-06 16:03:53.397251',NULL,'2023-11-06',0,'2023-11-06 16:03:53.397251',14,1,1,0),(10,'2023-11-06 16:19:59.579273',NULL,'2023-11-06',0,'2023-11-14 17:17:53.267772',15,3,1,300),(11,'2023-11-06 16:42:51.791005',NULL,'2023-11-06',0,'2023-11-06 16:42:51.791005',16,10,1,0),(12,'2023-11-07 10:04:11.616416',NULL,'2023-11-07',0,'2023-11-07 10:04:11.616416',17,3,1,0),(13,'2023-11-07 16:05:07.845099',NULL,'2023-11-07',0,'2023-11-07 16:05:07.845099',18,1,1,0),(14,'2023-11-07 21:20:21.672294',NULL,'2023-11-07',233,'2023-11-15 12:26:42.597688',19,3,1,114),(15,'2023-11-08 12:37:35.509367',NULL,'2023-11-08',0,'2023-11-08 12:37:35.509367',20,1,1,0),(16,'2023-11-09 12:00:32.492183',NULL,'2023-11-09',0,'2023-11-14 17:19:42.420326',21,6,1,300),(17,'2023-11-09 14:52:38.028776',NULL,'2023-11-09',0,'2023-11-09 14:52:38.028776',22,1,1,0),(18,'2023-11-09 14:55:54.037584',NULL,'2023-11-09',0,'2023-11-09 14:55:54.037584',23,1,1,0),(19,'2023-11-09 15:48:29.537254',NULL,'2023-11-09',180,'2023-11-16 16:48:02.117448',24,10,1,144),(20,'2023-11-10 10:39:35.490283',NULL,'2023-11-10',0,'2023-11-10 10:39:35.490283',25,1,1,0),(21,'2023-11-10 15:25:56.869075',NULL,'2023-11-10',40,'2023-11-10 16:03:33.637474',26,1,1,0),(22,'2023-11-10 15:41:10.236361',NULL,'2023-11-10',0,'2023-11-10 15:41:10.236361',27,1,1,0),(23,'2023-11-10 15:52:24.073489',NULL,'2023-11-10',0,'2023-11-10 15:52:24.073489',28,1,1,0),(24,'2023-11-11 13:22:25.369103',NULL,'2023-11-11',0,'2023-11-11 13:22:25.369103',29,1,1,0),(25,'2023-11-11 13:24:24.787278',NULL,'2023-11-11',0,'2023-11-11 13:24:24.787278',30,1,1,0),(26,'2023-11-13 16:17:58.212863',NULL,'2023-11-13',0,'2023-11-13 16:17:58.212863',31,1,1,0),(27,'2023-11-13 17:17:00.540822',NULL,'2023-11-13',0,'2023-11-13 17:17:00.540822',32,1,1,0),(28,'2023-11-13 17:37:56.916892',NULL,'2023-11-13',0,'2023-11-13 17:37:56.916892',33,1,1,0),(29,'2023-11-14 10:51:59.429524',NULL,'2023-11-14',0,'2023-11-14 10:51:59.429524',34,1,1,0),(30,'2023-11-14 11:00:20.077004',NULL,'2023-11-14',0,'2023-11-14 11:00:20.077004',35,1,1,0),(31,'2023-11-14 11:04:28.211994',NULL,'2023-11-14',0,'2023-11-14 11:04:28.211994',36,1,1,0),(32,'2023-11-14 11:22:47.624233',NULL,'2023-11-14',0,'2023-11-14 11:22:47.624233',37,1,1,0),(33,'2023-11-14 12:04:52.553958',NULL,'2023-11-14',0,'2023-11-14 12:04:52.553958',38,1,1,0),(34,'2023-11-14 17:59:32.773028',NULL,'2023-11-14',0,'2023-11-14 17:59:32.773028',39,1,1,0),(35,'2023-11-15 01:10:01.542065',NULL,'2023-11-15',0,'2023-11-15 01:10:01.542065',40,1,1,0),(36,'2023-11-15 09:39:32.163892',NULL,'2023-11-15',56,'2023-11-15 09:52:23.084044',41,2,1,78),(37,'2023-11-15 09:59:05.388485',NULL,'2023-11-15',0,'2023-11-15 09:59:05.388485',42,1,1,0),(38,'2023-11-15 10:00:37.403016',NULL,'2023-11-15',0,'2023-11-15 10:00:37.403016',43,1,1,0),(39,'2023-11-15 10:01:49.811134',NULL,'2023-11-15',0,'2023-11-15 10:01:49.811134',44,1,1,0),(40,'2023-11-15 10:07:34.173362',NULL,'2023-11-15',42,'2023-11-15 10:08:21.905954',45,5,1,207),(41,'2023-11-15 10:38:29.804537',NULL,'2023-11-15',0,'2023-11-15 10:38:29.804537',46,1,1,0),(42,'2023-11-15 18:03:22.326404',NULL,'2023-11-15',0,'2023-11-15 18:03:22.326404',55,1,1,0),(43,'2023-11-16 00:42:07.207348',NULL,'2023-11-16',0,'2023-11-16 00:42:07.207348',56,1,1,0),(44,'2023-11-16 09:18:09.853211',NULL,'2023-11-16',0,'2023-11-16 09:18:09.853211',57,1,1,0),(45,'2023-11-16 10:47:02.760108',NULL,'2023-11-16',0,'2023-11-16 10:47:02.760108',58,1,1,0),(46,'2023-11-16 11:02:47.004484',NULL,'2023-11-16',0,'2023-11-16 11:02:47.004484',59,5,1,0),(47,'2023-11-16 12:00:06.193975',NULL,'2023-11-16',0,'2023-11-16 12:00:06.193975',60,1,1,0),(48,'2023-11-16 12:08:58.060735',NULL,'2023-11-16',0,'2023-11-16 12:08:58.060735',61,1,1,0),(49,'2023-11-16 15:30:32.303352',NULL,'2023-11-16',0,'2023-11-16 15:30:32.303352',62,1,1,0),(50,'2023-11-16 15:31:45.898049',NULL,'2023-11-16',0,'2023-11-16 15:31:45.898049',63,1,1,0),(51,'2023-11-16 15:32:45.310356',NULL,'2023-11-16',0,'2023-11-16 15:32:45.310356',64,1,1,0),(52,'2023-11-16 15:38:30.139566',NULL,'2023-11-16',0,'2023-11-16 15:38:30.139566',65,1,1,0),(53,'2023-11-16 15:41:57.172894',NULL,'2023-11-16',0,'2023-11-16 15:41:57.172894',66,1,1,0);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-16 20:39:13

DROP DATABASE IF EXISTS group5_bookstore;
CREATE DATABASE group5_bookstore;
USE group5_bookstore;

-- MariaDB dump 10.18  Distrib 10.4.17-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: g05_bookstore
-- ------------------------------------------------------
-- Server version	10.4.17-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Derek','Hay','http://mayoclinic.com','Male'),(2,'Garrott','Askie','https://nasa.gov','Non-binary'),(3,'Micki','Videneev','http://soundcloud.com','Bigender'),(4,'Chrotoem','Lukacs','http://mediafire.com','Female'),(5,'Claybourne','Hillborne','https://cnn.com','Agender'),(6,'Marris','Gobbett','https://livejournal.com','Genderqueer'),(7,'Ivor','Zellmer','https://geocities.com','Non-binary'),(8,'Maurie','Iacoboni','https://wisc.edu','Polygender'),(9,'Gipsy','Stoite','http://icq.com','Polygender'),(10,'Swen','Voaden','https://bluehost.com','Male');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(50) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `b_year` year(4) DEFAULT NULL,
  `mrp` int(11) DEFAULT NULL,
  `num_pages` int(11) DEFAULT NULL,
  `lang` varchar(50) DEFAULT NULL,
  `book_description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FK_cat` (`category_id`),
  KEY `FK_author` (`author_id`),
  CONSTRAINT `FK_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `FK_cat` FOREIGN KEY (`category_id`) REFERENCES `book_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'226424542-5',1,'Fisher Inc',2,'Phalacrocorax albiventer',1991,547,842,'Chinese','Switchable asynchronous utilisation'),(2,'782809134-6',2,'Ferry-Dietrich',3,'Cordylus giganteus',2004,2787,586,'Hungarian','Proactive user-facing circuit'),(3,'526316243-X',5,'Spencer, Quigley and Stanton',5,'Ninox superciliaris',2006,1768,830,'Icelandic','Optimized uniform groupware'),(4,'791417617-6',5,'Breitenberg, Goodwin and Quitzon',2,'Geochelone elephantopus',1990,1371,565,'Hungarian','User-friendly asynchronous neural-net'),(5,'532310026-5',2,'Reichel LLC',9,'Gorilla gorilla',2009,1741,374,'Kazakh','Persistent asynchronous conglomeration'),(6,'106780385-8',2,'Connelly, Baumbach and Aufderhar',1,'Creagrus furcatus',2003,3821,578,'Montenegrin','Profit-focused client-driven info-mediaries'),(7,'566332181-0',2,'Cremin, Ondricka and Schumm',7,'Manouria emys',2006,2296,925,'Fijian','Implemented 3rd generation forecast'),(8,'545924208-8',1,'Zemlak Group',9,'Chlamydosaurus kingii',1984,2072,549,'Kyrgyz','Digitized object-oriented focus group'),(9,'893818838-8',5,'Harber, Kozey and Douglas',5,'unavailable',2012,1414,305,'Marathi','Reactive didactic workforce'),(10,'204386047-X',4,'Pouros, Murray and Glover',6,'Macropus robustus',2002,2371,221,'Oriya','Open-source intermediate data-warehouse'),(11,'061028428-2',4,'Bosco, Kub and Kiehn',4,'Canis aureus',2006,722,298,'Tajik','Optimized next generation utilisation'),(12,'191235481-0',5,'Windler-Wehner',6,'Diceros bicornis',2009,2010,794,'Thai','Networked holistic software'),(13,'476984142-6',3,'Pouros, Skiles and Grimes',4,'Acrobates pygmaeus',2002,4404,862,'Nepali','Reactive user-facing productivity'),(14,'936524938-4',4,'Casper Inc',6,'Phalacrocorax niger',1996,240,724,'Telugu','Extended systematic model'),(15,'622089167-8',5,'Blick-Stamm',10,'Sylvilagus floridanus',2000,869,301,'Norwegian','Public-key human-resource system engine'),(16,'744784190-0',5,'Metz and Sons',6,'Echimys chrysurus',2001,2963,442,'Kurdish','Right-sized content-based contingency'),(17,'543309810-9',1,'Hamill-Adams',8,'Amblyrhynchus cristatus',2007,3401,162,'Oriya','Open-source analyzing parallelism'),(18,'651982624-3',2,'Hermann and Sons',3,'Ardea golieth',1993,290,768,'Indonesian','Robust systematic focus group'),(19,'316159158-5',3,'Hayes, Beatty and Bergnaum',8,'Motacilla aguimp',1984,1689,677,'Irish Gaelic','Persevering reciprocal knowledge user'),(20,'986247367-3',1,'Gorczany, Harber and Grimes',3,'Kobus leche robertsi',1993,2210,128,'Yiddish','Cross-group encompassing paradigm'),(21,'990755377-8',1,'Murazik Group',4,'Bison bison',2000,3711,296,'Afrikaans','Assimilated dynamic solution'),(22,'841884138-9',3,'Murray-Padberg',4,'Chloephaga melanoptera',2009,926,142,'Kannada','Automated grid-enabled architecture'),(23,'171323191-3',2,'Cummerata Group',2,'Anser anser',2005,2488,784,'Greek','Reactive stable focus group'),(24,'973849306-4',5,'Gusikowski, Jacobs and Labadie',10,'Cacatua tenuirostris',1990,2149,275,'West Frisian','Expanded multi-state software'),(25,'069938420-6',1,'Hoppe-Mills',1,'Francolinus swainsonii',2009,868,875,'Spanish','Upgradable solution-oriented customer loyalty'),(26,'424759974-7',1,'Zboncak-Blick',5,'Axis axis',2005,2821,747,'Japanese','Seamless incremental extranet'),(27,'719479191-9',2,'Kozey-Graham',8,'Genetta genetta',2011,1494,697,'Mongolian','Ergonomic impactful info-mediaries'),(28,'748800431-0',2,'Pfeffer Group',6,'Macropus fuliginosus',2000,4931,263,'Yiddish','Secured tertiary focus group'),(29,'015858967-X',1,'Schmidt Group',6,'Sula dactylatra',1993,3783,536,'Malagasy','Open-architected optimal framework'),(30,'788542204-6',1,'West, White and Reichert',5,'Threskionis aethiopicus',2003,239,606,'Hiri Motu','Advanced fresh-thinking analyzer'),(500,'4568745695',1,'Penguin',1,'Good Year',2000,500,500,'English','Good Book');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES (1,'application'),(2,'Synergized'),(3,'systemic'),(4,'Mandatory'),(5,'ability'),(6,'Sharable'),(7,'context-sensitive'),(8,'Digitized'),(9,'tangible'),(10,'Synergized');
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Jason Bourne','Kandy','0714331372','nimal1998@gmail.com'),(2,'Kamal','Kelaniya','0714345672','kamal1997@gmail.com'),(3,'Bill Murr','Colombo','0659856589','bill@dc.com'),(4,'Mile Teller','Kandy','0564987456','safs@asfa.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `salary` int(11) NOT NULL DEFAULT 10000,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (6,'Male','Edas',10000,'Ted','Tada'),(7,'Male','California',90000,'Jay','Elec'),(8,'Male','Chicago',10000,'Kanye','West');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `book_id` int(11) NOT NULL,
  `list_price` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `min_qty` int(11) DEFAULT 1,
  PRIMARY KEY (`book_id`),
  CONSTRAINT `FK_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `CHK_qty` CHECK (`qty` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,550,3,10),(2,100,3,10),(3,750,28,10),(5,5000,8,10),(6,500,0,5),(8,1750,0,10);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telnum` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  `ques` varchar(75) NOT NULL,
  `ans` varchar(512) DEFAULT NULL,
  `isManager` tinyint(1) DEFAULT 0,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telnum` (`telnum`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_emp` (`emp_id`),
  CONSTRAINT `FK_emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (20,'0703400702','ted@mail.com','1000:442368e2730b2323d1032c9d7cfcd3c8:f42f193cd577b8662527dacfedc9873f0be9df6e3d73515a784e24249a7c9b997ad25bcb99aae3bb86d8975ef941617ddcea396e970148a31fa1ca8b4afd7e06','What\'s your favorite food?','1000:59c5887cbd87ac53e38a8c9086141a87:1f728d5ec911bdc66b19babf4c3b0996a6b79556b80c4a07b53dd43009f53b1b7c1a4e0ddbdeef215461b71f9e7b59143ed4734d1c7cedfa13c67d88340415da',1,6),(21,'0703400701','jay@mail.com','1000:425daee4a621298c9f1a32124548ebf3:ed09ced6e53b2832ab5908888b2eb2dcf3328e37ca01a552d2135d45142485590f3ce780fccd477b53cf139eb9b93acb8994cc43dc545187d78fd07febf9230b','Who was your childhood hero?','1000:3cbc3f77c446376679a6feae304622a5:dc306d1be70bc0b805d09c39f5913d1e6db2842e18c06e1bf0ebfd81da05d348a21bbcf66eba158409c32cc01da41d1774170adc71c1b0ffe7808de3dcbb02f2',0,7),(22,'0451234567','kanye@mail.com','1000:12218b0a2eab084fd1282cfbf59a9f46:2a382aab03d938c1252d1cf4330980f67ff64d78b9497908feaabd62d650814409d29cfa412d358d9592267635324777d77159fb172fafe924f4f7867189e62b','What\'s your favorite food?','1000:911d50cfd6fdf9a99938605d88f19033:458eede03e0f269dd4999ae5948545c3dffc5524ecafa647f0c5e16f0850621ca519a8dfc61bb12bf163f032253e5ac3cde47259fb8c4c9c38469cea31278c6d',0,8);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `book_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`book_id`,`order_id`),
  KEY `FK_order_del` (`order_id`),
  KEY `FK_Books` (`book_id`),
  CONSTRAINT `FK_Books` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FK_order_del` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,38,2,550),(1,39,1,550),(1,40,2,550),(1,43,1,550),(1,44,4,550),(1,45,1,550),(1,46,1,550),(1,47,1,550),(1,48,2,550),(1,49,1,550),(1,50,1,550),(1,51,1,550),(1,53,2,550),(1,54,2,550),(2,39,2,100),(2,40,3,100),(2,41,2,100),(2,42,3,100),(2,43,2,100),(2,44,1,100),(2,45,1,100),(2,46,1,100),(2,47,1,100),(2,48,1,100),(2,49,1,100),(2,50,2,100),(2,51,1,100),(2,52,1,100),(2,53,1,100),(2,54,2,100),(3,38,1,750),(3,39,2,750),(3,40,2,750),(3,41,2,750),(3,42,3,750),(3,43,3,750),(3,44,2,750),(3,45,1,750),(3,46,6,750),(3,47,1,750),(3,48,2,750),(3,49,1,750),(3,50,4,750),(3,51,1,750),(3,53,3,750),(3,54,2,750),(5,38,1,5000),(5,41,1,5000),(5,45,1,5000),(5,47,1,5000),(5,48,3,5000),(5,54,2,5000),(8,45,1,1750),(8,46,1,1750),(8,47,5,1750);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `order_date` date NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `total_price` int(11) DEFAULT NULL,
  `total_discount` int(11) DEFAULT 0,
  `discount_perc` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`order_id`),
  KEY `FK_Cus` (`customer_id`),
  KEY `FK_Employee` (`employee_id`),
  CONSTRAINT `FK_Cus` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK_Employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (38,1,7,'2021-09-19',4,6165,685,10),(39,2,6,'2021-08-12',5,2138,112,5),(40,3,6,'2021-07-08',7,2697,203,7),(41,1,7,'2021-09-19',5,6030,670,10),(42,2,6,'2020-09-18',6,2295,255,10),(43,1,7,'2020-07-09',6,3000,300,10),(44,2,6,'2020-07-09',7,3420,380,10),(45,4,6,'2021-04-01',5,7335,815,10),(46,2,7,'2021-09-20',9,6210,690,10),(47,1,6,'2020-03-20',9,14393,757,5),(48,1,6,'2021-09-20',8,15930,1770,10),(49,4,6,'2021-02-04',3,1260,140,10),(50,3,6,'2021-09-20',7,3563,187,5),(51,3,6,'2021-09-20',3,1330,70,5),(52,4,6,'2021-09-20',1,90,10,10),(53,4,6,'2021-03-11',6,3105,345,10),(54,2,8,'2021-01-07',4,1530,170,10);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-20 20:00:54

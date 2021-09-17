-- MariaDB dump 10.18  Distrib 10.4.17-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: bookstore
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
  `author_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `book_id` int(11) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'226424542-5',1,'Fisher Inc',1,'Phalacrocorax albiventer',1991,547,842,'Chinese','Switchable asynchronous utilisation'),(2,'782809134-6',2,'Ferry-Dietrich',3,'Cordylus giganteus',2004,2787,586,'Hungarian','Proactive user-facing circuit'),(3,'526316243-X',5,'Spencer, Quigley and Stanton',5,'Ninox superciliaris',2006,1768,830,'Icelandic','Optimized uniform groupware'),(4,'791417617-6',5,'Breitenberg, Goodwin and Quitzon',2,'Geochelone elephantopus',1990,1371,565,'Hungarian','User-friendly asynchronous neural-net'),(5,'532310026-5',2,'Reichel LLC',9,'Gorilla gorilla',2009,1741,374,'Kazakh','Persistent asynchronous conglomeration'),(6,'106780385-8',2,'Connelly, Baumbach and Aufderhar',1,'Creagrus furcatus',2003,3821,578,'Montenegrin','Profit-focused client-driven info-mediaries'),(7,'566332181-0',2,'Cremin, Ondricka and Schumm',7,'Manouria emys',2006,2296,925,'Fijian','Implemented 3rd generation forecast'),(8,'545924208-8',1,'Zemlak Group',9,'Chlamydosaurus kingii',1984,2072,549,'Kyrgyz','Digitized object-oriented focus group'),(9,'893818838-8',5,'Harber, Kozey and Douglas',5,'unavailable',2012,1414,305,'Marathi','Reactive didactic workforce'),(10,'204386047-X',4,'Pouros, Murray and Glover',6,'Macropus robustus',2002,2371,221,'Oriya','Open-source intermediate data-warehouse'),(11,'061028428-2',4,'Bosco, Kub and Kiehn',4,'Canis aureus',2006,722,298,'Tajik','Optimized next generation utilisation'),(12,'191235481-0',5,'Windler-Wehner',6,'Diceros bicornis',2009,2010,794,'Thai','Networked holistic software'),(13,'476984142-6',3,'Pouros, Skiles and Grimes',4,'Acrobates pygmaeus',2002,4404,862,'Nepali','Reactive user-facing productivity'),(14,'936524938-4',4,'Casper Inc',6,'Phalacrocorax niger',1996,240,724,'Telugu','Extended systematic model'),(15,'622089167-8',5,'Blick-Stamm',10,'Sylvilagus floridanus',2000,869,301,'Norwegian','Public-key human-resource system engine'),(16,'744784190-0',5,'Metz and Sons',6,'Echimys chrysurus',2001,2963,442,'Kurdish','Right-sized content-based contingency'),(17,'543309810-9',1,'Hamill-Adams',8,'Amblyrhynchus cristatus',2007,3401,162,'Oriya','Open-source analyzing parallelism'),(18,'651982624-3',2,'Hermann and Sons',3,'Ardea golieth',1993,290,768,'Indonesian','Robust systematic focus group'),(19,'316159158-5',3,'Hayes, Beatty and Bergnaum',8,'Motacilla aguimp',1984,1689,677,'Irish Gaelic','Persevering reciprocal knowledge user'),(20,'986247367-3',1,'Gorczany, Harber and Grimes',3,'Kobus leche robertsi',1993,2210,128,'Yiddish','Cross-group encompassing paradigm'),(21,'990755377-8',1,'Murazik Group',4,'Bison bison',2000,3711,296,'Afrikaans','Assimilated dynamic solution'),(22,'841884138-9',3,'Murray-Padberg',4,'Chloephaga melanoptera',2009,926,142,'Kannada','Automated grid-enabled architecture'),(23,'171323191-3',2,'Cummerata Group',2,'Anser anser',2005,2488,784,'Greek','Reactive stable focus group'),(24,'973849306-4',5,'Gusikowski, Jacobs and Labadie',10,'Cacatua tenuirostris',1990,2149,275,'West Frisian','Expanded multi-state software'),(25,'069938420-6',1,'Hoppe-Mills',1,'Francolinus swainsonii',2009,868,875,'Spanish','Upgradable solution-oriented customer loyalty'),(26,'424759974-7',1,'Zboncak-Blick',5,'Axis axis',2005,2821,747,'Japanese','Seamless incremental extranet'),(27,'719479191-9',2,'Kozey-Graham',8,'Genetta genetta',2011,1494,697,'Mongolian','Ergonomic impactful info-mediaries'),(28,'748800431-0',2,'Pfeffer Group',6,'Macropus fuliginosus',2000,4931,263,'Yiddish','Secured tertiary focus group'),(29,'015858967-X',1,'Schmidt Group',6,'Sula dactylatra',1993,3783,536,'Malagasy','Open-architected optimal framework'),(30,'788542204-6',1,'West, White and Reichert',5,'Threskionis aethiopicus',2003,239,606,'Hiri Motu','Advanced fresh-thinking analyzer');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Zimal','Colombo','0714331372','nimal1998@gmail.com'),(2,'Kamal','Kelaniya','0714345672','kamal1997@gmail.com'),(3,'Bill Murr','Colombo','0659856589','bill@dc.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Erlic','Andre','tchinnick0@gmpg.org','Male','393-303-6346',11000),(2,'Donall','Josovitz','djosovitz1@gov.uk','Genderqueer','268-238-3727',11857),(3,'Yula','Buyers','ybuyers2@yale.edu','Non-binary','670-820-4885',19113),(4,'Gardiner','New','gnew3@google.com.hk','Female','217-233-2290',14890),(5,'Algo','Zed','asgasg@assaf.com','Fale','4569872365',69500),(7,'Eddie','Buck','eboocock6@studiopress.com','Male','353-319-1889',13191),(8,'Ada','Boar','aboar7@cmu.edu','Bigender','859-464-6000',11139),(9,'Norma','Edworthye','nedworthye8@nasa.gov','Genderfluid','884-137-8288',11962),(13,'Nora','Allen','nora@central.com','Female','011111111',15000),(15,'Harry','Allen','henry@gmail.com','Male','874654136',10000),(20,'Eric','asgasg','asgasg','asgasg','64565',465465),(123,'erafsa','safasf','asfaf','asfasf','21312',12312),(213,'asfasf','asfasf','asfasf','asfasf','12312',12312),(596,'Ersag','Esar','aassaf','asfasf','2133',13123),(1512,'er','safsaf','asfasf','asfasf','1412',1242);
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
INSERT INTO `inventory` VALUES (1,500,840,5),(2,100,29,5),(3,50,7,5),(4,600,40,10),(5,1000,8,5);
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
  `fname` varchar(50) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `telnum` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  `ques` varchar(75) NOT NULL,
  `ans` varchar(512) DEFAULT NULL,
  `isManager` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telnum` (`telnum`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `order_detail_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`,`book_id`,`order_id`),
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
INSERT INTO `order_details` VALUES (1,1,10,5,500,2500),(1,1,12,5,500,2500),(1,1,14,5,500,2500),(1,2,15,1,100,100),(1,3,13,1,50,50),(2,3,15,1,50,50),(2,4,12,2,600,1200),(2,4,13,3,600,1800),(2,4,14,2,600,1200),(2,5,10,1,1000,1000),(3,4,15,3,600,1800),(6,1,11,1,500,500),(7,5,11,1,1000,1000),(8,3,11,1,50,50);
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
  `total_discount` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_Cus` (`customer_id`),
  KEY `FK_Emp` (`employee_id`),
  CONSTRAINT `FK_Cus` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK_Emp` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (10,2,1,'2021-09-16',2,3150,350),(11,1,1,'2021-09-16',3,1473,77),(12,3,1,'2021-09-16',2,3700,370),(13,3,1,'2021-09-16',4,1850,185),(14,2,1,'2021-09-16',7,3330,370),(15,3,1,'2021-09-17',5,1755,195);
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

-- Dump completed on 2021-09-17 16:47:33

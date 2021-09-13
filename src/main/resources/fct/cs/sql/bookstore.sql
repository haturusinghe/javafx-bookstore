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
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'226424542-5',4,'Fisher Inc',10,'Phalacrocorax albiventer',1991,547,842,'Chinese','Switchable asynchronous utilisation'),(2,'782809134-6',2,'Ferry-Dietrich',3,'Cordylus giganteus',2004,2787,586,'Hungarian','Proactive user-facing circuit'),(3,'526316243-X',5,'Spencer, Quigley and Stanton',5,'Ninox superciliaris',2006,1768,830,'Icelandic','Optimized uniform groupware'),(4,'791417617-6',5,'Breitenberg, Goodwin and Quitzon',2,'Geochelone elephantopus',1990,1371,565,'Hungarian','User-friendly asynchronous neural-net'),(5,'532310026-5',2,'Reichel LLC',9,'Gorilla gorilla',2009,1741,374,'Kazakh','Persistent asynchronous conglomeration'),(6,'106780385-8',2,'Connelly, Baumbach and Aufderhar',1,'Creagrus furcatus',2003,3821,578,'Montenegrin','Profit-focused client-driven info-mediaries'),(7,'566332181-0',2,'Cremin, Ondricka and Schumm',7,'Manouria emys',2006,2296,925,'Fijian','Implemented 3rd generation forecast'),(8,'545924208-8',1,'Zemlak Group',9,'Chlamydosaurus kingii',1984,2072,549,'Kyrgyz','Digitized object-oriented focus group'),(9,'893818838-8',5,'Harber, Kozey and Douglas',5,'unavailable',2012,1414,305,'Marathi','Reactive didactic workforce'),(10,'204386047-X',4,'Pouros, Murray and Glover',6,'Macropus robustus',2002,2371,221,'Oriya','Open-source intermediate data-warehouse'),(11,'061028428-2',4,'Bosco, Kub and Kiehn',4,'Canis aureus',2006,722,298,'Tajik','Optimized next generation utilisation'),(12,'191235481-0',5,'Windler-Wehner',6,'Diceros bicornis',2009,2010,794,'Thai','Networked holistic software'),(13,'476984142-6',3,'Pouros, Skiles and Grimes',4,'Acrobates pygmaeus',2002,4404,862,'Nepali','Reactive user-facing productivity'),(14,'936524938-4',4,'Casper Inc',6,'Phalacrocorax niger',1996,240,724,'Telugu','Extended systematic model'),(15,'622089167-8',5,'Blick-Stamm',10,'Sylvilagus floridanus',2000,869,301,'Norwegian','Public-key human-resource system engine'),(16,'744784190-0',5,'Metz and Sons',6,'Echimys chrysurus',2001,2963,442,'Kurdish','Right-sized content-based contingency'),(17,'543309810-9',1,'Hamill-Adams',8,'Amblyrhynchus cristatus',2007,3401,162,'Oriya','Open-source analyzing parallelism'),(18,'651982624-3',2,'Hermann and Sons',3,'Ardea golieth',1993,290,768,'Indonesian','Robust systematic focus group'),(19,'316159158-5',3,'Hayes, Beatty and Bergnaum',8,'Motacilla aguimp',1984,1689,677,'Irish Gaelic','Persevering reciprocal knowledge user'),(20,'986247367-3',1,'Gorczany, Harber and Grimes',3,'Kobus leche robertsi',1993,2210,128,'Yiddish','Cross-group encompassing paradigm'),(21,'990755377-8',1,'Murazik Group',4,'Bison bison',2000,3711,296,'Afrikaans','Assimilated dynamic solution'),(22,'841884138-9',3,'Murray-Padberg',4,'Chloephaga melanoptera',2009,926,142,'Kannada','Automated grid-enabled architecture'),(23,'171323191-3',2,'Cummerata Group',2,'Anser anser',2005,2488,784,'Greek','Reactive stable focus group'),(24,'973849306-4',5,'Gusikowski, Jacobs and Labadie',10,'Cacatua tenuirostris',1990,2149,275,'West Frisian','Expanded multi-state software'),(25,'069938420-6',1,'Hoppe-Mills',1,'Francolinus swainsonii',2009,868,875,'Spanish','Upgradable solution-oriented customer loyalty'),(26,'424759974-7',1,'Zboncak-Blick',5,'Axis axis',2005,2821,747,'Japanese','Seamless incremental extranet'),(27,'719479191-9',2,'Kozey-Graham',8,'Genetta genetta',2011,1494,697,'Mongolian','Ergonomic impactful info-mediaries'),(28,'748800431-0',2,'Pfeffer Group',6,'Macropus fuliginosus',2000,4931,263,'Yiddish','Secured tertiary focus group'),(29,'015858967-X',1,'Schmidt Group',6,'Sula dactylatra',1993,3783,536,'Malagasy','Open-architected optimal framework'),(30,'788542204-6',1,'West, White and Reichert',5,'Threskionis aethiopicus',2003,239,606,'Hiri Motu','Advanced fresh-thinking analyzer');
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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) DEFAULT NULL,
  `category_name` text DEFAULT NULL,
  `low_value` int(11) DEFAULT NULL,
  `up_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'less than 15k',0,15000),(2,'greater than 15k',15000,100000),(1,'Less than 25k',0,25000),(1,'greater than 25k',25000,150000);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
INSERT INTO `customer` VALUES (1,'Nimal','Colombo','0714331372','nimal1998@gmail.com'),(2,'Kamal','Kelaniya','0714345672','kamal1997@gmail.com'),(3,'Rayan','Matara','0714331372','nimal1993@gmail.com');
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
INSERT INTO `employee` VALUES (1,'Eric','Andre','tchinnick0@gmpg.org','Male','393-303-6346',11000),(2,'Donall','Josovitz','djosovitz1@gov.uk','Genderqueer','268-238-3727',11857),(3,'Yula','Buyers','ybuyers2@yale.edu','Non-binary','670-820-4885',19113),(4,'Gardiner','New','gnew3@google.com.hk','Female','217-233-2290',14890),(5,'Brandie','Peegrem','bpeegrem4@patch.com','Genderfluid','166-398-8717',18363),(6,'Maurizio','Hadcock','mhadcock5@bloomberg.com','Agender','508-750-4714',19405),(7,'Eddie','Boocock','eboocock6@studiopress.com','Male','353-319-1889',13191),(8,'Ada','Boar','aboar7@cmu.edu','Bigender','859-464-6000',11139),(9,'Norma','Edworthye','nedworthye8@nasa.gov','Genderfluid','884-137-8288',11962),(13,'Nora','Allen','nora@central.com','Female','011111111',15000),(15,'Henry','Allen','henry@gmail.com','Male','874654136',10000);
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
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,865,100,10),(2,200,20,5),(3,100,50,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (6,'Hal','Cad','12345678','hal@dc.com','1000:8fcd49560bf4543e23fcfbdfe08efebd:745be8740e9360d8a9381eefda61b53adb437c7b84127f03d6716b3cde3488aefdd378808067510736a846bbf5f5bc217899fda1bce9a5a2a64ce97676d98180','What\'s your favorite food?','1000:83eb1e8b2490226554d8294a0f06817e:8b2a7e4b3d39f1cf949ecc74a863439e5c205a5cc21f204379862234c72517b6c5385a5ecf543dfe469760897d27b8cdbbb2e180d46a598910eddeb881fe40ec',0),(7,'Cal','Hal','1236969','cal@dc.com','1000:8cd6723447b5eef2623302774bfbe561:083d97676b0bf97355c56919a473b2ead75aa30dabe60f05272e4be41867355c3d8ad1332768bde71ade24747872a60984ce8972bcf513a1f4da21e024902e34','What\'s your pet\'s name?','1000:5446fca3bb82e9d52f75d06487731e8b:287e24dec244da793a2969cbaeac1c09b22814471b31184144498654e5705986d36df048da3ae42cfa506ac8f7c2aff49ba0d6549dc5456c1389a2732d0c2fbc',0),(8,'asfa','asfasf','+4654654','asasf@asfas.casf','1000:6bde4b7e2c1e340bd1cb8b90a6d63b1f:55acf8483f59ac2d042786aaa31dc8874bda2f921779c33be96f76289e5b6314460db21332739eab25f3fe9a49fc84f866f314edf77a438c081abe5f2c4e107c','What\'s your pet\'s name?','1000:6da8fc3228f7d48e4b8552d348c9aa91:90b93c47e0c02b08d278b040bd3fc3433cae5938ce070f7f8631b4171e112092eef9cb2a607bc2a6be75661659a9053952e2e1ec5e75589855dc819af5e7676f',0),(9,'zack','snyder','0696969','zack@dc.com','1000:2ca0f755cbf0eb676a74f56e3f85744b:4f1eb64e3031bc6ece16046cd40dba0753ee9d43db2d24183cb5abba0380ba6a8798cf23c95d9e85b8a5cc5466b294dca4bf3e84951e762928d71e97a5879686','What\'s your favorite food?','1000:109cccc33b95a7340d78e6ac5c94d5d3:fffdf50fce20a4bd3fe37582541f115962dd24c2c7f8cb9bcac38d47ee530784a86bdf3e3edf237b1365bbb4905cbd958afa725c34a4ed0b79e31a9d09af7ca4',0);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  PRIMARY KEY (`book_id`,`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (3,1,5,1466,350),(2,3,4,508,439),(4,4,2,1287,251),(3,8,3,1989,355),(1,9,1,322,403),(1,10,3,133,98),(23,10,2,100,50);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `total_quantity` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `total_discount` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,'2021-06-13',24,2569,773),(2,4,4,'2021-02-23',37,1245,821),(3,2,2,'2021-04-12',16,4746,401),(4,5,4,'2021-05-19',96,2983,187),(5,5,2,'2021-05-25',97,1472,769);
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

-- Dump completed on 2021-09-13  7:02:19

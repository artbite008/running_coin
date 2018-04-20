# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: runningcoin
# Generation Time: 2018-04-20 11:05:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Group`;

CREATE TABLE `Group` (
  `GroupId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `MetaData` varchar(2000) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`GroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Group` WRITE;
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;

INSERT INTO `Group` (`GroupId`, `GroupName`, `MetaData`)
VALUES
	(1,'test','{\"test\": {}}');

/*!40000 ALTER TABLE `Group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Running_Record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Running_Record`;

CREATE TABLE `Running_Record` (
  `RuningRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `UserGroupId` int(11) NOT NULL,
  `Distance` float(3,1) NOT NULL,
  `CreationTime` datetime NOT NULL,
  `LastVotedTime` datetime DEFAULT NULL,
  `Status` int(11) NOT NULL,
  `Score` int(11) DEFAULT NULL,
  `SettledTime` datetime DEFAULT NULL,
  `EarnedCoins` double DEFAULT NULL,
  `Comments` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `Evidence` blob,
  PRIMARY KEY (`RuningRecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Running_Record` WRITE;
/*!40000 ALTER TABLE `Running_Record` DISABLE KEYS */;

INSERT INTO `Running_Record` (`RuningRecordId`, `UserGroupId`, `Distance`, `CreationTime`, `LastVotedTime`, `Status`, `Score`, `SettledTime`, `EarnedCoins`, `Comments`, `Evidence`)
VALUES
	(1,1,5.0,'2018-04-20 16:43:27',NULL,0,NULL,NULL,2,NULL,NULL),
	(2,3,2.0,'2018-04-20 16:52:09',NULL,0,NULL,NULL,1,NULL,NULL),
	(3,3,2.0,'2018-04-20 16:52:55',NULL,0,NULL,NULL,1,NULL,NULL),
	(4,3,4.0,'2018-04-20 16:55:39',NULL,0,NULL,NULL,1,NULL,NULL),
	(5,2,4.0,'2018-04-20 17:01:19',NULL,0,NULL,NULL,1,NULL,NULL),
	(6,2,1.0,'2018-04-20 17:15:15',NULL,0,NULL,NULL,1,NULL,NULL),
	(7,2,1.0,'2018-04-20 17:17:09',NULL,0,NULL,NULL,1,NULL,NULL);

/*!40000 ALTER TABLE `Running_Record` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Target_Distance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Target_Distance`;

CREATE TABLE `Target_Distance` (
  `TargetDistanceId` int(11) NOT NULL AUTO_INCREMENT,
  `UserGroupId` int(11) NOT NULL,
  `CreationTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TargetDistance` float(3,1) NOT NULL,
  PRIMARY KEY (`TargetDistanceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Target_Distance` WRITE;
/*!40000 ALTER TABLE `Target_Distance` DISABLE KEYS */;

INSERT INTO `Target_Distance` (`TargetDistanceId`, `UserGroupId`, `CreationTime`, `TargetDistance`)
VALUES
	(1,5,'2018-04-20 16:23:15',12.0),
	(2,1,'2018-04-20 16:28:57',11.0);

/*!40000 ALTER TABLE `Target_Distance` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table User_Info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User_Info`;

CREATE TABLE `User_Info` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Status` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Role` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Coins` double DEFAULT NULL,
  `Icon` blob,
  `TotalDistance` float(9,1) DEFAULT NULL,
  `MetaData` varchar(2000) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `User_Info` WRITE;
/*!40000 ALTER TABLE `User_Info` DISABLE KEYS */;

INSERT INTO `User_Info` (`UserId`, `UserName`, `Status`, `Role`, `Coins`, `Icon`, `TotalDistance`, `MetaData`)
VALUES
	(122,'james','active','member',0,X'3132322E68746D6C',0.0,NULL),
	(123,'speed','active','member',0,X'3132332E68746D6C',0.0,NULL),
	(124,'seagull','active','member',0,X'3132342E68746D6C',0.0,NULL),
	(125,'bulldog','active','member',0,X'3132352E68746D6C',0.0,NULL),
	(126,'danny','active','member',0,X'3132362E68746D6C',0.0,NULL);

/*!40000 ALTER TABLE `User_Info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table UserGroup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserGroup`;

CREATE TABLE `UserGroup` (
  `UserGroupId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `GroupId` int(11) NOT NULL,
  PRIMARY KEY (`UserGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `UserGroup` WRITE;
/*!40000 ALTER TABLE `UserGroup` DISABLE KEYS */;

INSERT INTO `UserGroup` (`UserGroupId`, `UserId`, `GroupId`)
VALUES
	(1,126,1),
	(2,122,1),
	(3,123,1),
	(4,124,1),
	(5,125,1);

/*!40000 ALTER TABLE `UserGroup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Vote_Record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Vote_Record`;

CREATE TABLE `Vote_Record` (
  `VoteRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `VoteUserGroupId` int(11) NOT NULL,
  `RuningRecordId` int(11) NOT NULL,
  `VotedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedTime` timestamp NULL DEFAULT NULL,
  `Status` int(11) NOT NULL,
  `Score` int(11) NOT NULL,
  `Comments` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`VoteRecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Vote_Record` WRITE;
/*!40000 ALTER TABLE `Vote_Record` DISABLE KEYS */;

INSERT INTO `Vote_Record` (`VoteRecordId`, `VoteUserGroupId`, `RuningRecordId`, `VotedTime`, `UpdatedTime`, `Status`, `Score`, `Comments`)
VALUES
	(1,1,7,'2018-04-20 17:32:35','2018-04-20 17:37:58',1,0,NULL),
	(4,2,1,'2018-04-20 17:46:53',NULL,1,0,NULL);

/*!40000 ALTER TABLE `Vote_Record` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

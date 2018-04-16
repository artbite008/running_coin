CREATE DATABASE `runningcoin` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `User_Group` (
  `GroupId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `MetaData` varchar(2000) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`GroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `User_Info` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupId` int(11) NOT NULL,
  `UserName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Status` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Role` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Coins` int(11) DEFAULT NULL,
  `Icon` blob,
  `TotalDistance` float(9,1) DEFAULT NULL,
  `MetaData` varchar(2000) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `GroupId_idx` (`GroupId`),
  CONSTRAINT `GroupId` FOREIGN KEY (`GroupId`) REFERENCES `User_Group` (`GroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Running_Record` (
  `RuningRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `GroupId` int(11) NOT NULL,
  `Distance` float(3,1) NOT NULL,
  `CreationTime` datetime NOT NULL,
  `LastVotedTime` datetime DEFAULT NULL,
  `Status` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Score` int(11) DEFAULT NULL,
  `SettledTime` datetime DEFAULT NULL,
  `EarnedCoins` int(11) DEFAULT NULL,
  `Comments` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `Evidence` blob,
  PRIMARY KEY (`RuningRecordId`),
  KEY `UserId_idx` (`UserId`),
  KEY `GroupId_idx` (`GroupId`),
  CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `User_Info` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GroupId` FOREIGN KEY (`GroupId`) REFERENCES `User_Group` (`GroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Vote_Record` (
  `VoteRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `VoteUserId` int(11) NOT NULL,
  `GroupId` int(11) NOT NULL,
  `RuningRecordId` int(11) NOT NULL,
  `VotedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CanceledTime` timestamp NULL DEFAULT NULL,
  `Status` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Score` int(11) NOT NULL,
  `Comments` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`VoteRecordId`),
  KEY `VoteUserId_idx` (`VoteUserId`),
  KEY `RuningRecordId_idx` (`RuningRecordId`),
  KEY `GroupId_idx` (`GroupId`),
  CONSTRAINT `GroupId` FOREIGN KEY (`GroupId`) REFERENCES `User_Group` (`GroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RuningRecordId` FOREIGN KEY (`RuningRecordId`) REFERENCES `Running_Record` (`RuningRecordId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `VoteUserId` FOREIGN KEY (`VoteUserId`) REFERENCES `User_Info` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Target_Distance` (
  `TargetDistanceId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `GroupId` int(11) NOT NULL,
  `CreationTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TargetDistance` float(3,1) NOT NULL,
  PRIMARY KEY (`TargetDistanceId`),
  KEY `UserId_idx` (`UserId`),
  KEY `GroupId_idx` (`GroupId`),
  CONSTRAINT `GroupId` FOREIGN KEY (`GroupId`) REFERENCES `User_Group` (`GroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `User_Info` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

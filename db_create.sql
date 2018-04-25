CREATE DATABASE `runningcoin` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `Group` (
  `GroupId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `MetaData` varchar(2000) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`GroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `User_Info` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
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

CREATE TABLE `UserGroup` (
  `UserGroupId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL，
  `GroupId` int(11) NOT NULL，
  PRIMARY KEY (`UserGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Running_Record` (
  `RuningRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `UserGroupId` int(11) NOT NULL,
  `Distance` float(3,1) NOT NULL,
  `CreationTime` datetime NOT NULL,
  `LastVotedTime` datetime DEFAULT NULL,
  `Status` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Score` int(11) DEFAULT NULL,
  `SettledTime` datetime DEFAULT NULL,
  `EarnedCoins` int(11) DEFAULT NULL,
  `Comments` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `Evidence` blob,
  PRIMARY KEY (`RuningRecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Vote_Record` (
  `VoteRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `VoteUserGroupId` int(11) NOT NULL,
  `RuningRecordId` int(11) NOT NULL,
  `VotedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CanceledTime` timestamp NULL DEFAULT NULL,
  `Status` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Score` int(11) NOT NULL,
  `Comments` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`VoteRecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Target_Distance` (
  `TargetDistanceId` int(11) NOT NULL AUTO_INCREMENT,
  `UserGroupId` int(11) NOT NULL,
  `CreationTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TargetDistance` float(3,1) NOT NULL,
  PRIMARY KEY (`TargetDistanceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : corse

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-15 14:45:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for info_building
-- ----------------------------
DROP TABLE IF EXISTS `info_building`;
CREATE TABLE `info_building` (
  `Name` varchar(255) NOT NULL,
  `Data` varchar(255) DEFAULT NULL,
  `Region` varchar(255) DEFAULT NULL,
  `Street` varchar(255) DEFAULT NULL,
  `Developer` varchar(255) DEFAULT NULL,
  `Park` varchar(255) DEFAULT NULL,
  `BuildType` varchar(255) DEFAULT NULL,
  `Ratio` varchar(255) DEFAULT NULL,
  `Density` varchar(255) DEFAULT NULL,
  `UserID` varchar(255) DEFAULT NULL,
  `UIName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Name`),
  KEY `UserID1` (`UserID`),
  KEY `Region` (`Region`),
  KEY `BuildType` (`BuildType`),
  CONSTRAINT `Region` FOREIGN KEY (`Region`) REFERENCES `info_reigon` (`Name`),
  CONSTRAINT `UserID1` FOREIGN KEY (`UserID`) REFERENCES `info_user` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for info_customer
-- ----------------------------
DROP TABLE IF EXISTS `info_customer`;
CREATE TABLE `info_customer` (
  `Name` varchar(255) NOT NULL,
  `Age` int(11) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `UserID` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`Name`),
  KEY `UserID4` (`UserID`),
  CONSTRAINT `UserID4` FOREIGN KEY (`UserID`) REFERENCES `info_user` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for info_house
-- ----------------------------
DROP TABLE IF EXISTS `info_house`;
CREATE TABLE `info_house` (
  `BuildingName` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Floor` varchar(255) DEFAULT NULL,
  `TotalFloor` varchar(255) DEFAULT NULL,
  `Orientation` varchar(255) DEFAULT NULL,
  `Area` varchar(255) DEFAULT NULL,
  `Price` varchar(255) DEFAULT NULL,
  `UserID` varchar(255) DEFAULT NULL,
  `CustomerName` varchar(255) DEFAULT NULL,
  `Status` varchar(255) CHARACTER SET utf8 DEFAULT 'NOT SOLD',
  `HouseType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Address`),
  KEY `BuildName` (`Floor`),
  KEY `Name` (`BuildingName`),
  KEY `UserID` (`UserID`),
  KEY `Address` (`Address`),
  KEY `CustomerName` (`CustomerName`),
  CONSTRAINT `BuildingName` FOREIGN KEY (`BuildingName`) REFERENCES `info_building` (`Name`),
  CONSTRAINT `CustomerName` FOREIGN KEY (`CustomerName`) REFERENCES `info_customer` (`Name`),
  CONSTRAINT `UserID2` FOREIGN KEY (`UserID`) REFERENCES `info_user` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for info_reigon
-- ----------------------------
DROP TABLE IF EXISTS `info_reigon`;
CREATE TABLE `info_reigon` (
  `Name` varchar(255) NOT NULL,
  `UserID` varchar(11) DEFAULT NULL,
  `UIName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Name`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `info_user` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for info_sales
-- ----------------------------
DROP TABLE IF EXISTS `info_sales`;
CREATE TABLE `info_sales` (
  `Commission` varchar(255) DEFAULT NULL,
  `Time` varchar(255) DEFAULT NULL,
  `CustomerName` varchar(255) DEFAULT NULL,
  `UserID` varchar(255) DEFAULT NULL,
  `HouseName` varchar(255) NOT NULL,
  PRIMARY KEY (`HouseName`),
  KEY `UserID3` (`UserID`),
  KEY `CustomerID` (`CustomerName`),
  CONSTRAINT `CusomerID` FOREIGN KEY (`CustomerName`) REFERENCES `info_customer` (`Name`),
  CONSTRAINT `UserID3` FOREIGN KEY (`UserID`) REFERENCES `info_user` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for info_user
-- ----------------------------
DROP TABLE IF EXISTS `info_user`;
CREATE TABLE `info_user` (
  `Name` varchar(255) NOT NULL DEFAULT '',
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE DATABASE IF NOT EXISTS springjta
  CHARACTER SET utf8;

USE springjta;

DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `order`
(
  	ftiny	TINYINT,
  	fsmall	SMALLINT,
  	fint	INT,
  	fbig	BIGINT,
  	fbouble	DOUBLE,
  	fdate	DATETIME,
  	fyear	YEAR(4),
  	fchar	CHAR(2),
  	fvchar	VARCHAR(255),
  	fdec	DECIMAL(10, 0)
);

CREATE TABLE `product`
(
  	ftiny	TINYINT,
  	fsmall	SMALLINT,
  	fint	INT,
  	fbig	BIGINT,
  	fbouble	DOUBLE,
  	fdate	DATETIME,
  	fyear	YEAR(4),
  	fchar	CHAR(2),
  	fvchar	VARCHAR(255),
  	fdec	DECIMAL(10, 0)
);

CREATE TABLE `user`
(
  	ftiny	TINYINT,
  	fsmall	SMALLINT,
  	fint	INT,
  	fbig	BIGINT,
  	fbouble	DOUBLE,
  	fdate	DATETIME,
  	fyear	YEAR(4),
  	fchar	CHAR(2),
  	fvchar	VARCHAR(255),
  	fdec	DECIMAL(10, 0)
);

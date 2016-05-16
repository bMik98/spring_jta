CREATE DATABASE IF NOT EXISTS springjta
  CHARACTER SET utf8;

USE springjta;

DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `order`
(
	id		INT UNSIGNED NOT NULL AUTO_INCREMENT,
  	ftiny	TINYINT,
  	fsmall	SMALLINT,
  	fbig	BIGINT,
  	fdouble	DOUBLE,
  	fdate	DATETIME,
  	fyear	YEAR(4),
  	fchar	CHAR(2),
  	fvchar	VARCHAR(255),
  	fdec	DECIMAL(10, 0),
  	PRIMARY KEY (id)
);

CREATE TABLE `product`
(
	id		INT UNSIGNED NOT NULL AUTO_INCREMENT,
  	ftiny	TINYINT,
  	fsmall	SMALLINT,
  	fbig	BIGINT,
  	fdouble	DOUBLE,
  	fdate	DATETIME,
  	fyear	YEAR(4),
  	fchar	CHAR(2),
  	fvchar	VARCHAR(255),
  	fdec	DECIMAL(10, 0),
  	PRIMARY KEY (id)
);

CREATE TABLE `user`
(
	id		INT UNSIGNED NOT NULL AUTO_INCREMENT,
  	ftiny	TINYINT,
  	fsmall	SMALLINT,
  	fbig	BIGINT,
  	fdouble	DOUBLE,
  	fdate	DATETIME,
  	fyear	YEAR(4),
  	fchar	CHAR(2),
  	fvchar	VARCHAR(255),
  	fdec	DECIMAL(10, 0),
  	PRIMARY KEY (id)
);

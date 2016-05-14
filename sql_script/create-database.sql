CREATE DATABASE IF NOT EXISTS springJTA
  CHARACTER SET utf8;

USE springJTA;

DROP TABLE IF EXISTS order;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  	user_id		INT UNSIGNED NOT NULL AUTO_INCREMENT,
	name 		VARCHAR(40) NOT NULL,
	lastname 	VARCHAR(60) NOT NULL,
	age 		INT,
	phone 		INT,
	email 		VARCHAR(40),
	city 		VARCHAR(30),
	street 		VARCHAR(30),
	house_num	INT,
	apartment_num INT,
  	PRIMARY KEY (user_id)
);

CREATE TABLE order
(
  	order_id	INT UNSIGNED   NOT NULL AUTO_INCREMENT,
	order_num	VARCHAR(40) NOT NULL,
	order_date	DATETIME NOT NULL,
	customer	VARCHAR(100),
	product_num	VARCHAR(40),
	product_name	VARCHAR(100),
	quantity	INT,
  	PRIMARY KEY (order_id)
);

CREATE TABLE product
(
  	product_id	INT UNSIGNED   NOT NULL AUTO_INCREMENT,
	product_num	VARCHAR(40) NOT NULL,
	product_name	VARCHAR(100) NOT NULL,
	product_rest	INT,
  	PRIMARY KEY (order_id)
);


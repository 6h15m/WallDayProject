CREATE database mydb;

USE mydb;

CREATE TABLE Calendar (
	year INT NOT NULL,
	month INT NOT NULL,
	day INT NOT NULL,
	title VARCHAR(128) NOT NULL,
	bgColor VARCHAR(128) NOT NULL,
	textColor VARCHAR(128) NOT NULL
) ;

DESCRIBE Calendar;
set session character_set_client = utf8;
set session character_set_results = utf8;
set session character_set_client = utf8;

show variables like 'c%';

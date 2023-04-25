DROP DATABASE IF EXISTS aurora;
CREATE DATABASE aurora;
USE aurora;
CREATE TABLE potrebiteli(
	id INT,
	name VARCHAR(255),
	pass VARCHAR(255),
	mail VARCHAR(255)
);
INSERT INTO potrebiteli VALUES
	(1, "admin", "admin", "admin@local.bg");
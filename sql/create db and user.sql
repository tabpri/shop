CREATE DATABASE malta
 DEFAULT CHARACTER SET utf8
 DEFAULT COLLATE utf8_general_ci;

CREATE USER 'malta'@'localhost'
  IDENTIFIED BY 'malta';
  
GRANT ALL ON malta.* TO 'malta'@'localhost';





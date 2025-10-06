CREATE DATABASE tp2db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'tp2user'@'localhost' IDENTIFIED BY 'tp2pass';
GRANT ALL PRIVILEGES ON tp2db.* TO 'tp2user'@'localhost';
FLUSH PRIVILEGES;

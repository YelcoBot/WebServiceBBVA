CREATE DATABASE  IF NOT EXISTS `service_bbva`;
-----------------
USE `service_bbva`;
-----------------
DROP TABLE IF EXISTS `cliente`;
-----------------
DROP TABLE IF EXISTS `tipo_documento`;
-----------------
CREATE TABLE `tipo_documento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `descripcion` varchar(55) NOT NULL,
  PRIMARY KEY (`id`)
);
-----------------
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_documento` int NOT NULL,
  `numero_documento` varchar(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`tipo_documento`) REFERENCES `tipo_documento` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);
-----------------
INSERT INTO `tipo_documento` VALUES (1,'CC','CEDULA DE CIUDADANIA'),(2,'CE','CEDULA DE EXTRANJERIA'),(3,'NIP','NUMERO DE IDENTIFICACION PERSONAL'),(4,'NIT','NUMERO DE IDENTIFICACION TRIBUTARIA'),(5,'TI','TARJETA DE IDENTIDAD'),(6,'PAP','PASAPORTE');
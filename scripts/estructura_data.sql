# SQL Manager 2005 Lite for MySQL 3.7.7.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : hospital_dh


SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `hospital_dh`;

CREATE DATABASE `hospital_dh`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `hospital_dh`;

#
# Structure for the `doctor_especialidades` table : 
#

DROP TABLE IF EXISTS `doctor_especialidades`;

CREATE TABLE `doctor_especialidades` (
  `DOCTOR_ESPECIALIDADES_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `DOCTOR_ID` bigint(20) unsigned NOT NULL,
  `ESPECIALIDAD_ID` bigint(20) unsigned NOT NULL,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  PRIMARY KEY (`DOCTOR_ESPECIALIDADES_ID`),
  KEY `FK_DCTR_SPCLDD_DOCTOR` (`DOCTOR_ID`),
  KEY `FK_DCTR_SPCLDD_SPCLDD` (`ESPECIALIDAD_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Structure for the `doctores` table : 
#

DROP TABLE IF EXISTS `doctores`;

CREATE TABLE `doctores` (
  `DOCTOR_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `HOSPITAL_ID` bigint(20) unsigned NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `APELLIDO` varchar(50) NOT NULL,
  `FECHA_NACIMIENTO` date DEFAULT NULL,
  `DIRECCION` text NOT NULL,
  `FOTO_PERFIL` longblob,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  `CREADO_EN` datetime NOT NULL,
  `ACTUALIZADO_EN` datetime DEFAULT NULL,
  `CREADO_POR` varchar(50) NOT NULL,
  `ACTUALIZADO_POR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DOCTOR_ID`),
  KEY `FK_DCTR_HSPTL` (`HOSPITAL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

#
# Data for the `doctores` table  (LIMIT 0,500)
#

INSERT INTO `doctores` (`DOCTOR_ID`, `HOSPITAL_ID`, `NOMBRE`, `APELLIDO`, `FECHA_NACIMIENTO`, `DIRECCION`, `FOTO_PERFIL`, `ESTADO`, `CREADO_EN`, `ACTUALIZADO_EN`, `CREADO_POR`, `ACTUALIZADO_POR`) VALUES 
  ('1','1','Marcelo','Prada','1992-02-14','Calle A',NULL,'AC','2020-11-16 06:05:56',NULL,'XXX',NULL),
  ('2','1','Alfredo','Lara','1993-04-14','Calle B',NULL,'AC','2020-11-16 06:06:20',NULL,'XXX',NULL),
  ('3','1','Marcela','Guesa','1982-08-03','Calle C',NULL,'AC','2020-11-16 06:06:49',NULL,'XXX',NULL),
  ('4','2','Lucas','Perez','2006-02-15','Calle E',NULL,'AC','2020-11-16 06:07:32',NULL,'XXX',NULL),
  ('5','2','Monica','Parra','2015-02-04','Calle D',NULL,'AC','2020-11-16 06:07:51',NULL,'XXX',NULL),
  ('6','3','Mariela','Jimenez','2001-02-08','Calle F',NULL,'AC','2020-11-16 06:08:21',NULL,'XXX',NULL),
  ('7','3','Laura','Lira','1992-09-08','Calle G',NULL,'AC','2020-11-16 06:08:44',NULL,'XXX',NULL);

COMMIT;

#
# Structure for the `doctores_especialidades` table : 
#

DROP TABLE IF EXISTS `doctores_especialidades`;

CREATE TABLE `doctores_especialidades` (
  `DOCTOR_ESPECIALIDAD_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `DOCTOR_ID` bigint(20) unsigned NOT NULL,
  `ESPECIALIDAD_ID` bigint(20) unsigned NOT NULL,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  PRIMARY KEY (`DOCTOR_ESPECIALIDAD_ID`),
  KEY `FK_DCTR_SPCLDD_DOCTOR` (`DOCTOR_ID`),
  KEY `FK_DCTR_SPCLDD_SPCLDD` (`ESPECIALIDAD_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Structure for the `especialidades` table : 
#

DROP TABLE IF EXISTS `especialidades`;

CREATE TABLE `especialidades` (
  `ESPECIALIDAD_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(150) NOT NULL,
  `AVATAR` longblob,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  `CREADO_EN` datetime NOT NULL,
  `ACTUALIZADO_EN` datetime DEFAULT NULL,
  `CREADO_POR` varchar(50) NOT NULL,
  `ACTUALIZADO_POR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ESPECIALIDAD_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Structure for the `hospitales` table : 
#

DROP TABLE IF EXISTS `hospitales`;

CREATE TABLE `hospitales` (
  `HOSPITAL_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(100) NOT NULL,
  `DIRECCION` text NOT NULL,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  `CREADO_EN` datetime NOT NULL,
  `ACTUALIZADO_EN` datetime DEFAULT NULL,
  `CREADO_POR` varchar(50) NOT NULL,
  `ACTUALIZADO_POR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`HOSPITAL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for the `hospitales` table  (LIMIT 0,500)
#

INSERT INTO `hospitales` (`HOSPITAL_ID`, `NOMBRE`, `DIRECCION`, `ESTADO`, `CREADO_EN`, `ACTUALIZADO_EN`, `CREADO_POR`, `ACTUALIZADO_POR`) VALUES 
  ('1','Hospital A','Calle ABC 10001','AC','2020-11-16 06:04:50',NULL,'XXX',NULL),
  ('2','Hospital B','Calle ABC 10002','AC','2020-11-16 06:05:00',NULL,'XXX',NULL),
  ('3','Hospital C','Calle ABC 10003','AC','2020-11-16 06:05:09',NULL,'XXX',NULL);

COMMIT;

#
# Structure for the `notas_visitas` table : 
#

DROP TABLE IF EXISTS `notas_visitas`;

CREATE TABLE `notas_visitas` (
  `NOTA_VISITA_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `PACIENTE_ID` bigint(20) unsigned NOT NULL,
  `DOCTOR_ID` bigint(20) unsigned NOT NULL,
  `DESCRIPCION` text NOT NULL,
  `FECHA` datetime NOT NULL,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  `CREADO_EN` datetime NOT NULL,
  `ACTUALIZADO_EN` datetime DEFAULT NULL,
  `CREADO_POR` varchar(50) NOT NULL,
  `ACTUALIZADO_POR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NOTA_VISITA_ID`),
  KEY `FK_NT_VST_DOCTOR` (`DOCTOR_ID`),
  KEY `FK_NT_VST_PCNT` (`PACIENTE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

#
# Data for the `notas_visitas` table  (LIMIT 0,500)
#

INSERT INTO `notas_visitas` (`NOTA_VISITA_ID`, `PACIENTE_ID`, `DOCTOR_ID`, `DESCRIPCION`, `FECHA`, `ESTADO`, `CREADO_EN`, `ACTUALIZADO_EN`, `CREADO_POR`, `ACTUALIZADO_POR`) VALUES 
  ('1','1','1','Analisis de salud','2020-11-11 13:09:19','AC','2020-11-16 06:09:57',NULL,'XXX',NULL),
  ('2','1','1','asdasd asdasdasd','2020-11-04 09:13:20','AC','2020-11-16 06:13:40',NULL,'XXX',NULL),
  ('3','1','1','asdasd','2020-11-18 06:14:33','AC','2020-11-16 06:14:38',NULL,'XXX',NULL),
  ('4','2','7','Prueba de laboratorio','2020-11-02 10:18:30','AC','2020-11-16 06:18:44',NULL,'XXX',NULL),
  ('5','3','4','Revision semanal','2020-11-11 13:19:20','AC','2020-11-16 06:19:35',NULL,'XXX',NULL),
  ('6','4','3','Revision mensual','2020-11-13 10:20:04','AC','2020-11-16 06:20:15',NULL,'XXX',NULL);

COMMIT;

#
# Structure for the `pacientes` table : 
#

DROP TABLE IF EXISTS `pacientes`;

CREATE TABLE `pacientes` (
  `PACIENTE_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `HOSPITAL_ID` bigint(20) unsigned NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `APELLIDO` varchar(50) NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL,
  `DIRECCION` text NOT NULL,
  `FOTO_PERFIL` longblob,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  `CREADO_EN` datetime NOT NULL,
  `ACTUALIZADO_EN` datetime DEFAULT NULL,
  `CREADO_POR` varchar(50) NOT NULL,
  `ACTUALIZADO_POR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PACIENTE_ID`),
  KEY `FK_HOSPITAL` (`HOSPITAL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

#
# Data for the `pacientes` table  (LIMIT 0,500)
#

INSERT INTO `pacientes` (`PACIENTE_ID`, `HOSPITAL_ID`, `NOMBRE`, `APELLIDO`, `FECHA_NACIMIENTO`, `DIRECCION`, `FOTO_PERFIL`, `ESTADO`, `CREADO_EN`, `ACTUALIZADO_EN`, `CREADO_POR`, `ACTUALIZADO_POR`) VALUES 
  ('1','1','Marcelino','Yucra','1998-02-12','Calle F',NULL,'AC','2020-11-16 06:09:57',NULL,'XXX',NULL),
  ('2','3','Marco','Cuevas','2017-02-13','Calle E',NULL,'AC','2020-11-16 06:18:44',NULL,'XXX',NULL),
  ('3','2','Pedro','Mayta','1997-02-12','Calle F',NULL,'AC','2020-11-16 06:19:35',NULL,'XXX',NULL),
  ('4','1','Cesar','Perez','1992-02-11','Calle T',NULL,'AC','2020-11-16 06:20:15',NULL,'XXX',NULL);

COMMIT;

#
# Structure for the `usuarios` table : 
#

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `USUARIO_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ALIAS` varchar(50) NOT NULL,
  `CONTRASENA` text NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `APELLIDO` varchar(50) NOT NULL,
  `ESTADO` varchar(2) NOT NULL DEFAULT 'AC',
  `CREADO_EN` datetime NOT NULL,
  `ACTUALIZADO_EN` datetime DEFAULT NULL,
  `CREADO_POR` varchar(50) NOT NULL,
  `ACTUALIZADO_POR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USUARIO_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


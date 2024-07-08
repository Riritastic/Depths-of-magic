# Host: localhost  (Version: 5.5.5-10.4.32-MariaDB)
# Date: 2024-07-08 14:51:42
# Generator: MySQL-Front 5.3  (Build 1.18)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

USE `depths of magic`;

#
# Source for table "armor"
#

DROP TABLE IF EXISTS `armor`;
CREATE TABLE `armor` (
  `nombre` varchar(20) NOT NULL,
  `material` varchar(15) NOT NULL,
  `resistencia` int(11) NOT NULL,
  `hero_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_armor_hero_idx` (`hero_id`),
  CONSTRAINT `fk_armor_hero` FOREIGN KEY (`hero_id`) REFERENCES `phpmyadmin`.`hero` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

#
# Data for table "armor"
#

INSERT INTO `armor` VALUES ('Capa','Tela',10,NULL),('Cota de Mithril','Cuero',15,NULL),('Guardia de la Noche','diamante',50,NULL),('yelmo','acero',20,NULL);

#
# Source for table "hero"
#

DROP TABLE IF EXISTS `hero`;
CREATE TABLE `hero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nivel_actual` int(11) NOT NULL,
  `vida` int(11) NOT NULL,
  `vida_maxima` int(11) NOT NULL,
  `kills` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

#
# Data for table "hero"
#

INSERT INTO `hero` VALUES (1,1,200,200,0);

#
# Source for table "monster"
#

DROP TABLE IF EXISTS `monster`;
CREATE TABLE `monster` (
  `nombre` varchar(15) NOT NULL,
  `vida` int(11) NOT NULL,
  `vida_maxima` int(11) NOT NULL,
  `daÃ±o` int(11) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

#
# Data for table "monster"
#

INSERT INTO `monster` VALUES ('AraÃ±a',70,70,20),('Ghoul',120,120,50),('bruja',100,100,30),('militar',80,80,15),('Goblin',50,50,10),('Slime',200,200,5);

#
# Source for table "room"
#

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `numero_nivel` int(11) NOT NULL,
  `cant_enemigos` int(11) NOT NULL,
  `descripcion` int(11) NOT NULL,
  `ancho` int(11) NOT NULL,
  `alto` int(11) NOT NULL,
  `hero_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero_nivel`),
  KEY `fk_room_hero_idx` (`hero_id`),
  CONSTRAINT `fk_room_hero` FOREIGN KEY (`hero_id`) REFERENCES `phpmyadmin`.`hero` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

#
# Data for table "room"
#


#
# Source for table "weapon"
#

DROP TABLE IF EXISTS `weapon`;
CREATE TABLE `weapon` (
  `nombre` varchar(20) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  `daÃ±o` int(11) NOT NULL,
  `rango` int(11) NOT NULL,
  `hero_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_weapon_hero_idx` (`hero_id`),
  CONSTRAINT `fk_weapon_hero` FOREIGN KEY (`hero_id`) REFERENCES `phpmyadmin`.`hero` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

#
# Data for table "weapon"
#

INSERT INTO `weapon` VALUES ('Daga','Cuerpo',30,3,NULL),('Espada','Cuerpo',40,5,NULL),('Hacha','Cuerpo',50,7,NULL),('glock 19','distancia',100,100,NULL);

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

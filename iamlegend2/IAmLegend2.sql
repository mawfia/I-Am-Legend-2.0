-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema iamlegend2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `iamlegend2` ;

-- -----------------------------------------------------
-- Schema iamlegend2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iamlegend2` DEFAULT CHARACTER SET utf8 ;
USE `iamlegend2` ;

-- -----------------------------------------------------
-- Table `inventory_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory_item` ;

CREATE TABLE IF NOT EXISTS `inventory_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(4500) NULL DEFAULT NULL,
  `description` VARCHAR(4500) NULL DEFAULT NULL,
  `weight` DOUBLE NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `image_url` LONGTEXT NULL DEFAULT NULL,
  `quantity_in_stock` INT(11) NULL DEFAULT NULL,
  `category` ENUM('WEAPON', 'OPTIC', 'AMMO', 'NUTRITION', 'EQUIPMENT') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 154
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ammo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ammo` ;

CREATE TABLE IF NOT EXISTS `ammo` (
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_ammo_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_ammo_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT(11) NOT NULL,
  `account_balance` INT(11) NULL DEFAULT NULL,
  `email` VARCHAR(55) NULL DEFAULT NULL,
  `password` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `middle_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `age` FLOAT NULL DEFAULT NULL,
  `zipcode` INT(11) NULL DEFAULT NULL,
  `height` FLOAT NULL DEFAULT NULL,
  `weight` FLOAT NULL DEFAULT NULL,
  `access_level` ENUM('LOGOUT', 'GUEST', 'BASIC', 'ADMIN') NOT NULL DEFAULT 'GUEST',
  `geoData` VARCHAR(100) NULL DEFAULT NULL,
  `security_question_1` VARCHAR(45) NULL DEFAULT NULL,
  `security_question_2` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart` ;

CREATE TABLE IF NOT EXISTS `cart` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `active` TINYINT(1) NOT NULL,
  `customer_account_number` INT(11) NOT NULL,
  `survival_score` DOUBLE NOT NULL,
  `total_price` DOUBLE NULL,
  `total_weight` DOUBLE NULL,
  PRIMARY KEY (`id`, `customer_account_number`),
  INDEX `fk_customer_account_number_idx` (`customer_account_number` ASC),
  CONSTRAINT `fk_customer_account_number`
    FOREIGN KEY (`customer_account_number`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cart_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart_items` ;

CREATE TABLE IF NOT EXISTS `cart_items` (
  `cart_id` INT(11) NOT NULL,
  `inventory_item_id` INT(11) NOT NULL,
  INDEX `fk_cart_items_cart1_idx` (`cart_id` ASC),
  INDEX `fk_cart_items_inventory_item1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_cart_items_cart1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_items_inventory_item1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `equipment` ;

CREATE TABLE IF NOT EXISTS `equipment` (
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_Equipment_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_Equipment_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nutrition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nutrition` ;

CREATE TABLE IF NOT EXISTS `nutrition` (
  `calories` VARCHAR(45) NULL DEFAULT NULL,
  `food_type` VARCHAR(45) NULL DEFAULT NULL,
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_nutrition_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_nutrition_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `optic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `optic` ;

CREATE TABLE IF NOT EXISTS `optic` (
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_optics_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_optics_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `weapon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `weapon` ;

CREATE TABLE IF NOT EXISTS `weapon` (
  `manufactuer` VARCHAR(45) NULL DEFAULT NULL,
  `weapon_type` VARCHAR(45) NULL DEFAULT NULL,
  `country_of_origin` VARCHAR(45) NULL DEFAULT NULL,
  `production_year` VARCHAR(45) NULL DEFAULT NULL,
  `length` VARCHAR(45) NULL DEFAULT NULL,
  `barrel_length` VARCHAR(45) NULL DEFAULT NULL,
  `rate_of_fire` VARCHAR(45) NULL DEFAULT NULL,
  `muzzel_velocity` VARCHAR(45) NULL DEFAULT NULL,
  `effective_firing_range` VARCHAR(45) NULL DEFAULT NULL,
  `maximum_firing_range` VARCHAR(45) NULL DEFAULT NULL,
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_Weapons_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_Weapons_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `iamlegend2`;
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (id, 'name', 'description', weight, price, 'image_url', quantity_in_stock, 'category');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (id, 'name', 'description', weight, price, 'image_url', quantity_in_stock, 'category');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (1, 'MP5', 'MP5', 6.3, 700, 'images/weapon/1.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (2, 'uzi', 'uzi', 7.72, 500, 'images/weapon/2.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (3, 'tec-9', 'tec-9', 3.08647, 500, 'images/weapon/3.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (4, 'mac-10', 'mac-10', 6.26, 450, 'images/weapon/4.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (5, 'UMP', 'UMP', 5.8, 700, 'images/weapon/5.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (6, 'Steyr Aug A3', 'Steyr Aug A3', 7.9, 1800, 'images/weapon/6.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (7, 'HK33A', 'HK33A', 8.05, 2500, 'images/weapon/7.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (8, 'SA80', 'SA80', 8.4, 500, 'images/weapon/8.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (9, 'M4A1', 'M4A1', 6.36, 700, 'images/weapon/9.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (10, 'DPMS GII Recon', 'DPMS GII Recon', 8.5, 1759, 'images/weapon/10.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (11, 'DS Arms SA58 Mini OSW', 'DS Arms SA58 Mini OSW', 9, 1975, 'images/weapon/11.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (12, 'FN FS2000 tactical', 'FN FS2000 tactical', 7.86, 2779, 'images/weapon/12.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (13, 'MR762A1', 'MR762A1', 9.94, 3999, 'images/weapon/13.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (14, 'IWI Tavor SAR', 'IWI Tavor SAR', 7.9, 1999, 'images/weapon/14.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (15, 'MasterPiece Arms MPAR556', 'MasterPiece Arms MPAR556', 7.8, 950, 'images/weapon/15.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (16, 'FN FAL', 'FN FAL', 9.48, 400, 'images/weapon/16.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (17, 'Type 81', 'Type 81', 7.5, 800, 'images/weapon/17.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (18, 'M1A SOCOM 16', 'M1A SOCOM 16', 9.2, 2186, 'images/weapon/18.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (19, 'ARX100', 'ARX100', 6.8, 1950, 'images/weapon/19.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (20, 'ACR', 'ACR', 9.8, 2552, 'images/weapon/20.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (21, 'LE901-16s', 'LE901-16s', 8.8, 2544, 'images/weapon/21.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (22, 'SIG716 patrol', 'SIG716 patrol', 9.3, 2399, 'images/weapon/22.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (23, 'ak-103', 'ak-103', 7.5, 1500, 'images/weapon/23.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (24, 'IMI galil', 'IMI galil', 9.8, 800, 'images/weapon/24.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (25, 'kel-tec rfb', 'kel-tec rfb', 8.1, 1888, 'images/weapon/25.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (26, '92A', '92A1', 2.215, 355, 'images/weapon/26.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (27, 'M1911', 'M1911', 2.44, 800, 'images/weapon/27.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (28, 'Desert Eagle', 'Desert Eagle', 4.4, 1790, 'images/weapon/28.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (29, 'Glock 39', 'Glock 39', 1.2, 405, 'images/weapon/29.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (30, 'Five-Seven', 'Five-Seven', 1.3, 1169, 'images/weapon/30.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (31, '227 Sub Compact', '228 Sub Compact', 1.82, 800, 'images/weapon/31.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (32, 'p2000', 'p2000', 1.36687, 1000, 'images/weapon/32.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (33, 'M&P R8', 'M&P R8', 0.79146, 1300, 'images/weapon/33.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (34, 'PK', 'PK', 19.84, 1790, 'images/weapon/34.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (35, 'M82', 'M82', 29.7, 8900, 'images/weapon/35.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (36, 'IMI Negev', 'IMI Negev', 16.31, 5500, 'images/weapon/36.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (37, 'M249', 'M249', 17, 3500, 'images/weapon/37.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (38, 'M76', 'M76', 10, 2200, 'images/weapon/38.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (39, 'Dragunov SVD', 'Dragunov SVD', 9.48, 900, 'images/weapon/39.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (40, 'Longbow T-76', 'Longbow T-76', 13.5, 4300, 'images/weapon/40.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (41, 'M25', 'M25', 11, 3200, 'images/weapon/41.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (42, 'M40', 'M40', 14.48, 1000, 'images/weapon/42.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (43, '1300 Tactical 12 GA.', '1301 Tactical 12 GA.', 6.3, 1075, 'images/weapon/43.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (44, 'ATI Bernelli M4', 'ATI Bernelli M4', 7.8, 1999, 'images/weapon/44.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (45, 'Remington 870', 'Remington 870', 7, 1059, 'images/weapon/45.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (46, 'FN SLP MKI 12 Ga', 'FN SLP MKI 12 Ga', 8.2, 1429, 'images/weapon/46.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (47, 'MOLOT VEPR 12 GA', 'MOLOT VEPR 12 GA', 9.25942, 1499, 'images/weapon/47.jpg', 20, 'WEAPON');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (48, '5.56x45mm', 'NATO', 0.0168571, 0.23, 'images/ammo/1.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (49, '10mm', 'Auto', 0.0178571, 0.45, 'images/ammo/2.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (50, '11.43x23mm', '.45 Automatic Colt Pistol', 0.0178571, 0.0328571, 'images/ammo/3.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (51, '7.62x51mm', 'NATO', 0.0215714, 0.59, 'images/ammo/4.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (52, '9x19mm', 'Parabellum', 0.0177143, 0.625, 'images/ammo/5.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (53, '12.7x33mm', '.5 Action Express', 0.0428571, 0.917, 'images/ammo/6.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (54, '0.45', 'G.A.P.', 0.0328571, 1.417, 'images/ammo/7.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (55, '5.7x28mm', 'FN Herstal', 0.00571429, 0.4518, 'images/ammo/8.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (56, '12.7x99mm', '.5 Browning Machine Gun', 0.0942857, 4.9599, 'images/ammo/9.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (57, '7.62x54mm', '', 0.0328571, 0.25, 'images/ammo/10.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (58, '7.92x57mm', 'Mauser', 0.0242857, 0.57, 'images/ammo/11.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (59, '0.338', 'Lapua Magnum', 0.0285714, 0.210637177, 'images/ammo/12.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (60, '12 Gauge', 'Shotgun shell', 0.0621429, 0.718, 'images/ammo/13.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (61, '9x33mmR', '.357 Magnum', 0.0178571, 0.3, 'images/ammo/14.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (62, '7.62x39mm', 'Type 81', 0.0175714, 0.26, 'images/ammo/15.jpg', 20, 'AMMO');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (63, 'ZRAK ON-M76 Optic', 'Magnification:  ZRAK M76 4� 5�10�  Power: Tiritium', 0.7, 215, 'images/optic/1.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (64, 'telescopic SUSAT/ACOG', 'Magnification: 4x Power: Tritium', 0.919, 795, 'images/optic/2.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (65, 'swarovski PH 1.5-6x42SR', 'Magnification: 1.5-6x Power: None', 1.09, 1599, 'images/optic/3.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (66, 'schmidt and bender PM II 3', 'Magnification: 3-12x', 1.89, 3789, 'images/optic/4.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (67, 'NightForce w/BORS', 'Magnificatino: 5.5-22x Power: CR-123', 0.8125, 1399, 'images/optic/5.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (68, 'Leupold VX-III ', 'Magnification: 4.5-14 Power: None', 0.94, 549, 'images/optic/6.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (69, 'leuopold  & stevens Ultra Mark 4 M3', 'Magnification: 10x', 1.38, 1200, 'images/optic/7.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (70, 'aimpoint compc3', 'Magnification: 1x Power: One 3 Volt Lithium battery ', 0.4375, 500, 'images/optic/8.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (71, 'Trijicon ACOG', 'Magnification: 4x Power:Tritium', 0.935, 2155, 'images/optic/9.jpg', 20, 'OPTIC');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (72, 'Hello Kitty Backpack', 'Hello Kitty Backpack', 1, 12, 'http://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=9398046', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (73, 'U.S. Military Surplus A.L.I.C.E. Pack, New', 'U.S. Military Surplus A.L.I.C.E. Pack, New', 3, 75, 'https://media.midwayusa.com/productimages/880x660/Primary/124/124431.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (74, 'ARC\'TERYX LEAF KHARD 60 Pack', 'ARC\'TERYX LEAF KHARD 60 Pack', 6, 350, 'http://images.arcteryx.com/details/450x500/Khard-60-Pack-Wolf-Back-View.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (75, 'Personal First Aid Kit', 'Personal First Aid Kit', 1, 25, 'http://www.p-wholesale.com/upimg/10/461a1/personal-first-aid-kit-280.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (76, 'Combat LifeSaver Kit', 'Combat LifeSaver Kit', 3, 89, 'http://i2.photobucket.com/albums/y48/chris-l/DSC_0795.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (77, 'Paramedic First Aid Kit', 'Paramedic First Aid Kit', 4, 234, 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRj1wcdxtA-PScvoIOu9COfcgrFXgL_63kBNnF7AXBFwqjxFj9Mkg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (78, 'Compass', 'Compass', 0.25, 8, 'https://images-na.ssl-images-amazon.com/images/I/817V8g8WzdL._SL1500_.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (79, 'matches(pack of 25)', 'matches(pack of 25)', 0.1, 1.25, 'http://www.ruralking.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/9/4/940bp_singlebox.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (80, 'fire starter', 'fire starter', 0.25, 7.99, 'https://www.rei.com/media/55c8a7ff-abda-4011-922d-d134657635c9', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (81, 'rope', 'rope', 1, 16.5, 'http://kingofwallpapers.com/rope/rope-004.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (82, 'Duct Tape', 'Duct Tape', 0.75, 2.5, 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Duct-tape.jpg/220px-Duct-tape.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (83, 'multi-tool (Leatherman)', 'multi-tool (Leatherman)', 0.5, 35, 'http://demandware.edgesuite.net/sits_pod21/dw/image/v2/AAMV_PRD/on/demandware.static/-/Sites-master/default/dw158f2580/large/49-oht.jpg?sw=288&sh=288&sm=fit&sfrm=png', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (84, 'candles(pack of 4)', 'candles(pack of 4)', 0.25, 3, 'https://cdn.davidwolfe.com/wp-content/uploads/2015/12/candles-492171_1280-e1451245145944.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (85, 'flashlight', 'flashlight', 1, 7, 'https://images-na.ssl-images-amazon.com/images/I/412DA2FE2NL.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (86, 'tarp', 'tarp', 1, 12, 'http://www.ddhammocks.com/images/products/_ALL_PRODUCTS/TARPS/DD_Tarp_M/DD_Tarp_M_gallery_01.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (87, 'tent', 'tent', 5, 98, 'http://ecx.images-amazon.com/images/I/81LmkUY3lLL._SL1500_.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (88, 'Yurt', 'Yurt', 12, 560, 'http://ecx.images-amazon.com/images/I/81LmkUY3lLL._SL1500_.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (89, 'sleeping bag', 'sleeping bag', 3, 115, 'https://epay.newschool.edu/C21120_ustores/web/images/store_12/sleeping_bag.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (90, 'pillow(temperpedic)', 'pillow(temperpedic)', 1, 45, 'https://epay.newschool.edu/C21120_ustores/web/images/store_12/sleeping_bag.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (91, 'radio', 'radio', 1, 21, 'https://epay.newschool.edu/C21120_ustores/web/images/store_12/sleeping_bag.jpg', 20, 'EQUIPMENT');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (92, 'Anchovy, canned in brine', 'Anchovy fillets preserved by canning in brine, then .', 16, 1, 'http://d3cizcpymoenau.cloudfront.net/images/31744/SIL_Anchovies_King_20Oscar_20Anchovies_20-_20Flat_20Fillets_20in_20Olive_20Oil_20copy.png', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (93, 'Apple, canned in syrup, pie filling', 'Round shaped fruit with either a green or red waxy skin and cream/ White coloured flesh. Peeled, chopped and canned in a light sugar syrup, suitable for use as a filling in apple pies.', 21, 1, 'https://i5.walmartimages.com/asr/cca94678-035c-4167-9251-8e5d15f81a4f_2.5aad58cd5e13ffe7e75213b25344bb37.jpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (94, 'Apricot, canned in intense sweetened', 'Small, orange-coloured stone fruit canned in intense sweetened liquid, then .', 16, 1, 'http://www.austinfoods.net/images/userFiles/products/apricot.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (95, 'Apricot, canned in light syrup', 'Small, orange coloured stone fruit canned in a light syrup, then .', 12, 1, 'https://www.delmonte.com/sites/default/files/a832be6b-cab3-4b8e-a508-0cf820900b65.png', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (96, 'Artichoke heart, canned in brine', 'Central edible portion of the unopened flower bud of a thistle, with tightly wrapped leaves gold-green to purple in colour. Canned in brine and .', 4, 1, 'https://img.thrivemarket.com/store/full/0/4/043182008914-1.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (97, 'Asparagus, canned in brine', 'Long crisp stem with a fragile feathery tip. Spears and cuts, canned in brine, then . Average contents per can 64% solids.', 10.5, 1, 'https://www.yummy.com/images/products/02000010039.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (98, 'Bamboo shoot, canned in water', 'Tender young shoots of an edible bamboo plant, cream in colour with a fibrous texture. Sliced and canned in water. Heated in own juice, then . Average contents per can 57% solids. Weight loss of solids on heating 14%.', 4, 1, 'http://www.tirakita.com/food/Photos/fd_thai_211b.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (99, 'Butter Beans, seeds & pod, canned', 'Green pod with creamy Yellow seeds. Cooked with no added fat. Also known as Yellow, wax, lima, and golden.', 15.5, 1, 'http://i.walmartimages.com/i/p/00/03/94/00/01/0003940001670_500X500.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (100, 'Cannellini Beans, canned in brine', 'Small White bean, oval in shape, removed from its pod. Boiled and canned in brine. Average contents per can 73% beans.', 15.5, 1, 'http://d3cizcpymoenau.cloudfront.net/images/20699/SIL_CannelliniBeans_GoyaPremium.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (101, 'Green Beans, seeds & pod, canned', 'Long, thin, green, crisp pod with small round smooth seeds inside. Cooked with no added fat.', 15.5, 1, 'https://ll-us-i5.wal.co/asr/9d9ec3fc-0076-4c9d-9d8b-6ef28cdae057_1.4abd104357a4f9d23a5afd8bcf733f0a.jpeg-0b63553c1c84fde36bdd06c14958285a268dd80e-optim-500x500.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (102, 'Lima Beans, canned', 'Flat kidney shaped bean that can be White or green, large or small. Slightly sweet flavour when cooked. Removed from its pod, canned, . Also known as madagascar bean, butter bean, calico bean and sieva bean.', 15.5, 1, 'https://www.delmonte.com/sites/default/files/grlima_16295_cweb.png', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (103, 'Mixed Beans, canned in brine', 'Three or four bean mix comprising red kidney, lima and butter beans and chickpeas. Canned in sweetened brine, then . Average contents per can 68% solids.', 15.5, 1, 'https://directeats.com/product/image/organic_mixed_beans_54237_mainproduct.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (104, 'Red Kidney Beans, canned in brine', 'Kidney shaped beans, canned in sweetened brine, before consumption. Average contents per can 72% solids.', 16, 1, 'http://target.scene7.com/is/image/Target/14771989?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (105, 'Corned Beef, canned', 'Canned processed meat product containing cooked beef, salt, sugar, sodium nitrite and water.', 12, 1, 'http://i.walmartimages.com/i/p/00/03/90/00/08/0003900008104_500X500.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (106, 'Beetroot, canned', 'Rounded root vegetable with dark brown-purple skin and stems and purple flesh. Boiled, then sliced and preserved in a can in an acidified salted brine. .', 4, 1, 'http://www.heinzfoodservice.com.au/-/media/930659fb08964a1499315f5780f163af.ashx', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (107, 'Blueberry, canned in syrup', 'Small, deep blue berry with Whiteish waxy bloom canned in sugar syrup, .', 15, 1, '', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (108, 'Carrot, baby, canned in brine, boiled', 'Crisp root vegetable with orange flesh and green leaves/stem. Heated in packing liquid, then . Average contents per can 63% solids.', 10.5, 1, 'https://images9.jetcdn.net/md5/99865c0050ad0a0fb4145925bd5d8503.500', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (109, 'Cherry, black, canned in syrup', 'Small, deep reddish-purple stone fruit canned in sugar-based syrup, then .', 21, 1, 'https://images6.jetcdn.net/md5/be13bb98f31b4e714dddd5c683460f8f.1500', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (110, 'Chickpea, canned in brine', 'Small, light coloured seed, removed from its pod. Canned in brine, then . Average contents per can 59% solids.', 12, 1, 'http://www.foodservicedirect.com/productimageslarge/OT493683L.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (111, 'Coconut, milk, canned', 'Canned liquid extract of the flesh of mature coconuts, generally with added water.', 12, 1, 'https://groceries.morrisons.com/productImages/251/251210011_0_640x640.jpg?identifier=99cc6a1d1246bb4f0f93a69c7d38194e', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (112, 'Crab meat, canned in brine', 'Flesh extracted from crabs and canned in brine. May also includes salt, sugar, water and citric acid. of brine. Products imported into Australia.', 12, 1, 'http://www.bumblebee.com/wp-content/uploads/2013/08/00086600702520_CF___JPEG_3.jpg?x66607', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (113, 'Vienna Sausage, canned, boiled', 'Canned frankfurt containing meat (including pork), skim milk powder, salt, potato starch, flavour enhancer, mineral salt, spices, sodium nitrite and water. Heated as per can instructions and before consumed.', 4, 1, 'https://images-na.ssl-images-amazon.com/images/I/51htCZMeB2L.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (114, 'Fruit salad, canned in heavy syrup', 'Canned mixed fruit in sugar-based syrup, then .', 10.6, 1, 'https://i5.walmartimages.com/asr/f9c59032-b55f-4eed-87d9-aa546ddf1955_1.863c6fa8c97dae8d88edbde340050488.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (115, 'Ham, leg, canned', 'Canned leg ham containing pork, gelatine, salt, mineral salt, agar, sugar, anti-oxidant, sodium nitrite and water. Includes lean and fat portions of ham.', 16, 1, 'http://www.aus-linkwa.com.au/wp-content/uploads/Leg-Ham-450g.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (116, 'Herring, canned with salt', 'Herring fillets preserved by canning with salt.', 16, 1, 'https://i5.walmartimages.com/asr/bb049939-985b-4a8f-b7cb-777b93d865bd_1.dba70053ed74a9a8ba9c7a105085a670.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (117, 'Kipper, canned', 'Kipper, preserved by canning, then .', 16, 1, 'http://ecx.images-amazon.com/images/I/51yLdZBHiZL.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (118, 'Lychee, canned in light syrup', 'Small round to oval shaped fruit with a scaly reddish to reddish-brown skin and off- White flesh, canned in a light sugar based syrup, then . Also known as leechi, litchi, laichi or lichu.', 16, 1, 'https://i5.walmartimages.com/asr/68cdf803-45d1-4254-870f-51fa1dc17911_1.cf6ee7dc666e4d441da1f4d2ff023c6e.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (119, 'Mackerel, canned in brine', 'Mackerel fillets preserved by canning in brine.', 16, 1, 'http://img.21food.com/20110609/product/1305221946828.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (120, 'Mackerel, canned in oil', 'Mackerel fillets preserved by canning in edible oil, then .', 16, 1, 'http://img.21food.com/20110609/product/1305221946828.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (121, 'Mandarin, canned in syrup', 'Segments of a mandarin, canned in a sugar-based syrup and .', 16, 1, 'http://image.made-in-china.com/43f34j00lyGtRUcFZnqM/Good-Quality-Canned-Mackerel-in-Oil.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (122, 'Milk, canned, evaporated, Regular', 'Australian produced evaporated milk. Prepared by evaporating approximately 60% of the water content of whole cows milk prior to sterilisation. Canned.', 12, 1, 'http://www.seriouseats.com/images/2015/10/evaporated-milk-shutterstock-211603402.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (123, 'Mixed fruit, peach & pear, in light syrup', 'Peaches and pears that have been canned in light syrup, then .', 4, 1, 'https://www.delmonte.com/sites/default/files/40e4e7f2-05a3-42a5-a542-4cf9a85e2f0c.png', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (124, 'Mushroom, golden, Asian, in brine', 'Small, long-stemmed, Yellow-brown mushrooms, canned in brine, then . Average contents per can, 46% solids.', 15, 1, 'https://images.jet.com/md5/27cd2027c6a14da6006dc065911822af.500', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (125, 'Mussel, smoked, canned in oil', 'Smoked mussels canned in edible oil and salt, then .', 4, 1, 'http://www.jutaifoods.com/upfile/princes-mussels-in-vegetable-oil287.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (126, 'Oyster, smoked, canned in oil', 'Oyster flesh preserved by smoking and canning in edible oil (generally cottonseed oil) and salt. of excess oil.', 4, 1, 'http://www.jutaifoods.com/upfile/princes-mussels-in-vegetable-oil287.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (127, 'Pea, processed, canned', 'A small round green seed, canned, then .', 15.25, 1, 'https://poolagirl.files.wordpress.com/2012/10/lesueur_peas.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (128, 'Peach, canned in syrup', 'Small stone fruit with a reddish/orange furry skin and orange flesh, canned in sugar-based syrup, then .', 15.25, 1, 'https://www.delmonte.com/sites/default/files/d4afae93-e2bc-4c31-8519-fda01b1db75c.png', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (129, 'Peach, canned, pie filling', 'Small stone fruit with a reddish/orange furry skin and orange flesh, canned and suitable for use as a filling in peach pies.', 21, 1, 'http://cdnimg2.webstaurantstore.com/images/products/extra_large/153935/806743.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (130, 'Potato, new, no added fat', 'Immature potatoes, small and round in shape, canned, typically with water, salt and food acids.', 12, 1, 'http://target.scene7.com/is/image/Target/13454152?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (131, 'Salmon, chum, solids with bone', 'Flesh and bones of fish labelled as being pink salmon. Canned in water with no added salt, .', 15.25, 1, 'https://cdn.shopify.com/s/files/1/0229/8573/products/BF-A-113_Package_1024x1024.jpg?v=1456771858', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (132, 'Salmon, pink, canned in brine', 'Flesh, bones and skin of fish labelled as pink salmon, preserved by canning in brine. of brine.', 15.25, 1, 'https://i5.walmartimages.com/asr/364bb13a-d8d4-41f5-bcce-b117ec9355dc_1.6f07db549f230308c4475ff4b8ac7fc8.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (133, 'Salmon, pink, canned, smoked', 'Flesh, bones and skin of fish labelled as pink salmon, preserved by canning in brine. .', 15.25, 1, 'http://www.stjeans.com/wp-content/uploads/2014/05/can-smoked-pink11.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (134, 'Salmon, red, canned in brine', 'Flesh, bones and skin of fish labelled as red salmon, preserved by canning in brine. of brine.', 15.25, 1, 'http://www.bumblebee.com/wp-content/uploads/2013/08/00086600000640_CF___JPEG_3.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (135, 'Sardine, canned in oil', 'Sardines preserved by salting and canning in edible oil.', 5, 1, 'http://target.scene7.com/is/image/Target/14775216?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (136, 'Sardine, canned in water,', 'Sardines preserved by canning in water, no added salt.', 5, 1, 'http://www.jutaifoods.com/upfile/fish3891.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (137, 'Sauerkraut, canned in brine', 'Fermented cabbage, preserved in brine in a can or bottle, before consumption.', 5, 1, 'http://target.scene7.com/is/image/Target/12935837?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (138, 'Soup, beef or lamb & vegetable, condensed, canned', 'Condensed soup containing beef or lamb and vegetables. Beef goulash, Rosella lambshank broth, Heinz mulligatawny mild beef curry and Campbells old fashion stockpot are examples of such a product. Requires dilution before consumption.', 10.5, 1, 'http://target.scene7.com/is/image/Target/13010831?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (139, 'Soup, beef, broth style, condensed, canned', 'Condensed beef broth style soup. Typical ingredients include concentrated beef stock, salt and diced beef. Requires dilution before consumption.', 10.5, 1, 'http://target.scene7.com/is/image/Target/13518834?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (140, 'Soup, chicken & vegetable, canned', 'Condensed soup containing chicken and vegetables. Requires dilution before consumption.', 10.5, 1, 'http://target.scene7.com/is/image/Target/47111218?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (141, 'Soup, chicken, broth style, canned', 'Condensed chicken broth style soup. Typical ingredients include concentrated chicken stock, salt and diced chicken. Requires dilution before consumption.', 10.5, 1, 'http://mms.businesswire.com/media/20130830005659/en/380647/5/Swanson_14_5oz_Chk_Broth.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (142, 'Soup, creamed chicken, canned', 'Condensed creamed chicken soup. Typical ingredients include chicken, wheat flour, skim milk powder, cream, vegetable fat, salt, sugar, onion, herbs, maize starch, water and natural colour. Requires dilution before consumption.', 10.5, 1, 'http://target.scene7.com/is/image/Target/13010833?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (143, 'Soup, creamed mushroom,  canned', 'Condensed creamed mushroom soup. Typical ingredients include mushroom, wheat flour, maize thickener, light cream or non fat milk solids, salt, vegetable gum, spices and vegetable oil. Requires dilution before consumption.', 10.5, 1, 'http://target.scene7.com/is/image/Target/13010837?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (144, 'Soup, minestrone,  canned', 'Condensed minestrone soup. Requires dilution before consumption.', 10.5, 1, 'http://target.scene7.com/is/image/Target/14840623?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (145, 'Soup, pea & ham,  canned', 'Condensed pea and ham soup. Requires dilution before consumption.', 16, 1, 'http://target.scene7.com/is/image/Target/14933835?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (146, 'Soup, tomato, condensed, canned', 'Condensed tomato soup. Typical ingredients include tomatoes, sugar, maize starch, salt, vegetable gum, and spices. Requires dilution before consumption.', 16, 1, 'http://target.scene7.com/is/image/Target/12955091?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (147, 'Spam, canned', 'Manufactured pork product containing chopped pork shoulder, leg ham, salt, sugar, mineral salts, sodium nitrite and water.', 12, 1, 'http://target.scene7.com/is/image/Target/13217521?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (148, 'Sprouts, bean, canned, no added salt', 'Sprouted from mung bean seeds. Long translucent to White in colour, with a short tapering green sheathed root. Canned in water with no added salt. Also known as ah choi, mung bean and sai dau.', 18, 1, 'http://target.scene7.com/is/image/Target/14828255?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (149, 'Sweetcorn, baby, canned in brine', 'Baby (miniature) corn on the cob, canned in salt and water, then and cooked by stir frying with no added fat.', 15.25, 1, 'http://target.scene7.com/is/image/Target/13324726?wid=1200&hei=1200&qlt=70&fmt=pjpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (150, 'Tuna, canned in brine', 'Tuna flesh preserved by canning in brine. of brine.', 15.25, 1, 'https://sleeknfit.files.wordpress.com/2013/04/cpn-00877-9.jpeg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (151, 'Tuna, canned in vegetable oil', 'Tuna flesh canned in edible oil (including olive oil, canola oil, polyunsaturated oil) with added salt. of excess oil.', 15.25, 1, 'http://www.my-grocery-list.com/wp-content/uploads/2015/09/Century-Tuna-Chunks-In-Veg-Oil.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (152, 'Tuna, canned in water', 'Tuna, preserved by canning in water.', 15.25, 1, 'http://www.my-grocery-list.com/wp-content/uploads/2015/09/Century-Tuna-Chunks-In-Veg-Oil.jpg', 20, 'NUTRITION');
INSERT INTO `inventory_item` (`id`, `name`, `description`, `weight`, `price`, `image_url`, `quantity_in_stock`, `category`) VALUES (153, 'Tuna, smoked, canned in oil', 'Tuna, preserved by smoking and canning in oil, then .', 15.25, 1, 'http://merrygobanana.com/wp-content/uploads/2016/04/bp4.jpg', 20, 'NUTRITION');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ammo`
-- -----------------------------------------------------
START TRANSACTION;
USE `iamlegend2`;
INSERT INTO `ammo` (`inventory_item_id`) VALUES (48);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (49);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (50);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (51);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (52);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (53);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (54);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (55);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (56);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (57);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (58);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (59);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (60);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (61);
INSERT INTO `ammo` (`inventory_item_id`) VALUES (62);

COMMIT;


-- -----------------------------------------------------
-- Data for table `equipment`
-- -----------------------------------------------------
START TRANSACTION;
USE `iamlegend2`;
INSERT INTO `equipment` (`inventory_item_id`) VALUES (72);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (73);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (74);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (75);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (76);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (77);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (78);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (79);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (80);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (81);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (82);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (83);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (84);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (85);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (86);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (87);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (88);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (89);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (90);
INSERT INTO `equipment` (`inventory_item_id`) VALUES (91);

COMMIT;


-- -----------------------------------------------------
-- Data for table `nutrition`
-- -----------------------------------------------------
START TRANSACTION;
USE `iamlegend2`;
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('59.3', 'Non-perishable', 92);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('28', 'Non-perishable', 93);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('27', 'Non-perishable', 94);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('18', 'Non-perishable', 95);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('25', 'Non-perishable', 96);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('46', 'Non-perishable', 97);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('25', 'Non-perishable', 98);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('100', 'Non-perishable', 99);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('150', 'Non-perishable', 100);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('34', 'Non-perishable', 101);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('216', 'Non-perishable', 102);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('170', 'Non-perishable', 103);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('127', 'Non-perishable', 104);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('52', 'Non-perishable', 105);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('91', 'Non-perishable', 106);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('215', 'Non-perishable', 107);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('30', 'Non-perishable', 108);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('149', 'Non-perishable', 109);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('269', 'Non-perishable', 110);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('445', 'Non-perishable', 111);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('82', 'Non-perishable', 112);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('260', 'Non-perishable', 113);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('70', 'Non-perishable', 114);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('463', 'Non-perishable', 115);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('73', 'Non-perishable', 116);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('61', 'Non-perishable', 117);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('125', 'Non-perishable', 118);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('85', 'Non-perishable', 119);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('44', 'Non-perishable', 120);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('80', 'Non-perishable', 121);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('200', 'Non-perishable', 122);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('60', 'Non-perishable', 123);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('30', 'Non-perishable', 124);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('110', 'Non-perishable', 125);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('150', 'Non-perishable', 126);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('70', 'Non-perishable', 127);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('100', 'Non-perishable', 128);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('110', 'Non-perishable', 129);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('60', 'Non-perishable', 130);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('39', 'Non-perishable', 131);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('38', 'Non-perishable', 132);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('58', 'Non-perishable', 133);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('39', 'Non-perishable', 134);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('73', 'Non-perishable', 135);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('58', 'Non-perishable', 136);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('0', 'Non-perishable', 137);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('100', 'Non-perishable', 138);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('15', 'Non-perishable', 139);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('166', 'Non-perishable', 140);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('20', 'Non-perishable', 141);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('110', 'Non-perishable', 142);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('90', 'Non-perishable', 143);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('100', 'Non-perishable', 144);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('160', 'Non-perishable', 145);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('110', 'Non-perishable', 146);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('180', 'Non-perishable', 147);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('110', 'Non-perishable', 148);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('60', 'Non-perishable', 149);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('34', 'Non-perishable', 150);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('52', 'Non-perishable', 151);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('32', 'Non-perishable', 152);
INSERT INTO `nutrition` (`calories`, `food_type`, `inventory_item_id`) VALUES ('55', 'Non-perishable', 153);

COMMIT;


-- -----------------------------------------------------
-- Data for table `optic`
-- -----------------------------------------------------
START TRANSACTION;
USE `iamlegend2`;
INSERT INTO `optic` (`inventory_item_id`) VALUES (63);
INSERT INTO `optic` (`inventory_item_id`) VALUES (64);
INSERT INTO `optic` (`inventory_item_id`) VALUES (65);
INSERT INTO `optic` (`inventory_item_id`) VALUES (66);
INSERT INTO `optic` (`inventory_item_id`) VALUES (67);
INSERT INTO `optic` (`inventory_item_id`) VALUES (68);
INSERT INTO `optic` (`inventory_item_id`) VALUES (69);
INSERT INTO `optic` (`inventory_item_id`) VALUES (70);
INSERT INTO `optic` (`inventory_item_id`) VALUES (71);

COMMIT;


-- -----------------------------------------------------
-- Data for table `weapon`
-- -----------------------------------------------------
START TRANSACTION;
USE `iamlegend2`;
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('heckler & koch', 'sub machine gun', 'germany', '1966', '19.3', '8.9', '800', '1394', '125', '200', 1);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('israel military industries', 'sub machine gun', 'israel', '1948', '17.5', '10.2', '600', '1312.34', '656.168', '800', 2);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('intratec', 'sub machine gun', 'sweden', '1985', '12.4803', '5', '600', '1181', '160', '400', 3);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('military armament coporation', 'sub machine gun', 'united states', '1970', '9.6', '4.49', '1090', '919', '164.042', '328.084', 4);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('heckler & koch', 'sub machine gun', 'germany', '1999', '17.7', '8', '650', '935', '328.084', '328.084', 5);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Steyr Mannlicher', 'assault rifle', 'austria', '2014', '31.1', '20', '680-750', '3182', '980', '1600', 6);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('hecklet & koch', 'assault rifle', 'germany', '1960', '36.2', '15.4', '750', '3117', '1312', '1312', 7);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('BAE Systems', 'assault rifle', 'united kingdom', '1994', '30.9', '20.4', '610-775', '3051', '1500', '1000', 8);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Colt manufacturing company', 'assault rifle', 'united states', '1994', '33', '14.5', '700-950', '2900', '200-600', '1640', 9);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('DPMS Firearms', 'assault rifle', 'united states', '2014', '38', '16', '', '', '', '', 10);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('DS Arms', 'assault rifle', 'united states', '2015', '32.5', '13', '', '', '', '', 11);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Fabrique Nationale de Herstal', 'assault rifle', 'belgium', '2001', '29.29', '17.44', '850', '2953', '1600', '', 12);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('heckler & koch', 'assault rifle', 'united states', '2014', '39.5', '16.5', '', '', '', '', 13);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('israel military industries', 'assault rifle', 'israel', '2001', '26.13', '18', '750-900', '2986', '1600', '', 14);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('masterpiece arms', 'assault rifle', 'united states', '2013', '38.25', '16', '', '', '', '', 15);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Fabrique Nationale de Herstal', 'rifle', 'belgium', '1953', '43', '21', '650-700', '2756', '', '', 16);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('china', 'rifle', 'china', '1983', '37.6', '17.5', '700-720', '2461', '280/500(CS/LR14)', '1706.04', 17);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Springfield', 'rifle', 'united states', '2015', '37.25', '16', '', '', '', '', 18);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Beretta', 'rifle', 'united states', '2015', '37.75', '16', '', '', '', '', 19);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('bushmaster', 'rifle', 'united states', '2006', '35.5', '18', '800', '3250', '1640', '1968.5', 20);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('colt', 'rifle', 'united states', '2016', '37.5', '16.1', '', '', '', '', 21);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Sig Sauer', 'rifle', 'germany', '2000', '38.3', '16', '', '', '', '', 22);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('izhmash', 'rifle', 'russia', '1994', '37.1', '16.3', '600', '2346', '1600', '1650', 23);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('israel military industries', 'rifle', 'israel', '1972', '41.3', '21.1', '630-750', '2788', '300-500', '', 24);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Kel-tec', 'rifle', 'united states', '2003', '26', '18', '', '2500', '1600', '1600', 25);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Beretta', 'pistol', 'italy', '1975', '8.5', '4.9', '', '1250', '160', '', 26);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Colt manufacturing company', 'pistol', 'united states', '1911', '8.25', '0.41667', '', '825', '', '', 27);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('magnum research', 'pistol', 'united states', '1982', '14.75', '0.8333', '', '1541.99', '', '656.168', 28);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('glock  ges.m.b.h', 'pistol', 'austria', '2000', '6.3', '3.46', '', '1230', '164.042', '', 29);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Fabrique Nationale de Herstal', 'pistol', 'belgium', '2000', '8.2', '4.8', '', '2050', '160.042', '4954.068', 30);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Sig Sauer', 'pistol', 'germany', '2012', '7.1', '3.9', '', '', '', '', 31);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('heckler & koch', 'pistol', 'germany', '2001', '6.8', '3.7', '', '1165', '', '', 32);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Smith & Wesson', 'pistol', 'united states', '2015', '10.5', '5', '', '', '', '', 33);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Mikhail Kalashnikov', 'machine gun', 'russia', '1961', '47.4', '23.8', '650', '2707', '3280.84', '12467.19', 34);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Beretta', 'machine gun', 'united states', '1989', '48', '20', '', '2799', '1969', '', 35);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('israel military industries', 'machine gun', 'israel', '1997', '40', '18.1', '1000', '3002', '984.252-3280.84', '3280.84', 36);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Fabrique Nationale de Herstal', 'machine gun', 'united states', '1984', '40.75', '18', '100', '3000', '2296.59', '11811.02', 37);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Zastava Arms', 'sniper rifle', 'yugoslavia', '1976', '44.7', '22', '', '2400', '2624.67', '', 38);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Izhmash', 'sniper rifle', 'russia', '1963', '48.2', '24.4', '30', '2723', '2624.67', '', 39);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Dakata Arms', 'sniper rifle', 'united states', '1997', '50', '28', '', '2850', '4593.176', '5280', 40);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Springfield', 'sniper rifle', 'united states', '1991', '44.3', '22', 'semi', '', '', '2952.76', 41);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('remington', 'sniper rifle', 'united states', '1966', '43.97', '24', '6', '2550', '2952.76', '2952.76', 42);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Beretta', 'shotgun', 'united states', '2016', '38', '18.5', '', '', '', '', 43);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Raven System', 'shotgun', 'italy', '1998', '34.8', '18.5', '', '', '164', '', 44);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('remington arms', 'shotgun', 'united states', '1951', '37.25', '14', '', '', '', '', 45);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Fabrique Nationale de Herstal', 'shotgun', 'united states', '2009', '43', '22', 'semi', '', '', '', 46);
INSERT INTO `weapon` (`manufactuer`, `weapon_type`, `country_of_origin`, `production_year`, `length`, `barrel_length`, `rate_of_fire`, `muzzel_velocity`, `effective_firing_range`, `maximum_firing_range`, `inventory_item_id`) VALUES ('Molot Oruzhie Ltd.', 'shotgun', 'russia', '2003', '38', '20', '', '', '', '', 47);

COMMIT;


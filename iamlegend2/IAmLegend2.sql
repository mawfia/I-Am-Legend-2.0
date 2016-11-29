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

-- -----------------------------------------------------
-- Schema iamlegend2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iamlegend2` DEFAULT CHARACTER SET utf8 ;
USE `iamlegend2` ;

-- -----------------------------------------------------
-- Table `iamlegend2`.`inventory_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`inventory_item` (
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
-- Table `iamlegend2`.`ammo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`ammo` (
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_ammo_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_ammo_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `iamlegend2`.`inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iamlegend2`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`customer` (
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
-- Table `iamlegend2`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`cart` (
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
    REFERENCES `iamlegend2`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iamlegend2`.`cart_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`cart_items` (
  `cart_id` INT(11) NOT NULL,
  `inventory_item_id` INT(11) NOT NULL,
  INDEX `fk_cart_items_cart1_idx` (`cart_id` ASC),
  INDEX `fk_cart_items_inventory_item1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_cart_items_cart1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `iamlegend2`.`cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_items_inventory_item1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `iamlegend2`.`inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iamlegend2`.`equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`equipment` (
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_Equipment_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_Equipment_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `iamlegend2`.`inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iamlegend2`.`nutrition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`nutrition` (
  `calories` VARCHAR(45) NULL DEFAULT NULL,
  `food_type` VARCHAR(45) NULL DEFAULT NULL,
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_nutrition_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_nutrition_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `iamlegend2`.`inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iamlegend2`.`optic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`optic` (
  `inventory_item_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_item_id`),
  INDEX `fk_optics_Product1_idx` (`inventory_item_id` ASC),
  CONSTRAINT `fk_optics_Product1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `iamlegend2`.`inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iamlegend2`.`weapon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iamlegend2`.`weapon` (
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
    REFERENCES `iamlegend2`.`inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

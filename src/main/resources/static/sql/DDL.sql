-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enjoytrip` ;

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`sido` (
                                                  `sido_code` INT NOT NULL,
                                                  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
                                                  PRIMARY KEY (`sido_code`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`gugun` (
                                                   `gugun_code` INT NOT NULL,
                                                   `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
                                                   `sido_code` INT NOT NULL,
                                                   PRIMARY KEY (`gugun_code`, `sido_code`),
                                                   INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
                                                   CONSTRAINT `gugun_to_sido_sido_code_fk`
                                                       FOREIGN KEY (`sido_code`)
                                                           REFERENCES `enjoytrip`.`sido` (`sido_code`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_info` (
                                                             `content_id` INT NOT NULL,
                                                             `content_type_id` INT NULL DEFAULT NULL,
                                                             `title` VARCHAR(100) NULL DEFAULT NULL,
                                                             `addr1` VARCHAR(100) NULL DEFAULT NULL,
                                                             `addr2` VARCHAR(50) NULL DEFAULT NULL,
                                                             `zipcode` VARCHAR(50) NULL DEFAULT NULL,
                                                             `tel` VARCHAR(50) NULL DEFAULT NULL,
                                                             `first_image` VARCHAR(200) NULL DEFAULT NULL,
                                                             `first_image2` VARCHAR(200) NULL DEFAULT NULL,
                                                             `readcount` INT NULL DEFAULT NULL,
                                                             `sido_code` INT NULL DEFAULT NULL,
                                                             `gugun_code` INT NULL DEFAULT NULL,
                                                             `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
                                                             `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
                                                             `mlevel` VARCHAR(2) NULL DEFAULT NULL,
                                                             PRIMARY KEY (`content_id`),
                                                             INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
                                                             INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
                                                             INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
                                                             CONSTRAINT `attraction_to_content_type_id_fk`
                                                                 FOREIGN KEY (`content_type_id`)
                                                                     REFERENCES `enjoytrip`.`content_type` (`content_type_id`),
                                                             CONSTRAINT `attraction_to_gugun_code_fk`
                                                                 FOREIGN KEY (`gugun_code`)
                                                                     REFERENCES `enjoytrip`.`gugun` (`gugun_code`),
                                                             CONSTRAINT `attraction_to_sido_code_fk`
                                                                 FOREIGN KEY (`sido_code`)
                                                                     REFERENCES `enjoytrip`.`sido` (`sido_code`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_description` (
                                                                    `content_id` INT NOT NULL,
                                                                    `homepage` VARCHAR(100) NULL DEFAULT NULL,
                                                                    `overview` VARCHAR(10000) NULL DEFAULT NULL,
                                                                    `telname` VARCHAR(45) NULL DEFAULT NULL,
                                                                    PRIMARY KEY (`content_id`),
                                                                    CONSTRAINT `attraction_detail_to_attraciton_id_fk`
                                                                        FOREIGN KEY (`content_id`)
                                                                            REFERENCES `enjoytrip`.`attraction_info` (`content_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_detail` (
                                                               `content_id` INT NOT NULL,
                                                               `cat1` VARCHAR(3) NULL DEFAULT NULL,
                                                               `cat2` VARCHAR(5) NULL DEFAULT NULL,
                                                               `cat3` VARCHAR(9) NULL DEFAULT NULL,
                                                               `created_time` VARCHAR(14) NULL DEFAULT NULL,
                                                               `modified_time` VARCHAR(14) NULL DEFAULT NULL,
                                                               `booktour` VARCHAR(5) NULL DEFAULT NULL,
                                                               PRIMARY KEY (`content_id`),
                                                               CONSTRAINT `attraction_detail_to_basic_content_id_fk`
                                                                   FOREIGN KEY (`content_id`)
                                                                       REFERENCES `enjoytrip`.`attraction_info` (`content_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`user` (
                                                  `user_no` INT NOT NULL AUTO_INCREMENT,
                                                  `user_id` VARCHAR(16) NOT NULL,
                                                  `user_name` VARCHAR(100) NOT NULL,
                                                  `user_pwd` VARCHAR(100) NOT NULL,
                                                  `email` VARCHAR(100) NOT NULL,
                                                  `is_admin` ENUM('T', 'F') NOT NULL DEFAULT 'F',
                                                  PRIMARY KEY (`user_no`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board_info` (
                                                        `board_type` ENUM('review', 'free', 'notice', 'team', 'plan') NOT NULL,
                                                        `board_title` VARCHAR(45) NOT NULL,
                                                        PRIMARY KEY (`board_type`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
                                                   `post_id` INT NOT NULL AUTO_INCREMENT,
                                                   `title` VARCHAR(40) NOT NULL,
                                                   `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
                                                   `content` VARCHAR(100) NOT NULL,
                                                   `user_no` INT NOT NULL,
                                                   `board_type` ENUM('review', 'free', 'notice', 'team', 'plan') NOT NULL,
                                                   `hits` INT NOT NULL DEFAULT '0',
                                                   PRIMARY KEY (`post_id`),
                                                   INDEX `board_to_board_info_board_type_fk_idx` (`board_type` ASC) VISIBLE,
                                                   INDEX `board_to_user_user_no_fk_idx` (`user_no` ASC) VISIBLE,
                                                   CONSTRAINT `board_to_user_user_no_fk`
                                                       FOREIGN KEY (`user_no`)
                                                           REFERENCES `enjoytrip`.`user` (`user_no`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION,
                                                   CONSTRAINT `board_to_board_info_board_type_fk`
                                                       FOREIGN KEY (`board_type`)
                                                           REFERENCES `enjoytrip`.`board_info` (`board_type`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`worldcup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`worldcup` (
                                                      `id` INT NOT NULL,
                                                      `title` VARCHAR(40) NULL DEFAULT NULL,
                                                      `content` VARCHAR(100) NULL DEFAULT NULL,
                                                      `img` VARCHAR(100) NULL DEFAULT NULL,
                                                      `wins` INT NULL DEFAULT '0',
                                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board_attraction_map`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board_attraction_map` (
                                                                  `post_id` INT NOT NULL,
                                                                  `content_id` INT NULL,
                                                                  PRIMARY KEY (`post_id`),
                                                                  INDEX `map_to_attraction_info_content_id_fk_idx` (`content_id` ASC) VISIBLE,
                                                                  CONSTRAINT `map_to_board_post_id_fk`
                                                                      FOREIGN KEY (`post_id`)
                                                                          REFERENCES `enjoytrip`.`board` (`post_id`)
                                                                          ON DELETE NO ACTION
                                                                          ON UPDATE NO ACTION,
                                                                  CONSTRAINT `map_to_attraction_info_content_id_fk`
                                                                      FOREIGN KEY (`content_id`)
                                                                          REFERENCES `enjoytrip`.`attraction_info` (`content_id`)
                                                                          ON DELETE NO ACTION
                                                                          ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`user_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`user_details` (
                                                          `user_no` INT NOT NULL,
                                                          `gugun_code` INT NULL,
                                                          PRIMARY KEY (`user_no`),
                                                          INDEX `user_details_to_gugun_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
                                                          CONSTRAINT `user_details_to_user_user_no_fk`
                                                              FOREIGN KEY (`user_no`)
                                                                  REFERENCES `enjoytrip`.`user` (`user_no`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `user_details_to_gugun_gugun_code_fk`
                                                              FOREIGN KEY (`gugun_code`)
                                                                  REFERENCES `enjoytrip`.`gugun` (`gugun_code`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;

USE `enjoytrip` ;

-- -----------------------------------------------------
-- Placeholder table for view `enjoytrip`.`user_admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`user_admin` (`user_no` INT, `user_id` INT, `user_name` INT, `user_pwd` INT, `email` INT, `is_admin` INT);

-- -----------------------------------------------------
-- Placeholder table for view `enjoytrip`.`user_cummon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`user_cummon` (`user_no` INT, `user_id` INT, `user_name` INT, `user_pwd` INT, `email` INT, `is_admin` INT);

-- -----------------------------------------------------
-- View `enjoytrip`.`user_admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`user_admin`;
USE `enjoytrip`;
CREATE  OR REPLACE VIEW `user_admin` AS
SELECT *
FROM user
WHERE is_admin = 'T';

-- -----------------------------------------------------
-- View `enjoytrip`.`user_cummon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`user_cummon`;
USE `enjoytrip`;
CREATE  OR REPLACE VIEW `user_cummon` AS
SELECT *
FROM user
WHERE is_admin = 'T';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO USER
(user_id,user_name,user_pwd,email,is_admin)
VALUES
    ('admin','관리자','1234','admin@ssafy.com','T'),
    ('ssafy','김싸피','1234','ssafy@ssafy.com','T');
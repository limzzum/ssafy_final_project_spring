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

DROP SCHEMA IF EXISTS `enjoytrip`;

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`content_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`content_type` (
                                                          `content_type_id` INT NOT NULL,
                                                          `content_type_name` VARCHAR(45) NULL,
                                                          PRIMARY KEY (`content_type_id`))
    ENGINE = InnoDB;


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
-- Table `enjoytrip`.`board_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board_info` (
                                                        `board_type` ENUM('review', 'free', 'notice', 'team', 'plan') NOT NULL,
                                                        `board_title` VARCHAR(45) NOT NULL,
                                                        PRIMARY KEY (`board_type`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


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
                                                  PRIMARY KEY (`user_no`),
                                                  UNIQUE INDEX `user_id` (`user_id` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
                                                   `post_id` INT NOT NULL AUTO_INCREMENT,
                                                   `title` VARCHAR(40) NOT NULL,
                                                   `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                   `content` VARCHAR(100) NOT NULL,
                                                   `user_no` INT NOT NULL,
                                                   `board_type` ENUM('review', 'free', 'notice', 'team', 'plan') NOT NULL,
                                                   `hits` INT NOT NULL DEFAULT '0',
                                                   PRIMARY KEY (`post_id`),
                                                   INDEX `board_to_board_info_board_type_fk_idx` (`board_type` ASC) VISIBLE,
                                                   INDEX `board_to_user_user_no_fk_idx` (`user_no` ASC) VISIBLE,
                                                   CONSTRAINT `board_to_board_info_board_type_fk`
                                                       FOREIGN KEY (`board_type`)
                                                           REFERENCES `enjoytrip`.`board_info` (`board_type`),
                                                   CONSTRAINT `board_to_user_user_no_fk`
                                                       FOREIGN KEY (`user_no`)
                                                           REFERENCES `enjoytrip`.`user` (`user_no`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board_attraction_map`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board_attraction_map` (
                                                                  `post_id` INT NOT NULL,
                                                                  `content_id` INT NULL DEFAULT NULL,
                                                                  PRIMARY KEY (`post_id`),
                                                                  INDEX `map_to_attraction_info_content_id_fk_idx` (`content_id` ASC) VISIBLE,
                                                                  CONSTRAINT `map_to_attraction_info_content_id_fk`
                                                                      FOREIGN KEY (`content_id`)
                                                                          REFERENCES `enjoytrip`.`attraction_info` (`content_id`),
                                                                  CONSTRAINT `map_to_board_post_id_fk`
                                                                      FOREIGN KEY (`post_id`)
                                                                          REFERENCES `enjoytrip`.`board` (`post_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`user_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`user_details` (
                                                          `user_no` INT NOT NULL,
                                                          `gugun_code` INT NULL DEFAULT NULL,
                                                          PRIMARY KEY (`user_no`),
                                                          INDEX `user_details_to_gugun_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
                                                          CONSTRAINT `user_details_to_gugun_gugun_code_fk`
                                                              FOREIGN KEY (`gugun_code`)
                                                                  REFERENCES `enjoytrip`.`gugun` (`gugun_code`),
                                                          CONSTRAINT `user_details_to_user_user_no_fk`
                                                              FOREIGN KEY (`user_no`)
                                                                  REFERENCES `enjoytrip`.`user` (`user_no`))
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
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`ssafy`@`localhost` SQL SECURITY DEFINER VIEW `enjoytrip`.`user_admin` AS select `enjoytrip`.`user`.`user_no` AS `user_no`,`enjoytrip`.`user`.`user_id` AS `user_id`,`enjoytrip`.`user`.`user_name` AS `user_name`,`enjoytrip`.`user`.`user_pwd` AS `user_pwd`,`enjoytrip`.`user`.`email` AS `email`,`enjoytrip`.`user`.`is_admin` AS `is_admin` from `enjoytrip`.`user` where (`enjoytrip`.`user`.`is_admin` = 'T');

-- -----------------------------------------------------
-- View `enjoytrip`.`user_cummon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`user_cummon`;
USE `enjoytrip`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`ssafy`@`localhost` SQL SECURITY DEFINER VIEW `enjoytrip`.`user_cummon` AS select `enjoytrip`.`user`.`user_no` AS `user_no`,`enjoytrip`.`user`.`user_id` AS `user_id`,`enjoytrip`.`user`.`user_name` AS `user_name`,`enjoytrip`.`user`.`user_pwd` AS `user_pwd`,`enjoytrip`.`user`.`email` AS `email`,`enjoytrip`.`user`.`is_admin` AS `is_admin` from `enjoytrip`.`user` where (`enjoytrip`.`user`.`is_admin` = 'T');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into content_type values(12,'관광지');
insert into content_type values(14,'문화시설');
insert into content_type values(15,'축제공연행사');
insert into content_type values(25,'여행코스');
insert into content_type values(28,'레포츠');
insert into content_type values(32,'숙박');
insert into content_type values(38,'쇼핑');
insert into content_type values(39,'음식점');
insert into board_info values ('review', 'reviewPage');
insert into board_info values ('free', 'freePage');
insert into board_info values ('notice', 'noticePage');
insert into board_info values ('team', 'teamPage');
insert into board_info values ('plan', 'planPage');
INSERT INTO USER
(user_no,user_id,user_name,user_pwd,email,is_admin)
VALUES
    (1,'admin','관리자','1234','admin@ssafy.com','T'),
    (2,'ssafy','김싸피','1234','ssafy@ssafy.com','T');
LOCK TABLES `worldcup` WRITE;
/*!40000 ALTER TABLE `worldcup` DISABLE KEYS */;
INSERT INTO `worldcup` VALUES (1,'울릉도','여기 해산물이 그렇게 맛있대요','worldcup1.png',1),(2,'제주도','돈있으면 당연히 제주도','worldcup2.png',132),(3,'경주','수학여행 국룰 경주','worldcup3.png',70),(4,'부산','해운대 보유 도시','worldcup4.png',57),(5,'울산','대충 울산 홍보문구','worldcup5.png',12),(6,'강릉','대충 강릉 홍보문구','worldcup6.png',48),(7,'여수','여수 밤바다~ 이 조명에 담긴~','worldcup7.png',35),(8,'해남','대충 해남 홍보문구','worldcup8.png',12);
/*!40000 ALTER TABLE `worldcup` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board`
(title,create_time,content,user_no,board_type,hits)
VALUES
    ("여행어때 사이트 오픈!!",now(),"우리나라의 여러 관광지들을 검색하고 계획, 리뷰, 추천 등 여행 정보 수집에 도움을 주는 여행어때 사이트를 오픈했습니다!! 많은 관심과 사랑 부탁드려요",1,"notice",12),
    ("관광지 검색 방법",now(),"잘 해보세요",1,"notice",12),
    ("나만의 여행 계획을 등록하고 다른 사람에게 공유해보세요",now(),"추첨을 통해 조용준 교수님의 디버깅 5회 쿠폰을 증정합니다",1,"notice",12),
    ("관광지 월드컵 기능 업데이트",now(),"여러 관광지 중 나만의 베스트 1픽을 골라보세요",1,"notice",12),
    ("맛집 뽑기 기능 업데이트",now(),"선택장애를 위한 맛집 추천",1,"notice",12),
    ("제주도 관광 후기",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기2",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기2",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기3",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기3",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기4",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기4",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기5",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기5",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기6",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기6",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기7",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기7",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기8",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기8",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기9",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기9",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기10",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기10",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기11",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기11",now(),"울릉도 관광 후기",2,"review",1),
    ("제주도 관광 후기12",now(),"제주도 관광 후기",2,"review",12),
    ("울릉도 관광 후기12",now(),"울릉도 관광 후기",2,"review",1)
;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
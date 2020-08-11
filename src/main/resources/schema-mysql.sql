create schema if not exists samurai;
use samurai;

CREATE TABLE if not exists `super_category` (
  `super_category_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `super_category_enable` bit(1) DEFAULT b'1',
  `super_category_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`super_category_id`)
);


CREATE TABLE if not exists `sub_category` (
  `sub_category_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `sub_category_enable` bit(1) DEFAULT b'1',
  `sub_category_name` varchar(100) DEFAULT NULL,
  `super_category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sub_category_id`),
  KEY `FKhjpo5kw7gbmm8xhyok8799d45` (`super_category_id`),
  CONSTRAINT `FKhjpo5kw7gbmm8xhyok8799d45` FOREIGN KEY (`super_category_id`) REFERENCES `super_category` (`super_category_id`)
);


CREATE TABLE IF NOT EXISTS `service_library` (
  `service_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `logo_image` longblob,
  `service_decommisioned` bit(1) DEFAULT b'0',
  `service_description` varchar(200) DEFAULT NULL,
  `service_name` varchar(100) DEFAULT NULL,
  `type_of_service` varchar(50) DEFAULT NULL,
  `sub_category_id` bigint DEFAULT NULL,
  `super_category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `FKbgly3u1kwjlcuicyo18d1xvlb` (`sub_category_id`),
  KEY `FK7sf2foa8jud339f477himo4gf` (`super_category_id`),
  CONSTRAINT `FK7sf2foa8jud339f477himo4gf` FOREIGN KEY (`super_category_id`) REFERENCES `super_category` (`super_category_id`),
  CONSTRAINT `FKbgly3u1kwjlcuicyo18d1xvlb` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_category` (`sub_category_id`)
);


CREATE TABLE IF NOT EXISTS `service_instance` (
  `service_instance_id` bigint NOT NULL AUTO_INCREMENT,
  `is_decomissioned` bit(1) DEFAULT b'0',
  `is_inactive` bit(1) DEFAULT b'0',
  `service_instance_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`service_instance_id`)
);

CREATE TABLE IF NOT EXISTS `service_library_instance` (
  `service_instance_id` bigint NOT NULL,
  `service_id` bigint NOT NULL,
  KEY `FK8hfs47y95gfgqs9k9m8isos7f` (`service_id`),
  KEY `FKkqqkj3tpml7jkpqcyx2ec9mgk` (`service_instance_id`),
  CONSTRAINT `FK8hfs47y95gfgqs9k9m8isos7f` FOREIGN KEY (`service_id`) REFERENCES `service_library` (`service_id`),
  CONSTRAINT `FKkqqkj3tpml7jkpqcyx2ec9mgk` FOREIGN KEY (`service_instance_id`) REFERENCES `service_instance` (`service_instance_id`)
);

CREATE TABLE if not exists `samurai_rpa` (
  `samurai_rpa_id` bigint NOT NULL AUTO_INCREMENT,
  `eform_id` bigint DEFAULT NULL,
  `eform_status_by_platform` varchar(50) DEFAULT NULL,
  `eform_status_update_date` datetime(6) DEFAULT NULL,
  `impact` varchar(20) DEFAULT NULL,
  `platform_remarks` varchar(100) DEFAULT NULL,
  `request_date_time` datetime(6) DEFAULT NULL,
  `severity` varchar(20) DEFAULT NULL,
  `type_of_request` varchar(20) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `zeva_request_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`samurai_rpa_id`)
);

CREATE TABLE if not exists `samurai_analytica` (
  `samurai_analytica_id` bigint NOT NULL AUTO_INCREMENT,
  `db_connection_url` varchar(100) DEFAULT NULL,
  `resolution_id` bigint DEFAULT NULL,
  `resolution_platform` varchar(100) DEFAULT NULL,
  `resolution_rank` bigint DEFAULT NULL,
  `resolution_response` varchar(1000) DEFAULT NULL,
  `solution_type` varchar(50) DEFAULT NULL,
  `samurai_rpa_id` bigint DEFAULT NULL,
  PRIMARY KEY (`samurai_analytica_id`),
  KEY `FKdwjrdfosu9sij0w79xpk44oon` (`samurai_rpa_id`),
  CONSTRAINT `FKdwjrdfosu9sij0w79xpk44oon` FOREIGN KEY (`samurai_rpa_id`) REFERENCES `samurai_rpa` (`samurai_rpa_id`)
);


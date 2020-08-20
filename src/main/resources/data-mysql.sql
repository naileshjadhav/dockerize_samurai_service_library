create schema if not exists samurai;
use samurai;

------super_category--------------
INSERT ignore INTO super_category (super_category_id, creation_date, super_category_enable,super_category_name) values (1, '2020-05-27 14:34:21.778000', 1, 'Tools for AI-Led Dev');
INSERT ignore INTO super_category (super_category_id, creation_date, super_category_enable,super_category_name) values (2, '2020-05-27 14:34:21.778000', 1, 'Industry-Led Dev Tools');
------sub_category--------------
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (2, '2020-05-27 14:34:21.778000', 1, 'Zensar’s AI/ML Tools', 1);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (3, '2020-05-27 14:34:21.778000', 1, 'IT Service Management', 1);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (4, '2020-05-27 14:34:21.778000', 1, 'IT System Monitoring', 1);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (1, '2020-05-27 14:34:21.778000', 1, 'Application Cloud Platforms', 2);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (5, '2020-05-27 14:34:21.778000', 1, 'Low Code Platforms', 2);
--Added new sub_category on 12082020-------
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (6, '2020-08-12 14:34:21.778000', 1, 'DevOps', 2);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (7, '2020-08-12 14:34:21.778000', 1, 'Quality Assurance', 2);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (8, '2020-08-12 14:34:21.778000', 1, 'SRE', 2);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (9, '2020-08-12 14:34:21.778000', 1, 'Automation Platforms', 2);
INSERT ignore INTO `samurai`.`sub_category` (`sub_category_id`, `creation_date`, `sub_category_enable`, `sub_category_name`, `super_category_id`) VALUES (10, '2020-08-17 14:34:21.778000', 1, 'Zensar Digital Service Management', 1);
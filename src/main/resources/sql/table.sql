create database test;
use test;

create table salary(
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `salary` float);

create table employee(
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(20),
    `salary_id` INT(11));
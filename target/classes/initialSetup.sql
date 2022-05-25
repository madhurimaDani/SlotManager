create database slotmanager;

CREATE TABLE `slot` (
                        `slot_id` int NOT NULL AUTO_INCREMENT,
                        `date` varchar(255) DEFAULT NULL,
                        `doctor` varchar(255) DEFAULT NULL,
                        `end_time` varchar(255) DEFAULT NULL,
                        `is_booked` varchar(255) DEFAULT NULL,
                        `start_time` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`slot_id`)
) ENGINE=InnoDB;

insert into slot(date, start_time, end_time, doctor, is_booked) values("2022/05/26","7AM","8AM","doctor1","yes");
insert into slot(date, start_time, end_time, doctor, is_booked) values("2022/05/26","8AM","9AM","doctor1","no");
insert into slot(date, start_time, end_time, doctor, is_booked) values("2022/05/26","9AM","10AM","doctor1","no");
insert into slot(date, start_time, end_time, doctor, is_booked) values("2022/05/26","3PM","4PM","doctor2","no");
insert into slot(date, start_time, end_time, doctor, is_booked) values("2022/05/26","4PM","5PM","doctor2","no");


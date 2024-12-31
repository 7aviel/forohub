create table perfil(
                       `perfil_id` bigint NOT NULL AUTO_INCREMENT,
                       `creation_date` date DEFAULT NULL,
                       `status` enum('CLOSED','OPEN') DEFAULT NULL,
                       `title` varchar(255) NOT NULL,
                       `course_id` bigint DEFAULT NULL,
                       `user_user_id` bigint DEFAULT NULL,
                       PRIMARY KEY (`perfil_id`),
                       KEY `FKtktaeeogyyjfv5ylr4r06ig1l` (`course_id`),
                       KEY `FKevuv7p02842lhi77hguyhcgos` (`user_user_id`),
                       CONSTRAINT `FK_topic_user` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`),
                       CONSTRAINT `FK_topic_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
)
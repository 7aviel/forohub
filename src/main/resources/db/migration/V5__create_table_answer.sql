create table answer(
                       `answer_id` bigint NOT NULL AUTO_INCREMENT,
                       `creation_date` date DEFAULT NULL,
                       `message` varchar(255) NOT NULL,
                       `solution` varchar(255) DEFAULT NULL,
                       `author_user_id` bigint NOT NULL,
                       `topic_id` bigint DEFAULT NULL,
                       PRIMARY KEY (`answer_id`),
                       KEY `FKgq4umc6kpjnu76vceypcuurio` (`author_user_id`),
                       KEY `FKgew5a7jo3778hfx2kmdy78826` (`topic_id`),
                       CONSTRAINT `FKgew5a7jo3778hfx2kmdy78826` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`),
                       CONSTRAINT `FKgq4umc6kpjnu76vceypcuurio` FOREIGN KEY (`author_user_id`) REFERENCES `user` (`user_id`)
)
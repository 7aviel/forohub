create table user(
                     `user_id` bigint NOT NULL AUTO_INCREMENT,
                     `email` varchar(255) NOT NULL UNIQUE,
                     `name` varchar(255) NOT NULL,
                     `password` varchar(255) NOT NULL,
                     PRIMARY KEY (`user_id`)
)
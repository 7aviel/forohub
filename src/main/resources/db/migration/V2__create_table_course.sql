create table course(
                       `course_id` bigint NOT NULL AUTO_INCREMENT,
                       `category_enum` enum('BACKEND','FRONTEND') NOT NULL,
                       `name` varchar(255) NOT NULL,
                       PRIMARY KEY (`course_id`)
)
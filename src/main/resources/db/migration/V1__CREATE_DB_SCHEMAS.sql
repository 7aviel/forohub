-- Create 'user' table
CREATE TABLE IF NOT EXISTS `user` (
                                      `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                      `email` VARCHAR(255) UNIQUE NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`user_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create 'course' table
CREATE TABLE IF NOT EXISTS `course` (
                                        `course_id` BIGINT NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(255) NOT NULL,
    `category` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`course_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create 'topic' table
CREATE TABLE IF NOT EXISTS `topic` (
                                       `topic_id` BIGINT NOT NULL AUTO_INCREMENT,
                                       `creation_date` DATE NOT NULL,
                                       `title` VARCHAR(255) NOT NULL,
    `course_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `status` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`topic_id`),
    KEY `FKtktaeeogyyjfv5ylr4r06ig1l` (`course_id`),
    KEY `FK38wu074adxipuj6a9ifd7jv5f` (`user_id`),
    CONSTRAINT `FK38wu074adxipuj6a9ifd7jv5f` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `FKtktaeeogyyjfv5ylr4r06ig1l` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create 'answer' table
CREATE TABLE IF NOT EXISTS `answer` (
                                        `answer_id` BIGINT NOT NULL AUTO_INCREMENT,
                                        `creation_date` DATE NOT NULL,
                                        `message` VARCHAR(255) NOT NULL,
    `solution` VARCHAR(255) DEFAULT NULL,
    `user_id` BIGINT NOT NULL,
    `topic_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`answer_id`),
    KEY `FK68tbcw6bunvfjaoscaj851xpb` (`user_id`),
    KEY `FKgew5a7jo3778hfx2kmdy78826` (`topic_id`),
    CONSTRAINT `FK68tbcw6bunvfjaoscaj851xpb` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `FKgew5a7jo3778hfx2kmdy78826` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create 'perfil' table
CREATE TABLE IF NOT EXISTS `perfil` (
                                        `perfil_id` BIGINT NOT NULL AUTO_INCREMENT,
                                        `username` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`perfil_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create 'user_perfil' table
CREATE TABLE IF NOT EXISTS `user_perfil` (
                                             `user_id` BIGINT NOT NULL,
                                             `perfil_id` BIGINT NOT NULL,
                                             KEY `FKso6jevks1x6may02n9s1j0vll` (`perfil_id`),
    KEY `FKlvck6te979di4yfqyu92nvmxd` (`user_id`),
    CONSTRAINT `FKlvck6te979di4yfqyu92nvmxd` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `FKso6jevks1x6may02n9s1j0vll` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`perfil_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

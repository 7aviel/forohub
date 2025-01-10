-- Correct the ALTER TABLE course statements

ALTER TABLE course
    DROP COLUMN category_enum;


ALTER TABLE course
    ADD COLUMN category VARCHAR(255) NOT NULL;

-- Correct the ALTER TABLE topic statements
ALTER TABLE topic
DROP COLUMN status;

ALTER TABLE topic
    ADD COLUMN status VARCHAR(255) DEFAULT NULL;

-- Correct the ALTER TABLE answer statements
ALTER TABLE answer
    MODIFY topic_id BIGINT DEFAULT NULL;

-- Correct the ALTER TABLE user statements
ALTER TABLE user
    MODIFY email VARCHAR(255) NOT NULL UNIQUE;

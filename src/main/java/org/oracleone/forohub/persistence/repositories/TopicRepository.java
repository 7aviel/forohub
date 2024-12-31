package org.oracleone.forohub.persistence.repositories;

import org.oracleone.forohub.persistence.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}

package org.oracleone.forohub.persistence.repositories;

import org.oracleone.forohub.persistence.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

}

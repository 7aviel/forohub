package org.oracleone.forohub.persistence.repositories;

import jakarta.transaction.Transactional;
import org.oracleone.forohub.persistence.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

    @Query("SELECT t FROM Topic t WHERE t.title =:title")
    @Transactional
    Optional<Topic> getTopicByTitle(@Param("title") String title);
}

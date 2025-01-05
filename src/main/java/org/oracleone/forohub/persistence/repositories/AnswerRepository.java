package org.oracleone.forohub.persistence.repositories;

import jakarta.transaction.Transactional;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    @Query("SELECT a FROM Answer a WHERE a.author =: author")
    @Transactional
    Optional<List<Answer>> findByName(@Param("author") String author);
}

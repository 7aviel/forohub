package org.oracleone.forohub.persistence.repositories;

import org.oracleone.forohub.persistence.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
}

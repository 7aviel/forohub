package org.oracleone.forohub.persistence.repositories;

import org.oracleone.forohub.persistence.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

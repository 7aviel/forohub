package org.oracleone.forohub.persistence.repositories;

import jakarta.transaction.Transactional;
import org.oracleone.forohub.persistence.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.name =:name")
    @Transactional
    Optional<Course> findByName(@Param("name") String name);
}

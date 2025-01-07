package org.oracleone.forohub.persistence.repositories;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import org.oracleone.forohub.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.name =:name AND u.email =:email")
    @Transactional
    Optional<User> findByNameAndEmail(@Param("name") String name, @Param("email") @Email String email);

}

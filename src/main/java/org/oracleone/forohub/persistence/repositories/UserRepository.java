package org.oracleone.forohub.persistence.repositories;

import org.oracleone.forohub.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
/* MARKED TO DELETE
  //  @Query("SELECT u FROM User u WHERE u.name =:name AND u.email =:email")
    Optional<User> findByNameAndEmail(@Param("name") String name, @Param("email") @Email String email);
*/
    Optional<User> findByEmail(String username);
}

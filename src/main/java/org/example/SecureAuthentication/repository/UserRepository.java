package org.example.SecureAuthentication.repository;

import org.example.SecureAuthentication.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
  boolean findUsersByUsername(String username);
  Users findByUsername(String username);
}

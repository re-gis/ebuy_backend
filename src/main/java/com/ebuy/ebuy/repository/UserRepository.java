package com.ebuy.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebuy.ebuy.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}

package com.example.auth_service.persistence.respositories;

import com.example.auth_service.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users_auth_service WHERE name = :name", nativeQuery = true)
    Optional<User> findByName(String name);

    @Query(value = "SELECT * FROM users_auth_service WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "UPDATE users_auth_service SET active = false WHERE id = :id", nativeQuery = true)
    void blockUserById(Long id);

    @Query(value = "UPDATE users_auth_service SET active = true WHERE id = :id", nativeQuery = true)
    void unblockUserById(Long id);
}


package com.example.auth_service.persistence.respositories;

import com.example.auth_service.persistence.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users_auth_service WHERE name = :name", nativeQuery = true)
    Optional<User> findByName(String name);

    @Query(value = "SELECT * FROM users_auth_service WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT active FROM users_auth_service WHERE id = :id", nativeQuery = true)
    Boolean isUserActive(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users_auth_service SET active = false WHERE id = :id", nativeQuery = true)
    int blockUserById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users_auth_service SET active = true WHERE id = :id", nativeQuery = true)
    int unblockUserById(Long id);

    @Transactional
    default void blockAllUsers() {
        List<User> users = findAll();
        users.forEach(user -> user.setActive(false));
        saveAll(users);
    }

    @Transactional
    default void unblockAllUsers() {
        List<User> users = findAll();
        users.forEach(user -> user.setActive(true));
        saveAll(users);
    }
}


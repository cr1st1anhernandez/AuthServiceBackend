package com.example.auth_service.controllers;

import com.example.auth_service.persistence.entities.User;
import com.example.auth_service.persistence.respositories.UserRepository;
import com.example.auth_service.services.IUserService;
import com.example.auth_service.services.models.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/block/{id}")
    public ResponseEntity<?> blockUserById(@PathVariable Long id){
        try {
            boolean isBlocked = userService.blockUserById(id);
            if (isBlocked) {
                return ResponseEntity.ok(id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or could not be blocked");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/unblock/{id}")
    public ResponseEntity<?> unblockUserById(@PathVariable Long id){
        try {
            boolean isUnblocked = userService.unblockUserById(id);
            if (isUnblocked) {
                return ResponseEntity.ok(id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or could not be unblocked");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/data/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){
        Optional<UserDTO> userDTOOptional = userService.getUserByName(name);
        if (userDTOOptional.isPresent()) {
            return ResponseEntity.ok(userDTOOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/block/all")
    public ResponseEntity<?> blockAllUsers() {
        userService.blockAllUsers();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/unblock/all")
    public ResponseEntity<?> unblockAllUsers() {
        userService.unblockAllUsers();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/lastlogin")
    public ResponseEntity<?> updateLastLogin(@RequestParam String name) {
        User user = userService.findByName(name);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }
}

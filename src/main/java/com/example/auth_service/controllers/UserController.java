package com.example.auth_service.controllers;

import com.example.auth_service.persistence.entities.User;
import com.example.auth_service.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/block/{id}")
    public ResponseEntity<?> blockUserById(@PathVariable Long id){
        userService.blockUserById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unblock/{id}")
    public ResponseEntity<?> unblockUserById(@PathVariable Long id){
        userService.unblockUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    private ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}

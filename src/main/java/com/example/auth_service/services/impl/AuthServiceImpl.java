package com.example.auth_service.services.impl;

import com.example.auth_service.persistence.entities.User;
import com.example.auth_service.persistence.respositories.UserRepository;
import com.example.auth_service.services.IAuthService;
import com.example.auth_service.services.IJWTUtilityService;
import com.example.auth_service.services.models.dtos.LoginDTO;
import com.example.auth_service.services.models.dtos.ResponseDTO;
import com.example.auth_service.services.models.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidations userValidations;

    @Override
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<User> user = userRepository.findByName(loginRequest.getName());

            if (user.isEmpty()) {
                jwt.put("error", "User not registered!");
                return jwt;
            }
            Long userId = user.get().getId();
            if (!userRepository.isUserActive(userId)) {
                jwt.put("error", "User is inactive!");
                return jwt;
            }
            if (verifyPassword(loginRequest.getPassword(), user.get().getPassword())) {
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "Invalid credentials");
            }
            return jwt;
        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }

    @Override
    public ResponseDTO register(User user) throws Exception {
        try {
            ResponseDTO response = userValidations.validate(user);
            List<User> getAllUsers = userRepository.findAll();

            if (response.getNumOfErrors() > 0){
                return response;
            }

            Optional<User> existingUserByName = userRepository.findByName(user.getName());
            if (existingUserByName.isPresent()) {
                response.setMessage("User already exists!");
                return response;
            }

            Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
            if (existingUserByEmail.isPresent()) {
                response.setMessage("Email already register!");
                return response;
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("User created successfully!");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}

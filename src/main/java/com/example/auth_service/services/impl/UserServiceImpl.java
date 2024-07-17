package com.example.auth_service.services.impl;

import com.example.auth_service.persistence.entities.User;
import com.example.auth_service.persistence.respositories.UserRepository;
import com.example.auth_service.services.IUserService;
import com.example.auth_service.services.models.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public boolean blockUserById(Long id){
        Boolean isActive = userRepository.isUserActive(id);
        if (Boolean.FALSE.equals(isActive)) {
            throw new IllegalStateException("User already blocked");
        }
        int affectedRows = userRepository.blockUserById(id);
        return affectedRows > 0;
    }

    public boolean unblockUserById(Long id){
        Boolean isActive = userRepository.isUserActive(id);
        if (Boolean.TRUE.equals(isActive)) {
            throw new IllegalStateException("User already unblocked");
        }
        int affectedRows = userRepository.unblockUserById(id);
        return affectedRows > 0;
    }

    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToUserDTO).toList();
    }

    public void blockAllUsers(){
        userRepository.blockAllUsers();
    }

    public void unblockAllUsers(){
        userRepository.unblockAllUsers();
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public User findByName(String name){
        return userRepository.findByName(name).orElse(null);
    }

    public Optional<UserDTO> getUserByName(String name){
        User user = userRepository.findByName(name).orElse(null);
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(convertToUserDTO(user));
    }

    private UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setLastLogin(user.getLastLogin());
        userDTO.setActive(user.isActive());
        return userDTO;
    }
}

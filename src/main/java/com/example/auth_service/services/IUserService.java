package com.example.auth_service.services;

import com.example.auth_service.persistence.entities.User;
import com.example.auth_service.services.models.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public User findByName(String name);
    public List<UserDTO> findAllUsers();
    public void deleteUserById(Long id);
    public boolean blockUserById(Long id);
    public boolean unblockUserById(Long id);
    public void blockAllUsers();
    public void unblockAllUsers();
    public void deleteAllUsers();
    public Optional<UserDTO> getUserByName(String name);
}

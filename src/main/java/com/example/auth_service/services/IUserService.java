package com.example.auth_service.services;

import com.example.auth_service.persistence.entities.User;

import java.util.List;

public interface IUserService {
    public void deleteUserById(Long id);
    public void blockUserById(Long id);
    public void unblockUserById(Long id);
    public List<User> findAllUsers();
}

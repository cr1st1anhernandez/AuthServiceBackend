package com.example.auth_service.services;

import com.example.auth_service.persistence.entities.User;
import com.example.auth_service.services.models.dtos.LoginDTO;
import com.example.auth_service.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String , String> login(LoginDTO login) throws Exception;

    public ResponseDTO register(User user) throws Exception;
}

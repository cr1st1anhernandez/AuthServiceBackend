package com.example.auth_service.services.models.validations;

import com.example.auth_service.persistence.entities.User;
import com.example.auth_service.services.models.dtos.ResponseDTO;

public class UserValidations {

    public ResponseDTO validate(User user) {
        ResponseDTO response = new ResponseDTO();
        response.setNumOfErrors(0);
        if(user.getName() == null || user.getName().isEmpty()) {
            response.setNumOfErrors(1);
            response.setMessage("Name is required");
        } else if(user.getName().length() < 5) {
            response.setNumOfErrors(1);
            response.setMessage("Name must be at least 5 characters");
        } else if(user.getPassword() == null || user.getPassword().isEmpty()) {
            response.setNumOfErrors(1);
            response.setMessage("Password is required");
        } else if(!user.getPassword().matches("^(?=.*[A-Z])(?=.*[\\W_]).{8,}$")) {
            response.setNumOfErrors(1);
            response.setMessage("Password must be at least 8 characters long, contain at least one uppercase letter, and one special character.");
        }
        return response;
    }
}

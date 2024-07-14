package com.example.auth_service.services.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    private Integer numOfErrors;
    private String message;
}

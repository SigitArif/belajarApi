package com.example.belajarAPI.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResVO {
    
    private String[] role;
    private String token;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("fullname")
    private String fullName;
}
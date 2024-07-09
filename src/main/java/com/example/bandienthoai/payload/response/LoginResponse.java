package com.example.bandienthoai.payload.response;

import com.example.bandienthoai.payload.response.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private String jwt;
    private AccountResponse account;
}

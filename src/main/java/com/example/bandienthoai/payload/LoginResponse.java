package com.example.bandienthoai.payload;

import com.example.bandienthoai.model.Account;
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

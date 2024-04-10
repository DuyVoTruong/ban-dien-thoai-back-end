package com.example.bandienthoai.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {
    private Long id;
    private String username;
    private String role;
}

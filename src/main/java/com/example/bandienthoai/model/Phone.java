package com.example.bandienthoai.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    private long id;
    private String name;
    private String brand;
    private long price;
    private String description;
}

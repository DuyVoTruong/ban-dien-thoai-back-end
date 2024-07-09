package com.example.bandienthoai.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
    private Long id;
    private String name;
    private String brand;
    private String price;
    private String description;
    private MultipartFile file;
    private String url;
    private Long sellerId;
}

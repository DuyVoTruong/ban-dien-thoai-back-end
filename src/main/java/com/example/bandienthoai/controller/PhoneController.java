package com.example.bandienthoai.controller;

import com.example.bandienthoai.model.Phone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ban-dien-thoai/phone")
public class PhoneController {
    @GetMapping("/all")
    public ResponseEntity<List> getAllPhone(){
        List<Phone> phones = new ArrayList<Phone>();
        phones.add(new Phone(1,"Samsung A30","Samsung",3000000,"tot" ));
        phones.add(new Phone(2,"Samsung A40","Samsung",4000000,"tot" ));
        phones.add(new Phone(3,"Samsung A50","Samsung",5000000,"tot" ));
        phones.add(new Phone(4,"Samsung A60","Samsung",6000000,"tot" ));
        phones.add(new Phone(5,"Samsung A70","Samsung",7000000,"tot" ));
        phones.add(new Phone(6,"Samsung A80","Samsung",8000000,"tot" ));
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }
}

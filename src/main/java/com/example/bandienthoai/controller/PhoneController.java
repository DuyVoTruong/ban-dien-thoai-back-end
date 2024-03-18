package com.example.bandienthoai.controller;

import com.example.bandienthoai.model.Phone;
import com.example.bandienthoai.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ban-dien-thoai/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/all")
    public ResponseEntity<List> getAllPhone(){
        List phones = phoneService.getAllPhone();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Phone> addPhone(@RequestBody Phone phone){
        Phone savedPhone = phoneService.addPhone(phone);
        return new ResponseEntity<>(savedPhone, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Phone> updatePhone(@RequestBody Phone phone){
        Phone updatedPhone = phoneService.updatePhone(phone);
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhone(@PathVariable long id){
        phoneService.deletePhone(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

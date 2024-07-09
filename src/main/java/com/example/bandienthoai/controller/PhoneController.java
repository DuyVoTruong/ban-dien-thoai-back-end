package com.example.bandienthoai.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.bandienthoai.model.Phone;
import com.example.bandienthoai.payload.request.DeleteImageRequest;
import com.example.bandienthoai.payload.request.PhoneRequest;
import com.example.bandienthoai.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/phone")
@CrossOrigin(origins = {"http://localhost:3000", "https://ban-dien-thoai-front-end-duyvotruongs-projects.vercel.app"})
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/all")
    public ResponseEntity<List> getAllPhone(){
        List phones = phoneService.getAllPhone();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
    public ResponseEntity addPhone(@ModelAttribute PhoneRequest phoneRequest){
        try {
            Map data = this.upload(phoneRequest.getFile(), null);
            Phone phone = new Phone();
            phone.setUrl(data.get("secure_url").toString());
            phone.setName(phoneRequest.getName());
            phone.setBrand(phoneRequest.getBrand());
            phone.setPrice(Long.parseLong(phoneRequest.getPrice()));
            phone.setDescription(phoneRequest.getDescription());
            phone.setSellerId(phoneRequest.getSellerId());
            System.out.println(data.get("secure_url"));
            Phone savedPhone = phoneService.addPhone(phone);
            return new ResponseEntity<>(savedPhone, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Phone> updatePhone(@ModelAttribute PhoneRequest phoneRequest){
        Phone phone = new Phone();
        System.out.println(phoneRequest.getName());
        if(phoneRequest.getFile()==null){
            phone.setId(phoneRequest.getId());
            phone.setName(phoneRequest.getName());
            phone.setBrand(phoneRequest.getBrand());
            phone.setPrice(Long.parseLong(phoneRequest.getPrice()));
            phone.setUrl(phoneRequest.getUrl());
            phone.setDescription(phoneRequest.getDescription());
            phone.setSellerId(phoneRequest.getSellerId());
        } else {
            phone.setId(phoneRequest.getId());
            phone.setName(phoneRequest.getName());
            phone.setBrand(phoneRequest.getBrand());
            phone.setPrice(Long.parseLong(phoneRequest.getPrice()));
            phone.setDescription(phoneRequest.getDescription());
            phone.setSellerId(phoneRequest.getSellerId());

            Phone temptPhone = phoneService.getPhoneById(phoneRequest.getId()); //Tìm phone theo id
            Map data = this.upload(phoneRequest.getFile(), temptPhone);
            phone.setUrl(data.get("secure_url").toString());
        }
        Phone updatedPhone = phoneService.updatePhone(phone);
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePhone(@PathVariable long id){
        phoneService.deletePhone(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/cloudinary/upload")
    public ResponseEntity<Map> uploadImage(@ModelAttribute PhoneRequest phone){

        Map data = this.upload(phone.getFile(), null);
        System.out.println(data.get("secure_url"));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/cloudinary/delete")
    public ResponseEntity deleteImage(@RequestBody DeleteImageRequest request){
        try {
            String[] list = request.getUrl().split("/");
            String publicId = list[list.length-1].split("\\.")[0];
            this.cloudinary.uploader().destroy("Phone/"+publicId, Map.of());
        } catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    public Map upload(MultipartFile file, Phone phone)  {
        try{

            if(phone != null){  //xóa hình ảnh đã lưu trên cloudinary
                String[] list = phone.getUrl().split("/");
                String publicId = list[list.length-1].split("\\.")[0];
                this.cloudinary.uploader().destroy("Phone/"+publicId, Map.of());
            }

            Map data = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder","Phone"));
            return data;
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }

}

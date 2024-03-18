package com.example.bandienthoai.service;

import com.example.bandienthoai.model.Phone;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PhoneService {
    Phone addPhone(Phone phone);
    void deletePhone(long id);
    Phone updatePhone(Phone phone);
    List getAllPhone();
}

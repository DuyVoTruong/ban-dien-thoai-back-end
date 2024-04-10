package com.example.bandienthoai.service.impl;

import com.example.bandienthoai.model.Phone;
import com.example.bandienthoai.repository.PhoneRepository;
import com.example.bandienthoai.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRespository;

    @Override
    public Phone addPhone(Phone phone) {
        return phoneRespository.save(phone);
    }

    @Override
    public void deletePhone(long id) {
        phoneRespository.deleteById(id);
    }

    @Override
    public Phone updatePhone(Phone phone) {
        Phone phoneFromDB = phoneRespository.findById(phone.getId()).orElse(null);
        if(phoneFromDB ==null) {
            return null;
        }
        return phoneRespository.save(phone);
    }

    @Override
    public List<Phone> getAllPhone() {
        return phoneRespository.findAll();
    }
}

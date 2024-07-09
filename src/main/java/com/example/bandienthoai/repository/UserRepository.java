package com.example.bandienthoai.repository;

import com.example.bandienthoai.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User,String>{

}

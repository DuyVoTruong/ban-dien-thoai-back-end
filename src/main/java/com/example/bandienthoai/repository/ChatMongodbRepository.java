package com.example.bandienthoai.repository;

import com.example.bandienthoai.model.ChatMongodb;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMongodbRepository extends MongoRepository <ChatMongodb, String> {

}

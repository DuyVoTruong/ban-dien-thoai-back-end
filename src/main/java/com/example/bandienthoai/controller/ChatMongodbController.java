package com.example.bandienthoai.controller;

import com.example.bandienthoai.model.ChatMongodb;
import com.example.bandienthoai.repository.ChatMongodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatMongodbController {

    @Autowired
    ChatMongodbRepository chatMongodbRepository;

    @GetMapping("/all")
    public ResponseEntity<List> getAllChatMongodb(){
        List<ChatMongodb> chatMongodbs = chatMongodbRepository.findAll();
        return new ResponseEntity<>(chatMongodbs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ChatMongodb> addChatMongodb(@RequestBody ChatMongodb chatMongodb){
        ChatMongodb savedChatMongodb = chatMongodbRepository.save(chatMongodb);
        return new ResponseEntity<>(savedChatMongodb, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ChatMongodb> updateChatMongodb(@RequestBody ChatMongodb chatMongodb){
        ChatMongodb chatMongodbFromDB = chatMongodbRepository.findById(chatMongodb.getChatId()).orElse(null);
        if(chatMongodb.getMessage()!=null){
            chatMongodbFromDB.setMessage(chatMongodb.getMessage());
        }
        ChatMongodb updatedMongodb = chatMongodbRepository.save(chatMongodbFromDB);
        return new ResponseEntity<>(updatedMongodb, HttpStatus.OK);
    }
}

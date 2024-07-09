package com.example.bandienthoai.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Map;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMongodb {
    @Id
    private String chatId;

    private String sellerId;
    private String userId;

    private ArrayList<Map> message;
}

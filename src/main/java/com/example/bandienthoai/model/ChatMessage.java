package com.example.bandienthoai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ChatMessage {
    private String senderName;
    private String receiverName;
    private String status;
    private String message;
    private String nickname;
    private String content;
    private Date timestamp;
}

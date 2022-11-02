package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.Message;

import java.util.List;

public interface MessageRepository {

    List<Message> findMessageById(int messageId);
    long createMessage(Message message);

}

package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.model.User;

import java.util.List;

public interface MessageRepository {

    List<Message> createMessage(Message message);

}

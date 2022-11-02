package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
public class MessageRepositoryImpl implements MessageRepository{
    @Override
    public List<Message> createMessage(Message message) {
        return null;
    }
}

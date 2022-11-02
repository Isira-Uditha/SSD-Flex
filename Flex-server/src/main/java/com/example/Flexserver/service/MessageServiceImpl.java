package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{
    @Override
    public Response createMessage(Message message) {
        return null;
    }
}

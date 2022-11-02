package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import com.example.Flexserver.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Response createMessage(Message message) {
        return Response.builder()
                .data(Map.of("message", this.messageRepository.createMessage(message)))
                .status(Status.SUCCESS)
                .message("Message created successfully")
                .build();
    }
}

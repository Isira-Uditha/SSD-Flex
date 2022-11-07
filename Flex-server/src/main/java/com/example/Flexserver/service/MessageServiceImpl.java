package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import com.example.Flexserver.repository.MessageRepository;
import com.example.Flexserver.utils.GenerateKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Autowired
    private GenerateKeys generateKeys ;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Response createMessage(Message message) throws NoSuchPaddingException, NoSuchAlgorithmException {

        //Decrypt the message
        message.setMessage(generateKeys.decrypt(message.getMessage()));

        return Response.builder()
                .data(Map.of("message", this.messageRepository.createMessage(message)))
                .status(Status.SUCCESS)
                .message("Message created successfully")
                .build();
    }
}

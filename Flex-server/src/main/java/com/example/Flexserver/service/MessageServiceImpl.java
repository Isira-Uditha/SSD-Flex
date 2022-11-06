package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import com.example.Flexserver.repository.MessageRepository;
import com.example.Flexserver.utils.GenerateKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Value("${APPID}")
    private String appId;

    @Autowired
    private GenerateKeys generateKeys ;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Response createMessage(Message message) throws NoSuchPaddingException, NoSuchAlgorithmException {

        //Decrypt the message
        Cipher cipher = Cipher.getInstance("RSA");
        try {
            cipher.init(Cipher.DECRYPT_MODE, generateKeys.readPrivateKey(appId));
            byte[] encodedMessage = Base64.getDecoder().decode(message.getMessage());
            String dyMessage = new String(cipher.doFinal(encodedMessage),  "UTF-8");
            message.setMessage(dyMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.builder()
                .data(Map.of("message", this.messageRepository.createMessage(message)))
                .status(Status.SUCCESS)
                .message("Message created successfully")
                .build();
    }
}

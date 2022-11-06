package com.example.Flexserver.resource;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageResource {

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Response> createMessage(@RequestBody Message message) throws NoSuchPaddingException, NoSuchAlgorithmException {
        return ResponseEntity.ok(this.messageService.createMessage(message));
    }
}

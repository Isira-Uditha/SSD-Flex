package com.example.Flexserver.resource;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageResource {

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Response> createMessage(@RequestBody Message message) {
        return ResponseEntity.ok(this.messageService.createMessage(message));
    }
}

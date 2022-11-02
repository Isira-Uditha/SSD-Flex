package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.response.Response;


public interface MessageService {

    Response createMessage(Message message);

}

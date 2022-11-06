package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.domain.response.Response;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;


public interface MessageService {

    Response createMessage(Message message) throws NoSuchPaddingException, NoSuchAlgorithmException;

}

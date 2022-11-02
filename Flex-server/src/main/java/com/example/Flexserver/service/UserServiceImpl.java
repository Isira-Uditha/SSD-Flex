package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Override
    public Response findUserById(int userId) {
        return null;
    }

    @Override
    public Response createUser(User user) {
        return null;
    }
}

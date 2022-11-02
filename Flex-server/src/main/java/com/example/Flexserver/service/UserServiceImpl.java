package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import com.example.Flexserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response findUserById(int userId) {
        return Response.builder()
            .data(Map.of("user", this.userRepository.findUserById(userId)))
            .status(Status.SUCCESS)
            .message("User found successfully")
            .build();
    }

    @Override
    public Response createUser(User user) {
        return Response.builder()
                .data(Map.of("user", this.userRepository.createUser(user)))
                .status(Status.SUCCESS)
                .message("User created successfully")
                .build();
    }
}

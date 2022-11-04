package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import com.example.Flexserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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
    public Response findUserByUserNameAndPassword(String userName, String password) {

        //Fetch the user
        List<User> user = this.userRepository.findUserByUserName(userName);
        if(!user.isEmpty()){
            //Check weather the password matches
            if(!this.userRepository.checkPassword(password,user.get(0).getPassword(),passwordEncoder)){
                user.clear();
            }
        }
        return Response.builder()
                .data(Map.of("user", user))
                .status(Status.SUCCESS)
                .message("User found successfully")
                .build();
    }

    @Override
    public Response createUser(User user) {
        //Encrypt the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return Response.builder()
                .data(Map.of("user", this.userRepository.createUser(user)))
                .status(Status.SUCCESS)
                .message("User created successfully")
                .build();
    }
}

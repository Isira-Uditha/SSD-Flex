package com.example.Flexserver.repository;


import com.example.Flexserver.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserRepository {

    List<User> findUserById(int userId);

    List<User> findUserByUserName(String userName);

    boolean checkPassword(String enteredPassword,String password, PasswordEncoder passwordEncoder);

    long createUser(User user);

}

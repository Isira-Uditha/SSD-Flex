package com.example.Flexserver.repository;


import com.example.Flexserver.domain.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findUserById(int userId);

    List<User> findUserByUserNameAndPassword(String userName, String password);

    long createUser(User user);

}

package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;

public interface UserService {

    Response findUserById(int userId);

    Response findUserByUserNameAndPassword(String userName, String password);

    Response createUser(User user);


}

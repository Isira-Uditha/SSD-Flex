package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;

public interface UserService {

    Response findUserById(int userId);

    Response createUser(User user);


}

package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<User> findUserById(int userId) {
        return null;
    }

    @Override
    public List<User> createUser(User user) {
        return null;
    }
}

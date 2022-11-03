package com.example.Flexserver.repository.mapper;

import com.example.Flexserver.domain.model.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .username(rs.getString("username"))
                .name(rs.getString("name"))
                .role(rs.getString("role"))
                .password(rs.getString("password"))
                .build();
    }

}

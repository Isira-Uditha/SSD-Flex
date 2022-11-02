package com.example.Flexserver.repository.mapper;

import com.example.Flexserver.domain.model.Message;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Message.builder()
                .id(rs.getInt("id"))
                .message(rs.getString("message"))
                .userId(rs.getInt("user_id"))
                .build();
    }

}
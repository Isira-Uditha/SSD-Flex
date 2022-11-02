package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.Message;
import com.example.Flexserver.repository.mapper.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class MessageRepositoryImpl implements MessageRepository{

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MessageRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Message> findMessageById(int messageId) {
        StringBuilder query = new StringBuilder("SELECT * FROM message WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", messageId);

        return namedParameterJdbcTemplate.query(query.toString(), param, new MessageMapper());
    }

    @Override
    public long createMessage(Message message) {
        StringBuilder query = new StringBuilder("INSERT INTO message(message, user_id) ");
        query.append("VALUES (?,?)");
        MapSqlParameterSource param = new MapSqlParameterSource();

        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getMessage());
            ps.setInt(2, message.getUserId());

            return ps;
        }, key);


        return Objects.requireNonNull(key.getKey()).longValue();

    }
}

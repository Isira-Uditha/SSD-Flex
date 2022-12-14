package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.repository.mapper.MessageMapper;
import com.example.Flexserver.repository.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> findUserById(int userId) {
        StringBuilder query = new StringBuilder("SELECT * FROM user WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", userId);

        return namedParameterJdbcTemplate.query(query.toString(), param, new UserMapper());
    }

    @Override
    public List<User> findUserByUserName(String userName) {
        StringBuilder query = new StringBuilder("SELECT * FROM user WHERE username=:username ");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("username", userName);

        return namedParameterJdbcTemplate.query(query.toString(), param, new UserMapper());
    }

    @Override
    public boolean checkPassword(String enteredPassword, String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(enteredPassword,password);
    }

    @Override
    public long createUser(User user) {
        StringBuilder query = new StringBuilder("INSERT INTO user(username, name, password, role) ");
        query.append("VALUES (?,?,?,?)");
        MapSqlParameterSource param = new MapSqlParameterSource();

        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            return ps;
        }, key);


        return Objects.requireNonNull(key.getKey()).longValue();
    }
}

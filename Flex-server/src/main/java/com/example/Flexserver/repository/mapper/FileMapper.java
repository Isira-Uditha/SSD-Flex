package com.example.Flexserver.repository.mapper;

import com.example.Flexserver.domain.model.File;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMapper implements RowMapper<File> {

    @Override
    public File mapRow(ResultSet rs, int rowNum) throws SQLException {
        return File.builder()
                .id(rs.getInt("id"))
                .file(rs.getString("file"))
                .userId(rs.getInt("user_id"))
                .build();
    }

}

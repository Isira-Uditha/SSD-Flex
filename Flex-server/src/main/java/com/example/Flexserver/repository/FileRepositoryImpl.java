package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Slf4j
@Repository
public class FileRepositoryImpl implements FileRepository{
    private final JdbcTemplate jdbcTemplate;

    private final Path root = Paths.get("uploads");

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FileRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public long createFile(File file) {
        StringBuilder query = new StringBuilder("INSERT INTO file(file_path, user_id) VALUES (?, ?)");

        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, file.getFilePath());
            ps.setInt(2, file.getUserId());
            return ps;
        }, key);

        return Objects.requireNonNull(key.getKey()).longValue();
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {

        //Create Upload Folder if not exist
        if(!Files.exists(root)){
            this.init();
        }

        //Save the file in the local storage
        if(!Files.exists(this.root.resolve(file.getOriginalFilename()))){
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        }

        //Return the file path
        Path filePath = root.resolve(file.getOriginalFilename());

        return filePath.toString();
    }

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
}

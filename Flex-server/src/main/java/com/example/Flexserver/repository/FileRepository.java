package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FileRepository {
    long createFile(File file);
    String saveFile(MultipartFile file) throws IOException;
}

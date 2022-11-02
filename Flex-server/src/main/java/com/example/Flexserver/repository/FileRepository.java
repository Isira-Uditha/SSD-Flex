package com.example.Flexserver.repository;

import com.example.Flexserver.domain.model.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FileRepository {
    List<File> createFile(File file);
}

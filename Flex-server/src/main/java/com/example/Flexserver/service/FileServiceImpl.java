package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.File;
import com.example.Flexserver.domain.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileServiceImpl implements FileService{
    @Override
    public Response createFile(File file) {
        return null;
    }
}

package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.File;
import com.example.Flexserver.domain.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileService {

    Response createFile(MultipartFile file,String userId) throws IOException;

}

package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.File;
import com.example.Flexserver.domain.response.Response;


public interface FileService {

    Response createFile(File file);

}

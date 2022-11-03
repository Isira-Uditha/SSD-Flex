package com.example.Flexserver.service;

import com.example.Flexserver.domain.model.File;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import com.example.Flexserver.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Response createFile(MultipartFile file, String userId) throws IOException {
        //Save the file in the local storage
        String filePath = this.fileRepository.saveFile(file);

        //Create a new file object
        File newFile = new File();
        newFile.setFilePath(filePath);
        newFile.setUserId(Integer.parseInt(userId));

        return Response.builder()
                .data(Map.of("file", this.fileRepository.createFile(newFile)))
                .status(Status.SUCCESS)
                .message("File created successfully")
                .build();
    }
}

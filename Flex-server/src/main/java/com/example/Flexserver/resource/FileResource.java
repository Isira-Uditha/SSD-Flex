package com.example.Flexserver.resource;

import com.example.Flexserver.domain.model.File;
import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileResource {

    private final FileService fileService;

    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<Response> createFile(@RequestBody File file) {
        return ResponseEntity.ok(this.fileService.createFile(file));
    }
}

package com.example.Flexserver.resource;

import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileResource {

    private final FileService fileService;

    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<Response> createFile(@RequestParam(required = true) String userId, @RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.ok(this.fileService.createFile(file,userId));
    }
}

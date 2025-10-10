package com.example.musicvalidation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;


import java.io.File;
import java.io.IOException;
@Service
public class FileStorageService implements IFileStorageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir, fileName);
        file.transferTo(dest);

        return fileName;
    }
}

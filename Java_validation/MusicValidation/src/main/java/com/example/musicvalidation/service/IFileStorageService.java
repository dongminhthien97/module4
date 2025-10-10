package com.example.musicvalidation.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileStorageService {
    String saveFile(MultipartFile file) throws IOException;
}

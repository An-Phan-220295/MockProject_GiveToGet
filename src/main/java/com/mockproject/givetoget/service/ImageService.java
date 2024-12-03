package com.mockproject.givetoget.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageService {
    private static final String STORAGE_PATH = "C:\\game\\Learning\\Workspaces\\MockProject_GiveToGet\\image";
    private static final String STORAGE_ITEM_DIRECTORY = "\\item";

    public void saveImgItem(MultipartFile file) throws IOException {
        String storageApartment = STORAGE_PATH + STORAGE_ITEM_DIRECTORY;
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.contains("..")) {
            throw new SecurityException("Filename contains invalid path sequence: " + fileName);
        }
        var target = new File(storageApartment + File.separator + fileName);
        if (!target.getParentFile().getCanonicalPath().equals(new File(storageApartment).getCanonicalPath())) {
            throw new SecurityException("unsupported filename");
        }

        Files.copy(file.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}

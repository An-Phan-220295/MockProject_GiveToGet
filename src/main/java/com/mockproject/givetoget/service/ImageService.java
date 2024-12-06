package com.mockproject.givetoget.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private static final String STORAGE_PATH = "C:\\game\\Learning\\Workspaces\\MockProject_GiveToGet\\image";
//    private static final String STORAGE_PATH = "D:\\GitHub\\MockProject_GiveToGet\\image";
    private static final String STORAGE_ITEM_DIRECTORY = "\\item";

    public List<String> saveMultipleImgItems(List<MultipartFile> files) throws IOException {
        String storageImg = STORAGE_PATH + STORAGE_ITEM_DIRECTORY;

        File directory = new File(storageImg);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create storage directory");
            }
        }

        List<String> savedFileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                throw new NullPointerException("One or more files are null or empty");
            }
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.contains("..")) {
                throw new SecurityException("Filename contains invalid path sequence: " + fileName);
            }
            var target = new File(storageImg + File.separator + fileName);

            if (!target.getParentFile().getCanonicalPath().equals(directory.getCanonicalPath())) {
                throw new SecurityException("Unsupported filename: " + fileName);
            }

            Files.copy(file.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            savedFileNames.add(fileName);
        }

        return savedFileNames;
    }
}

package com.blog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtil {

    public static String saveImage(MultipartFile imageFile) {
        String directory = "your_image_directory"; // Set the path to the directory where you want to save the images

        if (!imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                String filename = generateUniqueFileName(imageFile.getOriginalFilename());
                Path path = Paths.get(directory + File.separator + filename);
                Files.write(path, bytes);
                return directory + File.separator + filename; // Return the path where the image is saved
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String generateUniqueFileName(String originalFileName) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + fileExtension;
    }
}

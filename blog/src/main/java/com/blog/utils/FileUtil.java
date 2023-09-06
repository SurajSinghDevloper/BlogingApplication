package com.blog.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileUtil {

	private List<String> saveImagesToFolder(List<MultipartFile> images) {
	    // Define the folder where you want to save the images
	    String uploadDir = "your_upload_directory_here";

	    // Create a list to store image file names
	    List<String> imageFileNames = new ArrayList<>();

	    // Iterate through the images and save them with UUID-based file names
	    for (MultipartFile image : images) {
	        // Generate a unique file name using UUID
	        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

	        // Save the image file name to the list
	        imageFileNames.add(fileName);

	        // Save the image file to the folder
	        try {
	            Path filePath = Paths.get(uploadDir, fileName);
	            Files.write(filePath, image.getBytes());
	        } catch (IOException e) {
	            // Handle exceptions
	            e.printStackTrace();
	        }
	    }

	    return imageFileNames;
	}

}

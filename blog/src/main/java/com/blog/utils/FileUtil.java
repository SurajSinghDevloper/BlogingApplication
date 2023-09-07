package com.blog.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class FileUtil {
	 @Value("${blog.image.upload.path}") // Specify the directory where images should be saved in your application.properties or application.yml file.
	    private String imageUploadPath;
	 
//	    public void saveImage(String imageName, byte[] imageBytes) throws IOException {
//	        String imagePath = imageUploadPath + imageName;
//
//	        try (FileOutputStream fos = new FileOutputStream(imagePath)) {
//	            fos.write(imageBytes);
//	        } catch (IOException e) {
//	            // Handle the exception appropriately
//	            e.printStackTrace();
//	            throw e;
//	        }
//	    }
	 
	 public void saveImage(String imageName, byte[] imageBytes) throws IOException {
		    String imagePath = imageUploadPath + imageName;

		    File imageDirectory = new File(imageUploadPath);

		    if (!imageDirectory.exists()) {
		        if (imageDirectory.mkdirs()) {
		            // Directory created successfully, proceed to save the image
		            try (FileOutputStream fos = new FileOutputStream(imagePath)) {
		                fos.write(imageBytes);
		                // Log success
		                System.out.println("Image saved to: " + imagePath);
		            } catch (IOException e) {
		                // Handle the exception appropriately
		                e.printStackTrace();
		                throw e;
		            }
		        } else {
		            // Handle directory creation failure
		            throw new IOException("Failed to create image directory.");
		        }
		    } else {
		        // Directory already exists, proceed to save the image
		        try (FileOutputStream fos = new FileOutputStream(imagePath)) {
		            fos.write(imageBytes);
		            // Log success
		            System.out.println("Image saved to: " + imagePath);
		        } catch (IOException e) {
		            // Handle the exception appropriately
		            e.printStackTrace();
		            throw e;
		        }
		    }
		}

}

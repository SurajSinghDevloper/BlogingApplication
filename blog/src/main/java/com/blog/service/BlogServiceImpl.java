package com.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Blog;
import com.blog.entity.BlogImages;
import com.blog.repository.BlogImagesRepository;
import com.blog.repository.BlogRepository;
import com.blog.utils.FileUtil;

@Service
public class BlogServiceImpl implements BlogService{
	 @Autowired
	    private BlogRepository blogRepository;

	 @Autowired
	 private BlogImagesRepository blogImageRepository;

	    @Autowired
	    private FileUtil fileUtil;
	    
//	    
//	    public Blog createBlogWithImages(Blog blog, List<MultipartFile> imageFiles) {
//	        try {
//	            // Save the blog first to generate its ID
//	            Blog savedBlog = blogRepository.save(blog);
//
//	            // Create a list to store the blog images
//	            List<BlogImages> blogImages = new ArrayList<>();
//
//	            for (MultipartFile imageFile : imageFiles) {
//	                // Generate a unique image name
//	                String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
//
//	                // Save the image to the server's filesystem
//	                byte[] imageBytes = imageFile.getBytes();
//	                fileUtil.saveImage(imageName, imageBytes);
//
//	                // Create a BlogImage entity
//	                BlogImages blogImage = new BlogImages();
//	                blogImage.setImageName(imageName);
//	                blogImage.setBlog(savedBlog); // Set the blog for the image
//
//	                // Add the BlogImage to the list
//	                blogImages.add(blogImage);
//	            }
//
//	            // Save the list of BlogImages
//	            blogImageRepository.saveAll(blogImages);
//
//	            // Set the list of BlogImages in the Blog entity
//	            savedBlog.setImages(blogImages);
//
//	            // Update and return the saved Blog entity
//	            return blogRepository.save(savedBlog);
//	        } catch (IOException e) {
//	            // Handle the exception appropriately
//	            e.printStackTrace();
//	            return null;
//	        }
//	    }
	    
	    
	    
	    
	    public Blog createBlogWithImages(Blog blog, List<MultipartFile> imageFiles) {
	        // Create a list to store the blog images
	        List<BlogImages> blogImages = new ArrayList<>();

	        try {
	            // Save the blog first to generate its ID
	            Blog savedBlog = blogRepository.save(blog);

	            for (MultipartFile imageFile : imageFiles) {
	                // Generate a unique image name
	                String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

	                // Save the image to the server's filesystem
	                byte[] imageBytes = imageFile.getBytes();
	                fileUtil.saveImage(imageName, imageBytes);

	                // Create a BlogImage entity
	                BlogImages blogImage = new BlogImages();
	                blogImage.setImageName(imageName);
	                blogImage.setBlog(savedBlog); // Set the blog for the image

	                // Add the BlogImage to the list
	                blogImages.add(blogImage);
	            }

	            // Save the list of BlogImages
	            blogImageRepository.saveAll(blogImages);

	            // Set the list of BlogImages in the Blog entity
	            savedBlog.setImages(blogImages);

	            // Update and return the saved Blog entity
	            return blogRepository.save(savedBlog);
	        } catch (IOException e) {
	            // Handle the exception appropriately, e.g., log it or throw a custom exception
	            e.printStackTrace();
	            // You might want to throw a custom exception here or log the error and return null if necessary
	            throw new RuntimeException("Failed to create the blog with images.", e);
	        }
	    }

}

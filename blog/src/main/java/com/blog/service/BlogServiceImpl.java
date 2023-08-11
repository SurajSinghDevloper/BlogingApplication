package com.blog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.blog.entity.Blog;
import com.blog.repository.BlogRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Value("${user.image.upload.path}")
    private String imageUploadPath;

    @Override
    @Transactional
    public Blog createBlog(Blog blog, MultipartFile imageFile) {
        try {
            // Generate a unique image name (you can use any logic for generating the name)
            String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

            // Save the image to the server's filesystem
            byte[] imageBytes = imageFile.getBytes();
            Path imageFilePath = Paths.get(imageUploadPath + imageName);

            // Ensure the directory for storing images exists before saving
            Files.createDirectories(imageFilePath.getParent());

            Files.write(imageFilePath, imageBytes);

            // Set the image name in the Blog entity and save it to the database
            blog.setBlogImg(imageName);
            return blogRepository.save(blog);
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Blog getBlogById(int blogId) {
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        return optionalBlog.orElse(null);
    }

    @Override
    @Transactional
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void deleteBlog(int blogId) {
        blogRepository.deleteById(blogId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Blog> getAllBlogsByUserId(int userId) {
        return blogRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Blog> getBlogsByUserId(int userId) {
        return blogRepository.findByUserIdOrderByDateDesc(userId);
    }
}

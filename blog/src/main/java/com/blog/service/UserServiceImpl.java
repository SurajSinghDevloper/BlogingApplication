package com.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.User;
import com.blog.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;

    @Value("${user.image.upload.path}")
    private String imageUploadPath; 
    
 
    
    @Override
    public User updateImage(String email, MultipartFile imageFile) {
        try {
            // Generate a unique image name (you can use any logic for generating the name)
            String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

            // Save the image to the server's filesystem
            byte[] imageBytes = imageFile.getBytes();
            Path imageFilePath = Paths.get(imageUploadPath + imageName);

            // Ensure the directory for storing images exists before saving
            Files.createDirectories(imageFilePath.getParent());

            Files.write(imageFilePath, imageBytes);
            User user = usersRepository.findByEmail(email);
            // Set the image name in the User entity and save it to the database
            user.setProfileImg(imageName);
            return usersRepository.save(user);
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(int userId) {
        Optional<User> userOptional = usersRepository.findById(userId);
        return userOptional.orElse(null);
    }

    public User updateUser(User user) {
        return usersRepository.save(user);
    }

    public void deleteUser(int userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = usersRepository.findByEmail(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null; // Return null if the user is not found or password doesn't match
        }
    }

    public User signUpUser(User usr) {
        User usrs = usersRepository.save(usr);
        return usrs;
    }

    public Boolean doesUserExistByusername(String username) {
        User user = usersRepository.findByEmail(username);
        return user != null;
    }

    public User getUserByusername(String username) {
        User usr = usersRepository.findByEmail(username);
        return usr;
    }

	@Override
	public Boolean doesUserExistByEmail(String username) {
		User user = usersRepository.findByEmail(username);
        return user != null;
	}

	@Override
	public User getUserByEmail(String username) {
		User usr = usersRepository.findByEmail(username);
        return usr;
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return usersRepository.save(user);
	}
}

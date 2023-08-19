//package com.blog.service;
//
//
//import lombok.AllArgsConstructor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.blog.entity.User;
//import com.blog.repository.UsersRepository;
//
//@Service
//@AllArgsConstructor
//
//public class JwtUserDetailsService implements UserDetailsService{
//	@Autowired
//	private  UsersRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new org.springframework.security.core.userdetails.User(user.getusername(), user.getPassword(), null);
//    }
//}

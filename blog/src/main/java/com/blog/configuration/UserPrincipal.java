//package com.blog.configuration;
//
//import com.blog.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class UserPrincipal implements UserDetails {
//    private String email;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserPrincipal(int userId, String email, String password, Collection<? extends GrantedAuthority> authorities) {
//
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static UserPrincipal create(User user) {
//        String role = user.getRole();
//        System.out.println("Role from User entity: " + role);
//        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
////        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
//        System.out.println("Authorities: " + authorities);
//
//        return new UserPrincipal(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//
//
//
// 
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true; // Modify as needed
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true; // Modify as needed
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; // Modify as needed
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true; // Modify as needed
//    }
//}

















package com.blog.configuration;

import com.blog.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        String role = user.getRole();
        System.out.println("Role from User entity: " + role);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        System.out.println("Authorities: " + authorities);

        return new UserPrincipal(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify as needed
    }
}


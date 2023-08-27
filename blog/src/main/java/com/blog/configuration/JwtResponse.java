package com.blog.configuration;

import java.util.ArrayList;

import com.blog.entity.User;

import lombok.Data;

@Data
public class JwtResponse {
ArrayList<String> data = new ArrayList<>();
	
	
    private User user;
    private String jwtToken;

    public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
       
    }
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

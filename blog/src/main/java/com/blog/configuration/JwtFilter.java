package com.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.configuration.JwtService;
import com.blog.configuration.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter{
	 @Autowired
	    private JwtUtil jwtUtil;

	 @Autowired
	    private JwtService jwtService;

//	    @Override
//	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//	                                    FilterChain filterChain) throws ServletException, IOException {
//	        final String authorizationHeader = request.getHeader("Authorization");
//
//	        String username = null;
//	        String jwt = null;
//
//	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
//	            jwt = authorizationHeader.substring(7);
//	            username = jwtUtil.extractUsername(jwt);
//	        }
//
//	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//	            if (jwtUtil.validateToken(jwt, userDetails)) {
//	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//	                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//	            }
//	        }
//	        filterChain.doFilter(request, response);
//	    }
	    
	    

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

	        final String requestTokenHeader = request.getHeader("Authorization");

	        String username = null;
	        String jwtToken = null;

	        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	            jwtToken = requestTokenHeader.substring(7);
	            try {
	                username = jwtUtil.getUsernameFromToken(jwtToken);
	            } catch (IllegalArgumentException e) {
	                System.out.println("Unable to get JWT Token");
	            } catch (ExpiredJwtException e) {
	                System.out.println("JWT Token has expired");
	            }
	        } else {
	            System.out.println("JWT token does not start with Bearer");
	        }

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = jwtService.loadUserByUsername(username);

	            if (jwtUtil.validateToken(jwtToken, userDetails)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
	        filterChain.doFilter(request, response);

	    }

}

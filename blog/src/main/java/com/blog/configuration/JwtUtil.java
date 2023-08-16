package com.blog.configuration;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;


@Component

public class JwtUtil {
	 @Value("${jwt.secret}")
	    private String secret;

	    @Value("${jwt.expiration}")
	    private Long expiration;

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public String generateToken(String email) {
	    	System.out.println("GENERATE TOKEN METHOD CALL WITH email ====== " +email);
	        return createToken(email);
	    }

	    private String createToken(String email) {
	        return Jwts.builder()
	            .setSubject(email)
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
	            .signWith(SignatureAlgorithm.HS256, secret)
	            .compact();
	    }

	    public Boolean validateToken(String token, String email) {
	        final String tokenUsername = extractUsername(token);
	        return (tokenUsername.equals(email) && !isTokenExpired(token));
	    }
}

//package com.blog.contollerRestful;
//
//
//import lombok.AllArgsConstructor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.blog.configuration.JwtUtil;
//import com.blog.entity.AuthRequest;
//import com.blog.service.JwtUserDetailsService;
//
//@RestController
//@AllArgsConstructor
//public class AuthController {
//	@Autowired
//	private  AuthenticationManager authenticationManager;
//	@Autowired
//    private  JwtUserDetailsService jwtUserDetailsService;
//	@Autowired
//    private  JwtUtil jwtUtil;
//
//	public ResponseEntity<?> createAuthenticationToken(AuthRequest authRequest) throws Exception {
//	    try {
//	        authenticationManager.authenticate(
//	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//	        );
//	    } catch (Exception ex) {
//	        throw new Exception("Incorrect username or password", ex);
//	    }
//
//	    final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authRequest.getUsername());
//	    final String jwt = jwtUtil.generateToken(authRequest.getUsername());
//
//	    return ResponseEntity.ok(new JwtResponse(jwt));
//	}
//
//
//    static class JwtResponse {
//        private final String jwt;
//
//        public JwtResponse(String jwt) {
//            this.jwt = jwt;
//        }
//
//        public String getJwt() {
//            return jwt;
//        }
//    }
//}

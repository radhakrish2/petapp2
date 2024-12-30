package com.pet.auth;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pet.response.ApiResponse;
import com.pet.auth.RegisterRequest;
import com.pet.entity.dto.LoginDTO;
import com.pet.auth.LoginRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${file.upload-dir}")
    private String uploadDir;
    
    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody RegisterRequest registerRequest) {
        ApiResponse<String> response = authService.registerUser(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Authenticate user and return token
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginDTO>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        ApiResponse<LoginDTO> response = authService.authenticateUser(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/get")
    public String getMethodName() {
    	
    	   Path uploadPath = Paths.get(uploadDir);
           if (!Files.exists(uploadPath)) {
               try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           }
    	
    	
        return "Welcome" +": "+ uploadPath.toUri();
    }
    
}

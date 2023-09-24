package com.ebuy.ebuy.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebuy.ebuy.dtos.LoginRequest;
import com.ebuy.ebuy.dtos.SignupRequest;
import com.ebuy.ebuy.entities.User;
import com.ebuy.ebuy.payload.ApiResponse;
import com.ebuy.ebuy.payload.JwtResponse;
import com.ebuy.ebuy.repository.UserRepository;
import com.ebuy.ebuy.util.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(path = "/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        User user = (User) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt, "Success"));
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignupRequest signupRequest) {
        User user = new User();
        if (signupRequest.getEmail() == null || signupRequest.getFirstName() == null
                || signupRequest.getLastName() == null || signupRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.fail(false, "All credentials are required!"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.fail(false, "Email already exists..."));
        } else {
            // Create user account
            user.setEmail(signupRequest.getEmail());
            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            userRepository.save(user);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success( user));
    }
}

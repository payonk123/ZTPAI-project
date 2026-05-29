package org.example.projekt_ztpai.controllers;

import org.example.projekt_ztpai.dto.AuthResponse;
import org.example.projekt_ztpai.dto.LoginRequest;
import org.example.projekt_ztpai.dto.RegisterRequest;
import org.example.projekt_ztpai.models.Role;
import org.example.projekt_ztpai.models.User;
import org.example.projekt_ztpai.repository.UserRepository;
import org.example.projekt_ztpai.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setRole(Role.valueOf(req.getRole()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(),
                        req.getPassword())
        );
        User user =
                userRepository.findByUsername(req.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(),
                user.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

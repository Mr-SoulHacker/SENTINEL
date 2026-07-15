package com.sentinel.sentinel.service;

import com.sentinel.sentinel.dto.AuthResponse;
import com.sentinel.sentinel.dto.LoginRequest;
import com.sentinel.sentinel.dto.RegisterRequest;
import com.sentinel.sentinel.model.Role;
import com.sentinel.sentinel.model.User;
import com.sentinel.sentinel.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        String username = request.getUsername().trim();
        String email = request.getEmail().trim().toLowerCase();

        // Check duplicate username
        if (userRepository.existsByUsername(username)) {
            return "Username already exists";
        }

        // Check duplicate email
        if (userRepository.existsByEmail(email)) {
            return "Email already exists";
        }

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);

        // Store encoded password
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        // Public registration always creates a CITIZEN.
        // Client cannot create an ADMIN account.
        user.setRole(Role.CITIZEN);

        user.setEnabled(true);

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );

        String token =
                jwtService.generateToken(
                        user.getUsername()
                );

        return new AuthResponse(
                true,
                "Login successful",
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }
}
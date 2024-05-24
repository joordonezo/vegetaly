package com.core.vegetaly2.services;

import com.core.vegetaly2.dto.AuthResponseDto;
import com.core.vegetaly2.dto.LoginCredentialsDto;
import com.core.vegetaly2.models.User;
import com.core.vegetaly2.repositories.UserRepository;
import com.core.vegetaly2.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseDto login(LoginCredentialsDto loginCredentialsDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentialsDto.getUsername(), loginCredentialsDto.getPassword()));
            UserDetails userDetails = userRepository.findByUsername(loginCredentialsDto.getUsername()).orElseThrow();
            return AuthResponseDto.builder()
                    .status(true)
                    .token(jwtService.getToken(userDetails))
                    .username(userDetails.getUsername())
                    .build();
        } catch (Exception e) {
            return AuthResponseDto.builder()
                    .status(false)
                    .build();
        }

    }

    public AuthResponseDto register(LoginCredentialsDto loginCredentialsDto) {
        User user = User.builder()
                .username(loginCredentialsDto.getUsername())
                .password(passwordEncoder.encode(loginCredentialsDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponseDto.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}

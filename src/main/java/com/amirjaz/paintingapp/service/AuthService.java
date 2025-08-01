package com.amirjaz.paintingapp.service;

import com.amirjaz.paintingapp.dto.AuthRequest;
import com.amirjaz.paintingapp.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> mockUserPasswords;
    private final Map<String, User> mockUserEntities;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        // Initialize mock user passwords
        this.mockUserPasswords = new HashMap<>();
        this.mockUserPasswords.put("admin", passwordEncoder.encode("password"));
        this.mockUserPasswords.put("user", passwordEncoder.encode("123456"));
        this.mockUserPasswords.put("test", passwordEncoder.encode("test"));

        // Initialize mock user entities
        this.mockUserEntities = new HashMap<>();
        this.mockUserEntities.put("admin", User.builder().id(1L).username("admin").password(passwordEncoder.encode("password")).build());
        this.mockUserEntities.put("user", User.builder().id(2L).username("user").password(passwordEncoder.encode("123456")).build());
        this.mockUserEntities.put("test", User.builder().id(3L).username("test").password(passwordEncoder.encode("test")).build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = mockUserPasswords.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(password)
                .authorities("ROLE_USER")
                .build();
    }

    public void register(AuthRequest request) {
        throw new RuntimeException("Registration is disabled - using mock users only");
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication == null ? null : authentication.getName();
        if (username == null) {
            throw new UsernameNotFoundException("User not authenticated");
        }

        User user = mockUserEntities.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return user;
    }
}
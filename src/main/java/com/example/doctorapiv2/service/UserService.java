package com.example.doctorapiv2.service;

import com.example.doctorapiv2.domain.User;
import com.example.doctorapiv2.dto.request.GetOrderRequest;
import com.example.doctorapiv2.dto.request.SignUpRequest;
import com.example.doctorapiv2.repository.UserRepository;
import com.example.doctorapiv2.security.JwtUtils;
import com.example.doctorapiv2.security.Tokens;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        val user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username with " + username + " not found"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public Tokens save(SignUpRequest request) {
        if (!userRepository.existsByUsername(request.getLogin())) {
            val loginToSave = request.getLogin();
            val userToSave = new User();
            userToSave.setUsername(loginToSave);
            userToSave.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(userToSave);
            return jwtUtils.generateTokens(loginToSave);
        }
        return null;
    }
    //TODO Implement
    public void getUserOrders(GetOrderRequest request) {
    }
}

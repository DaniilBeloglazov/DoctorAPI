package com.example.doctorapiv2.authentication;

import com.example.doctorapiv2.dto.request.LoginRequest;
import com.example.doctorapiv2.repository.UserRepository;
import com.example.doctorapiv2.security.JwtUtils;
import com.example.doctorapiv2.security.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public Tokens performLogin(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        return jwtUtils.generateTokens(request.getLogin());
    }
}

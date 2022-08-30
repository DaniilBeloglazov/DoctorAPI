package com.example.doctorapiv2.authentication;

import com.example.doctorapiv2.dto.request.LoginRequest;
import com.example.doctorapiv2.security.Tokens;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authorizationUser")
    public ResponseEntity<Tokens> login(@RequestBody @Validated LoginRequest request) {
        val tokens = authService.performLogin(request);
        return ResponseEntity.ok(tokens);
    }
}

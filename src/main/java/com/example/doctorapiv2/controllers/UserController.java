package com.example.doctorapiv2.controllers;

import com.example.doctorapiv2.dto.request.SignUpRequest;
import com.example.doctorapiv2.security.Tokens;
import com.example.doctorapiv2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/createNewUser")
    public ResponseEntity<Tokens> createUser(@RequestBody @Validated SignUpRequest request) {
        val tokens = userService.save(request);
        return ResponseEntity.ok(tokens);
    }
}

package com.shopy.shopybe.controller;

import com.shopy.shopybe.model.SignUpRequest;
import com.shopy.shopybe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);

        return ResponseEntity.ok().build();
    }
}

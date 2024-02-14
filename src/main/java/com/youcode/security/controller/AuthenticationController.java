package com.youcode.security.controller;


import com.youcode.security.pld.request.AuthenticationRequest;
import com.youcode.security.pld.request.RefreshTokenRequest;
import com.youcode.security.pld.request.RegisterRequest;
import com.youcode.security.pld.response.AuthenticationResponse;
import com.youcode.security.pld.response.RefreshTokenResponse;
import com.youcode.security.services.RefreshTokenService;
import com.youcode.security.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){

        return ResponseEntity.ok(userService.register(request));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request){

        return ResponseEntity.ok(refreshTokenService.generateNewToken(request));
    }


}

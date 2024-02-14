package com.youcode.security.services;

import com.youcode.security.pld.request.AuthenticationRequest;
import com.youcode.security.pld.request.RegisterRequest;
import com.youcode.security.pld.response.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}

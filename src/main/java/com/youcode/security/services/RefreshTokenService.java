package com.youcode.security.services;

import com.youcode.security.entities.RefreshToken;
import com.youcode.security.pld.request.RefreshTokenRequest;
import com.youcode.security.pld.response.RefreshTokenResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long userId);
    RefreshToken verifyExpiration(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    RefreshTokenResponse generateNewToken(RefreshTokenRequest request);
    void deleteByToken(String token);
}

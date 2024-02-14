package com.youcode.security.services.impl;

import com.youcode.security.entities.RefreshToken;
import com.youcode.security.entities.User;
import com.youcode.security.entities.enums.TokenType;
import com.youcode.security.exception.TokenException;
import com.youcode.security.pld.request.RefreshTokenRequest;
import com.youcode.security.pld.response.RefreshTokenResponse;
import com.youcode.security.repository.RefreshTokenRepository;
import com.youcode.security.repository.UserRepository;
import com.youcode.security.services.JwtService;
import com.youcode.security.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceimpl implements RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Value("${application.security.refresh-token-expiration}")
    private long refreshExpiration;

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        RefreshToken refreshToken = RefreshToken.builder()
                .revoked(false)
                .user(user)
                .token(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
                .expiryDate(Instant.now().plusMillis(refreshExpiration))
                .build();
        return refreshTokenRepository.save(refreshToken);

    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token == null){
            log.error("Token is null");
            throw new TokenException(null,"Token is null");
        }
        if(token.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new TokenException(token.getToken(),"Refresh Token is expired");
        }
        return token;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public RefreshTokenResponse generateNewToken(RefreshTokenRequest request) {
        User user =
                refreshTokenRepository.findByToken(request.getRefreshToken())
                        .map(this::verifyExpiration)
                        .map(RefreshToken::getUser)
                        .orElseThrow(() -> new
                                TokenException(request.getRefreshToken(),"Refresh token does not exist"));
        String token = jwtService.generateToken(user);
        return RefreshTokenResponse.builder()
                .accessToken(token)
                .refreshToken(request.getRefreshToken())
                .tokenType(TokenType.BEARER.name())
                .build();
    }

    @Override
    public void deleteByToken(String token) {

    }
}

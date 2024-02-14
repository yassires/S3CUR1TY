package com.youcode.security.repository;

import com.youcode.security.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

/*    @Query("select t from RefreshToken t where t.user.id = :userId and t.expiryDate = false and t.revoked = false")
    List<RefreshToken> findAllValidByUserId(Integer userId);*/
}

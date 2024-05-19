package com.luan.authenticationservice.repository;

import com.luan.authenticationservice.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    @Query("""
        select t from TokenEntity t inner join UserEntity u on t.user.userId = u.userId
        where t.user.userId = :userId and t.loggedOut = false
        """)
    List<TokenEntity> findAllTokensByUser(Long userId);
    Optional<TokenEntity> findByToken(String token);
    @Query("""
        select t from TokenEntity t inner join UserEntity u on t.user.userId = u.userId
        where t.loggedOut = false
        """)
    List<TokenEntity> findAllByIsLoggedOutFalse();
}


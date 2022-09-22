package com.twitter.clone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.clone.entity.RefreshTokenEntity;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long>{
	    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);
	    void deleteByRefreshToken(String refreshToken);
}

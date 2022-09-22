package com.twitter.clone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.clone.entity.LikeEntity;
import com.twitter.clone.entity.TweetEntity;
import com.twitter.clone.entity.UserEntity;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long>{
	Optional<LikeEntity> findByUserAndTweet(UserEntity user, TweetEntity tweet);
    Optional<List<LikeEntity>> findAllByUser(UserEntity user);
}

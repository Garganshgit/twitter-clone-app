package com.twitter.clone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.clone.entity.RetweetEntity;
import com.twitter.clone.entity.TweetEntity;
import com.twitter.clone.entity.UserEntity;

@Repository
public interface RetweetRepository extends JpaRepository<RetweetEntity, Long>{
	Optional<RetweetEntity> findByUserAndTweet(UserEntity user, TweetEntity tweet);
    Optional<RetweetEntity> findAllByTweet(TweetEntity tweet);
    Optional<List<RetweetEntity>> findAllByUser(UserEntity user);
}

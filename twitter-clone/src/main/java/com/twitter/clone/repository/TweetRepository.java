package com.twitter.clone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.clone.entity.TweetEntity;
import com.twitter.clone.entity.TweetType;
import com.twitter.clone.entity.UserEntity;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long>{
	Optional<List<TweetEntity>> findAllByUserAndType(UserEntity user, TweetType type);
    Optional<List<TweetEntity>> findAllByTweetAndType(TweetEntity tweet, TweetType type);
    Optional<List<TweetEntity>> findAllByType(TweetType type);
}

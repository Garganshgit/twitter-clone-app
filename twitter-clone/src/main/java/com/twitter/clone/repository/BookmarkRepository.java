package com.twitter.clone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.clone.entity.BookmarkEntity;
import com.twitter.clone.entity.TweetEntity;
import com.twitter.clone.entity.UserEntity;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>{
	Optional<BookmarkEntity> findByUserAndTweet(UserEntity user, TweetEntity tweet);
    Optional<List<BookmarkEntity>> findAllByUser(UserEntity user);
}

package com.twitter.clone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.clone.dto.LikeRetweetBookmarkDto;
import com.twitter.clone.service.TweetService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/retweets")
@RequiredArgsConstructor
public class RetweetController {
	
	private final TweetService tweetService;
	
	@PostMapping("/retweet")
    public ResponseEntity<HttpStatus> postRetweet(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        this.tweetService.retweet(likeRetweetBookmarkDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/is-retweeted")
    public ResponseEntity<Boolean> isRetweeted(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        return ResponseEntity.ok(this.tweetService.isRetweeted(likeRetweetBookmarkDto));
    }
	
}

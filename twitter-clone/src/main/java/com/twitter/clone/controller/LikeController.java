package com.twitter.clone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.clone.dto.LikeRetweetBookmarkDto;
import com.twitter.clone.service.TweetService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
	
	private final TweetService tweetService;
	
	@PostMapping("/like")
    public ResponseEntity<HttpStatus> like(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        this.tweetService.like(likeRetweetBookmarkDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/is-liked")
    public ResponseEntity<Boolean> isLiked(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        return ResponseEntity.ok(this.tweetService.isLiked(likeRetweetBookmarkDto));
    }

    @GetMapping("/like-counter/{id}")
    public ResponseEntity<Integer> likeCounter(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.tweetService.likeCounter(id));
    }
	
}

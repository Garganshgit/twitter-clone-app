package com.twitter.clone.controller;

import java.util.List;

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
import com.twitter.clone.dto.TweetResponseDto;
import com.twitter.clone.service.TweetService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
	
	private final TweetService tweetService;

    @PostMapping("/bookmark")
    public ResponseEntity<HttpStatus> bookmark(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        this.tweetService.bookmark(likeRetweetBookmarkDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<TweetResponseDto>> getBookmarks(@PathVariable String username){

        return ResponseEntity.ok(this.tweetService.getBookmarksByUsername(username));
    }

    @PostMapping("/is-bookmarked")
    public ResponseEntity<Boolean> isBookmarked(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        return ResponseEntity.ok(this.tweetService.isBookmarked(likeRetweetBookmarkDto));
    }
	
}

package com.twitter.clone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponseDto {
	private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String duration;
    private String tweetText;
    private Integer replyCounter;
    private Integer retweetCounter;
    private Integer likeCounter;
    private UserDto retweetedBy;
    private TweetResponseDto quote;
}

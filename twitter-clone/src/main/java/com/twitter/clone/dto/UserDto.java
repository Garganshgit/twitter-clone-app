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
public class UserDto {
	private String firstName;
    private String lastName;
    private String username;
    private String bio;
    private String location;
    private String personalWebsite;
    private String birthDate;
}

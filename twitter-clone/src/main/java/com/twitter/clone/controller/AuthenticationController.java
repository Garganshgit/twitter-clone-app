package com.twitter.clone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.clone.dto.LoginRequestDto;
import com.twitter.clone.dto.LoginResponseDto;
import com.twitter.clone.dto.RefreshTokenDto;
import com.twitter.clone.dto.SignUpRequestDto;
import com.twitter.clone.service.AuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        this.authenticationService.signup(signUpRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok().body(this.authenticationService.login(loginRequestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(@RequestBody RefreshTokenDto refreshTokenDto){
        this.authenticationService.logout(refreshTokenDto);
        return ResponseEntity.ok().build();
    };

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto ){
        return ResponseEntity.ok().body(this.authenticationService.refreshToken(refreshTokenDto));
    }

    @GetMapping("/usernames")
    public ResponseEntity<List<String>> findAllUsernames(){
        List<String> usernames = this.authenticationService.findAllUsernames();
        return ResponseEntity.ok(usernames);
    }
	
}

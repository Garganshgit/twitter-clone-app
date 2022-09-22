package com.twitter.clone.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.twitter.clone.dto.LoginRequestDto;
import com.twitter.clone.dto.LoginResponseDto;
import com.twitter.clone.dto.RefreshTokenDto;
import com.twitter.clone.dto.SignUpRequestDto;
import com.twitter.clone.entity.UserEntity;
import com.twitter.clone.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
//    private final UserService userService;
	
    public void signup(SignUpRequestDto signUpRequestDto){

        userRepository.save(
                UserEntity.builder()
                        .firstName(signUpRequestDto.getFirstName())
                        .lastName(signUpRequestDto.getLastName())
                        .username(signUpRequestDto.getUsername())
                        .email(signUpRequestDto.getEmail())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .profilePicturePath("C:/uploads/DummyProfilePicture.jpg")
                        .bannerPicturePath("C:/uploads/DummyBannerPicture.jpg")
                        .build()
        );

    }
    
    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtService.generateToken(authentication);
        String refreshToken = jwtService.generateRefreshToken().getRefreshToken();
        String username = loginRequestDto.getUsername();

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .username(username)
                .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                .build();
    }
    
    public void logout(RefreshTokenDto refreshTokenDto){
        jwtService.deleteRefreshToken(refreshTokenDto.getRefreshToken());
        SecurityContextHolder.clearContext();
    }
    
    public LoginResponseDto refreshToken(RefreshTokenDto refreshTokenDto){
        this.jwtService.validateRefreshToken(refreshTokenDto.getRefreshToken());
        String accessToken = this.jwtService.generateTokenWithUsername(refreshTokenDto.getUsername());
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenDto.getRefreshToken())
                .username(refreshTokenDto.getUsername())
                .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                .build();
    }
    
    public UserEntity getUserFromJwt(){
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByUsername(principal.getSubject()).orElseThrow();
    }
    
    public List<String> findAllUsernames(){
        return this.userRepository.findAll().stream().map(user -> user.getUsername()).collect(Collectors.toList());
    }
    
}

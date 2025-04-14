package com.demo.DemoSecurityJWT.user.controller;

import com.demo.DemoSecurityJWT.user.config.JwtUtil;
import com.demo.DemoSecurityJWT.user.dto.LoginRequestDto;
import com.demo.DemoSecurityJWT.user.dto.RefreshRequestDto;
import com.demo.DemoSecurityJWT.user.dto.TokenResponseDto;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        System.out.println("Login request: " + request.getUsername());

        try {
            // Xác thực thông tin người dùng
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            System.out.println("Authentication success");

            // Lấy thông tin người dùng từ DB
            final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
            // Tạo access token và refresh token
            final String accessToken = jwtUtil.generateToken(user.getUsername());
            final String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

            return ResponseEntity.ok(new TokenResponseDto(accessToken, refreshToken));
        } catch (BadCredentialsException ex) {
            System.out.println("Invalid credentials for user: " + request.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Unexpected error");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequestDto request) {
        String refreshToken = request.getRefreshToken();
        System.out.println("Received refresh token: " + refreshToken);

        if (jwtUtil.validateToken(refreshToken) && !jwtUtil.isTokenExpired(refreshToken)) {
            // Lấy username từ refresh token
            String username = jwtUtil.extractUsername(refreshToken);
            // Tạo access token mới
            String newAccessToken = jwtUtil.generateToken(username);
            System.out.println("Generated new access token for user: " + username);
            return ResponseEntity.ok(new TokenResponseDto(newAccessToken, refreshToken));
        } else {
            System.out.println("Invalid or expired refresh token for user");
            return ResponseEntity.status(401).body("Invalid or expired refresh token");
        }
    }
}

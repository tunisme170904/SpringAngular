package com.demo.DemoSecurityJWT.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class TokenResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 3054036114376220721L;

    private String accessToken;
    private String refreshToken;
}
package com.demo.DemoSecurityJWT.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RefreshRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 6785046773386182452L;

    private String refreshToken;
}

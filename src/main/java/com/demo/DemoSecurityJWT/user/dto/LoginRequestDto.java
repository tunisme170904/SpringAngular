package com.demo.DemoSecurityJWT.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4402091823391903629L;

    private String username;
    private String password;
}
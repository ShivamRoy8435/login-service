package com.login.login_service.dto.requestDto;

import lombok.Data;

@Data
public class JwtRequestDto {
    private String userName;
    private String password;
}

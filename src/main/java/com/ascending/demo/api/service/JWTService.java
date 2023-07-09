package com.ascending.demo.api.service;

import com.ascending.demo.api.dto.UserDto;
import io.jsonwebtoken.Claims;

public interface JWTService {
    String generateToken(UserDto userDto);

    Claims decryptJwtToken(String token);

    boolean hasTokenExpired(String token);

    boolean validateAccessToken(String token);
}

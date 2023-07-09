package com.ascending.demo.api.controller;

import com.ascending.demo.api.dto.UserDto;
import com.ascending.demo.api.service.JWTService;
import com.ascending.demo.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> authenticate(@RequestBody UserDto userDto) {
        //1. validate username/password
        //400 bad request
        //2. generate token
        //200 or 500

        Map<String, String> resultMap = new HashMap<>();
        try {
            UserDto retrievedUserDto = userService.getUserByCredentials(userDto.getEmail(), userDto.getPassword());
            if(retrievedUserDto == null) {
                resultMap.put("msg", "The input user email and password are incorrect");
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(resultMap);
            }

            String token = jwtService.generateToken(retrievedUserDto);
            resultMap.put("token", token);
        } catch (Exception e) {
            logger.error("Authenticate user failed. error = {}", e.getMessage());
            String errorMsg = e.getMessage();
            resultMap.put("msg", errorMsg);
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(resultMap);
        }
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(resultMap);
    }

}

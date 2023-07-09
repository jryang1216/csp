package com.ascending.demo.api.service.impl;

import com.ascending.demo.api.dto.RoleDto;
import com.ascending.demo.api.dto.UserDto;
import com.ascending.demo.api.service.JWTService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class JwtServiceImpl implements JWTService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //two methods to generate key
    //Method 1:
    //private final String SECRET_KEY = System.getProperty("secret_key");
    //Method 2:
    private final String SECRETE_KEY = System.getenv("secret_key");

    private final long EXPIRATION_TIME = 86400 * 1000; //in milliseconds

    private final String ISSUE = "Jingrong";
    @Override
    /*
           1. decide signature algorithm
         * 2. hard code secret key  -- late ruse VM option to pass in the key
         * 3. organize our payload:  Claims ---> map   cliams has some prefefined keys   ,  add your own custom key/value pairs
         * 4. set claims JWT api
         * 5. sign JWT with key
         * 6. generate the token
    */
    public String generateToken(UserDto userDto) {
        //check if secrete key is extracted
        logger.info("====, retrieved SECRETE_KEY = {}", SECRETE_KEY);
        //decide JWT signature algorithm to be used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        //Create and organize Claims (payloads)
        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(userDto.getId()));
        claims.setSubject(userDto.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUE);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        /*  1. retrieve all roles belong to the specific userDto
         *     user vs role is many to many
         *  2. loop through each role to populate (concatenate URI strings)
         *     the four variables:
         *     a. allowedReadResources
         *     b. allowedCreateResources
         *     c. allowedUpdateResources
         *     d. allowedDeleteResources
         *  3. remove the ending "comma" from the above four String variables
         *  4. Add the four Strings as custom attributes to JWT Claims         *
         */

        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";

        Set<RoleDto> roleDtoSet = userDto.getRoleDtoSet();
        for(RoleDto roleDto : roleDtoSet) {
            if(roleDto.isAllowedRead()) {
                allowedReadResources = String.join(",", roleDto.getAllowedResource(), allowedReadResources);
            }
            if(roleDto.isAllowedCreate()) {
                allowedCreateResources = String.join(",", roleDto.getAllowedResource(), allowedCreateResources);
            }
            if(roleDto.isAllowedUpdate()) {
                allowedUpdateResources = String.join(",", roleDto.getAllowedResource(), allowedUpdateResources);
            }
            if(roleDto.isAllowedDelete()) {
                allowedDeleteResources = String.join(",", roleDto.getAllowedResource(), allowedDeleteResources);
            }
            logger.info("=====, The final result of allowedReadResources = {}", allowedReadResources);
            logger.info("=====, The final result of allowedCreateResources = {}", allowedCreateResources);
            logger.info("=====, The final result of allowedUpdateResources = {}", allowedUpdateResources);
            logger.info("=====, The final result of allowedDeleteResources = {}", allowedDeleteResources);

            /*
            Regular Expression:
            str.replaceAll(".$", "")  --> means remove the last character (except line terminators) of a string
            line terminators are carriage return "\r" and new line "\n"
            $means remove any character
            ^ start of a line
            . -> any character
            * -> zero or more
            ? -> once or more
            *? zero or many times
            Examples:
            str.replaceAll("^(.*?) ", "") -->  It is looking for the first space from the beginning of a string and remove it.
						"12 123 2345678" -> "123 2345678 "
						"123 2345678"
             */
            claims.put("allowedReadResources", allowedReadResources.replaceAll(",$", ""));
            claims.put("allowedCreateResources", allowedCreateResources.replaceAll(",$", ""));
            claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(",$", ""));
            claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(",$", ""));

        }





        //set claims and sign --> sign with plain secret key String
        //JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, SECRETE_KEY);
        //we can also sign with String byte array
        byte[] secreteKeyStringBytes = DatatypeConverter.parseBase64Binary(SECRETE_KEY);
        //Key signKey = new SecretKeySpec(secreteKeyStringBytes, signatureAlgorithm.getJcaName());
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, secreteKeyStringBytes);

        //build the JWT token and then serializes the token to compact it
        String generatedToken = jwtBuilder.compact();

        return generatedToken;
    }

    @Override
    public Claims decryptJwtToken(String token) {
        byte[] secreteKeyStringBytes = DatatypeConverter.parseBase64Binary(SECRETE_KEY);
        Claims claims = Jwts.parser()
                .setSigningKey(secreteKeyStringBytes)
                .parseClaimsJws(token)
                .getBody();
        logger.info("====parsed claims = {}", claims);
        return claims;
    }

    @Override
    public boolean hasTokenExpired(String token) {
        boolean hasTokenExpired = true;
        try {
            Claims claims = decryptJwtToken(token);
            Date tokenExpirationDate = claims.getExpiration();
            Date nowDate = new Date();

            hasTokenExpired = nowDate.after(tokenExpirationDate);
        } catch (ExpiredJwtException e) {
            logger.error("ExpiredJwtException is caught, error = {}", e.getMessage());
        }
        return hasTokenExpired;
    }

    @Override
    public boolean validateAccessToken(String token) {
        boolean isTokenValid = false;
        try {
            byte[] secretKeyStringBytes = DatatypeConverter.parseBase64Binary(String.valueOf(SECRET_KEY));
            Jwts.parser()
                    .setSigningKey(secretKeyStringBytes)
                    .parseClaimsJws(token);
            isTokenValid = true;
        } catch (ExpiredJwtException ex) {
            logger.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            logger.error("Signature validation failed");
        }
        return isTokenValid;
    }





















}

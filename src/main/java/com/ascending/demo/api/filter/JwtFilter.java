package com.ascending.demo.api.filter;

import com.ascending.demo.api.dto.UserDto;
import com.ascending.demo.api.service.JWTService;
import com.ascending.demo.api.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "jwtFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})

public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTH_URI_EXTERNAL = "project2023/auth";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;


    private String AUTH_URI = "/auth";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        int statusCode = authorization(request);//决定让他继续往前走还是return error
        if (statusCode == HttpServletResponse.SC_ACCEPTED) { //best practice
            filterChain.doFilter(request, response);
        } else {
            response.sendError(statusCode);
        }
    }

    private int authorization(HttpServletRequest request) {

        int statusCode = HttpServletResponse.SC_ACCEPTED;

        String incomingUri = request.getRequestURI();
        logger.info("=====, within authorization(...), incomingUri = {}", incomingUri);

        if (incomingUri.equalsIgnoreCase(AUTH_URI)) {
            return HttpServletResponse.SC_ACCEPTED;
        }

        try {
            String wholeTokenString = request.getHeader("Authorization");
            if (retrievedWholeTokenStringExist(wholeTokenString)) {
                String token = wholeTokenString.split(" ")[1].trim();
                logger.info("=====, within authorization(...), token = {}", token);
                if (token == null || token.trim().isEmpty()) {
                    return HttpServletResponse.SC_UNAUTHORIZED;
                }

                //validate token
//                boolean isTokenValid = jwtService.validateAccessToken(token);
//                if (isTokenValid) {
//                    statusCode = HttpServletResponse.SC_ACCEPTED;
//                }

                /*
                 *  1. parse the token and get Claims
                 *  2. verify user by ID through userService => unnecessary?
                 *  3. retrieve http method value from input HttpServletRequest (GET, POST, UPDATE,
                 *       PATCH, DELETE?)
                 *  4. based on http method value, retrieve the corresponding concatenated URI string
                 *  5. check to see if the allowed URI string does include the incomingUri
                 *      a. If yes, return HttpServletResponse.SC_ACCEPTED to allow the incoming request
                 *         to continue
                 *      b. If no, return HttpServletResponse.SC_UNAUTHORIZED to block the incoming request
                 */
                Claims claims = jwtService.decryptJwtToken(token);
                if(!isClaimsValid(claims)) {
                    return HttpServletResponse.SC_UNAUTHORIZED;
                }
                logger.info("==== within authorization(...), parsed claims = {}", claims);

                //May not be necessary去访问一下库
                if(isUserRetrievedByUserIdInvalid(claims.getId())){
                    return HttpServletResponse.SC_UNAUTHORIZED;
                }

                //based on http method value, retrieve the corresponding concatenated URI string
                String httpMethodValue = request.getMethod();
                boolean isRequestedUriAllowedToBeAccessed = checkRequestUriAuthorization(claims, httpMethodValue, incomingUri);
                if(isRequestedUriAllowedToBeAccessed) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                }

                //parse the token and verify user info
//                Claims claims = jwtService.decryptJwtToken(token);
//                logger.info("==== within authorization(...), parsed claims = {}", claims);

            }
        } catch (Exception e) {
            logger.error("Exception is thrown when parsing JWT token, error = {}", e.getMessage());

        }
        return statusCode;
    }

    private boolean checkRequestUriAuthorization(Claims claims, String httpMethodValue, String requestUri) {
        boolean isAuthorized = false;
        String allowedResources = findAllowedResourcesUsingHttpMethodValueWithClaims(claims, httpMethodValue);
        String[] allowedResourcesArray = allowedResources.split(",");
        for(String eachAllowedResource : allowedResourcesArray) {
            logger.info("======, requestUri = {}, eachAllowedResource = {}", requestUri, eachAllowedResource);
            if(requestUri.trim().toLowerCase().startsWith(eachAllowedResource.trim().toLowerCase())) {
                isAuthorized = true;
                break;
            }
        }
        return isAuthorized;
    }

    private String findAllowedResourcesUsingHttpMethodValueWithClaims(Claims claims, String httpMethodValue) {
        String allowedResources = "";
        switch(httpMethodValue) {
            case "GET" :
                allowedResources = (String)claims.get("allowedReadResources");
                break;
            case "POST" :
                allowedResources = (String)claims.get("allowedCreateResources");
                break;
            case "PUT" :
                allowedResources = (String)claims.get("allowedUpdateResources");
                break;
            case "PATCH" :
                allowedResources = (String)claims.get("allowedUpdateResources");
                break;
            case "DELETE" :
                allowedResources = (String)claims.get("allowedDeleteResources");
                break;
        }
        return allowedResources;
    }

    private boolean isUserRetrievedByUserIdInvalid(String id) {
        boolean isValid = false;
        if(id != null) {
            UserDto userDto = userService.getUserById(Long.valueOf(id));
            if(userDto != null) {
                isValid = true;
                logger.info("=====, Now using userId = {}, retrieved userDto = {}", id, userDto);
            }
        }
        return !isValid;

    }

    private boolean isClaimsValid(Claims claims) {
        boolean isClaimsValid = true;
        if(claims == null || claims.isEmpty()) {
            isClaimsValid = false;
        }
        return isClaimsValid;
    }

    private boolean retrievedWholeTokenStringExist(String wholeTokenString) {
        boolean isStringExist = false;
        if (wholeTokenString != null && !wholeTokenString.trim().isEmpty()) {
            isStringExist = true;
        }
        return isStringExist;
    }


    private boolean incomingUriRequestForAuth(String uri) {
        boolean isUriRequestForAuth = false;
        if (uri.equalsIgnoreCase(AUTH_URI) || uri.equalsIgnoreCase(AUTH_URI_EXTERNAL)) {
            isUriRequestForAuth = true;
        }
        return isUriRequestForAuth;

    }

    //When the app is deployed to external Tomcat, Spring DI unable to be fulfilled
    //Therefore, we need to override the following method which inherited from GenericFilterBean to initialize the DI components manually
    @Override
    public void initFilterBean() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
    }
}
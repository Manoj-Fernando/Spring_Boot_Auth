/**
 * 
 */
package com.auth0.samples.authapi.springbootauthupdated.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.auth0.samples.authapi.springbootauthupdated.security.SecurityConstants.EXPIRATION_TIME;
import static com.auth0.samples.authapi.springbootauthupdated.security.SecurityConstants.HEADER_STRING;
import static com.auth0.samples.authapi.springbootauthupdated.security.SecurityConstants.SECRET;
import static com.auth0.samples.authapi.springbootauthupdated.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.samples.authapi.springbootauthupdated.user.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Manoj Fernando A
 *By extending the filter provided within the security framework, 
 *Spring can automatically identify the best place to put it in the security chain
 *
 *This Filter is called only when login end point is hit
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /*
	 * This method is automatically called by Authentication Manager to authenticate
	 */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            ApplicationUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), ApplicationUser.class);
            log.info("Authentication is happening");
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
	 * This method is automatically called by Authentication Manager to authenticate
	 */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
    	log.info("Successful authentication happened");
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        log.info("JWT has been issued");
    }
}
/**
 * 
 */
package com.auth0.samples.authapi.springbootauthupdated.security;

/**
 * @author Manoj Fernando A
 *
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 120000; // 10 days=864_000_000ms 1 minute=60000ms
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
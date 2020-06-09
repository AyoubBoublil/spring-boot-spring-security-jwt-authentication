package com.boublil.jwtspringsecurity.security;

public class SecurityParams {
    public static final String SECRET = "ayoub@gmail.com";
    public static final long EXPIRATION_TIME = 846_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}

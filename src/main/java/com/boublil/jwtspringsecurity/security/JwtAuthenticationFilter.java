package com.boublil.jwtspringsecurity.security;

import com.boublil.jwtspringsecurity.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AppUser user = null;
        //Si les données envoyé au format wwwUrlEncoded ===> username=ayoub&password=123456
     /*   String username  =request.getParameter("username");
        String password = request.getParameter("password");*/

        //Si les données envoyé au format Json
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User springUser = (User) authResult.getPrincipal();
        String jwtToken = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityParams.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityParams.SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();

        response.addHeader(SecurityParams.HEADER_STRING, SecurityParams.TOKEN_PREFIX + jwtToken);
    }
}

package com.boublil.jwtspringsecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, Content-Type," +
                        "Access-Control-Request-Method," +
                        "Access-Control-Request-Headers," +
                        "Authorization");

        response.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin," +
                        "Access-Control-Allow-Credentials," +
                        "Authorization");


        String jwt = request.getHeader(SecurityParams.HEADER_STRING);
        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            if (jwt == null || !jwt.startsWith(SecurityParams.TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }

            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityParams.SECRET)
                    .parseClaimsJws(jwt.replace(SecurityParams.TOKEN_PREFIX, ""))
                    .getBody();
            String username = claims.getSubject();
            ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
            });

            UsernamePasswordAuthenticationToken authenticatedUser =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            chain.doFilter(request, response);

        }
    }
}

package ru.pio.aclij.taskmanagement.security.configs;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.pio.aclij.taskmanagement.security.services.util.JwtTokenUtil;
import ru.pio.aclij.taskmanagement.security.services.util.TokenAuthenticationHelper;

import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Log
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil util;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = TokenAuthenticationHelper.getTokenFromRequest(request);
        String username = null;
        if (token != null) {
            try {
                username = util.getUsername(token);
            } catch (ExpiredJwtException e) {
                log.info("Token expired");
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println(username);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            System.out.println(util.getRoles(token).isEmpty());
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    util.getRoles(token).stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet())
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        System.out.println(username);
        System.out.println(token);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(request, response);
    }
}

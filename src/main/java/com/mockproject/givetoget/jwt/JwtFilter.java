package com.mockproject.givetoget.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            String token = headerAuth.substring(7);
            try {
                UserInfo data = jwtUtils.parserToken(token);
                if (data != null) {
                    List<SimpleGrantedAuthority> role = new ArrayList<>();
                    role.add(new SimpleGrantedAuthority(data.getRole().replaceAll("[\\[\\]]", "")));
                    UsernamePasswordAuthenticationToken user =
                            new UsernamePasswordAuthenticationToken(data.getEmail(), "", role);
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(user);
                }
            } catch (Exception e) {
                System.err.println("Token không hợp lệ: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}

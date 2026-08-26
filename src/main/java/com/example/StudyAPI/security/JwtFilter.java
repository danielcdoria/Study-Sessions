package com.example.StudyAPI.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.imageio.IIOException;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetails;
    public JwtFilter(JwtUtil jwtUtil,
                     UserDetailsServiceImpl userDetails){
        this.jwtUtil = jwtUtil;
        this.userDetails = userDetails;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException{
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.equals("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.isValidToken(token)){
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtUtil.extractEmail(token);
    }


}

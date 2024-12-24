package com.example.School_Management_system.filters;

import com.example.School_Management_system.services.jwt.UserDetailServiceImpl;
import com.example.School_Management_system.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    private final UserDetailServiceImpl userDetailService;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(UserDetailServiceImpl userDetailService,JwtUtil jwtUtil)
    {
        this.userDetailService=userDetailService;
        this.jwtUtil=jwtUtil;
    }
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorizationHeader);
        String username = null;
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            System.out.println("Extracted Token from Header: " + token);
            try {
                username = jwtUtil.extractAllClaims(token).getSubject();
            } catch (Exception e) {
                logger.error("JWT processing failed: " + e.getMessage());
            }
        }

           if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
               UserDetails userDetails=userDetailService.loadUserByUsername(username);
               if(jwtUtil.validateToken(token,userDetails)){
                   UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               }
           }

           filterChain.doFilter(request,response);
    }
}

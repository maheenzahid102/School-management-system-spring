package com.example.School_Management_system.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req; // Cast to HttpServletRequest
        HttpServletResponse response = (HttpServletResponse) res; // Cast to HttpServletResponse

        Map<String, String> map = new HashMap<>();

        // Get the Origin header from the request
        String originHeader = request.getHeader("Origin");

        // Set headers for CORS
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");

        // Explicitly allow the Authorization header
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With");

        // If the request method is OPTIONS, send a 200 OK response
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res); // Pass the request and response to the next filter in the chain
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SimpleCorsFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req; // Cast to HttpServletRequest
//        HttpServletResponse response = (HttpServletResponse) res; // Cast to HttpServletResponse
//
////        Map<String, String> map = new HashMap<>();
////
////        // Get the Origin header from the request
////        String originHeader = request.getHeader("Origin");
////
////        // Set headers for CORS
////        response.setHeader("Access-Control-Allow-Origin", originHeader);
////        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
////        response.setHeader("Access-Control-Max-Age", "3600");
////        response.setHeader("Access-Control-Allow-Headers", "*");
////
////        // If the request method is OPTIONS, send a 200 OK response
////        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
////            response.setStatus(HttpServletResponse.SC_OK);
////        } else {
////            chain.doFilter(req, res); // Pass the request and response to the next filter in the chain
////        }
////    }
////
////    @Override
////    public void init(FilterConfig filterConfig) throws ServletException {
////        Filter.super.init(filterConfig);
////    }
////
////    @Override
////    public void destroy() {
////        Filter.super.destroy();
////    }
//}

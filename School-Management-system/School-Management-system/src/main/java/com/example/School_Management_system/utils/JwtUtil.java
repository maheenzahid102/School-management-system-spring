package com.example.School_Management_system.utils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

    @Component
    public class JwtUtil {

        // Secret key for signing the JWT
        private static final String SECRET_KEY = "8u6Ywr3rk8QDdThmhquT1Fy5ebM0unDxyxh6TQQ0Ep0=";

        // Token validity in milliseconds (e.g., 1 hour)
        private static final long TOKEN_VALIDITY = 3600000;

        // Generate a JWT token
        static public String generateToken(String username) {

            System.out.println(username);
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        }

        // Validate the token
        public boolean validateToken(String token, UserDetails username) {
            try {
                final String extractedUsername = extractUsername(token);
                return (extractedUsername.equals(username) && !isTokenExpired(token));
            } catch (Exception e) {
                return false;
            }
        }

        // Extract username from token
        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        // Extract expiration date from token
        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        // Extract any claim from token
        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        // Check if the token is expired
        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        // Extract all claims with URL-safe decoding
        public Claims extractAllClaims(String token) {
            try {
                return Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY )
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            } catch (IllegalArgumentException | MalformedJwtException | UnsupportedJwtException e) {
                throw new RuntimeException("Invalid JWT format: " + e.getMessage());
            } catch (ExpiredJwtException e) {
                throw new RuntimeException("JWT has expired: " + e.getMessage());
            } catch (DecodingException e) {
                throw new RuntimeException("Decoding error in JWT: " + e.getMessage());
            }
        }

        // Decode URL-safe Base64 token to standard Base64
        private String decodeUrlSafeBase64(String token) {
            token = token.replace('-', '+').replace('_', '/');
            int paddingLength = 4 - token.length() % 4;
            if (paddingLength < 4) {
                token += "=".repeat(paddingLength); // Add padding if necessary
            }
            return token;
        }
    }


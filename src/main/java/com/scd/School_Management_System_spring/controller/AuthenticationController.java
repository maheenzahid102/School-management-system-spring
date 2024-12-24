package com.scd.School_Management_System_spring.controller;

import com.scd.School_Management_System_spring.JwtUtil.Jwtutil;
import com.scd.School_Management_System_spring.dto.AuthenticationRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private Jwtutil jwtutil;

    @Autowired
    private HttpServletResponse httpServletResponse;
@PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (DisabledException disabledException) {
            try {
                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "User not created");
                return null;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt=Jwtutil.generateToken(userDetails.getUsername());
        return new  AuthenticationResponse(jwt);
    }
}

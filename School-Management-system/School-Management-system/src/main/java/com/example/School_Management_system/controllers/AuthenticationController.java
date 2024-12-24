//////package com.example.School_Management_system.controllers;
//////import com.example.School_Management_system.utils.JwtUtil;
//////import com.example.School_Management_system.dto.AuthenticationRequest;
//////import jakarta.servlet.http.HttpServletResponse;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.http.HttpStatus;
//////import org.springframework.http.ResponseEntity;
//////import org.springframework.security.authentication.AuthenticationManager;
//////import org.springframework.security.authentication.BadCredentialsException;
//////import org.springframework.security.authentication.DisabledException;
//////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////import org.springframework.security.core.userdetails.UserDetails;
//////import org.springframework.security.core.userdetails.UserDetailsService;
//////import org.springframework.web.bind.annotation.PostMapping;
//////import org.springframework.web.bind.annotation.RequestBody;
//////import org.springframework.web.bind.annotation.RequestMapping;
//////import org.springframework.web.bind.annotation.RestController;
//////
//////import java.io.IOException;
//////
//////@RestController
//////@RequestMapping("/authenticate")
//////public class AuthenticationController {
//////
//////    @Autowired
//////    private AuthenticationManager authenticationManager;
//////    @Autowired
//////    private UserDetailsService userDetailsService;
//////    @Autowired
//////    private JwtUtil jwtutil;
//////
//////    //    @Autowired
////////    private HttpServletResponse httpServletResponse;
////////    @PostMapping
////////    public <AuthenticationRequest> ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
////////        try {
////////            System.out.println("INSIDE");
////////            authenticationManager.authenticate(
////////                    new UsernamePasswordAuthenticationToken(
////////                            authenticationRequest.getEmail(),
////////                            authenticationRequest.getPassword()
////////                    )
////////            );
////////        } catch (BadCredentialsException e) {
////////            throw new BadCredentialsException("Invalid username or password");
////////        } catch (DisabledException disabledException) {
////////            try {
////////                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "User not created");
////////                return null;
////////            } catch (IOException) {
////////                ioException.printStackTrace();
////////            }
////////        }
////////        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
////////
////////        final String jwt=JwtUtil.generateToken(userDetails.getUsername());
////////        System.out.println("HERE");
////////        return new ResponseEntity<>(jwt, HttpStatus.OK);
////////    }
////////}
//////    @PostMapping
//////    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
//////        try {
//////            // Authenticate user using email and password
//////            authenticationManager.authenticate(
//////                    new UsernamePasswordAuthenticationToken(
//////                            authenticationRequest.getEmail(),
//////                            authenticationRequest.getPassword()
//////                    )
//////            );
//////        } catch (BadCredentialsException e) {
//////            throw new BadCredentialsException("Invalid username or password");
//////        } catch (DisabledException disabledException) {
//////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User account is disabled");
//////        }
//////
//////        // Load user details
//////        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//////
//////        // Generate JWT
//////        final String jwt = jwtutil.generateToken(userDetails.getUsername());
//////
//////        // Return the JWT as a response
//////        return new ResponseEntity<>(jwt, HttpStatus.OK);
//////    }
//////
////package com.example.School_Management_system.controllers;
////
////import com.example.School_Management_system.repositories.UserRepository;
////import com.example.School_Management_system.utils.JwtUtil;
////import com.example.School_Management_system.dto.AuthenticationRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import netscape.javascript.JSObject;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.BadCredentialsException;
////import org.springframework.security.authentication.DisabledException;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import java.io.IOException;
////import java.util.Optional;
////
////@RestController
////@RequestMapping("/authenticate")
////public class AuthenticationController {
////
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    @Autowired
////    private UserDetailsService userDetailsService;
////
////    @Autowired
////    private JwtUtil jwtutil;
////
////
////    @Autowired
////    private UserRepository userRepository;
////
////    public static final String TOKEN_PREFIX="Bearer";
////
////    public static final String HEADER_STRING="Authorization";
////
////    @PostMapping
////    public void  createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response)throws IOException,JSONException {
////        try {
////            // Authenticate user using email and password
////            authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(
////                            authenticationRequest.getEmail(),
////                            authenticationRequest.getPassword()
////                    )
////            );
////        } catch (BadCredentialsException e) {
////            throw new BadCredentialsException("Invalid username or password");
////        } catch (DisabledException disabledException) {
////           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User account is disabled");
////            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not Activated");
////            return;
////        }
////
////        // Load user details
////        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
////
////        Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
////
////        // Generate JWT
////        final String jwt = jwtutil.generateToken(userDetails.getUsername());
////
////        if(optionalUser.isPresent()){
////            response.getWriter().write(new JSObject()
////                    .put("userId",optionalUser.get().getId())
////                    .put("role",optionalUser.get().getRole())
////                    .toString());
////        }
////
////        response.setHeader("Access-Control-Expose-Headers","Authorization");
////        response.setHeader("Access-Control-Allow-Headers","Authorization,X-Pingother,Origin,X-Requested-With,Contennt-Type,Accept,X-Custom-header");
////        response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);
////        // Return the JWT as a response
////       // return new ResponseEntity<>(jwt, HttpStatus.OK);
////    }
////}
//
//package com.example.School_Management_system.controllers;
//
//import com.example.School_Management_system.repositories.UserRepository;
//import com.example.School_Management_system.utils.JwtUtil;
//import com.example.School_Management_system.dto.AuthenticationRequest;
//import com.example.School_Management_system.models.User;  // Make sure to import your User model
//import jakarta.servlet.http.HttpServletResponse;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/authenticate")
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public static final String TOKEN_PREFIX = "Bearer ";
//    public static final String HEADER_STRING = "Authorization";
//
//    @PostMapping
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
//        try {
//            // Authenticate user using email and password
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getEmail(),
//                            authenticationRequest.getPassword()
//                    )
//            );
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        } catch (DisabledException disabledException) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not activated");
//        }
//
//        // Load user details
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//
//        // Generate JWT
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//         Create a response JSON object with user details
//        JSONObject responseJson = new JSONObject();
//        if (optionalUser.isPresent()) {
//            responseJson.put("userId", optionalUser.get().getId());
//            responseJson.put("role", optionalUser.get().getRole());
//        }
//
//        // Set headers and JWT token
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-With,Content-Type,Accept,X-Custom-header");
//        response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
//
//        // Return response with user details and JWT
//        response.getWriter().write(responseJson.toString());
//        return ResponseEntity.status(HttpStatus.OK).body("Authentication successful");
//    }
//}
package com.example.School_Management_system.controllers;

import com.example.School_Management_system.repositories.UserRepository;
import com.example.School_Management_system.utils.JwtUtil;
import com.example.School_Management_system.dto.AuthenticationRequest;
import com.example.School_Management_system.entities.User;  // Make sure to import your User model
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

//    @PostMapping
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
//        try {//this was working last correclty working code
//            // Authenticate user using email and password
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getEmail(),
//                            authenticationRequest.getPassword()
//                    )
//            );
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        } catch (DisabledException disabledException) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not activated");
//        }
//
//        // Load user details
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//
//        // Generate JWT
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//
//        // Create a response map or object
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("jwt", jwt);
//
//        // Return response with JWT token
//        System.out.println("Generated JWT: " + jwt);
//        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
//    }
//}

    @PostMapping
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try {
            // Log incoming request
            System.out.println("Authentication request received for: " + authenticationRequest.getEmail());

            // Authenticate user using email and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            // Log exception
            System.out.println("Bad credentials exception: " + e.getMessage());

            // Set response status and write the response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid username or password");
            return;
        } catch (DisabledException disabledException) {
            // Log exception
            System.out.println("User is disabled: " + disabledException.getMessage());

            // Set response status and write the response
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("User is not activated");
            return;
        }

        // Load user details
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        // Log user details
        System.out.println("Loaded user details for: " + userDetails.getUsername());

        // Generate JWT
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Log generated JWT
        System.out.println("Generated JWT: " + jwt);

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Create the response JSON object with user details and JWT
        ObjectNode responseJson = objectMapper.createObjectNode();
        responseJson.put("jwt", jwt);

        // Optionally add user details to the response if present
        if (optionalUser.isPresent()) {
            responseJson.put("userId", optionalUser.get().getId());
            responseJson.put("role", optionalUser.get().getRole().toString());
        }

        // Set headers and JWT token
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-With,Content-Type,Accept,X-Custom-header");
        response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

        // Log response JSON before writing
        System.out.println("Writing response JSON: " + responseJson.toString());

        // Write the response JSON to the response body
        response.getWriter().write(responseJson.toString());

        // Set the response status to 200 OK
        response.setStatus(HttpServletResponse.SC_OK);
        System.out.println("Headers after setting:");
        System.out.println("Access-Control-Expose-Headers: " + response.getHeader("Access-Control-Expose-Headers"));
        System.out.println("Access-Control-Allow-Headers: " + response.getHeader("Access-Control-Allow-Headers"));
        System.out.println("Authorization: " + response.getHeader(HEADER_STRING));

        // Write the response


        // Log that the response was successfully written
        System.out.println("Response successfully written with status 200 OK.");
    }
}

//    @PostMapping
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
//        try {
//            // Authenticate user using email and password
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getEmail(),
//                            authenticationRequest.getPassword()
//                    )
//            );
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        } catch (DisabledException disabledException) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not activated");
//        }
//
//        // Load user details
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//
//        // Generate JWT
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
////        // Create a response JSON object with user details
////        JSONObject responseJson = new JSONObject();
////        if (optionalUser.isPresent()) {
////            responseJson.put("userId", optionalUser.get().getId());
////            responseJson.put("role", optionalUser.get().getRole());
////        }
//
//        // Set headers and JWT token
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-With,Content-Type,Accept,X-Custom-header");
//        response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
//
//        // Write the response JSON to the response body
//        //response.getWriter().write(responseJson.toString());
//        response.setStatus(HttpServletResponse.SC_OK);  // Set the response status to 200 OK
//
//        return null; // Return null because the response is already written
//    }
//}

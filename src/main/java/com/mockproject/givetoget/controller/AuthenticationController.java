package com.mockproject.givetoget.controller;

import com.google.gson.Gson;
import com.mockproject.givetoget.jwt.JwtUtils;
import com.mockproject.givetoget.jwt.UserInfo;
import com.mockproject.givetoget.request.LoginRequest;
import com.mockproject.givetoget.utils.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private Gson gson;

    @PostMapping("/log-in")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authen);
            UserInfo userInfo = UserInfo.builder()
                    .email(authentication.getName())
                    .role(authentication.getAuthorities().toString())
                    .build();

            String token = jwtUtils.generateToken(userInfo);
            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (UserNotExistException e) {
            return new ResponseEntity<>("Account does not exist", HttpStatus.NOT_FOUND);
        }
    }
}

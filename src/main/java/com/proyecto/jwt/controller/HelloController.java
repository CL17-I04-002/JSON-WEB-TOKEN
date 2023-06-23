package com.proyecto.jwt.controller;

import com.proyecto.jwt.config.MyUserDetailsService;
import com.proyecto.jwt.model.AuthenticationRequest;
import com.proyecto.jwt.model.AuthenticationResponse;
import com.proyecto.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello World!");
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            /**
             * Validamos las crdenciales
             */
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
        );
        }catch (BadCredentialsException e){
            //throw new Exception("Incorrect username of password", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username of password");
        }
        /**
         * En caso las credenciales sean correctas, buscamos el usuario de la bd en base a lo que digito el usuario y le generamos un jwt
         */
        try {
            final UserDetails userDetails = userDetailsService
                    .loadByUsernameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            System.out.println(jwt);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username of password");
        }
    }
}
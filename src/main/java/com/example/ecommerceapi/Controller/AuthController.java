package com.example.ecommerceapi.Controller;

import com.example.ecommerceapi.DTO.AuthRegisterRequest;
import com.example.ecommerceapi.DTO.AuthRequest;
import com.example.ecommerceapi.DTO.AuthResponse;
import com.example.ecommerceapi.Service.JwtService;
import com.example.ecommerceapi.Service.UserService;
import com.example.ecommerceapi.entities.User;
import com.example.ecommerceapi.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.websocket.RemoteEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    // kullanıcıyı dogrulamak icin
    private final AuthenticationManager authenticationManager;
    // token üretimi icin
    private final JwtService jwtService;
    // kullanıcı bilgilerini yüklemek icin
    private final UserDetailsService userDetailsService;

    // password encoder
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRegisterRequest req) {
        String hashedPassword = passwordEncoder.encode(req.getPassword());
       User newUser = new User(req.getUserName(),req.getEmail(),hashedPassword,req.getPhoneNumber());
       try{
           userService.createUser(newUser);
           return new ResponseEntity<>(HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(req.getEmail());
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token, "token olustu"));



    }
}
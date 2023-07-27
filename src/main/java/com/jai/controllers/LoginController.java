package com.jai.controllers;

import com.jai.dtos.LoginRequestDTO;
import com.jai.dtos.LoginResponseDTO;
import com.jai.dtos.UserDTO;
import com.jai.entity.User;
import com.jai.services.UserServiceImpl;
import com.jai.utils.JWTUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class LoginController {

    private UserServiceImpl userService;
    private ModelMapper modelMapper;
    private JWTUtils jwtUtils;
    private AuthenticationProvider authenticationManager;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        String token = null;
        LoginResponseDTO loginResponseDTO = null;
        Map<String, Object> claims = new HashMap<>();
        Set<String> roles = new HashSet<>();
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(loginRequestDTO.getUsername());
        for (GrantedAuthority r: userDetails.getAuthorities()){
            roles.add(r.getAuthority());
        }
        claims.put("Roles", roles);
        token = jwtUtils.generateToken(claims, loginRequestDTO.getUsername());
        if (token != null){
            loginResponseDTO = new LoginResponseDTO(token, "Token generated successfully");
        }
        return loginResponseDTO;

    }


}

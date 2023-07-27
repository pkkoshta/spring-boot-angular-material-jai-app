package com.jai.controllers;

import com.jai.dtos.LoginRequestDTO;
import com.jai.dtos.LoginResponseDTO;
import com.jai.dtos.UserDTO;
import com.jai.entity.User;
import com.jai.services.UserServiceImpl;
import com.jai.utils.JWTUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;
    private ModelMapper modelMapper;

    @GetMapping("/getUsers")
    public List<User> users(){
        return userService.users();
    }

    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO){
        User user = null;
        UserDTO userDTO1=null;
        user = modelMapper.map(userDTO, User.class);
        user = userService.create(user);
        userDTO1=  modelMapper.map(user, UserDTO.class);
        return userDTO1;
    }
}

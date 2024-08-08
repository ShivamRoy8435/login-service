package com.login.login_service.controller;

import com.login.login_service.config.PasswordEncoderConfig;
import com.login.login_service.dto.requestDto.JwtRequestDto;
import com.login.login_service.dto.responseDto.JwtResponseDto;
import com.login.login_service.entity.UserEntity;
import com.login.login_service.repository.UserRepo;
import com.login.login_service.service.MyUserDetailService;
import com.login.login_service.utils.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepository; // Replace with your actual repository

    @Autowired
    private PasswordEncoder passwordEncoder;
@GetMapping("/user")
public String gethello(){
    return "hii";
}
    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody JwtRequestDto jwtRequestDto) throws Exception {
        authenticate(jwtRequestDto.getUserName(), jwtRequestDto.getPassword());


//            List<UserEntity> users = userRepository.findAll(); // Replace with actual method to get users
//            for (UserEntity user : users) {
//                String encodedPassword = passwordEncoder.encode(user.getPassword());
//                user.setPassword(encodedPassword);
//                userRepository.save(user);
//            }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequestDto.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        JwtResponseDto jwtResponseDto= new JwtResponseDto();
        jwtResponseDto.setJwtToken(jwt);
        return jwtResponseDto;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

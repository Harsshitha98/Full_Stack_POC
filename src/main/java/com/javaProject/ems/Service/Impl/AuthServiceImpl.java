package com.javaProject.ems.Service.Impl;

import com.javaProject.ems.DTO.LoginDTO;
import com.javaProject.ems.DTO.RegisterDTO;
import com.javaProject.ems.Entity.Role;
import com.javaProject.ems.Entity.User;
import com.javaProject.ems.Exception.EmsException;
import com.javaProject.ems.Repository.RoleRepository;
import com.javaProject.ems.Repository.UserRepository;
import com.javaProject.ems.Security.JwtTokenProvider;
import com.javaProject.ems.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDTO registerDTO) {

        //Username exists or not in db
        if(userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new EmsException(HttpStatus.BAD_REQUEST,"Username already exist!");
        }

        if(userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new EmsException(HttpStatus.BAD_REQUEST,"Email already exist!");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRoles = roleRepository.findByName("ROLE_USER");
        roles.add(userRoles);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}

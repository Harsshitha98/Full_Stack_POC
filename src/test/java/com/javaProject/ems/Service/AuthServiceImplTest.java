package com.javaProject.ems.Service;

import com.javaProject.ems.DTO.LoginDTO;
import com.javaProject.ems.DTO.RegisterDTO;
import com.javaProject.ems.Entity.Role;
import com.javaProject.ems.Entity.User;
import com.javaProject.ems.Exception.EmsException;
import com.javaProject.ems.Repository.RoleRepository;
import com.javaProject.ems.Repository.UserRepository;
import com.javaProject.ems.Security.JwtTokenProvider;
import com.javaProject.ems.Service.Impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void register_UserAlreadyExists_ThrowsException() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("existingUser");
        registerDTO.setEmail("test@example.com");
        registerDTO.setPassword("password");

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        EmsException exception = assertThrows(EmsException.class, () -> {
            authService.register(registerDTO);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Username already exist!", exception.getMessage());
    }

    @Test
    public void register_SuccessfulRegistration_ReturnsMessage() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("newUser");
        registerDTO.setEmail("new@example.com");
        registerDTO.setPassword("password");
        registerDTO.setName("New User");

        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("new@example.com")).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(new Role());

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode("password")).thenReturn(encodedPassword);

        String result = authService.register(registerDTO);

        assertEquals("User Registered Successfully!", result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void login_SuccessfulLogin_ReturnsToken() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsernameOrEmail("user@example.com");
        loginDTO.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn("jwtToken");

        String token = authService.login(loginDTO);

        assertEquals("jwtToken", token);
        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }
}

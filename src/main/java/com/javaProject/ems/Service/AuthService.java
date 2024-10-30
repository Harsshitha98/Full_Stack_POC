package com.javaProject.ems.Service;

import com.javaProject.ems.DTO.LoginDTO;
import com.javaProject.ems.DTO.RegisterDTO;

public interface AuthService {

    String register(RegisterDTO registerDTO);

    String login(LoginDTO loginDTO);

}

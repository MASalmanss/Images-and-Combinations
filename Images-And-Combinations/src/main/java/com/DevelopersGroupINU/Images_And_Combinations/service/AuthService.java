package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.LoginDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.LoginViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.RegisterDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;

public interface AuthService {
    Boolean register(RegisterDto registerDto);
    LoginViewDto login(LoginDto loginDto);
}

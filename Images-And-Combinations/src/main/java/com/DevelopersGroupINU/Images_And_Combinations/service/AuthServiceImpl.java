package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.LoginDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.LoginViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.RegisterDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.mapper.UserMapper;
import com.DevelopersGroupINU.Images_And_Combinations.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;


    @Override
    public Boolean register(RegisterDto registerDto) {
        Users user = userMapper.dtoToEntity(registerDto);
        usersRepository.save(user);
        return true;
    }

    @Override
    public LoginViewDto login(LoginDto loginDto) {
        Users users = usersRepository.findByEmail(loginDto.getEmail());
        if(Objects.equals(loginDto.getPassword(), users.getPassword())){
            LoginViewDto dto = new LoginViewDto();
            dto.setUserId(users.getId());
            dto.setFullName(users.getFullName());
            return dto;
        }
        return null;
     }
}

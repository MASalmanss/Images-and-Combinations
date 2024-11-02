package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.mapper.UserMapper;
import com.DevelopersGroupINU.Images_And_Combinations.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;


    @Override
    public Void save(UserCreateDto userCreateDto) {
        Users users = userMapper.dtoToEntity(userCreateDto);
        users.setActive(true);
        usersRepository.save(users);
        return null;
    }

    @Override
    public UserViewDto findById(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found !"));
        return userMapper.entityToDto(users);
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public UserViewDto update(UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public List<UserViewDto> findAll() {
        List<Users> users = usersRepository.findAll();
        List<UserViewDto> dtoList = userMapper.entityListToDtoList(users);
        return dtoList;
    }
}

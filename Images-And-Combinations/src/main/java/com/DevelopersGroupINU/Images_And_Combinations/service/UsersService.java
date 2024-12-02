package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {
    Void save(UserCreateDto users);
    UserViewDto findById(Long id);
    Void deleteById(Long id);
    UserViewDto update(UserUpdateDto userUpdateDto);
    List<UserViewDto> findAll();
    Boolean saveImg(MultipartFile file , Long id);
}

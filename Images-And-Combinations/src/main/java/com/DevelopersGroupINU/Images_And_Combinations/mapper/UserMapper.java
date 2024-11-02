package com.DevelopersGroupINU.Images_And_Combinations.mapper;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    Users dtoToEntity(UserCreateDto userCreateDto);
    UserViewDto entityToDto(Users users);

    List<UserViewDto> entityListToDtoList(List<Users> users);
}

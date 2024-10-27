package com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos;

import lombok.Data;

@Data
public class UserCreateDto {
    private String fullName;
    private String email;
    private String password;
}

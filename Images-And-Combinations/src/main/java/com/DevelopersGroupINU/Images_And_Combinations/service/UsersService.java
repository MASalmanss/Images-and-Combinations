package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;

import java.util.List;

public interface UsersService {
    Void save(Users users);
    Users findById(Long id);
    Void deleteById(Long id);
    Users update(Users users);
    List<Users> findAll();
}

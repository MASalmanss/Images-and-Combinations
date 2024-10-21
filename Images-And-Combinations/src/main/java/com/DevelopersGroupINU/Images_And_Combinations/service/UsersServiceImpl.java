package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.repository.UsersRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;
    @Override
    public Void save(Users users) {
        usersRepository.save(users);
        return null;
    }

    @Override
    public Users findById(Long id) {
        return null;
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public Users update(Users users) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }
}

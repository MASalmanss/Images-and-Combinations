package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.mapper.UserMapper;
import com.DevelopersGroupINU.Images_And_Combinations.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private static final String UPLOAD_DIR = "uploads/";


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

    @Override
    public Boolean saveImg(MultipartFile file , Long id) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }


            String originalFileName = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFileName);


            String uniqueFileName = "users_" + id + "_" + UUID.randomUUID() + fileExtension;
            Users users = usersRepository.findById(id).orElseThrow();
            users.setImageName(uniqueFileName);
            usersRepository.save(users);

            Path path = Paths.get(UPLOAD_DIR, uniqueFileName);
            Files.write(path, file.getBytes());
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}

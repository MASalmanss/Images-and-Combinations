package com.DevelopersGroupINU.Images_And_Combinations.controller;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UsersController {

    private final UsersService usersService;
/*
    @GetMapping("/{id}")
    public ResponseEntity<UserViewDto> findById(@PathVariable Long id){
        var users = usersService.findById(id);
        return ResponseEntity.ok(users);
    }

    @GetMapping("")
    public List<UserViewDto> findAll(){

        var liste =  usersService.findAll();
        return liste;

    }

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody UserCreateDto userCreateDto){
        usersService.save(userCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<UserViewDto> update(@RequestBody UserUpdateDto userUpdateDto){
        var nesne = usersService.update(userUpdateDto);
        return ResponseEntity.ok(nesne);
    }
*/
    @PostMapping("/file/{id}")
    public ResponseEntity<Void> saveFile(@RequestParam("file")MultipartFile file , @PathVariable("id") Long id){
        var sonuc = usersService.saveImg(file , id);
        return sonuc ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}

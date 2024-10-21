package com.DevelopersGroupINU.Images_And_Combinations.controller;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id){
        var users = usersService.findById(id);
        return ResponseEntity.ok(users);
    }

    @GetMapping("")
    public List<Users> findAll(){
        return usersService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody Users users){
        usersService.save(users);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Users> update(@RequestBody Users users){
        var nesne = usersService.update(users);
        return ResponseEntity.ok(nesne);
    }

}

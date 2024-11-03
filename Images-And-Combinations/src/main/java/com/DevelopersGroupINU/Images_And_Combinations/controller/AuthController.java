package com.DevelopersGroupINU.Images_And_Combinations.controller;

import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.LoginDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.LoginViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.authDtos.RegisterDto;
import com.DevelopersGroupINU.Images_And_Combinations.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto){
       Boolean result = authService.register(registerDto);
       if (result.equals(true)){
           return ResponseEntity.ok().build();
       }
       return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginViewDto> login(@RequestBody LoginDto loginDto){
       var result = authService.login(loginDto);
       if(result == null){
           return ResponseEntity.badRequest().build();
       }
       else {
           return ResponseEntity.ok(result);
       }
    }

}

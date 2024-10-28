package com.DevelopersGroupINU.Images_And_Combinations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    // "/" veya herhangi bir bilinmeyen URL çağrıldığında index.htmfl dosyasına yönlendirir
    @GetMapping(value = {"/", "/{path:[^\\.]*}"})
    public String index() {
        return "forward:/index.html";
    }
}

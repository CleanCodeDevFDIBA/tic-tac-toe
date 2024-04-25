package com.fdibaProject.tictactoe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String Home(){
        return "Welcome To ToDo App";
    }
}

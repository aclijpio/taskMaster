package ru.pio.aclij.taskmanagement.taskmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.pio.aclij.taskmanagement.security.dtos.UserDto;

@Controller
public class AuthController {

    @GetMapping ("/login")
    private String login(@ModelAttribute("request") UserDto userDto){
        return "/login";
    }

}

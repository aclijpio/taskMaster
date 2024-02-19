package ru.pio.aclij.taskmanagement.security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pio.aclij.taskmanagement.security.dtos.RegistrationUserDto;
import ru.pio.aclij.taskmanagement.security.dtos.UserDto;
import ru.pio.aclij.taskmanagement.security.services.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/authorization")
    private void authorization(@RequestBody UserDto userDto){

    }
    @PostMapping("/registration")
    private void registration(@RequestBody RegistrationUserDto registrationUserDto){

    }

}

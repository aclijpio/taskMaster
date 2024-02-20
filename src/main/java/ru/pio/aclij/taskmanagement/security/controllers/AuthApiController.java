package ru.pio.aclij.taskmanagement.security.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pio.aclij.taskmanagement.security.dtos.RegistrationUserDto;
import ru.pio.aclij.taskmanagement.security.dtos.UserDto;
import ru.pio.aclij.taskmanagement.security.services.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService service;

    @PostMapping("/authorization")
    public ResponseEntity<?> authorization(@RequestBody UserDto userDto, HttpServletResponse response){
        return service.createAuthToken(userDto, response);
    }
    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationUserDto registrationUserDto){
        service.createNewUser(registrationUserDto);
    }

}

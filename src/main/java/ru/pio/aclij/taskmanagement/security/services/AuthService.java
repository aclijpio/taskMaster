package ru.pio.aclij.taskmanagement.security.services;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagement.security.dtos.RegistrationUserDto;
import ru.pio.aclij.taskmanagement.security.dtos.UserDto;
import ru.pio.aclij.taskmanagement.security.services.util.JwtTokenUtil;
import ru.pio.aclij.taskmanagement.security.services.util.TokenAuthenticationHelper;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil util;

    public ResponseEntity<?> createAuthToken(UserDto userDto, HttpServletResponse response){
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            userDto.getUsername(), userDto.getPassword()));
        } catch (BadCredentialsException e) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Except");
        }
        UserDetails userDetails = userService.loadUserByUsername(userDto.getUsername());
        String token = util.generateToken(userDetails);
        TokenAuthenticationHelper.addTokenToCookie(response, token);
        return ResponseEntity.ok(token);
    }
    public void createNewUser(RegistrationUserDto registrationUserDto) {
        if (registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword()))
            userService.createNewUser(registrationUserDto);
        throw new BadCredentialsException("createNewUser");
    }

}

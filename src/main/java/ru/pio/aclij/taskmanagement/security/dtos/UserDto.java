package ru.pio.aclij.taskmanagement.security.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
}

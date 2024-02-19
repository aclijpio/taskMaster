package ru.pio.aclij.taskmanagement.security.mappers;

import ru.pio.aclij.taskmanagement.security.dtos.UserDto;
import ru.pio.aclij.taskmanagement.security.entities.User;

import java.util.List;

public final class UserMapper extends ModelAbstractMapper<User, UserDto> {
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }


    @Override
    List<User> toListModel(List<UserDto> userDtos) {
        return super.toListModel(userDtos);
    }

    @Override
    public List<UserDto> toListDto(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .toList();
    }
}

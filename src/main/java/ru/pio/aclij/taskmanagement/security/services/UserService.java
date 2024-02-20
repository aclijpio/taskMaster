package ru.pio.aclij.taskmanagement.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagement.security.dtos.RegistrationUserDto;
import ru.pio.aclij.taskmanagement.security.entities.User;
import ru.pio.aclij.taskmanagement.security.exceptions.UserNotFoundException;
import ru.pio.aclij.taskmanagement.security.repositories.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }
    public User createNewUser(RegistrationUserDto registrationUserDto){
        User user = User.builder()
                .username(registrationUserDto.getUsername())
                .password(encoder.encode(registrationUserDto.getPassword()))
                .roles(List.of(roleService.getDefaultRole()))
                .build();
        repository.save(user);
        return user;
    }
}

package ru.pio.aclij.taskmanagement;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.pio.aclij.taskmanagement.security.entities.Role;
import ru.pio.aclij.taskmanagement.security.entities.User;
import ru.pio.aclij.taskmanagement.security.entities.UsersRoles;
import ru.pio.aclij.taskmanagement.security.repositories.RoleRepository;
import ru.pio.aclij.taskmanagement.security.repositories.UserRepository;
import ru.pio.aclij.taskmanagement.security.repositories.UsersRolesRepository;
import ru.pio.aclij.taskmanagement.security.services.properties.RoleProperty;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final RoleProperty roleProperty;

    @PostConstruct
    public void initData(){
        User user = userRepository.save(
                User.builder()
                        .username("admin")
                        .password("123")
                        .build()
        );

        Role role = roleRepository.save(
                Role.builder()
                        .name(roleProperty.getDefaultRole())
                        .build()
        );

        usersRolesRepository.save(
                UsersRoles.builder()
                        .user_id(user.getId())
                        .role_id(role.getId())
                        .build()
        );

    }


}

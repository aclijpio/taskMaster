package ru.pio.aclij.taskmanagement.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagement.security.entities.Role;
import ru.pio.aclij.taskmanagement.security.exceptions.RoleNotFoundException;
import ru.pio.aclij.taskmanagement.security.repositories.RoleRepository;
import ru.pio.aclij.taskmanagement.security.services.properties.RoleProperty;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;
    private final RoleProperty property;

    public Role getDefaultRole(){
        return this.repository.findByName(property.getDefaultRole())
                .orElseThrow(RoleNotFoundException::new);
    }
}

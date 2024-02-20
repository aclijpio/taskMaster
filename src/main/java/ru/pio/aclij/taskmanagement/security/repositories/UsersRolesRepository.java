package ru.pio.aclij.taskmanagement.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pio.aclij.taskmanagement.security.entities.UsersRoles;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
}

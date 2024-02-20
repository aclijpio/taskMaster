package ru.pio.aclij.taskmanagement.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users_roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersRoles {
    @Id
    private Long user_id;
    private Long role_id;
}

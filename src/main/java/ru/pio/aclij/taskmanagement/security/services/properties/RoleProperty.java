package ru.pio.aclij.taskmanagement.security.services.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.user.service")
@Data
public class RoleProperty {
    private String defaultRole;
}

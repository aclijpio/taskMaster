package ru.pio.aclij.taskmanagement.security.services.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "security.jwt")
@Data
public class JwtTokenProperty {
    private String secret;
    private Duration lifetime;
}

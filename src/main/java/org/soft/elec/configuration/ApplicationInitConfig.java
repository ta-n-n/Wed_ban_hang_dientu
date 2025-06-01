package org.soft.elec.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.soft.elec.entity.enums.Role;
import org.soft.elec.entity.models.User;
import org.soft.elec.repository.UserRepository;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Value("${app.admin.firstName}")
    private String adminFirstName;

    @Value("${app.admin.lastName}")
    private String adminLastName;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.phone}")
    private String adminPhone;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.role}")
    private String adminRoleName;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ApplicationInitConfig(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            Role adminRole = Role.valueOf(adminRoleName);
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setFirstName(adminFirstName);
                admin.setLastName(adminLastName);
                admin.setEmail(adminEmail);
                admin.setPhone(adminPhone);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setEnabled(true);
                admin.setRole(adminRole);
                userRepository.save(admin);
                log.warn("Default Admin account created. Please change the default password.");
            }
        };
    }
}

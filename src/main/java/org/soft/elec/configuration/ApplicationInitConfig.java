package org.soft.elec.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soft.elec.constant.enums.Role;
import org.soft.elec.entity.models.User;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
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

  private final UserRepository userRepository;
  private final PasswordUtil passwordUtil;

  @Bean
  public ApplicationRunner applicationRunner() {
    return args -> {
      if (userRepository.findByEmail(adminEmail).isEmpty()) {
        Role adminRole = Role.valueOf(adminRoleName.toUpperCase());
        User admin =
            User.builder()
                .firstName(adminFirstName)
                .lastName(adminLastName)
                .email(adminEmail)
                .phone(adminPhone)
                .password(passwordUtil.encode(adminPassword))
                .enabled(true)
                .role(adminRole)
                .build();
        userRepository.save(admin);
        log.warn(
            "Default Admin account created: [{}] - You should change the default password immediately!",
            adminEmail);
      } else {
        log.info("Admin account already exists: [{}]", adminEmail);
      }
    };
  }
}

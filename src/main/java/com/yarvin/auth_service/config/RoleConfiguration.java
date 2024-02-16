package com.yarvin.auth_service.config;

import com.yarvin.auth_service.repository.RoleRepository;
import com.yarvin.auth_service.store.entity.RoleEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository repository) {
        return args -> {
            RoleEntity role_user = new RoleEntity(
                    1L,
                    "User"
            );
            repository.save(role_user);
            RoleEntity role_admin = new RoleEntity(
                    2L,
                    "Admin"
            );
            repository.save(role_admin);
        };

    }
};
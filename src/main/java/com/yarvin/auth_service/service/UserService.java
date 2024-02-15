package com.yarvin.auth_service.service;

import com.yarvin.auth_service.repository.UserRepository;
import com.yarvin.auth_service.store.entity.RoleEntity;
import com.yarvin.auth_service.store.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;


    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }



    public UserEntity create(UserEntity user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }


    public UserEntity getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }


    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }


    public UserEntity getCurrentUser() {

        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }



    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(RoleEntity.builder().name("Admin").build());
        save(user);
    }

}

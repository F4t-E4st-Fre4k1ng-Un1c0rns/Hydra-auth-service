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
        if (repository.existsByEmail(user.getEmail())) {

            throw new RuntimeException("Пользователь с такой почтой уже существует");
        }

        return save(user);
    }

    public UserEntity getByUsername(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public UserEntity getCurrentUser() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(email);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(RoleEntity.builder().id(1L).name("Admin").build());

        save(user);
    }

}

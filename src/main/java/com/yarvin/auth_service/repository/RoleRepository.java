package com.yarvin.auth_service.repository;

import com.yarvin.auth_service.store.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);


    RoleEntity findByid(Long Long);
}

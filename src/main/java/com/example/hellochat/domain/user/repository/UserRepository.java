package com.example.hellochat.domain.user.repository;


import com.example.hellochat.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    boolean existsByName(String name);
    List<UserEntity> findByNameContaining(String username);
}
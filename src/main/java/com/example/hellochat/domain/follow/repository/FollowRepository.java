package com.example.hellochat.domain.follow.repository;

import com.example.hellochat.domain.follow.entity.Follow;
import com.example.hellochat.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFromUser(UserEntity userEntity);
    Optional<Follow> findByFromUser(UserEntity userEntity);
}

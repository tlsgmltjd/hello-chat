package com.example.hellochat.domain.follow.repository;

import com.example.hellochat.domain.follow.entity.Follow;
import com.example.hellochat.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFromUser(UserEntity userEntity);
}

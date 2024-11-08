package com.jhome.user.repository;

import com.jhome.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findAllByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

}

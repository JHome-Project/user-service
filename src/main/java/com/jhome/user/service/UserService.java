package com.jhome.user.service;

import com.jhome.user.constant.ResponseCode;
import com.jhome.user.domain.UserEntity;
import com.jhome.user.dto.JoinRequest;
import com.jhome.user.dto.UserRequest;
import com.jhome.user.exception.UserException;
import com.jhome.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity join(final JoinRequest request) {
        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();

        return saveUser(newUser);
    }

    public UserEntity edit(UserEntity user, UserRequest request) {
        user.edit(request);
        return saveUser(user);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public Boolean isExistUser(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<UserEntity> getUserlist() {
        return userRepository.findAll();
    }

    public UserEntity getUserOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
    }

    public void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }

}
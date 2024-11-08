package com.jhome.user.service;

import com.jhome.user.constant.ResponseCode;
import com.jhome.user.domain.UserEntity;
import com.jhome.user.dto.JoinRequest;
import com.jhome.user.dto.MessageResponse;
import com.jhome.user.dto.UserRequest;
import com.jhome.user.exception.UserException;
import com.jhome.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MessageResponse<?> join(final JoinRequest request) {
        String username = request.getUsername();

        Boolean isExists = userRepository.existsByUsername(username);

        if (isExists) throw new UserException();

        UserEntity newUser = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(newUser);

        return MessageResponse.of(ResponseCode.SUCCESS, newUser);
    }

    public MessageResponse<?> list() {
        List<UserEntity> userList = userRepository.findAll();

        return MessageResponse.of(ResponseCode.SUCCESS, userList);
    }

    public MessageResponse<?> detail(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(UserException::new);

        return MessageResponse.of(ResponseCode.SUCCESS, user);
    }

    public MessageResponse<?> edit(String username, UserRequest request) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(UserException::new);

        user.edit(request);

        userRepository.save(user);

        return MessageResponse.of(ResponseCode.SUCCESS, user);
    }

    public MessageResponse<?> delete(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(UserException::new);

        userRepository.delete(user);

        return MessageResponse.of(ResponseCode.SUCCESS, username);
    }
}
package com.jhome.user.controller;

import com.jhome.user.constant.ResponseCode;
import com.jhome.user.domain.UserEntity;
import com.jhome.user.dto.JoinRequest;
import com.jhome.user.dto.MessageResponse;
import com.jhome.user.dto.UserRequest;
import com.jhome.user.exception.UserException;
import com.jhome.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> join(
            @Valid @RequestBody JoinRequest request) {
        log.info("[USER JOIN] Start, request = {}", request.toString());

        Boolean isExists = userService.isExistUser(request.getUsername());

        if (isExists) {
            throw new UserException(ResponseCode.USER_ALREADY_EXIST);
        }

        UserEntity newUser = userService.join(request);

        log.info("[USER JOIN] End, nweUser = {}", newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.success(newUser));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        log.info("[USER LIST] Start");

        List<UserEntity> userList = userService.getUserlist();

        log.info("[USER LIST] End, userList = {}", userList);
        return ResponseEntity
                .ok(MessageResponse.success(userList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(
            @PathVariable("id") String username) {
        log.info("[USER DETAIL] Start, username = {}", username);

        UserEntity user = userService.getUserOrThrow(username);

        log.info("[USER DETAIL] End, username = {}", username);
        return ResponseEntity
                .ok(MessageResponse.success(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(
            @PathVariable("id") String username,
            @Valid @RequestBody UserRequest request) {
        log.info("[USER EDIT] Start, username = {}, request = {}", username, request);
        UserEntity user = userService.getUserOrThrow(username);

        user = userService.edit(user, request);

        log.info("[USER EDIT] End, user = {}", user);
        return ResponseEntity
                .ok(MessageResponse.success(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") String username) {
        log.info("[USER DELETE] Start, username = {}", username);

        UserEntity user = userService.getUserOrThrow(username);

        userService.deleteUser(user);

        log.info("[USER DELETE] End, username = {}", username);
        return ResponseEntity
                .ok(MessageResponse.success());
    }

}

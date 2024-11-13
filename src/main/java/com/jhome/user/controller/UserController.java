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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> join(
            @Valid @RequestBody JoinRequest request) {

        Boolean isExists = userService.isExistUser(request.getUsername());

        if (isExists) {
            throw new UserException(ResponseCode.USER_EXCEPTIONS);
        }

        UserEntity newUser = userService.join(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of(ResponseCode.SUCCESS, newUser));
    }

    @GetMapping
    public ResponseEntity<?> list() {

        List<UserEntity> userList = userService.getUserlist();

        return ResponseEntity
                .ok(MessageResponse.of(ResponseCode.SUCCESS, userList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(
            @PathVariable("id") String username) {

        UserEntity user = userService.getUserOrThrow(username);

        return ResponseEntity
                .ok(MessageResponse.of(ResponseCode.SUCCESS, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(
            @PathVariable("id") String username,
            @Valid @RequestBody UserRequest request) {

        UserEntity user = userService.getUserOrThrow(username);

        user = userService.edit(user, request);

        return ResponseEntity
                .ok(MessageResponse.of(ResponseCode.SUCCESS, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") String username) {

        UserEntity user = userService.getUserOrThrow(username);

        userService.deleteUser(user);

        return ResponseEntity
                .ok(MessageResponse.of(ResponseCode.SUCCESS, username));
    }

}

package com.jhome.user.controller;

import com.jhome.user.dto.JoinRequest;
import com.jhome.user.dto.UserRequest;
import com.jhome.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> join(
            @Valid @RequestBody final JoinRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.join(request));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(
            @PathVariable("id") String username
    ) {
        return ResponseEntity.ok(userService.detail(username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(
            @PathVariable("id") String username,
            @Valid @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.edit(username, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") String username
    ) {
        return ResponseEntity.ok(userService.delete(username));
    }

}

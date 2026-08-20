package org.example.redisstudy.controller;

import lombok.RequiredArgsConstructor;
import org.example.redisstudy.service.UserService;
import org.example.redisstudy.service.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Cache-Aside 기본 처리 구현
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable Long userId) {
        UserDto dto = userService.read(userId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> modifyUserProfile(@PathVariable Long userId, String name, String email, String tier) {
        UserDto dto = userService.modify(userId, name, email, tier);
        return ResponseEntity.ok(dto);
    }
}

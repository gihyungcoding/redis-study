package org.example.redisstudy.service;

import org.example.redisstudy.service.dto.UserDto;

public interface UserService {

    UserDto read(Long userId);

    UserDto modify(Long userId, String name, String email, String tier);
}

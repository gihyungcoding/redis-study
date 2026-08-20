package org.example.redisstudy.service.impl;

import org.example.redisstudy.model.User;
import org.example.redisstudy.service.UserService;
import org.example.redisstudy.service.dto.UserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(cacheNames = "getUserProfile", key = "'user:profile:' + #userId", cacheManager = "cacheManager")
    public UserDto read(Long userId) {
        System.out.println("cache miss");
        final User user = User.getUsers().stream().filter(us -> us.getId().equals(userId))
                .findAny()
                .orElseThrow();

        return UserDto.toDto(user);
    }

    @Override
    @CacheEvict(cacheNames = "getUserProfile", key = "'user:profile:' + #userId", cacheManager = "cacheManager")
    public UserDto modify(Long userId, String name, String email, String tier) {
        System.out.println("cache evict");
        User user = User.getUsers().stream().filter(us -> us.getId().equals(userId))
                .findAny()
                .orElseThrow();
        user = user.change(name, email, tier);
        return UserDto.toDto(user);
    }
}

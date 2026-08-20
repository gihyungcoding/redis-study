package org.example.redisstudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/items")
@RequiredArgsConstructor
public class SetGetController {

    private final StringRedisTemplate redisTemplate;

    @PostMapping("/{itemId}")
    public String create(@PathVariable String itemId, String value) {
        String key = "item:".concat(itemId);

        redisTemplate.opsForValue().set(key, value);

        return "key=" + key + ", value=" + value;
    }

    @GetMapping("/{itemId}")
    public String read(@PathVariable String itemId) {
        String key = "item:".concat(itemId);

        String result = redisTemplate.opsForValue().get(key);

        if (result == null) {
            return "Item not found in Redis";
        }

        return "key=" + key + ", value=" + result;
    }
}

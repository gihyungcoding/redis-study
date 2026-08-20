package org.example.redisstudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 로컬 Redis(localhost:6379)가 떠 있어야 통과한다. `docker compose up -d` 로 먼저 띄울 것.
 */
@SpringBootTest
class RedisConnectionTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void 문자열_저장과_조회() {
        stringRedisTemplate.opsForValue().set("study:hello", "world", Duration.ofSeconds(30));

        assertThat(stringRedisTemplate.opsForValue().get("study:hello")).isEqualTo("world");
    }

    @Test
    void 객체_JSON_저장과_조회() {
        // 타입 정보(@class)는 final 이 아닌 타입에만 기록되므로
        // Map.of() 같은 불변 구현체 대신 LinkedHashMap 을 쓴다
        Map<String, Object> value = new LinkedHashMap<>();
        value.put("name", "redis");
        value.put("port", 6379);
        redisTemplate.opsForValue().set("study:object", value, Duration.ofSeconds(30));

        assertThat(redisTemplate.opsForValue().get("study:object")).isEqualTo(value);
    }
}

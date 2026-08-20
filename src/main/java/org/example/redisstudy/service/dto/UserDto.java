package org.example.redisstudy.service.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.redisstudy.model.User;

@Getter
public class UserDto {

    private final Long id;

    private final String name;

    private final String email;

    private final String tier;

    /**
     * 캐시에서 꺼낼 때 Jackson 이 이 생성자로 객체를 만든다.
     * (생성자가 없으면 역직렬화가 LinkedHashMap 으로 떨어진다)
     */
    @Builder
    public UserDto(Long id, String name, String email, String tier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tier = tier;
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .tier(user.getTier()).build();
    }
}

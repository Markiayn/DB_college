package ua.markiyan.sonara.mapper;

import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.entity.Users;

public final class UserMapper {
    private UserMapper() {}

    public static Users toEntity(UserRequest dto) {
        return Users.builder()
                .email(dto.email())
                .name(dto.name())
                .country(dto.country())
                .status(Users.Status.ACTIVE) // дефолт
                .build();
    }

    public static UserResponse toResponse(Users e) {
        return new UserResponse(
                e.getId(),
                e.getEmail(),
                e.getName(),
                e.getCountry(),
                e.getStatus().name(),
                e.getCreatedAt().toString()
        );
    }
}


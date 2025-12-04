package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Email String email,
        @Size(min = 2, max = 100) String name,
        @Size(max = 80) String country
) {}


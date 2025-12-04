package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubscriptionRequest(
        @NotNull Long userId,
        @Size(min = 1, max = 50) String planCode
) {}


package ua.markiyan.sonara.dto.response;

public record SubscriptionResponse(
        Long id,
        Long userId,
        String planCode,
        String status
) {}


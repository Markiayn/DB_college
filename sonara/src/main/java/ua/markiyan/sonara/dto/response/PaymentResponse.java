package ua.markiyan.sonara.dto.response;

import java.math.BigDecimal;

public record PaymentResponse(
        Long id,
        Long userId,
        Long subscriptionId,
        BigDecimal amount,
        String status
) {}


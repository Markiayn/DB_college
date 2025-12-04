package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PaymentRequest(
        @NotNull Long userId,
        @NotNull Long subscriptionId,
        @NotNull BigDecimal amount
) {}


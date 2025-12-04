package ua.markiyan.sonara.service;

import ua.markiyan.sonara.dto.request.PaymentRequest;
import ua.markiyan.sonara.dto.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse create(Long subscriptionId, PaymentRequest req);
    List<PaymentResponse> listBySubscription(Long subscriptionId);
    PaymentResponse get(Long id);
    List<PaymentResponse> listByUser(Long userId);
}


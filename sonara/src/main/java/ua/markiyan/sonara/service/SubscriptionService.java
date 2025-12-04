package ua.markiyan.sonara.service;

import ua.markiyan.sonara.dto.request.SubscriptionRequest;
import ua.markiyan.sonara.dto.response.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponse create(Long userId, SubscriptionRequest req);
    List<SubscriptionResponse> listByUser(Long userId);
    SubscriptionResponse get(Long id);
    void delete(Long id);
}


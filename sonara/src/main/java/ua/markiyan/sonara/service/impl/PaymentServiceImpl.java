package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.PaymentRequest;
import ua.markiyan.sonara.dto.response.PaymentResponse;
import ua.markiyan.sonara.entity.Payment;
import ua.markiyan.sonara.entity.Subscription;
import ua.markiyan.sonara.entity.User;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.repository.PaymentRepository;
import ua.markiyan.sonara.repository.SubscriptionRepository;
import ua.markiyan.sonara.repository.UserRepository;
import ua.markiyan.sonara.service.PaymentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;
    private final SubscriptionRepository subscriptionRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public PaymentResponse create(Long subscriptionId, PaymentRequest req) {
        User u = userRepo.findById(req.userId()).orElseThrow(() -> new NotFoundException("User %d not found".formatted(req.userId())));
        Subscription s = subscriptionRepo.findById(subscriptionId).orElseThrow(() -> new NotFoundException("Subscription %d not found".formatted(subscriptionId)));
        Payment p = Payment.builder()
                .user(u)
                .subscription(s)
                .amount(req.amount())
                .currency("USD")
                .status(Payment.PaymentStatus.COMPLETED)
                .build();
        p = repo.save(p);
        return toResponse(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> listBySubscription(Long subscriptionId) {
        if (!subscriptionRepo.existsById(subscriptionId)) throw new NotFoundException("Subscription %d not found".formatted(subscriptionId));
        return repo.findAllBySubscription_Id(subscriptionId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse get(Long id) {
        Payment p = repo.findById(id).orElseThrow(() -> new NotFoundException("Payment %d not found".formatted(id)));
        return toResponse(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> listByUser(Long userId) {
        if (!userRepo.existsById(userId)) throw new NotFoundException("User %d not found".formatted(userId));
        return repo.findAllByUser_Id(userId).stream().map(this::toResponse).toList();
    }

    private PaymentResponse toResponse(Payment p) {
        return new PaymentResponse(p.getId(), p.getUser().getId(), p.getSubscription().getId(), p.getAmount(), p.getStatus().name());
    }
}


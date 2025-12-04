package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.SubscriptionRequest;
import ua.markiyan.sonara.dto.response.SubscriptionResponse;
import ua.markiyan.sonara.entity.Subscription;
import ua.markiyan.sonara.entity.User;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.repository.SubscriptionRepository;
import ua.markiyan.sonara.repository.UserRepository;
import ua.markiyan.sonara.service.SubscriptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public SubscriptionResponse create(Long userId, SubscriptionRequest req) {
        User u = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User %d not found".formatted(userId)));
        Subscription s = Subscription.builder()
                .user(u)
                .planCode(Subscription.PlanCode.valueOf(req.planCode()))
                .build();
        s = repo.save(s);
        return toResponse(s);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> listByUser(Long userId) {
        if (!userRepo.existsById(userId)) throw new NotFoundException("User %d not found".formatted(userId));
        return repo.findAllByUser_Id(userId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponse get(Long id) {
        Subscription s = repo.findById(id).orElseThrow(() -> new NotFoundException("Subscription %d not found".formatted(id)));
        return toResponse(s);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Subscription %d not found".formatted(id));
        repo.deleteById(id);
    }

    private SubscriptionResponse toResponse(Subscription s) {
        return new SubscriptionResponse(s.getId(), s.getUser().getId(), s.getPlanCode().name(), s.getStatus().name());
    }
}


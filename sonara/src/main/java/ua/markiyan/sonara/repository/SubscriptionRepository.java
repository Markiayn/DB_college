package ua.markiyan.sonara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Subscription;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByUser_Id(Long userId);
}


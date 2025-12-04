package ua.markiyan.sonara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByUser_Id(Long userId);
    List<Payment> findAllBySubscription_Id(Long subscriptionId);
}


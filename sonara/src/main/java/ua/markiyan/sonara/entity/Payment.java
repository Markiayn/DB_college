package ua.markiyan.sonara.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "Payment",
        indexes = {
                @Index(name = "idx_payment_user", columnList = "user_id"),
                @Index(name = "idx_payment_subscription", columnList = "subscription_id")
        }
)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "paid_at", columnDefinition = "DATETIME")
    private LocalDateTime paidAt;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition =
            "ENUM('credit_card','paypal','apple_pay','google_pay','crypto')")
    private PaymentMethod method = PaymentMethod.credit_card;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition =
            "ENUM('pending','completed','failed','declined','refunded')")
    private PaymentStatus status = PaymentStatus.pending;


    public enum PaymentStatus {
        pending,completed,failed,declined,refunded
    }

    public enum PaymentMethod {
        credit_card,paypal,apple_pay,google_pay,crypto
    }
}


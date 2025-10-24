package ua.markiyan.sonara.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "Subscription",
        indexes = @Index(name = "idx_sub_user", columnList = "user_id")
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "plan_code", nullable = false, columnDefinition = "ENUM('free','premium','family','student')")
    private PlanCode planCode = PlanCode.free;

    @CreationTimestamp
    @Column(name = "started_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startedAt;

    @Column(name = "ends_at", columnDefinition = "DATETIME")
    private LocalDateTime endsAt;

    public enum PlanCode {
       free,premium,family,student
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('trial','active','expired','canceled','pending','suspended')")
    private SubscriptionStatus status = SubscriptionStatus.trial;

    public enum SubscriptionStatus {
        trial,active,expired,canceled,pending,suspended
    }
}


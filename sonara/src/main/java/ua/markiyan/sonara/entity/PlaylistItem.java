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
        name = "PlaylistItem",
        uniqueConstraints = @UniqueConstraint(name = "uq_playlist_position", columnNames = {"playlist_id", "position"}),
        indexes = {
                @Index(name = "idx_pi_playlist", columnList = "playlist_id"),
                @Index(name = "idx_pi_track", columnList = "track_id")
        }
)
public class PlaylistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @Column(nullable = false)
    private Integer position;

    @CreationTimestamp
    @Column(name = "added_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime addedAt;
}


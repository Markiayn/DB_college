package ua.markiyan.sonara.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}

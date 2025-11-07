package ua.markiyan.sonara.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByNameIgnoreCase(String name);

    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);

    boolean existsByNameIgnoreCase(String name);


    Page<Users> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Users> findByCountryIgnoreCase(String country, Pageable pageable);

    Page<Users> findByStatusIgnoreCase(String status, Pageable pageable);

    Page<Users> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    // ---- 2 фільтри ----
    Page<Users> findByNameContainingIgnoreCaseAndCountryIgnoreCase(
            String name, String country, Pageable pageable);

    Page<Users> findByNameContainingIgnoreCaseAndStatusIgnoreCase(
            String name, String status, Pageable pageable);

    Page<Users> findByNameContainingIgnoreCaseAndCreatedAtBetween(
            String name, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Users> findByCountryIgnoreCaseAndStatusIgnoreCase(
            String country, String status, Pageable pageable);

    Page<Users> findByCountryIgnoreCaseAndCreatedAtBetween(
            String country, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Users> findByStatusIgnoreCaseAndCreatedAtBetween(
            String status, LocalDateTime start, LocalDateTime end, Pageable pageable);

    // ---- 3 фільтри ----
    Page<Users> findByNameContainingIgnoreCaseAndCountryIgnoreCaseAndStatusIgnoreCase(
            String name, String country, String status, Pageable pageable);

    Page<Users> findByNameContainingIgnoreCaseAndCountryIgnoreCaseAndCreatedAtBetween(
            String name, String country, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Users> findByNameContainingIgnoreCaseAndStatusIgnoreCaseAndCreatedAtBetween(
            String name, String status, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Users> findByCountryIgnoreCaseAndStatusIgnoreCaseAndCreatedAtBetween(
            String country, String status, LocalDateTime start, LocalDateTime end, Pageable pageable);

    // ---- 4 фільтри ----
    Page<Users> findByNameContainingIgnoreCaseAndCountryIgnoreCaseAndStatusIgnoreCaseAndCreatedAtBetween(
            String name, String country, String status, LocalDateTime start, LocalDateTime end, Pageable pageable);
}


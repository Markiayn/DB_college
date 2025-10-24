package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.entity.Users;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.mapper.UserMapper;
import ua.markiyan.sonara.repository.UserRepository;
import ua.markiyan.sonara.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserResponse create(UserRequest req) {
        if (repo.existsByNameIgnoreCase(req.name())) {
            throw new IllegalArgumentException("User with the same name already exists");
        }

        // мапимо DTO в ентіті
        Users entity = UserMapper.toEntity(req);

        // тут шифруємо пароль перед збереженням
        entity.setPasswordHash(encoder.encode(req.password()));

        Users saved = repo.save(entity);
        return UserMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse get(Long id) {
        Users u = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
        return UserMapper.toResponse(u);
    }

    @Override
    public Page<UserResponse> search(String name, String country, String status, LocalDate createdAt, Pageable pageable) {
        String n = trimOrNull(name);
        String c = trimOrNull(country);
        String s = trimOrNull(status);

        boolean hn = n != null && !n.isBlank();
        boolean hc = c != null && !c.isBlank();
        boolean hs = s != null && !s.isBlank();
        boolean hd = createdAt != null;

        LocalDateTime start = null, end = null;
        if (hd) {
            start = createdAt.atStartOfDay();
            end = createdAt.plusDays(1).atStartOfDay();
        }

        Page<Users> page;

        // 4 фільтри
        if (hn && hc && hs && hd) {
            page = repo.findByNameContainingIgnoreCaseAndCountryIgnoreCaseAndStatusIgnoreCaseAndCreatedAtBetween(
                    n, c, s, start, end, pageable);

            // 3 фільтри
        } else if (hn && hc && hs) {
            page = repo.findByNameContainingIgnoreCaseAndCountryIgnoreCaseAndStatusIgnoreCase(n, c, s, pageable);
        } else if (hn && hc && hd) {
            page = repo.findByNameContainingIgnoreCaseAndCountryIgnoreCaseAndCreatedAtBetween(n, c, start, end, pageable);
        } else if (hn && hs && hd) {
            page = repo.findByNameContainingIgnoreCaseAndStatusIgnoreCaseAndCreatedAtBetween(n, s, start, end, pageable);
        } else if (hc && hs && hd) {
            page = repo.findByCountryIgnoreCaseAndStatusIgnoreCaseAndCreatedAtBetween(c, s, start, end, pageable);

            // 2 фільтри
        } else if (hn && hc) {
            page = repo.findByNameContainingIgnoreCaseAndCountryIgnoreCase(n, c, pageable);
        } else if (hn && hs) {
            page = repo.findByNameContainingIgnoreCaseAndStatusIgnoreCase(n, s, pageable);
        } else if (hn && hd) {
            page = repo.findByNameContainingIgnoreCaseAndCreatedAtBetween(n, start, end, pageable);
        } else if (hc && hs) {
            page = repo.findByCountryIgnoreCaseAndStatusIgnoreCase(c, s, pageable);
        } else if (hc && hd) {
            page = repo.findByCountryIgnoreCaseAndCreatedAtBetween(c, start, end, pageable);
        } else if (hs && hd) {
            page = repo.findByStatusIgnoreCaseAndCreatedAtBetween(s, start, end, pageable);

            // 1 фільтр
        } else if (hn) {
            page = repo.findByNameContainingIgnoreCase(n, pageable);
        } else if (hc) {
            page = repo.findByCountryIgnoreCase(c, pageable);
        } else if (hs) {
            page = repo.findByStatusIgnoreCase(s, pageable);
        } else if (hd) {
            page = repo.findByCreatedAtBetween(start, end, pageable);

            // без фільтрів
        } else {
            page = repo.findAll(pageable);
        }

        return page.map(UserMapper::toResponse);
    }

    private String trimOrNull(String v) {
        if (v == null) return null;
        String t = v.trim();
        return t.isEmpty() ? null : t;
    }
}


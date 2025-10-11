package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.entity.User;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.mapper.UserMapper;
import ua.markiyan.sonara.repository.UserRepository;
import ua.markiyan.sonara.service.UserService;

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
        User entity = UserMapper.toEntity(req);

        // тут шифруємо пароль перед збереженням
        entity.setPasswordHash(encoder.encode(req.password()));

        User saved = repo.save(entity);
        return UserMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse get(Long id) {
        User u = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
        return UserMapper.toResponse(u);
    }
}


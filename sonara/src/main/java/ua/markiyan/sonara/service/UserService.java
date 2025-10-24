package ua.markiyan.sonara.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;

import java.time.LocalDate;

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse get(Long id);

    Page<UserResponse> search(String name, String country, String status, LocalDate createdAt, Pageable pageable);
}

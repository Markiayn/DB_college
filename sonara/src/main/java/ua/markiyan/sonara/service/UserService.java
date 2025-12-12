package ua.markiyan.sonara.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.request.UserUpdateRequest;
import ua.markiyan.sonara.dto.response.UserResponse;

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse get(Long id);

    UserResponse update(Long id, UserUpdateRequest req);
    void delete(Long id);

    Page<UserResponse> search(String q, Pageable pageable);

    UserResponse findByEmail(String email);
}

package ua.markiyan.sonara.service;

import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse get(Long id);
}

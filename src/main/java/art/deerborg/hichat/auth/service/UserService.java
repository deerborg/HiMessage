package art.deerborg.hichat.auth.service;

import art.deerborg.hichat.auth.model.dto.request.UserCreatedRequest;
import art.deerborg.hichat.auth.model.dto.response.UserResponse;
import art.deerborg.hichat.auth.model.dto.response.UsersResponse;
import art.deerborg.hichat.auth.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    ResponseEntity<UserResponse> createdUser(UserCreatedRequest userCreatedRequest);

    User getUserByUsername(String username);
}

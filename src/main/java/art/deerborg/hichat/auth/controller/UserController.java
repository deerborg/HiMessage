package art.deerborg.hichat.auth.controller;

import art.deerborg.hichat.auth.model.dto.request.UserCreatedRequest;
import art.deerborg.hichat.auth.model.dto.response.UserResponse;
import art.deerborg.hichat.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponse> createdUser(@RequestBody UserCreatedRequest userCreatedRequest) {
        return userService.createdUser(userCreatedRequest);
    }

    // Test function for body
    @GetMapping("/get-user")
    public ResponseEntity<UserResponse> getUser() {
        return userService.getAuthenticatedUserId();
    }
}

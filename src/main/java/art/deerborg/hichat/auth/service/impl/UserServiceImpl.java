package art.deerborg.hichat.auth.service.impl;

import art.deerborg.hichat.auth.model.dto.request.UserCreatedRequest;
import art.deerborg.hichat.auth.model.dto.response.UserResponse;
import art.deerborg.hichat.auth.model.entity.User;
import art.deerborg.hichat.auth.model.enums.Role;
import art.deerborg.hichat.auth.model.mapper.UserMapper;
import art.deerborg.hichat.auth.repository.UserRepository;
import art.deerborg.hichat.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponse> createdUser(UserCreatedRequest userCreatedRequest) {

        userCreatedRequest.setPassword(passwordEncoder.encode(userCreatedRequest.getPassword()));

        User user = userMapper.fromUserCreatedRequest(userCreatedRequest);

        user.setRoles(Collections.singletonList(Role.USER));

        UserResponse response = userMapper.toUserResponse(userRepository.save(user));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public ResponseEntity<UserResponse> getAuthenticatedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserResponse response = userMapper.toUserResponse(userRepository.findByUsername(authentication.getName()).orElseThrow());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

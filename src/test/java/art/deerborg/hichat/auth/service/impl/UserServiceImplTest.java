package art.deerborg.hichat.auth.service.impl;

import art.deerborg.hichat.auth.model.dto.request.UserCreatedRequest;
import art.deerborg.hichat.auth.model.dto.response.UserResponse;
import art.deerborg.hichat.auth.model.entity.User;
import art.deerborg.hichat.auth.model.enums.Role;
import art.deerborg.hichat.auth.model.mapper.UserMapper;
import art.deerborg.hichat.auth.repository.UserRepository;
import art.deerborg.hichat.auth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userMapper = Mockito.mock(UserMapper.class);
        this.passwordEncoder = Mockito.mock(PasswordEncoder.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder, userMapper);
    }

    @Test
    void Should_Success_Mapped_For_Request_And_Return_Response_When_CreatedUser() {

        UserCreatedRequest userCreatedRequest = new UserCreatedRequest();
        userCreatedRequest.setUsername("username");
        userCreatedRequest.setPassword("password");

        Mockito.when(passwordEncoder.encode(userCreatedRequest.getPassword())).thenReturn("password");

        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setRoles(Collections.singletonList(Role.USER));

        user.setUsername(userCreatedRequest.getUsername());
        user.setPassword("password");


        Mockito.when(userMapper.fromUserCreatedRequest(userCreatedRequest)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserResponse response = new UserResponse();

        response.setId(user.getId());

        Mockito.when(userMapper.toUserResponse(user)).thenReturn(response);

        ResponseEntity<UserResponse> result = userService.createdUser(userCreatedRequest);

        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(userCreatedRequest.getPassword());
        Mockito.verify(userMapper, Mockito.times(1)).fromUserCreatedRequest(userCreatedRequest);
        Mockito.verify(userMapper, Mockito.times(1)).toUserResponse(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());

    }

    @Test
    void Should_Success_Given_Username_And_Return_User_When_GetUserByUsername() {
        String username = "username";
        User user = new User();
        user.setUsername(username);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User result = userService.getUserByUsername(username);

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

}
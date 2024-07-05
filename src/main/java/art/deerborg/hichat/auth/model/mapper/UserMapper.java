package art.deerborg.hichat.auth.model.mapper;

import art.deerborg.hichat.auth.model.dto.request.UserCreatedRequest;
import art.deerborg.hichat.auth.model.dto.response.UserResponse;
import art.deerborg.hichat.auth.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreatedRequest(UserCreatedRequest userCreatedRequest);
    UserResponse toUserResponse(User user);
}

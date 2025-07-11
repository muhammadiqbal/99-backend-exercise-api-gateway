package _9.group.gateway.APIGateway.client.response;

import _9.group.gateway.APIGateway.dto.User;
import lombok.Data;

@Data
public class UserResponse {
    private Boolean result;
    private User user;
}

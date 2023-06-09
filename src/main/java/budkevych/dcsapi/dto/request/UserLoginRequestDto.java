package budkevych.dcsapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank(message = "Login can't be null or blank!")
    private String login;
    private String password;
}

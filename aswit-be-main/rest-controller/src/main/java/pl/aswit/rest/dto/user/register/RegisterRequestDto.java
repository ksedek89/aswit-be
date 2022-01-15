package pl.aswit.rest.dto.user.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto{
    private String email;
    private String login;
    private String password;
}

package pl.aswit.rest.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String login;
    private String password;
}

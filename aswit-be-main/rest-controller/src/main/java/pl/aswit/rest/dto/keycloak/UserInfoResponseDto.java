package pl.aswit.rest.dto.keycloak;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoResponseDto {
    private String sub;
    private String emailVerified;
    private String name;
    private String preferredUsername;
    private String givenName;
    private String email;
}

package pl.aswit.rest.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KeycloakRegisterRequestDto {
    private String email;
    private String username;
    private boolean enabled;
}

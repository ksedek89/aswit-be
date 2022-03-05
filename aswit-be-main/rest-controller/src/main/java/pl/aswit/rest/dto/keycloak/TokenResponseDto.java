package pl.aswit.rest.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponseDto {
    private String accessToken;
    private Integer expiresIn;
    private Integer refreshExpiresIn;
    private String refreshToken;
    private String tokenType;
    @JsonProperty("not-before-policy")
    private Integer notBeforePolicy;
    private String sessionState;
    private String scope;

}

package pl.aswit.rest.services.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.aswit.rest.client.KeycloakFeignClient;
import pl.aswit.rest.dto.general.GeneralResponseDto;
import pl.aswit.rest.dto.keycloak.TokenResponseDto;
import pl.aswit.rest.dto.keycloak.UserInfoResponseDto;
import pl.aswit.rest.dto.register.RegisterDto;

import static pl.aswit.rest.enums.GrantTypeE.client_credentials;

@Service
public class AccessTokenService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String secret;

    @Autowired
    private KeycloakFeignClient keycloakFeignClient;


    public TokenResponseDto getAppToken() {
        TokenResponseDto tokenResponse = keycloakFeignClient.logApp(client_credentials.toString(), clientId, secret);
        return tokenResponse;
    }
}

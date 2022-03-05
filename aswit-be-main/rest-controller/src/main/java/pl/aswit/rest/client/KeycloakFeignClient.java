package pl.aswit.rest.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import pl.aswit.rest.dto.keycloak.KeycloakRegisterRequestDto;
import pl.aswit.rest.dto.keycloak.TokenResponseDto;
import pl.aswit.rest.dto.keycloak.UserInfoResponseDto;

import java.util.Map;

public interface KeycloakFeignClient {

    @RequestLine("GET /realms/Aswit/protocol/openid-connect/userinfo")
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Authorization: Bearer {Authorization}"})
    UserInfoResponseDto getUserInfo(@Param("Authorization") String authorizationHeader);

    @RequestLine("POST /realms/Aswit/protocol/openid-connect/token")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    TokenResponseDto logApp(@Param("grant_type") String grantType, @Param("client_id") String clientId, @Param("client_secret") String clientSecret);

    @RequestLine("POST /admin/realms/Aswit/users")
    @Headers({"Authorization: Bearer {Authorization}"})
    ResponseEntity registerUser(@Param("Authorization") String authorizationHeader, @RequestBody KeycloakRegisterRequestDto keycloakRegisterRequestDto);

}

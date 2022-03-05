package pl.aswit.rest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.aswit.rest.client.KeycloakFeignClient;
import pl.aswit.rest.dto.general.GeneralResponseDto;
import pl.aswit.rest.dto.keycloak.KeycloakGeneralResponseDto;
import pl.aswit.rest.dto.keycloak.KeycloakRegisterRequestDto;
import pl.aswit.rest.dto.keycloak.TokenResponseDto;
import pl.aswit.rest.dto.register.RegisterDto;
import org.keycloak.admin.client.KeycloakBuilder;
import pl.aswit.rest.enums.CodeE;
import pl.aswit.rest.enums.StatusE;
import pl.aswit.rest.services.keycloak.AccessTokenService;

import java.io.IOException;

import static pl.aswit.rest.utils.Utils.convertStringToCodeE;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KeycloakFeignClient keycloakFeignClient;
    @Autowired
    private AccessTokenService accessTokenService;

    public ResponseEntity<GeneralResponseDto> register(RegisterDto registerDto) throws IOException {
        try{
            TokenResponseDto appToken = accessTokenService.getAppToken();
        KeycloakRegisterRequestDto registerRequest = KeycloakRegisterRequestDto
            .builder()
            .username(registerDto.getEmail())
            .enabled(false)
            .email(registerDto.getEmail())
            .build();
            ResponseEntity responseEntity = keycloakFeignClient.registerUser(appToken.getAccessToken(), registerRequest);
        }catch (Exception e){
            if(e instanceof FeignException){
                FeignException feignException = (FeignException) e;
                KeycloakGeneralResponseDto generalResponseDto = objectMapper.readValue(feignException.content(), KeycloakGeneralResponseDto.class);
                CodeE codeE = convertStringToCodeE(generalResponseDto.getErrorMessage());
                GeneralResponseDto build = GeneralResponseDto
                    .builder()
                    .errorMessage(generalResponseDto.getErrorMessage())
                    .errorCode(codeE.toString())
                    .errorMessage(generalResponseDto.getErrorMessage())
                    .feMessage(codeE.getFeMessage()).status(StatusE.NOT_OK).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(build);
            }
            log.error(e.getMessage(), e);
        }
        return null;
    }
}

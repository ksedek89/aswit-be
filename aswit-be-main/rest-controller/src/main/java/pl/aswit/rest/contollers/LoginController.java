package pl.aswit.rest.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.aswit.rest.dto.general.GeneralResponseDto;
import pl.aswit.rest.dto.register.RegisterDto;
import pl.aswit.rest.services.LoginService;
import java.io.IOException;

@RestController
@RequestMapping( value =  "${app.public.path}/login")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponseDto> register(@RequestBody RegisterDto registerDto, Authentication authentication) throws IOException {
        return loginService.register(registerDto);
    }


}

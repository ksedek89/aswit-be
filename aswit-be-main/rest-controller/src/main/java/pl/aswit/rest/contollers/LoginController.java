package pl.aswit.rest.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aswit.rest.services.login.LoginService;
import pl.aswit.rest.dto.user.UserDto;
import pl.aswit.rest.dto.user.generic.GenericResponseDto;
import pl.aswit.rest.dto.user.register.RegisterRequestDto;

@RestController
@RequestMapping( value =  "${app.public.path}/login")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto){
        log.info(userDto.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto> register(@RequestBody RegisterRequestDto userDto){
        GenericResponseDto genericResponseDto = loginService.register(userDto);
        return ResponseEntity.ok(genericResponseDto);
    }

    @PostMapping("/sendMail")
    public ResponseEntity<GenericResponseDto> sendMail() throws Exception {
       loginService.sendMail();
        return ResponseEntity.ok().build();
    }


}

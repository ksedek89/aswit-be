package pl.aswit.rest.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aswit.rest.dto.user.UserDto;

@RestController
@RequestMapping( value =  "${app.public.path}/login")
@Slf4j
public class LoginController {

    @GetMapping("/get-user")
    public UserDto getUsers(){
        return UserDto.builder().login("ksedek").password("haslo").build();
    }

    @PostMapping()
    public ResponseEntity login(@RequestBody UserDto userDto){
        log.info(userDto.toString());
        return ResponseEntity.ok().build();
    }

}

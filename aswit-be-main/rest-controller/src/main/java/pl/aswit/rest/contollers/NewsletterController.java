package pl.aswit.rest.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aswit.rest.dto.user.generic.GenericResponseDto;
import pl.aswit.rest.dto.user.subscribe.SubscribeRequestDto;
import pl.aswit.rest.services.newsletter.NewsletterService;

@RestController
@RequestMapping( value =  "${app.public.path}/newsletter")
@Slf4j
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;

    @PostMapping("/subscribe")
    public ResponseEntity subscribe(@RequestBody SubscribeRequestDto userDto){
        GenericResponseDto genericResponseDto = newsletterService.subscribe(userDto);
        return ResponseEntity.ok(genericResponseDto);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<GenericResponseDto> unsubscribe(@RequestBody SubscribeRequestDto userDto){
        GenericResponseDto  genericResponseDto = newsletterService.unsubscribe(userDto);
        return ResponseEntity.ok(genericResponseDto);
    }


}

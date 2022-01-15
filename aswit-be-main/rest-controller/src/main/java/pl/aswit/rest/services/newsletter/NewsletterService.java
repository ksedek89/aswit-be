package pl.aswit.rest.services.newsletter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aswit.model.entity.NewsletterMail;
import pl.aswit.model.repository.NewsletterMailRepository;
import pl.aswit.rest.dto.enums.StatusE;
import pl.aswit.rest.dto.user.generic.GenericResponseDto;
import pl.aswit.rest.dto.user.subscribe.SubscribeRequestDto;


@Service
@Slf4j
public class NewsletterService {

    @Autowired
    private NewsletterMailRepository newsletterMailRepository;

    public GenericResponseDto subscribe(SubscribeRequestDto userDto) {
        NewsletterMail byEmail = newsletterMailRepository.findByEmail(userDto.getEmail());
        if(byEmail != null){
            if(byEmail.getActive() == 1){
                return GenericResponseDto.builder().status(StatusE.NOT_OK).message("Podany adres jest już zarejestrowany").build();
            }else {
                byEmail.setActive(1);
                newsletterMailRepository.save(byEmail);
                return GenericResponseDto.builder().status(StatusE.OK).message("Pomyślnie dodano adres").build();
            }
        }
        NewsletterMail newsletterMail= NewsletterMail.builder().active(1).email(userDto.getEmail()).build();
        newsletterMailRepository.save(newsletterMail);
        return GenericResponseDto.builder().status(StatusE.OK).message("Pomyślnie dodano adres").build();
    }

    public GenericResponseDto unsubscribe(SubscribeRequestDto userDto) {
        NewsletterMail byEmail = newsletterMailRepository.findByEmail(userDto.getEmail());
        if(byEmail != null){
            if(byEmail.getActive() == 0){
                return GenericResponseDto.builder().status(StatusE.NOT_OK).message("Adres nie jest zarejestrowany").build();
            }else{
                byEmail.setActive(0);
                newsletterMailRepository.save(byEmail);
                return GenericResponseDto.builder().status(StatusE.OK).message("Pomyślnie usunięto adres").build();
            }
        }

        return GenericResponseDto.builder().status(StatusE.NOT_OK).message("Podany adres nie istnieje").build();
    }
}

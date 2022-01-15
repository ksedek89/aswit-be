package pl.aswit.rest.services.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.aswit.model.entity.ActivationLink;
import pl.aswit.model.entity.User;
import pl.aswit.model.repository.ActivationLinkRepository;
import pl.aswit.model.repository.UserRepository;
import pl.aswit.rest.consts.EmailParams;
import pl.aswit.rest.dto.enums.StatusE;
import pl.aswit.rest.dto.user.generic.GenericResponseDto;
import pl.aswit.rest.dto.user.register.RegisterRequestDto;

import javax.transaction.Transactional;
import java.util.Base64;
import java.util.UUID;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivationLinkRepository activationLinkRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    public GenericResponseDto register(RegisterRequestDto userDto) {
        try {
            GenericResponseDto genericResponseDto = GenericResponseDto .builder().build();
            User byLogin = userRepository.findByLogin(userDto.getLogin());
            if(byLogin != null){
                return genericResponseDto.setStatus(StatusE.NOT_OK).setMessage("Podany login już istnieje. Proszę podać inny");
            }
            User byEmail = userRepository.findByEmail(userDto.getEmail());
            if(byEmail != null){
                return genericResponseDto.setStatus(StatusE.NOT_OK).setMessage("Podany email już istnieje. Proszę podać inny");
            }

            User user = User
                .builder()
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                //TODO zaszyfrować hasło
                .password(userDto.getPassword())
                .build();

            ActivationLink activationLink = ActivationLink
                .builder()
                .active(1)
                .user(user)
                .uuid(getLinkUuid()).build();
            userRepository.save(user);
            activationLinkRepository.save(activationLink);

            String mailContent = prepareEmailContent(activationLink.getUuid());

        //TODO ZROBIĆ ŁADNEGO MAILA
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("rejestracja@aswit.pl");
            message.setTo(userDto.getEmail());
            message.setSubject("Temat");
            message.setText(mailContent);
            javaMailSender.send(message);

            return genericResponseDto.setMessage("Sukces. Proszę kliknąć w link aktywacyjny, który został wysłany na podany mail").setStatus(StatusE.OK);

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        return GenericResponseDto .builder().status(StatusE.NOT_OK).message("Wystąpił błąd. Proszę spróbować później").build();
    }

    private String getLinkUuid(){
        return Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
    }

    private String prepareEmailContent(String linkUuid){
        return "Proszę kliknąć w link: " + "http://www.aswit.pl/confirm?" + EmailParams.LINK_UUID.getParamName() + "=" + linkUuid;
    }


}

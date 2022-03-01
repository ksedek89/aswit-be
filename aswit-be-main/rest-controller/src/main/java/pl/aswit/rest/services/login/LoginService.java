/*
package pl.aswit.rest.services.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import pl.aswit.rest.consts.EmailParams;
import pl.aswit.rest.dto.enums.StatusE;
import pl.aswit.rest.dto.user.generic.GenericResponseDto;
import pl.aswit.rest.dto.user.register.RegisterRequestDto;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.UUID;

@Service
@Slf4j
public class LoginService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    public GenericResponseDto register(RegisterRequestDto userDto) {
        try {


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


    public void sendMail() throws Exception {
        FileInputStream fis = new FileInputStream(new File("/home/ksedek/aswit/aswit-be/mail_templates/karta_pracy_drugi_mail.html"));
        byte[] bytes = fis.readAllBytes();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String body = new String(bytes);

        String email = "ksedek89@gmail.com";
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("rejestracja@aswit.pl");
        helper.setSubject("subject");
        helper.setTo(email);
        body = body.replace("PATTERN_NAME", "Ania");
        helper.setText(body, true);

        javaMailSender.send(mimeMessage);

    }

    private String getLinkUuid(){
        return Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
    }

    private String prepareEmailContent(String linkUuid){
        return "Proszę kliknąć w link: " + "http://www.aswit.pl/confirm?" + EmailParams.LINK_UUID.getParamName() + "=" + linkUuid;
    }


}
*/

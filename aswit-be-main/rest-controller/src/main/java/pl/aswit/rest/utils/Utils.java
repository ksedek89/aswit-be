package pl.aswit.rest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pl.aswit.rest.dto.general.GeneralResponseDto;
import pl.aswit.rest.enums.CodeE;
import pl.aswit.rest.enums.StatusE;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
public class Utils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String prepareErrorResponse(CodeE errorCode){
        try {
            return objectMapper.writeValueAsString(GeneralResponseDto
                .builder()
                .errorCode(errorCode.toString())
                .errorMessage(errorCode.getErrorMessage())
                .status(StatusE.NOT_OK)
                .build());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static void prepareHttpServletResponse(HttpServletResponse httpServletResponse, int statusCode, CodeE erorCode){
        try {
            httpServletResponse.setStatus(statusCode);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(prepareErrorResponse(erorCode));

        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }

    public static CodeE convertStringToCodeE(String message){
       return Arrays.stream(CodeE.values()).filter(e->e.getFeMessage()!= null && e.getErrorMessage().equals(message)).findAny().orElse(CodeE.CODE_0000);
    }
}


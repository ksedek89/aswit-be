package pl.aswit.rest.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum CodeE {
    CODE_0000("Unknown error"),
    CODE_0001("No Authorization header"),
    CODE_0002("Wrong Authorization header"),
    CODE_0003("No Bearer Header"),


    REGISTER_0001("User exists with same username", "Podany email jest już zarejestrowany" );

    CodeE(String errorMessage, String feMessage) {
        this.errorMessage = errorMessage;
        this.feMessage = feMessage;
    }

    CodeE(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;
    private String feMessage;

}

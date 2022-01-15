package pl.aswit.rest.consts;

public enum EmailParams {
    LINK_UUID("uuid");

    EmailParams(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    private String paramName;
}

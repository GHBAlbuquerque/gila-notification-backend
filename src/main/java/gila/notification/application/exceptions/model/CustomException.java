package gila.notification.application.exceptions.model;

import java.util.Map;

public class CustomException extends Exception {

    private final String code;
    private Map<String, String> errors;

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(String code, String message, Map<String, String> errors) {
        super(message);
        this.code = code;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

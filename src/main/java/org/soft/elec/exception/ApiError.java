package org.soft.elec.exception;
import lombok.Getter;

@Getter
public class ApiError {
    private final String message;

    public ApiError(String message) {
        this.message = message;
    }
}


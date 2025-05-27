package org.soft.elec.exception;

import lombok.Getter;
import org.soft.elec.entity.enums.ErrorCode;

@Getter
public class AppEx extends RuntimeException {
    private final ErrorCode errorCode;

    public AppEx(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AppEx(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
    }
}

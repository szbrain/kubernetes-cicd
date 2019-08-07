package com.koolearn.club.web.webapp.exception;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ClubTeachException extends RuntimeException {
    private static final long serialVersionUID = 463358782142663414L;

    private int errorCode;
    private String errorInfo;
    private Throwable e;

    public ClubTeachException(int errorCode) {
        this.errorCode = errorCode;
    }

    public ClubTeachException(int errorCode, Throwable e) {
        this.errorCode = errorCode;
        this.e = e;
    }

    public ClubTeachException(int errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

}

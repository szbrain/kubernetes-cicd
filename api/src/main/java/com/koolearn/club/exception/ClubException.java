package com.koolearn.club.exception;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ClubException extends RuntimeException {
    private static final long serialVersionUID = 463358782142663414L;

    private int errorCode;
    private String errorInfo;
    private Throwable e;

    public ClubException(int errorCode) {
        this.errorCode = errorCode;
    }

    public ClubException(int errorCode, Throwable e) {
        this.errorCode = errorCode;
        this.e = e;
    }

    public ClubException(int errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

}

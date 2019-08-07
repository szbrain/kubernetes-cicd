package com.koolearn.club.exception;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ClubServiceException extends ClubException {
    private static final long serialVersionUID = 463358782142663414L;

    private int errorCode;
    private String errorInfo;
    private Throwable e;

    public ClubServiceException(int errorCode) {
        super(errorCode);
    }

    public ClubServiceException(int errorCode, Throwable e) {
        super(errorCode,e);
    }

    public ClubServiceException(int errorCode, String errorInfo) {
        super(errorCode,errorInfo);
    }

}

package com.sod.doc.chatapp.exception;

import org.chenile.base.exception.ErrorNumException;

public class UserServiceException extends ErrorNumException {
    public UserServiceException(int errorNum, String message) {
        super(errorNum, message);
    }

    public UserServiceException(int errorNum, int subErrorNum, String message) {
        super(errorNum, subErrorNum, message);
    }
}

package com.sod.doc.contentreader.exception;

import org.chenile.base.exception.ErrorNumException;
import org.chenile.base.exception.ServerException;
import org.chenile.base.response.ResponseMessage;
import org.chenile.core.interceptors.ChenileExceptionHandler;

public class ContentReaderServerException extends ErrorNumException {
    public ContentReaderServerException(ResponseMessage responseMessage) {
        super(responseMessage);
    }
}

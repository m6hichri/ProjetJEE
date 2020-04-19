package com.mh.forum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserAuthenticationException extends RuntimeException {

    /**
     *
     */

    public UserAuthenticationException()
    {
        super("UNAUTHORIZED hhh");
    }

    private static final long serialVersionUID = 1L;

}


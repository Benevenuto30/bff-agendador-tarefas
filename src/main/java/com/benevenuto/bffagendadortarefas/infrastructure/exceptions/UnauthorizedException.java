package com.benevenuto.bffagendadortarefas.infrastructure.exceptions;

import javax.naming.AuthenticationException;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}

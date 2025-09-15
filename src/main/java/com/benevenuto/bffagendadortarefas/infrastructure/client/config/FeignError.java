package com.benevenuto.bffagendadortarefas.infrastructure.client.config;

import com.benevenuto.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.benevenuto.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.benevenuto.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.benevenuto.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError  implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response ) {
        switch (response.status()) {
            case 409:
                return new ConflictException("Conflito ao processar a requisição");
            case 403:
                return new ResourceNotFoundException("Recurso não encontrado");
            case 401:
                return new UnauthorizedException("Não autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}


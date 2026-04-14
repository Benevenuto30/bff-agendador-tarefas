package com.benevenuto.bffagendadortarefas.business;

import com.benevenuto.bffagendadortarefas.business.dto.out.ViaCepResponseDTO;
import com.benevenuto.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final UsuarioClient viaCepClient;

    public ViaCepResponseDTO buscarCep(String cep) {
        return viaCepClient.buscarDadosDeCep(cep);
    }
}

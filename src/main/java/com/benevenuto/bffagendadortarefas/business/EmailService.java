package com.benevenuto.bffagendadortarefas.business;

import com.benevenuto.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.benevenuto.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient client;

    public void enviaEmail(TarefasResponseDTO dto){
        client.enviarEmail(dto);
    }
}

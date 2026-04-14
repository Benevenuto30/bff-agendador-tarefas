package com.benevenuto.bffagendadortarefas.business;


import com.benevenuto.bffagendadortarefas.business.dto.in.TarefasRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.benevenuto.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.benevenuto.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;

    public TarefasResponseDTO gravarTarefa(String token, TarefasRequestDTO dto) {
      return client.gravarTarefas(dto, token);
    }

    public List<TarefasResponseDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return client.buscaListaDeTarefasPorPerido(dataInicial, dataFinal, token);
    }

    public List<TarefasResponseDTO> buscaTarefasPorEmail(String token) {
       return client.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
       client.deletaTarefaPorId(id, token);
    }

    public TarefasResponseDTO alteraStatus(StatusNotificacaoEnum status, String id, String token){
       return client.alteraStatusNotificacao(status, id, token);
    }

    public TarefasResponseDTO updateTarefas(TarefasRequestDTO dto, String id, String token){
        return client.updateTarefas(dto, id, token);
    }
}

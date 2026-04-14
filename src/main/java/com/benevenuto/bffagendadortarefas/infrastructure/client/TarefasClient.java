package com.benevenuto.bffagendadortarefas.infrastructure.client;

import com.benevenuto.bffagendadortarefas.business.dto.in.TarefasRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.benevenuto.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasResponseDTO gravarTarefas(@RequestBody TarefasRequestDTO dto,
                                     @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasResponseDTO> buscaListaDeTarefasPorPerido(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token
    );

    @GetMapping
    List<TarefasResponseDTO> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasResponseDTO alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                               @RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasResponseDTO updateTarefas(@RequestBody TarefasRequestDTO dto, @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);
}
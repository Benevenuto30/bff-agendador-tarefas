package com.benevenuto.bffagendadortarefas.controller;

import com.benevenuto.bffagendadortarefas.business.TarefasService;
import com.benevenuto.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.benevenuto.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.benevenuto.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.benevenuto.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Agendador-tarefas", description = "Agendar tarefas, buscar, deletar e alterar status")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Gravar tarefas", description = "Endpoint para gravar tarefas no sistema")
    @ApiResponse(responseCode = "200", description = "Tarefa gravada com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar eventos", description = "Endpoint para buscar eventos por período")
    @ApiResponse(responseCode = "200", description = "Eventos buscados com sucesso")
    @ApiResponse(responseCode = "404", description = "Eventos não encontrados")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPerido(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar tarefas por email", description = "Endpoint para buscar tarefas associadas ao email do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas buscadas com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {

        List<TarefasDTOResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefa", description = "Endpoint para deletar uma tarefa pelo ID")
    @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Atualizar status da tarefa", description = "Endpoint para atualizar o status de uma tarefa")
    @ApiResponse(responseCode = "204", description = "Status da tarefa atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar tarefa", description = "Endpoint para atualizar os detalhes de uma tarefa existente")
    @ApiResponse(responseCode = "204", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id,
                                                           @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}

package com.benevenuto.bffagendadortarefas.controller;

import com.benevenuto.bffagendadortarefas.business.EmailService;
import com.benevenuto.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.benevenuto.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/email")
@Tag(name = "Notificação de tarefas", description = "Envio de notificações por email")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @Operation(summary = "Enviar email", description = "Envia um email de notificação de tarefa para o usuário")
    @ApiResponse(responseCode = "200", description = "Email enviado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasResponseDTO dto){
        emailService.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }

}

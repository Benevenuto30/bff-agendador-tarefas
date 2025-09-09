package com.benevenuto.bffagendadortarefas.business;

import com.benevenuto.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.benevenuto.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String emailUsuario;

    @Value("${usuario.senha}")
    private String senhaUsuario;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        log.info("Iniciado a busca de tarefas para notificação");
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefasResponseDTO> listaTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Lista de tarefas encontradas: " + listaTarefas);

        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Usuário notificado: " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });
    }

    public String login(LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);

    }

    public LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(emailUsuario)
                .senha(senhaUsuario)
                .build();
    }
}

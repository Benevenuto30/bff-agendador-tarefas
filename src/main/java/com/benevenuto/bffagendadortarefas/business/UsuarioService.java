package com.benevenuto.bffagendadortarefas.business;

import com.benevenuto.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.benevenuto.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.benevenuto.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.benevenuto.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public String loginUsuario(UsuarioDTOResponse usuarioDTO) {
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse salvaUsuario(UsuarioDTOResponse usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTOResponse dto) {
        return client.atualizaDadosUsuario(dto,token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTOResponse enderecoDTO, String token) {
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);

    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTOResponse telefoneDTO, String token) {
        return client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTOResponse dto) {
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTOResponse dto) {
        return client.cadastraTelefone(dto, token);
    }
}

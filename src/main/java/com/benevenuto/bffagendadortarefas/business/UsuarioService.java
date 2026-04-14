package com.benevenuto.bffagendadortarefas.business;

import com.benevenuto.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import com.benevenuto.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public String loginUsuario(LoginRequestDTO loginDTORequest) {
        return client.login(loginDTORequest);
    }

    public UsuarioResponseDTO salvaUsuario(UsuarioRequestDTO usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioResponseDTO atualizaDadosUsuario(String token, UsuarioRequestDTO dto) {
        return client.atualizaDadosUsuario(dto,token);
    }

    public EnderecoResponseDTO atualizaEndereco(Long idEndereco, EnderecoRequestDTO enderecoDTO, String token) {
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);

    }

    public TelefoneResponseDTO atualizaTelefone(Long idTelefone, TelefoneRequestDTO telefoneDTO, String token) {
        return client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoResponseDTO cadastraEndereco(String token, EnderecoRequestDTO dto) {
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneResponseDTO cadastraTelefone(String token, TelefoneRequestDTO dto) {
        return client.cadastraTelefone(dto, token);
    }
}

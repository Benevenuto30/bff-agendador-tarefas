package com.benevenuto.bffagendadortarefas.infrastructure.client;

import com.benevenuto.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioResponseDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO loginDTORequest);

    @PostMapping
    UsuarioResponseDTO salvaUsuario(@RequestBody UsuarioRequestDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioResponseDTO atualizaDadosUsuario(@RequestBody UsuarioRequestDTO dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoResponseDTO atualizaEndereco(@RequestBody EnderecoRequestDTO dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneResponseDTO atualizaTelefone(@RequestBody TelefoneRequestDTO dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoResponseDTO cadastraEndereco(@RequestBody EnderecoRequestDTO dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneResponseDTO cadastraTelefone(@RequestBody TelefoneRequestDTO dto,
                                         @RequestHeader("Authorization") String token);
}

package com.benevenuto.bffagendadortarefas.controller;

import com.benevenuto.bffagendadortarefas.business.UsuarioService;
import com.benevenuto.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.benevenuto.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import com.benevenuto.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioResponseDTO> salvaUsuario(@RequestBody UsuarioRequestDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuários", description = "Login de usuários cadastrados no sistema")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public String login(@RequestBody LoginRequestDTO loginDTORequest) {
        return usuarioService.loginUsuario(loginDTORequest);
    }

    @GetMapping
    @Operation(summary = "Buscar usuários", description = "Busca usuários cadastrados no sistema por email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioResponseDTO> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuário", description = "Deleta usuários cadastrados no sistema por email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza usuário", description = "Atualiza dados do usuário cadastrado no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioResponseDTO> atualizaDadosUsuario(@RequestBody UsuarioRequestDTO dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço do usuário", description = "Atualiza o endereço de um usuário cadastrado no sistema")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário ou endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoResponseDTO> atualizaEndereco(@RequestBody EnderecoRequestDTO dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza endereço do usuário", description = "Atualiza o telefone de um usuário cadastrado no sistema")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário ou telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneResponseDTO> atualizaTelefone(@RequestBody TelefoneRequestDTO dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastra ebdereço do usuário", description = "Cadastra o endereço de um usuário cadastrado no sistema")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoResponseDTO> cadastraEndereco(@RequestBody EnderecoRequestDTO dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastra telefone do usuário", description = "Cadastra o telefone de um usuário cadastrado no sistema")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneResponseDTO> cadastraTelefone(@RequestBody TelefoneRequestDTO dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,dto));
    }
}

package com.benevenuto.bffagendadortarefas.business.dto.in;

import com.benevenuto.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoRequestDTO> enderecos;
    private List<TelefoneResponseDTO> telefones;
}

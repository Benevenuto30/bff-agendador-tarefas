package com.benevenuto.bffagendadortarefas.business.dto.in;

import com.benevenuto.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResquest> enderecos;
    private List<TelefoneDTOResponse> telefones;
}
